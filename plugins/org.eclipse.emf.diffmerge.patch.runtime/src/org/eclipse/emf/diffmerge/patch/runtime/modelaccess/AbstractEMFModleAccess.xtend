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

import com.google.common.base.Optional
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.diffmerge.patch.api.ModelPatchException
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature

abstract class AbstractEMFModleAccess implements EMFModelAccess {

  protected abstract def EObject doCreate(EClass type)
  protected abstract def void doRemove(EObject element)
  protected abstract def void doAdd(EObject container, EStructuralFeature feature, Object element)
  protected abstract def void doAdd(EObject container, EStructuralFeature feature, Object element, int index)
  protected abstract def void doRemove(EObject container, EStructuralFeature feature, Object element)
  protected abstract def void doRemove(EObject container, EStructuralFeature feature, int index)
  protected abstract def void doChangeIndex(EObject container, EStructuralFeature feature, Object element, int index)

  override create(EClassifier type) {
    if(type instanceof EClass) {
      doCreate(type)
    } else {
      throw new IllegalArgumentException("Only EClasses are supported as element types.")
    }
  }

  override add(EObject container, EStructuralFeature feature, Object element, Optional<Integer> index) {
    if(feature.isMany) {
      val featureValue = container.eGet(feature);
      if(featureValue instanceof EList) {
        if(feature.isUnique && featureValue.contains(element)) {
          throw new ModelPatchException('''Element is already in the unique list''')
        }
        if (index.isPresent && featureValue.size >= index.get) {
          doAdd(container, feature, element, index.get)
        } else {
          doAdd(container, feature, element)
        }
      } else {
        throw new IllegalStateException("Feature value is not EList")
      }
    } else {
      doAdd(container, feature, element)
    }
  }


  override remove(EObject element) {
    doRemove(element)
  }

  override remove(EObject container, EStructuralFeature feature, Object element, Optional<Integer> index) {
    val featureValue = container.eGet(feature)
    if(feature.isMany) {
      if(featureValue instanceof EList) {
        if(!featureValue.contains(element)){
          throw new ModelPatchException('''Cannot remove nonexistent element''')
        }
        if (index.isPresent
          && featureValue.size > index.get
          && featureValue.get(index.get) == element
        ) {
          val int intIndex = index.get
          doRemove(container, feature, intIndex)
        } else {
          doRemove(container, feature, element)
        }
      } else {
        throw new IllegalStateException("Feature value is not EList")
      }
    } else {
      if(featureValue == element) {
        doRemove(container, feature, element)
      } else {
        throw new ModelPatchException('''Cannot remove nonexistent element''')
      }
    }
  }

  override changeIndex(EObject container, EStructuralFeature feature, Object element, Optional<Integer> newIndex) {
    if(feature.isMany && newIndex.isPresent) {
      val featureValue = container.eGet(feature)
      if(featureValue instanceof EList) {
        if(featureValue.size >= newIndex.get) {
          doChangeIndex(container, feature, element, newIndex.get)
        }
      } else {
        throw new IllegalStateException("Feature value is not EList")
      }
    }
  }
}
