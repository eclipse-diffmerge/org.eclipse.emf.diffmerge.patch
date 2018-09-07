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
 * A representation of the model object '<em><b>EReference Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EReferenceEntry#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage#getEReferenceEntry()
 * @model
 * @generated
 */
public interface EReferenceEntry extends EStructuralFeatureEntry {
  /**
   * Returns the value of the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' reference.
   * @see #setTarget(EIdentifiable)
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage#getEReferenceEntry_Target()
   * @model
   * @generated
   */
  EIdentifiable getTarget();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EReferenceEntry#getTarget <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' reference.
   * @see #getTarget()
   * @generated
   */
  void setTarget(EIdentifiable value);

} // EReferenceEntry
