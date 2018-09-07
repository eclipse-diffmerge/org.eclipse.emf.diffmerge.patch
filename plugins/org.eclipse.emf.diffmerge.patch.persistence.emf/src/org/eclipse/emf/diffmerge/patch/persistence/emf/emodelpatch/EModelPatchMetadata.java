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
 * A representation of the model object '<em><b>Metadata</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata#getCreatedAt <em>Created At</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata#getInput <em>Input</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage#getEModelPatchMetadata()
 * @model
 * @generated
 */
public interface EModelPatchMetadata extends EModelPatchElement {
  /**
   * Returns the value of the '<em><b>Author</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Author</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Author</em>' attribute.
   * @see #setAuthor(String)
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage#getEModelPatchMetadata_Author()
   * @model
   * @generated
   */
  String getAuthor();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata#getAuthor <em>Author</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Author</em>' attribute.
   * @see #getAuthor()
   * @generated
   */
  void setAuthor(String value);

  /**
   * Returns the value of the '<em><b>Created At</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Created At</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Created At</em>' attribute.
   * @see #setCreatedAt(String)
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage#getEModelPatchMetadata_CreatedAt()
   * @model
   * @generated
   */
  String getCreatedAt();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata#getCreatedAt <em>Created At</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Created At</em>' attribute.
   * @see #getCreatedAt()
   * @generated
   */
  void setCreatedAt(String value);

  /**
   * Returns the value of the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Description</em>' attribute.
   * @see #setDescription(String)
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage#getEModelPatchMetadata_Description()
   * @model
   * @generated
   */
  String getDescription();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata#getDescription <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' attribute.
   * @see #getDescription()
   * @generated
   */
  void setDescription(String value);

  /**
   * Returns the value of the '<em><b>Input</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Input</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Input</em>' attribute.
   * @see #setInput(String)
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage#getEModelPatchMetadata_Input()
   * @model
   * @generated
   */
  String getInput();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata#getInput <em>Input</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Input</em>' attribute.
   * @see #getInput()
   * @generated
   */
  void setInput(String value);

} // EModelPatchMetadata
