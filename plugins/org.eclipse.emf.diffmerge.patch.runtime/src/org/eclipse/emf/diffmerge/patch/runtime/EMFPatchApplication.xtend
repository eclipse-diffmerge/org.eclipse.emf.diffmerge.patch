/*******************************************************************************
 * Copyright (c) 2016-2017, Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Balazs Grill, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.runtime

import com.google.common.base.Optional
import com.google.common.base.Preconditions
import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import java.util.List
import java.util.Map
import org.eclipse.emf.diffmerge.patch.AttributeEntry
import org.eclipse.emf.diffmerge.patch.ElementEntry
import org.eclipse.emf.diffmerge.patch.ReferenceEntry
import org.eclipse.emf.diffmerge.patch.api.ChangeDirection
import org.eclipse.emf.diffmerge.patch.api.ModelPatch
import org.eclipse.emf.diffmerge.patch.api.ModelPatchDiagnosticElement
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntry
import org.eclipse.emf.diffmerge.patch.api.PatchApplication
import org.eclipse.emf.diffmerge.patch.api.PatchApplicationDiagnostic
import org.eclipse.emf.diffmerge.patch.runtime.identifier.IdentifiedEMFObjectLocator
import org.eclipse.emf.diffmerge.patch.runtime.modelaccess.EMFModelAccess
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.Data

public class EMFPatchApplication implements PatchApplication {
  @Accessors(PUBLIC_GETTER)
  val EMFModelAccess modelAccess
  @Accessors(PUBLIC_GETTER)
  val ResourceSet input
  @Accessors(PUBLIC_GETTER,PROTECTED_SETTER)
  IdentifiedEMFObjectLocator locator
  val ModelPatch modelPatch
  val PatchApplicationDiagnostic diagnostics
  val Map<String, EObject> storage
  val Multimap<FeatureHolder, EObject> addedOpposites = HashMultimap.create
  val Multimap<FeatureHolder, EObject> removedOpposites = HashMultimap.create

  protected new(ModelPatch modelPatch, EMFModelAccess modelAccess, ResourceSet input) {
    Preconditions.checkNotNull(modelPatch, "Model patch cannot be null")
    Preconditions.checkNotNull(modelAccess, "Model access cannot be null")
    Preconditions.checkNotNull(input, "Input cannot be null")

    this.modelPatch = modelPatch
    this.modelAccess = modelAccess
    this.input = input
    this.diagnostics = new PatchApplicationDiagnostic
    this.storage = newHashMap
    this.locator = new IdentifiedEMFObjectLocator()
  }

  protected def void apply() {
    val List<ModelPatchEntry> entries = modelPatch.entries
    for (ModelPatchEntry entry : entries) {
      try {
        val contextId = entry.context.identifier

        switch (entry.entryType) {
          case ELEMENT:  applyElementEntry(contextId, entry)
          case REFERENCE: applyReferenceEntry(contextId, entry)
          case ATTRIBUTE: applyAttributeEntry(contextId, entry)
        }
      } catch (Exception ex) {
        val diagnosticElement = new ModelPatchDiagnosticElement()
        diagnosticElement.setCaughtException(ex)
        diagnosticElement.setProblematicEntry(entry)
        diagnosticElement.setMessage(ex.message)
        diagnostics.addElement(diagnosticElement)
      }
    }
  }

  def private void applyElementEntry(String elementId, ModelPatchEntry entry) {
    val elementEntry = (entry as ElementEntry)
    val typeId = elementEntry.type.identifier
    val locatedType = locator.locateEClassifier(typeId)
    if (entry.direction == ChangeDirection.ADD) {
      // create object and put into storage
      if(!locatedType.present){
        throw new IllegalStateException('''Could not find EClassifier''')
      }
      val type = locatedType.get
      val existingId = findEObjectById(elementId)
      if(existingId.present){
        throw new IllegalStateException('''Cannot create element with existing id''')
      }
      val createdElement = createElement(elementId, type)
      storage.put(elementId, createdElement)
    } else {
      // locate element and remove from model
      val element = findEObjectById(elementId)
      if (element.isPresent()) {
        modelAccess.remove(element.get())
      }
      // if the element is not in the model, delete it from the storage
      val eObject = storage.remove(elementId)
      if (eObject == null) {
        throw new IllegalStateException('''Could not find element in storage''')
      }
    }
  }

  def private void applyReferenceEntry(String contextId, ModelPatchEntry entry) {
    // Find the requested element
    val locatedContext = findEObjectById(contextId)
    val referenceEntry = (entry as ReferenceEntry)
    // Find the reference
    val featureId = referenceEntry.feature.identifier
    val locatedEStructuralFeature = locator.locateEStructuralFeature(featureId)
    // owner = the element with the reference
    if(!locatedContext.present){
            throw new IllegalStateException('''Could not find owner object''')
        }
    val owner = locatedContext.get
    // feature = the reference to modify
    if(!locatedEStructuralFeature.present){
      throw new IllegalStateException('''Could not find feature''')
    }
    val feature = locatedEStructuralFeature.get
    // The feature should be a reference for a ReferenceEntry
    if (!(feature instanceof EReference)) {
      throw new IllegalStateException('''Feature is not a reference''')
    }
    val reference = (feature as EReference)
    val targetId = referenceEntry.target.identifier
    var target = storage.get(targetId)
    if (target == null) {
      val locatedTargetObject = findEObjectById(targetId)
      target = locatedTargetObject.orNull
    }
    if (target == null) {
      throw new IllegalStateException('''Could not find target object''')
    }
    val index = referenceEntry.index
    if (referenceEntry.direction == ChangeDirection.ADD) {
      setOrAdd(owner, reference, target, index)
    } else {
      unsetOrRemove(owner, reference, target, index)
      // put removed element to the storage
      if (reference.isContainment()) {
        storage.put(targetId, target)
      }
    }
  }

  def private void applyAttributeEntry(String elementId, ModelPatchEntry entry) {
    val locatedEObject = findEObjectById(elementId)
    val attributeEntry = (entry as AttributeEntry)
    val featureId = attributeEntry.feature.identifier
    val locatedEStructuralFeature = locator.locateEStructuralFeature(featureId)
    if (!locatedEObject.isPresent()) {
      throw new IllegalStateException('''Could not find owner object''')
    }
    if (!locatedEStructuralFeature.isPresent()) {
      throw new IllegalStateException('''Could not find feature''')
    }
    val owner = locatedEObject.get()
    val feature = locatedEStructuralFeature.get()
    var Object value = null
    if (feature instanceof EAttribute) {
      val attributeType = ((feature as EAttribute)).getEAttributeType()
      value = EcoreUtil.createFromString(attributeType, attributeEntry.value)
    } else {
      throw new IllegalStateException('''Feature is not an attribute''')
    }
    val index = attributeEntry.index
    if (entry.direction == ChangeDirection.ADD) {
      setOrAdd(owner, feature, value, index)
    } else {
      unsetOrRemove(owner, feature, value, index)
    }
  }

  def private EObject createElement(String elementId, EClassifier type) {
    val eObject = modelAccess.create(type)
    EcoreUtil.setID(eObject, elementId)
    return eObject
  }

  def private void setOrAdd(EObject owner, EStructuralFeature feature, Object value, Optional<Integer> index) {
    val holder = new FeatureHolder(owner, feature)
    if (addedOpposites.containsKey(holder)
      && addedOpposites.get(holder).contains(value)
    ) {
      modelAccess.changeIndex(owner, feature, value, index)
    } else {
      modelAccess.add(owner, feature, value, index)

      if (feature instanceof EReference) {
        val opposite = feature.EOpposite
        if (opposite != null) {
          val oppositeHolder = new FeatureHolder(value as EObject, opposite)
          addedOpposites.put(oppositeHolder, owner)
        }
      }
    }
  }

  def private void unsetOrRemove(EObject owner, EStructuralFeature feature, Object value, Optional<Integer> index) {
    val holder = new FeatureHolder(owner, feature)
    if (removedOpposites.containsKey(holder)
      && removedOpposites.get(holder).contains(value)
    ) {
      return
    } else {
      modelAccess.remove(owner, feature, value, index)
      if (feature instanceof EReference) {
        val opposite = feature.EOpposite
        if (opposite != null) {
          val oppositeHolder = new FeatureHolder(value as EObject, opposite)
          removedOpposites.put(oppositeHolder, owner)
        }
      }
    }
  }

  def private Optional<EObject> findEObjectById(String identifier) {
    Preconditions.checkNotNull(identifier, "Identifier cannot be null")
    var eObject = storage.get(identifier)
    if (eObject == null) {
      val locatedEObject = locator.locateEObject(identifier)
      if (locatedEObject.isPresent()) {
        return locatedEObject
      }
    }
    return Optional.fromNullable(eObject)
  }

  override getDiagnostics() {
    return diagnostics
  }

  override getModelPatch() {
    return modelPatch
  }

}

@Data
class FeatureHolder {
  EObject eObject
  EStructuralFeature feature
}
