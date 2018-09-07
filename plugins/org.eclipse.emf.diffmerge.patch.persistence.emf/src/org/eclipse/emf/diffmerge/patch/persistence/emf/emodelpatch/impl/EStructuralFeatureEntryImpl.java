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
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EStructuralFeatureEntry;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EStructural Feature Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EStructuralFeatureEntryImpl#getFeature <em>Feature</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EStructuralFeatureEntryImpl extends EModelPatchEntryImpl implements EStructuralFeatureEntry {
  /**
   * The cached value of the '{@link #getFeature() <em>Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeature()
   * @generated
   * @ordered
   */
  protected EIdentifiable feature;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EStructuralFeatureEntryImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return EModelPatchPackage.Literals.ESTRUCTURAL_FEATURE_ENTRY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EIdentifiable getFeature() {
    if (feature != null && feature.eIsProxy()) {
      InternalEObject oldFeature = (InternalEObject)feature;
      feature = (EIdentifiable)eResolveProxy(oldFeature);
      if (feature != oldFeature) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EModelPatchPackage.ESTRUCTURAL_FEATURE_ENTRY__FEATURE, oldFeature, feature));
      }
    }
    return feature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EIdentifiable basicGetFeature() {
    return feature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFeature(EIdentifiable newFeature) {
    EIdentifiable oldFeature = feature;
    feature = newFeature;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EModelPatchPackage.ESTRUCTURAL_FEATURE_ENTRY__FEATURE, oldFeature, feature));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case EModelPatchPackage.ESTRUCTURAL_FEATURE_ENTRY__FEATURE:
        if (resolve) return getFeature();
        return basicGetFeature();
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
      case EModelPatchPackage.ESTRUCTURAL_FEATURE_ENTRY__FEATURE:
        setFeature((EIdentifiable)newValue);
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
      case EModelPatchPackage.ESTRUCTURAL_FEATURE_ENTRY__FEATURE:
        setFeature((EIdentifiable)null);
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
      case EModelPatchPackage.ESTRUCTURAL_FEATURE_ENTRY__FEATURE:
        return feature != null;
    }
    return super.eIsSet(featureID);
  }

} //EStructuralFeatureEntryImpl
