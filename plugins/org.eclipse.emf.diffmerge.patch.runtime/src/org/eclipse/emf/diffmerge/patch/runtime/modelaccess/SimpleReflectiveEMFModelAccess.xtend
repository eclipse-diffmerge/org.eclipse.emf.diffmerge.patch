/*******************************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
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
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.util.EcoreUtil

class SimpleReflectiveEMFModelAccess extends AbstractEMFModleAccess {
  override protected doCreate(EClass type) {
    val eObject = type.EPackage.EFactoryInstance.create(type)
    return eObject
  }

  override protected doAdd(EObject container, EStructuralFeature feature, Object element) {
    if(feature.isMany) {
      val featureValueList = container.eGet(feature) as EList
      featureValueList.add(element)
    } else {
      container.eSet(feature, element)
    }
  }

  override protected doAdd(EObject container, EStructuralFeature feature, Object element, int index) {
    val featureValue = container.eGet(feature) as EList
    featureValue.add(index, element)
  }

  override protected doChangeIndex(EObject container, EStructuralFeature feature, Object element, int index) {
    val featureValue = container.eGet(feature) as EList
    featureValue.move(index, element)
  }

  override protected doRemove(EObject element) {
    EcoreUtil.remove(element)
  }

  override protected doRemove(EObject container, EStructuralFeature feature, Object element) {
    if(feature.isMany) {
      val featureValueList = container.eGet(feature) as EList
      featureValueList.remove(element)
    } else {
      container.eUnset(feature)
    }
  }

  override protected doRemove(EObject container, EStructuralFeature feature, int index) {
    val featureValueList = container.eGet(feature) as EList
    featureValueList.remove(index)
  }
}
