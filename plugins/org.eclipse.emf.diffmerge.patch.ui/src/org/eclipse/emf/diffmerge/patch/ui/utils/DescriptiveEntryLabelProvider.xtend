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
package org.eclipse.emf.diffmerge.patch.ui.utils

import org.eclipse.emf.diffmerge.patch.AttributeEntry
import org.eclipse.emf.diffmerge.patch.ElementEntry
import org.eclipse.emf.diffmerge.patch.ReferenceEntry
import org.eclipse.emf.diffmerge.patch.api.ChangeDirection
import org.eclipse.emf.diffmerge.patch.api.Identifiable
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntry
import org.eclipse.emf.diffmerge.patch.StructuralFeatureEntry

class DescriptiveEntryLabelProvider extends DefaultEntryLabelProvider {

  override getLabel(ModelPatchEntry entry) {
    return entry.shortPrettyPrint
  }

  private dispatch def String shortPrettyPrint(ModelPatchEntry entry) '''«entry.direction» «entry.entryType» «IF entry.direction==ChangeDirection.ADD»TO«ELSE»FROM«ENDIF» «entry.context.identifier»'''
  private dispatch def String shortPrettyPrint(ElementEntry entry) '''«entry.direction.decodeDirectionLabel(true).toFirstUpper» '«entry.context.identifier»' (type: '«entry.type.typeString»')'''
  private dispatch def String shortPrettyPrint(AttributeEntry entry) {
    if(entry.index==null || !entry.index.present) {
      return '''«entry.direction.decodeDirectionLabel(false).toFirstUpper» '«entry.feature.featureString»' attribute of '«entry.context.identifier»' «IF entry.direction==ChangeDirection.ADD»to '«entry.value»'«ELSE»(old value: «entry.value»)«ENDIF»'''
    } else {
      return '''«entry.direction.decodeDirectionLabel(true).toFirstUpper» '«entry.value»' value «entry.direction.toFrom» the '«entry.feature.featureString»' «entry.listEnding»'''
    }
  }
  private dispatch def String shortPrettyPrint(ReferenceEntry entry) {
    if(entry.index==null || !entry.index.present) {
      return '''«entry.direction.decodeDirectionLabel(false).toFirstUpper» '«entry.feature.featureString»' reference of '«entry.context.identifier»'«IF entry.direction==ChangeDirection.ADD» to '«entry.target.identifier»'«ELSE» (old target: '«entry.target.identifier»')«ENDIF»'''
    } else {
      return '''«entry.direction.decodeDirectionLabel(true).toFirstUpper» reference to '«entry.target.identifier»' «entry.direction.toFrom» the '«entry.feature.featureString»' «entry.listEnding»'''
    }
  }

  def String getListEnding(StructuralFeatureEntry entry) '''list of '«entry.context.identifier»' (index: «entry.index.get»)'''

  private def String decodeDirectionLabel(ChangeDirection direction, boolean isAddRemove) {
    if(isAddRemove) {
      if(direction==ChangeDirection.ADD) {
        return "add"
      } else {
        return "remove"
      }
    } else {
      if(direction==ChangeDirection.ADD) {
        return "set"
      } else {
        return "unset"
      }
    }
  }

  private def String toFrom(ChangeDirection direction) {
    if(direction==ChangeDirection.ADD) {
      return "to"
    } else {
      return "from"
    }
  }

  private def getTypeString(Identifiable identifiable) {
    return identifiable.identifier//.split('#').last
  }

  private def String getFeatureString(Identifiable identifiable) {
    return identifiable.identifier//.split("#").last.split(Pattern.quote(".")).last
  }
}
