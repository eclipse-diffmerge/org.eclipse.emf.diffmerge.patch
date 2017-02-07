/*******************************************************************************
 * Copyright (c) 2017 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Abel Hegedus (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
 package org.eclipse.emf.diffmerge.patch.persistence.emf.transform

import java.util.List
import org.apache.log4j.Logger
import org.eclipse.emf.diffmerge.patch.AbstractModelPatchEntry
import org.eclipse.emf.diffmerge.patch.AttributeEntry
import org.eclipse.emf.diffmerge.patch.ElementEntry
import org.eclipse.emf.diffmerge.patch.ReferenceEntry
import org.eclipse.emf.diffmerge.patch.StructuralFeatureEntry
import org.eclipse.emf.diffmerge.patch.api.ChangeDirection
import org.eclipse.emf.diffmerge.patch.api.Identifiable
import org.eclipse.emf.diffmerge.patch.api.ModelPatch
import org.eclipse.emf.diffmerge.patch.api.ModelPatchMetadata
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EChangeDirection
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EIdentifiable
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchFactory
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EStructuralFeatureEntry
import org.eclipse.emf.diffmerge.patch.runtime.EMFModelPatchMetadata
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.viatra.query.runtime.base.api.BaseIndexOptions
import org.eclipse.viatra.query.runtime.base.api.IndexingLevel
import org.eclipse.viatra.query.runtime.base.api.NavigationHelper
import org.eclipse.viatra.query.runtime.base.api.ViatraBaseFactory

class ModelPatchToEMF {


  extension val EModelPatchFactory factory = EModelPatchFactory.eINSTANCE
  val targetIdentiferFeature = EModelPatchPackage::eINSTANCE.EIdentifiable_TargetIdentifier
  val elementsFeature = EModelPatchPackage::eINSTANCE.EModelPatch_Elements
  val typesFeature = EModelPatchPackage::eINSTANCE.EModelPatch_Types
  val featuresFeature = EModelPatchPackage::eINSTANCE.EModelPatch_Features

  val NavigationHelper indexer
  val ModelPatch source
  val EModelPatch target
  val Logger logger
  /**
   * When true, the same targetIdentifier can appear in the elements, types and features lists.
   *
   * Note that duplicates in the same list are not allowed even if set to true.
   */
  val boolean allowDuplicateIdentifiables;

  new(ModelPatch source, EModelPatch target, Logger logger, boolean allowDuplicateIdentifiables) {
    this.source = source
    this.target = target
    this.allowDuplicateIdentifiables = allowDuplicateIdentifiables

    this.logger = logger
    indexer = ViatraBaseFactory.instance.createNavigationHelper(target, new BaseIndexOptions(false, IndexingLevel.NONE), logger)
    indexer.registerEStructuralFeatures(#{targetIdentiferFeature}, IndexingLevel.FULL)

  }

  def transform(){
    source.metadata.transformElement

    source.entries.filter(AbstractModelPatchEntry).forEach[
      it.transformElement
    ]

    indexer.dispose
  }

  protected def void transformElement(ModelPatchMetadata metadata) {
    val emetadata = if (metadata instanceof EMFModelPatchMetadata) {
        createEModelPatchEMFMetadata => [
          modelUris += metadata.modelUriList
          usedNamespaceUris += metadata.usedNamespaceUris
        ]
      } else {
        createEModelPatchMetadata
      }
    emetadata => [
      author = metadata.author
      createdAt = metadata.createdAt
      description = metadata.description
      input = metadata.input
    ]
    target.metadata = emetadata
    return
  }

  protected def void transformElement(AbstractModelPatchEntry entry) {
    val eentry = entry.transformEntry
    target.entries.add(eentry)
    return
  }

  protected def dispatch EModelPatchEntry transformEntry(ElementEntry entry) {
    createEElementEntry => [
      entry.transformAbstractEntryFeatures(it)
      type = entry.type.ensureEIdentifiable(typesFeature)
    ]
  }

  protected def dispatch EModelPatchEntry transformEntry(AttributeEntry entry) {
    createEAttributeEntry => [
      entry.transformStructuralFeatureEntryFeatures(it)
      value = entry.value
    ]
  }

  protected def dispatch EModelPatchEntry transformEntry(ReferenceEntry entry) {
    createEReferenceEntry => [
      entry.transformStructuralFeatureEntryFeatures(it)
      target = entry.target.ensureEIdentifiable(elementsFeature)
    ]
  }

  protected def transformAbstractEntryFeatures(AbstractModelPatchEntry entry, EModelPatchEntry eentry) {
      eentry.direction = entry.direction.transformDirection
      eentry.context = entry.context.ensureEIdentifiable(elementsFeature)
  }

  protected def transformStructuralFeatureEntryFeatures(StructuralFeatureEntry entry, EStructuralFeatureEntry eentry) {
      entry.transformAbstractEntryFeatures(eentry)
      eentry.feature = entry.feature.ensureEIdentifiable(featuresFeature)
  }

  protected def transformDirection(ChangeDirection direction) {
    return switch direction {
      case ADD: EChangeDirection.ADD
      case REMOVE: EChangeDirection.REMOVE
    }
  }

  protected def ensureEIdentifiable(Identifiable identifiable, EStructuralFeature identifiableContainmentFeature) {
    val identifier = identifiable.identifier
    val allEIdentifiables = indexer.findByAttributeValue(identifier, targetIdentiferFeature).filter(EIdentifiable)

    // no identifiable found, we have to create it
    if (allEIdentifiables.empty) {
      val eidentifable = identifiable.prepareEIdentifiable(identifiableContainmentFeature)
      return eidentifable
    }

    // otherwise an identifiable with the given targetIdentifier already exists
    val noOfIdentifiables = allEIdentifiables.size
    val onlyGivenFeature = allEIdentifiables.filter[eContainmentFeature === identifiableContainmentFeature]
    val noOfIdentifiablesInGivenFeature = onlyGivenFeature.size
    if(noOfIdentifiablesInGivenFeature == 1 && noOfIdentifiables == 1){
      // this is the only one and it is in the correct list
      return onlyGivenFeature.head
    }

    if (allowDuplicateIdentifiables && onlyGivenFeature.empty) {
      // although there is already another identifiable, it is in a different list
      val eidentifable = identifiable.prepareEIdentifiable(identifiableContainmentFeature)
      return eidentifable
    }

    // if we get here, there is a duplicate
    throw new IllegalStateException(String.format("Duplicate identifiers found: %s", identifier))
  }

  protected def EIdentifiable prepareEIdentifiable(Identifiable identifiable, EStructuralFeature identifiableContainmentFeature) {
    val eidentifable = createEIdentifiable => [
      targetIdentifier = identifiable.identifier
      description = identifiable.description
    ]
    val list = target.eGet(identifiableContainmentFeature) as List<EIdentifiable>
    list.add(eidentifable)
    eidentifable
  }

}
