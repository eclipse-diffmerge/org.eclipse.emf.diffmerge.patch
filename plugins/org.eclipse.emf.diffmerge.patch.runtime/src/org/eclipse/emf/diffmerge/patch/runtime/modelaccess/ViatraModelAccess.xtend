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
package org.eclipse.emf.diffmerge.patch.runtime.modelaccess

import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.IModelManipulations

class ViatraModelAccess extends AbstractEMFModleAccess {
  val IModelManipulations viatraModelManipulator
  val Resource temporaryResource

  new(IModelManipulations viatraModelManipulator, Resource temporaryResource) {
    this.viatraModelManipulator = viatraModelManipulator
    this.temporaryResource = temporaryResource
  }

  override protected doCreate(EClass type) {
    viatraModelManipulator.create(temporaryResource, type)
  }

  override protected doRemove(EObject element) {
    viatraModelManipulator.remove(element)
  }

  override protected doAdd(EObject container, EStructuralFeature feature, Object element) {
    if(feature.isMany) {
      if(feature instanceof EReference  && (feature as EReference).isContainment) {
        if(element instanceof EObject) {
          viatraModelManipulator.moveTo(element, container, feature as EReference)
        } else {
          throw new IllegalArgumentException("Only EObjects can be added to EReferences.")
        }
      } else {
        viatraModelManipulator.add(container, feature, element)
      }
    } else {
      viatraModelManipulator.set(container, feature, element)
    }
  }

  override protected doAdd(EObject container, EStructuralFeature feature, Object element, int index) {
    if(feature instanceof EReference  && (feature as EReference).isContainment) {
      if(element instanceof EObject) {
        viatraModelManipulator.moveTo(element as EObject, container, feature as EReference, index)
      } else {
        throw new IllegalArgumentException("Only EObjects can be added to EReferences.")
      }
    } else {
      viatraModelManipulator.add(container, feature, element, index)
    }
  }

  override protected doRemove(EObject container, EStructuralFeature feature, Object element) {
    if(feature.isMany) {
      viatraModelManipulator.remove(container, feature, element)
    } else {
      viatraModelManipulator.set(container, feature, feature.defaultValue)
    }
  }

  override protected doRemove(EObject container, EStructuralFeature feature, int index) {
    viatraModelManipulator.remove(container, feature, index)
  }

  override protected doChangeIndex(EObject container, EStructuralFeature feature, Object element, int index) {
    val oldIndex = (container.eGet(feature) as EList).indexOf(element)
    viatraModelManipulator.changeIndex(container, feature, oldIndex, index)
  }

}
