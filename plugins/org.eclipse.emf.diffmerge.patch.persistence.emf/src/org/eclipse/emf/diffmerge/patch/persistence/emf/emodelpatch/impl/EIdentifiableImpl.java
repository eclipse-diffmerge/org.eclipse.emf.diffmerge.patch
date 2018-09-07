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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EIdentifiable;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EIdentifiable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EIdentifiableImpl#getTargetIdentifier <em>Target Identifier</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EIdentifiableImpl#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EIdentifiableImpl extends EModelPatchElementImpl implements EIdentifiable {
  /**
   * The default value of the '{@link #getTargetIdentifier() <em>Target Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetIdentifier()
   * @generated
   * @ordered
   */
  protected static final String TARGET_IDENTIFIER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTargetIdentifier() <em>Target Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetIdentifier()
   * @generated
   * @ordered
   */
  protected String targetIdentifier = TARGET_IDENTIFIER_EDEFAULT;

  /**
   * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
  protected static final String DESCRIPTION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
  protected String description = DESCRIPTION_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EIdentifiableImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return EModelPatchPackage.Literals.EIDENTIFIABLE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTargetIdentifier() {
    return targetIdentifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetIdentifier(String newTargetIdentifier) {
    String oldTargetIdentifier = targetIdentifier;
    targetIdentifier = newTargetIdentifier;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EModelPatchPackage.EIDENTIFIABLE__TARGET_IDENTIFIER, oldTargetIdentifier, targetIdentifier));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDescription() {
    return description;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDescription(String newDescription) {
    String oldDescription = description;
    description = newDescription;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EModelPatchPackage.EIDENTIFIABLE__DESCRIPTION, oldDescription, description));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case EModelPatchPackage.EIDENTIFIABLE__TARGET_IDENTIFIER:
        return getTargetIdentifier();
      case EModelPatchPackage.EIDENTIFIABLE__DESCRIPTION:
        return getDescription();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case EModelPatchPackage.EIDENTIFIABLE__TARGET_IDENTIFIER:
        setTargetIdentifier((String)newValue);
        return;
      case EModelPatchPackage.EIDENTIFIABLE__DESCRIPTION:
        setDescription((String)newValue);
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
      case EModelPatchPackage.EIDENTIFIABLE__TARGET_IDENTIFIER:
        setTargetIdentifier(TARGET_IDENTIFIER_EDEFAULT);
        return;
      case EModelPatchPackage.EIDENTIFIABLE__DESCRIPTION:
        setDescription(DESCRIPTION_EDEFAULT);
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
      case EModelPatchPackage.EIDENTIFIABLE__TARGET_IDENTIFIER:
        return TARGET_IDENTIFIER_EDEFAULT == null ? targetIdentifier != null : !TARGET_IDENTIFIER_EDEFAULT.equals(targetIdentifier);
      case EModelPatchPackage.EIDENTIFIABLE__DESCRIPTION:
        return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
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
    result.append(" (targetIdentifier: ");
    result.append(targetIdentifier);
    result.append(", description: ");
    result.append(description);
    result.append(')');
    return result.toString();
  }

} //EIdentifiableImpl
