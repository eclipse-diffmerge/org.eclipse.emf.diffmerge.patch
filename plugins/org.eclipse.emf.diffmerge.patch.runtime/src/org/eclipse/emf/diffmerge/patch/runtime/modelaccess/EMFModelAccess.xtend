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
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature

interface EMFModelAccess {

  /**
   * Creates a new model element with the specified type.
   */
  def EObject create(EClassifier type)

  /**
   * Adds an existing model element to a selected EReference.
   */
  def void add(EObject container, EStructuralFeature feature, Object element, Optional<Integer> index)

  /**
   * Removes an object from the model.
   */
  def void remove(EObject element)

  /**
   * Removes an object from the 'many'-valued feature, or unsets the
   * 'single'-valued feature. If the feature is a containment reference,
   * the element is removed from the model as well.
   */
  def void remove(EObject container, EStructuralFeature feature, Object element, Optional<Integer> index)

  /**
   * Moves an object inside an EStructuralFeature of an EObject to the specified index.
   */
  def void changeIndex(EObject container, EStructuralFeature feature, Object element, Optional<Integer> newIndex)
}
