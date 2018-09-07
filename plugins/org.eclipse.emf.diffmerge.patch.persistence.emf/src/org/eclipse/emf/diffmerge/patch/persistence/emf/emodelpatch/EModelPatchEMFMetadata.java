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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EMF Metadata</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEMFMetadata#getModelUris <em>Model Uris</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEMFMetadata#getUsedNamespaceUris <em>Used Namespace Uris</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage#getEModelPatchEMFMetadata()
 * @model
 * @generated
 */
public interface EModelPatchEMFMetadata extends EModelPatchMetadata {
  /**
   * Returns the value of the '<em><b>Model Uris</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Model Uris</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Uris</em>' attribute list.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage#getEModelPatchEMFMetadata_ModelUris()
   * @model
   * @generated
   */
  EList<String> getModelUris();

  /**
   * Returns the value of the '<em><b>Used Namespace Uris</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Used Namespace Uris</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Used Namespace Uris</em>' attribute list.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage#getEModelPatchEMFMetadata_UsedNamespaceUris()
   * @model
   * @generated
   */
  EList<String> getUsedNamespaceUris();

} // EModelPatchEMFMetadata
