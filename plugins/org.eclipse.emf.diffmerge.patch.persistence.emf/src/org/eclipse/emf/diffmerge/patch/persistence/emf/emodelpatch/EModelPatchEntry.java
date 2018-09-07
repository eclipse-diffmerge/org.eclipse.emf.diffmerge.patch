/**
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Abel Hegedus (IncQuery Labs Ltd.) - initial API and implementation
 */
package org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry#getContext <em>Context</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage#getEModelPatchEntry()
 * @model abstract="true"
 * @generated
 */
public interface EModelPatchEntry extends EModelPatchElement {
  /**
   * Returns the value of the '<em><b>Direction</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EChangeDirection}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Direction</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Direction</em>' attribute.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EChangeDirection
   * @see #setDirection(EChangeDirection)
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage#getEModelPatchEntry_Direction()
   * @model
   * @generated
   */
  EChangeDirection getDirection();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry#getDirection <em>Direction</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Direction</em>' attribute.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EChangeDirection
   * @see #getDirection()
   * @generated
   */
  void setDirection(EChangeDirection value);

  /**
   * Returns the value of the '<em><b>Context</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Context</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Context</em>' reference.
   * @see #setContext(EIdentifiable)
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage#getEModelPatchEntry_Context()
   * @model
   * @generated
   */
  EIdentifiable getContext();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry#getContext <em>Context</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Context</em>' reference.
   * @see #getContext()
   * @generated
   */
  void setContext(EIdentifiable value);

} // EModelPatchEntry
