/*******************************************************************************
 * Copyright (c) 2016-2017, Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Balazs Grill, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.runtime

import com.google.common.base.Optional
import java.util.Comparator
import java.util.Set
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.diffmerge.api.IComparison
import org.eclipse.emf.diffmerge.api.Role
import org.eclipse.emf.diffmerge.api.diff.IDifference
import org.eclipse.emf.diffmerge.api.diff.IPresenceDifference
import org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence
import org.eclipse.emf.diffmerge.diffdata.EElementPresence
import org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence
import org.eclipse.emf.diffmerge.patch.api.ChangeDirection
import org.eclipse.emf.diffmerge.patch.api.Identifiable
import org.eclipse.emf.diffmerge.patch.api.ModelPatch
import org.eclipse.emf.diffmerge.patch.api.ModelPatchBuilder
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntry
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntryBuilder
import org.eclipse.emf.diffmerge.patch.api.ModelPatchException
import org.eclipse.emf.diffmerge.patch.runtime.identifier.EMFIdentifierProvider
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtend.lib.annotations.Accessors

class ModelPatchRecorder {
  @Accessors
  extension EMFIdentifierProvider identifierProvider = new EMFIdentifierProvider

  private ModelPatchBuilder attributeRemoveBuilder
  private ModelPatchBuilder referenceRemoveBuilder
  private ModelPatchBuilder containmentRemoveBuilder
  private ModelPatchBuilder secondContainmentRemoveBuilder
  private ModelPatchBuilder elementRemoveBuilder

  private ModelPatchBuilder elementAddBuilder
  private ModelPatchBuilder containmentAddBuilder
  private ModelPatchBuilder referenceAddBuilder
  private ModelPatchBuilder attributeAddBuilder

  private ModelPatchBuilder modelPatchBuilder

  private Set<ModelPatchEntry> addedEntries = newHashSet();

  def ModelPatch generateModelPatch(IComparison comparison) {
    val differences = (<IDifference>newArrayList => [
      it += comparison.getDifferences(Role.REFERENCE)
      it += comparison.getDifferences(Role.TARGET)
    ])
    return differences.generateModelPatch
  }

  def ModelPatch generateModelPatch(Iterable<? extends IDifference> diffs) {
    val differences = <IDifference>newArrayList(diffs).sortInplace(DiffComparator.INSTANCE)
    initModelPatchBuilders()
    differences.forEach[
      processDifference
    ]

    val modelPatch = modelPatchBuilder.appendNewEntries(attributeRemoveBuilder)
                    .appendNewEntries(referenceRemoveBuilder)
                    .appendNewEntries(containmentRemoveBuilder)
                    .appendNewEntries(secondContainmentRemoveBuilder)
                    .appendNewEntries(elementRemoveBuilder)
                    .appendNewEntries(elementAddBuilder)
                    .appendNewEntries(containmentAddBuilder)
                    .appendNewEntries(referenceAddBuilder)
                    .appendNewEntries(attributeAddBuilder)
                    .build

    return modelPatch
  }

  protected def initModelPatchBuilders() {
    attributeRemoveBuilder = ModelPatchBuilder.create
    referenceRemoveBuilder = ModelPatchBuilder.create
    containmentRemoveBuilder = ModelPatchBuilder.create
    secondContainmentRemoveBuilder = ModelPatchBuilder.create
    elementRemoveBuilder = ModelPatchBuilder.create
    elementAddBuilder = ModelPatchBuilder.create
    containmentAddBuilder = ModelPatchBuilder.create
    referenceAddBuilder = ModelPatchBuilder.create
    attributeAddBuilder = ModelPatchBuilder.create
    modelPatchBuilder = ModelPatchBuilder.create
  }

  private def void processDifference(IDifference diff) {
    if(diff instanceof IPresenceDifference) {
      val mergeDestination = Role.REFERENCE
      // which model contains the presence
      val presenceRole = diff.presenceRole
      val direction = presenceRole.changeDirection

      processDifference(diff, mergeDestination, presenceRole, direction)
    }
  }

  private dispatch def void processDifference(IDifference diff, Role mergeDestination, Role presenceRole, ChangeDirection direction) {
    throw new ModelPatchException('''Unsupported IDifference descendant: «diff.class.name»''')
  }

  private dispatch def void processDifference(EAttributeValuePresence diff, Role mergeDestination, Role presenceRole, ChangeDirection direction) {
    val owner = diff.elementMatch.get(mergeDestination)
    val feature = diff.feature
    val value = diff.value
    val eAttributeType = (feature as EAttribute).getEAttributeType()

    val ownerId = new Identifiable(owner.id)
    val featureId = new Identifiable(feature.identify)
    val stringValue = EcoreUtil.convertToString(eAttributeType, value);

    val presenceOwner = diff.elementMatch.get(presenceRole)
    val index = presenceOwner.indexOf(value, feature)

    val entryBuilder = ModelPatchBuilder.entryBuilder(ownerId, direction) => [
      it.feature = featureId
      it.value = stringValue
      it.index = index
    ]
    val attributeEntry = entryBuilder.buildAttributeEntry

    if(direction == ChangeDirection.ADD) {
      if(!feature.isMany) {
        val oldValue = owner.eGet(feature)
        if(oldValue !== null){
          // create REMOVE if it already has value
          val oldStringValue = EcoreUtil.convertToString(eAttributeType, oldValue);
          val removeEntry = (entryBuilder => [
            it.direction = ChangeDirection.REMOVE
            it.value = oldStringValue
          ]).buildAttributeEntry
          attributeRemoveBuilder.addNewEntry(removeEntry)
        }
      }
      attributeAddBuilder.addNewEntry(attributeEntry)
    } else {
      attributeRemoveBuilder.addNewEntry(attributeEntry)
    }
  }

  private def Optional<Integer> indexOf(EObject presenceOwner, Object value, EStructuralFeature feature) {
    if(feature.isMany) {
      val values = presenceOwner.eGet(feature) as EList
      val index = values.indexOf(value)
      if(index < 0) {
        throw new IllegalStateException("The object is not found in the containing list.")
      } else {
        return Optional.of(index)
      }
    }
    return Optional.absent
  }

  private dispatch def void processDifference(EReferenceValuePresence diff, Role mergeDestination, Role presenceRole, ChangeDirection direction) {
    val owner = diff.elementMatch.get(mergeDestination)
    val feature = diff.feature as EReference
    val value = if(diff.valueMatch !== null){
        diff.valueMatch.get(presenceRole)
    } else {
        diff.value
    }

    val ownerId = new Identifiable(owner.id)
    val featureId = new Identifiable(feature.identify)
    val targetId = new Identifiable(getId(value))

    val presenceOwner = diff.elementMatch.get(presenceRole)
    val index = presenceOwner.indexOf(value, feature)

    val entryBuilder = ModelPatchBuilder.entryBuilder(ownerId, direction) => [
        it.feature = featureId
        it.target = targetId
        it.index = index
    ]
    val referenceEntry = entryBuilder.buildReferenceEntry

    if(direction == ChangeDirection.ADD) {
      if(!feature.isMany) {
        val oldValue = owner.eGet(feature)
        if(oldValue !== null) {
          if(oldValue instanceof EObject){
            val oldValueId = new Identifiable(oldValue.id)
            // create REMOVE if it already has value
            val removeEntry = (entryBuilder => [
              it.direction = ChangeDirection.REMOVE
              it.target = oldValueId
            ]).buildReferenceEntry
            if(feature.isContainment) {
              secondContainmentRemoveBuilder.addNewEntry(removeEntry)
            } else {
              referenceRemoveBuilder.addNewEntry(removeEntry)
            }
          }
        }
      }
      if(feature.isContainment) {
        containmentAddBuilder.addNewEntry(referenceEntry)
      } else {
        referenceAddBuilder.addNewEntry(referenceEntry)
      }
    } else {
      if(feature.isContainment) {
        secondContainmentRemoveBuilder.addNewEntry(referenceEntry)
      } else {
        referenceRemoveBuilder.addNewEntry(referenceEntry)
      }
    }
  }

  private dispatch def void processDifference(EElementPresence diff, Role mergeDestination, Role presenceRole, ChangeDirection direction) {
    val element = diff.element
    var parent = diff.ownerMatch.get(mergeDestination)
    val ownershipDifference = diff.elementMatch.getOwnershipDifference(presenceRole)
    val containmentHasPresence = ownershipDifference !== null
    var EReference containmentFeature = null
    if(ownershipDifference !== null){
      containmentFeature = ownershipDifference.feature
    } else {
      // in some cases, there is no ownershipDifference
      // this happens when only one role is allowed and
      // the current container feature can be used
      containmentFeature = element.eContainingFeature as EReference
      parent = element.eContainer
    }

    val elementId = new Identifiable(element.id)
    val typeId = new Identifiable(element.eClass.identify)
    val parentId = new Identifiable(parent.id)
    val containmentFeatureId = new Identifiable(containmentFeature.identify)

    val index = element.eContainer.indexOf(element, containmentFeature)

    if(direction == ChangeDirection.ADD){
      val elementEntry = (ModelPatchBuilder.entryBuilder(elementId, direction) => [it.type = typeId]).buildElementEntry
      elementAddBuilder.addNewEntry(elementEntry)

      val entryBuilder = ModelPatchBuilder.entryBuilder(parentId, direction) => [
        it.feature = containmentFeatureId
        it.target = elementId
        it.index = index
      ]
      val referenceEntry = entryBuilder.buildReferenceEntry
      if(!containmentFeature.isMany){
        val oldValue = parent.eGet(containmentFeature)
        if(oldValue !== null && oldValue != element) {
          if(oldValue instanceof EObject){
            val oldValueId = new Identifiable(oldValue.id)
            // create REMOVE if it already has value
            val removeEntry = (entryBuilder => [
              it.direction = ChangeDirection.REMOVE
              it.target = oldValueId
            ]).buildReferenceEntry
            containmentRemoveBuilder.addNewEntry(removeEntry)
          }
        }
      }
      if(!containmentHasPresence) {
        containmentAddBuilder.addNewEntry(referenceEntry)
      }
      entryBuilder => [
        it.direction = ChangeDirection.ADD
        it.context = elementId
        it.index = Optional.absent
      ]
      // We need to save the attributes (create add entries)
      element.saveAllAttribute(attributeAddBuilder, entryBuilder)

      // We need to save the non-containment outward references (create add entries)
      element.saveAllNonContainmentRef(referenceAddBuilder, entryBuilder)

    } else {
      val elementAsContextEntryBuilder = ModelPatchBuilder.entryBuilder(elementId, direction)
      // We need to save the attributes (create remove entries)
      element.saveAllAttribute(attributeRemoveBuilder, elementAsContextEntryBuilder)

      // We need to save the non-containment outward references (create remove entries)
      element.saveAllNonContainmentRef(referenceRemoveBuilder, elementAsContextEntryBuilder)

      if(!containmentHasPresence) {
        // We need to create a new entry to remove the containment
        val removeableContainmentEntry = (ModelPatchBuilder.entryBuilder(parentId, direction) => [
          it.feature = containmentFeatureId
          it.target = elementId
          it.index = index
        ]).buildReferenceEntry
        containmentRemoveBuilder.addNewEntry(removeableContainmentEntry)
      }
      val elementRemove = (elementAsContextEntryBuilder => [
        it.type = typeId
      ]).buildElementEntry
      elementRemoveBuilder.addNewEntry(elementRemove)
    }
  }

  private def void saveAllAttribute(EObject element, ModelPatchBuilder patchBuilder, ModelPatchEntryBuilder entryBuilder) {
    for(eAttr : element.eClass.EAllAttributes.filter[!it.isID]) {
      if(element.eIsSet(eAttr)) {
        val value = element.eGet(eAttr)
        if(value instanceof EList) {
          for(v : value) {
            val index = element.indexOf(v, eAttr)
            val addedAttributeEntry = (entryBuilder => [
              it.feature = new Identifiable(eAttr.identify)
              it.value = EcoreUtil.convertToString(eAttr.getEAttributeType(), v);
              it.index = index
            ]).buildAttributeEntry
            patchBuilder.addNewEntry(addedAttributeEntry)
          }
        } else {
          val index = element.indexOf(value, eAttr)
          val addedAttributeEntry = (entryBuilder => [
            it.feature = new Identifiable(eAttr.identify)
            it.value = EcoreUtil.convertToString(eAttr.getEAttributeType(), value);
            it.index = index
          ]).buildAttributeEntry
          patchBuilder.addNewEntry(addedAttributeEntry)
        }
      }
    }
  }
  private def void saveAllNonContainmentRef(EObject element, ModelPatchBuilder patchBuilder, ModelPatchEntryBuilder entryBuilder) {
    for(eRef : element.eClass.EAllReferences.filter[!it.isContainment && !it.derived && it.changeable && (it.EOpposite === null || it.EOpposite.isDerived || !it.EOpposite.isContainment)]) {
      val value = element.eGet(eRef)
      if(value !== null) {
        if(value instanceof EObject) {
          val index = element.indexOf(value, eRef)
          val removeableReferenceEntry = (entryBuilder => [
            it.feature = new Identifiable(eRef.identify)
            it.target = new Identifiable(value.id)
            it.index = index
          ]).buildReferenceEntry
          patchBuilder.addNewEntry(removeableReferenceEntry)
        } else if(value instanceof EList) {
          for(v : value) {
            if(v instanceof EObject) {
              val index = element.indexOf(v, eRef)
              val referenceEntry = (entryBuilder => [
                it.feature = new Identifiable(eRef.identify)
                it.target = new Identifiable(v.id)
                it.index = index
              ]).buildReferenceEntry
              patchBuilder.addNewEntry(referenceEntry)
            }
          }
        }
      }
    }
  }

  private def addNewEntry(ModelPatchBuilder builder, ModelPatchEntry entry) {
    if(!addedEntries.contains(entry)){
      addedEntries.add(entry)
      return builder.addEntry(entry)
    }
    return builder
  }

  private def appendNewEntries(ModelPatchBuilder target, ModelPatchBuilder source) {
    target.append(source)
    return target
  }

  private def ChangeDirection getChangeDirection(Role scopeDiff) {
    if(scopeDiff == Role.TARGET){
      return ChangeDirection.ADD
    }
    return ChangeDirection.REMOVE
  }

  private def String getId(EObject eObject) {
    return identifierProvider.identifyEObject(eObject)
  }

  protected static class DiffComparator implements Comparator<IDifference> {
    public static val ADD_ATTRIBUTE_PRIORITY = 7
    public static val ADD_REFERENCE_PRIORITY = 6
    public static val ADD_ELEMENT_PRIORITY = 5
    public static val ADD_CONTAINMENT_PRIORITY = 4
    public static val REMOVE_CONTAINMENT_PRIORITY = 3
    public static val REMOVE_ELEMENT_PRIORITY = 2
    public static val REMOVE_REFERENCE_PRIORITY = 1
    public static val REMOVE_ATTRIBUTE_PRIORITY = 0

    public static def getINSTANCE() {
      return new DiffComparator()
    }

    override compare(IDifference o1, IDifference o2) {
      val result = o1.priority-o2.priority
      if(result != 0) {
        return result
      }
      return o1.compareTo(o2)
    }

    dispatch def int compareTo(IDifference o1, IDifference o2) {
      return 0
    }
    dispatch def int compareTo(EElementPresence o1, EElementPresence o2) {
      if(o1.priority == ADD_ELEMENT_PRIORITY) {
        // If differences are additions
        return o1.element.deepness-o2.element.deepness
      } else {
        // If differences are deletions
        return o2.element.deepness-o1.element.deepness
      }
    }
    dispatch def int compareTo(EReferenceValuePresence o1, EReferenceValuePresence o2) {
      if(o1.priority == ADD_CONTAINMENT_PRIORITY) {
        // If differences are deletions
        return o1.valueMatch.target.deepness-o2.valueMatch.target.deepness
      } else if(o1.priority == REMOVE_CONTAINMENT_PRIORITY) {
        // If differences are additions
        return o2.valueMatch.reference.deepness-o1.valueMatch.reference.deepness
      }
      return 0
    }

    private def int getDeepness(EObject eObject) {
      if(eObject.eContainer === null) {
        return 0
      }
      return eObject.eContainer.deepness+1
    }

    dispatch def int getPriority(IDifference diff) {
      throw new ModelPatchException("Unsupported difference type")
    }
    dispatch def int getPriority(EAttributeValuePresence diff) {
      if(Role.TARGET == diff.presenceRole) {
        return ADD_ATTRIBUTE_PRIORITY
      }
      return REMOVE_ATTRIBUTE_PRIORITY
    }
    dispatch def int getPriority(EElementPresence diff) {
      if(Role.TARGET == diff.presenceRole) {
        return ADD_ELEMENT_PRIORITY
      }
      return REMOVE_ELEMENT_PRIORITY
    }
    dispatch def int getPriority(EReferenceValuePresence diff) {
      if(Role.TARGET == diff.presenceRole) {
        if((diff.feature as EReference).isContainment) {
          return ADD_CONTAINMENT_PRIORITY
        }
        return ADD_REFERENCE_PRIORITY
      }
      if((diff.feature as EReference).isContainment) {
        return REMOVE_CONTAINMENT_PRIORITY
      }
      return REMOVE_REFERENCE_PRIORITY
    }
  }
}
