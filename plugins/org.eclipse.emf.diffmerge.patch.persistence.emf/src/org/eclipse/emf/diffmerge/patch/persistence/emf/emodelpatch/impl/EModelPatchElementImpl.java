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
package org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchElement;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchElementImpl#getElementIdentifier <em>Element Identifier</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EModelPatchElementImpl extends MinimalEObjectImpl.Container implements EModelPatchElement {
  /**
   * The default value of the '{@link #getElementIdentifier() <em>Element Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElementIdentifier()
   * @generated
   * @ordered
   */
  protected static final String ELEMENT_IDENTIFIER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getElementIdentifier() <em>Element Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElementIdentifier()
   * @generated
   * @ordered
   */
  protected String elementIdentifier = ELEMENT_IDENTIFIER_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected EModelPatchElementImpl() {
    super();
    setElementIdentifier(EcoreUtil.generateUUID());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return EModelPatchPackage.Literals.EMODEL_PATCH_ELEMENT;
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getElementIdentifier() {
    return elementIdentifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElementIdentifier(String newElementIdentifier) {
    String oldElementIdentifier = elementIdentifier;
    elementIdentifier = newElementIdentifier;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EModelPatchPackage.EMODEL_PATCH_ELEMENT__ELEMENT_IDENTIFIER, oldElementIdentifier, elementIdentifier));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case EModelPatchPackage.EMODEL_PATCH_ELEMENT__ELEMENT_IDENTIFIER:
        return getElementIdentifier();
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
      case EModelPatchPackage.EMODEL_PATCH_ELEMENT__ELEMENT_IDENTIFIER:
        setElementIdentifier((String)newValue);
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
      case EModelPatchPackage.EMODEL_PATCH_ELEMENT__ELEMENT_IDENTIFIER:
        setElementIdentifier(ELEMENT_IDENTIFIER_EDEFAULT);
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
      case EModelPatchPackage.EMODEL_PATCH_ELEMENT__ELEMENT_IDENTIFIER:
        return ELEMENT_IDENTIFIER_EDEFAULT == null ? elementIdentifier != null : !ELEMENT_IDENTIFIER_EDEFAULT.equals(elementIdentifier);
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
    result.append(" (elementIdentifier: ");
    result.append(elementIdentifier);
    result.append(')');
    return result.toString();
  }

} //EModelPatchElementImpl
