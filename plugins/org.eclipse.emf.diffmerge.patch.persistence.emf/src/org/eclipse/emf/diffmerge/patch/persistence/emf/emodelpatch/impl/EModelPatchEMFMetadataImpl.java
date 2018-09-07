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
package org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEMFMetadata;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EMF Metadata</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchEMFMetadataImpl#getModelUris <em>Model Uris</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchEMFMetadataImpl#getUsedNamespaceUris <em>Used Namespace Uris</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EModelPatchEMFMetadataImpl extends EModelPatchMetadataImpl implements EModelPatchEMFMetadata {
  /**
   * The cached value of the '{@link #getModelUris() <em>Model Uris</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelUris()
   * @generated
   * @ordered
   */
  protected EList<String> modelUris;

  /**
   * The cached value of the '{@link #getUsedNamespaceUris() <em>Used Namespace Uris</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUsedNamespaceUris()
   * @generated
   * @ordered
   */
  protected EList<String> usedNamespaceUris;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EModelPatchEMFMetadataImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return EModelPatchPackage.Literals.EMODEL_PATCH_EMF_METADATA;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getModelUris() {
    if (modelUris == null) {
      modelUris = new EDataTypeUniqueEList<String>(String.class, this, EModelPatchPackage.EMODEL_PATCH_EMF_METADATA__MODEL_URIS);
    }
    return modelUris;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getUsedNamespaceUris() {
    if (usedNamespaceUris == null) {
      usedNamespaceUris = new EDataTypeUniqueEList<String>(String.class, this, EModelPatchPackage.EMODEL_PATCH_EMF_METADATA__USED_NAMESPACE_URIS);
    }
    return usedNamespaceUris;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case EModelPatchPackage.EMODEL_PATCH_EMF_METADATA__MODEL_URIS:
        return getModelUris();
      case EModelPatchPackage.EMODEL_PATCH_EMF_METADATA__USED_NAMESPACE_URIS:
        return getUsedNamespaceUris();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case EModelPatchPackage.EMODEL_PATCH_EMF_METADATA__MODEL_URIS:
        getModelUris().clear();
        getModelUris().addAll((Collection<? extends String>)newValue);
        return;
      case EModelPatchPackage.EMODEL_PATCH_EMF_METADATA__USED_NAMESPACE_URIS:
        getUsedNamespaceUris().clear();
        getUsedNamespaceUris().addAll((Collection<? extends String>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case EModelPatchPackage.EMODEL_PATCH_EMF_METADATA__MODEL_URIS:
        getModelUris().clear();
        return;
      case EModelPatchPackage.EMODEL_PATCH_EMF_METADATA__USED_NAMESPACE_URIS:
        getUsedNamespaceUris().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case EModelPatchPackage.EMODEL_PATCH_EMF_METADATA__MODEL_URIS:
        return modelUris != null && !modelUris.isEmpty();
      case EModelPatchPackage.EMODEL_PATCH_EMF_METADATA__USED_NAMESPACE_URIS:
        return usedNamespaceUris != null && !usedNamespaceUris.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (modelUris: ");
    result.append(modelUris);
    result.append(", usedNamespaceUris: ");
    result.append(usedNamespaceUris);
    result.append(')');
    return result.toString();
  }

} //EModelPatchEMFMetadataImpl
