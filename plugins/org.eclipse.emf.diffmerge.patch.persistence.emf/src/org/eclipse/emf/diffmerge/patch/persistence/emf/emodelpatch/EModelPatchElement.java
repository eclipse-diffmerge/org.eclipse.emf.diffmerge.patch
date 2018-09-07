/**
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchElement#getElementIdentifier <em>Element Identifier</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage#getEModelPatchElement()
 * @model abstract="true"
 * @generated
 */
public interface EModelPatchElement extends EObject {
  /**
   * Returns the value of the '<em><b>Element Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Element Identifier</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Element Identifier</em>' attribute.
   * @see #setElementIdentifier(String)
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage#getEModelPatchElement_ElementIdentifier()
   * @model id="true"
   * @generated
   */
  String getElementIdentifier();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchElement#getElementIdentifier <em>Element Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Element Identifier</em>' attribute.
   * @see #getElementIdentifier()
   * @generated
   */
  void setElementIdentifier(String value);

} // EModelPatchElement
