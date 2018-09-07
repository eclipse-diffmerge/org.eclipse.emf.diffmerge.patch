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

import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EChangeDirection;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EIdentifiable;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchEntryImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchEntryImpl#getContext <em>Context</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EModelPatchEntryImpl extends EModelPatchElementImpl implements EModelPatchEntry {
  /**
   * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDirection()
   * @generated
   * @ordered
   */
  protected static final EChangeDirection DIRECTION_EDEFAULT = EChangeDirection.ADD;

  /**
   * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDirection()
   * @generated
   * @ordered
   */
  protected EChangeDirection direction = DIRECTION_EDEFAULT;

  /**
   * The cached value of the '{@link #getContext() <em>Context</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContext()
   * @generated
   * @ordered
   */
  protected EIdentifiable context;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EModelPatchEntryImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return EModelPatchPackage.Literals.EMODEL_PATCH_ENTRY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EChangeDirection getDirection() {
    return direction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDirection(EChangeDirection newDirection) {
    EChangeDirection oldDirection = direction;
    direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EModelPatchPackage.EMODEL_PATCH_ENTRY__DIRECTION, oldDirection, direction));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EIdentifiable getContext() {
    if (context != null && context.eIsProxy()) {
      InternalEObject oldContext = (InternalEObject)context;
      context = (EIdentifiable)eResolveProxy(oldContext);
      if (context != oldContext) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EModelPatchPackage.EMODEL_PATCH_ENTRY__CONTEXT, oldContext, context));
      }
    }
    return context;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EIdentifiable basicGetContext() {
    return context;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContext(EIdentifiable newContext) {
    EIdentifiable oldContext = context;
    context = newContext;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EModelPatchPackage.EMODEL_PATCH_ENTRY__CONTEXT, oldContext, context));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case EModelPatchPackage.EMODEL_PATCH_ENTRY__DIRECTION:
        return getDirection();
      case EModelPatchPackage.EMODEL_PATCH_ENTRY__CONTEXT:
        if (resolve) return getContext();
        return basicGetContext();
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
      case EModelPatchPackage.EMODEL_PATCH_ENTRY__DIRECTION:
        setDirection((EChangeDirection)newValue);
        return;
      case EModelPatchPackage.EMODEL_PATCH_ENTRY__CONTEXT:
        setContext((EIdentifiable)newValue);
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
      case EModelPatchPackage.EMODEL_PATCH_ENTRY__DIRECTION:
        setDirection(DIRECTION_EDEFAULT);
        return;
      case EModelPatchPackage.EMODEL_PATCH_ENTRY__CONTEXT:
        setContext((EIdentifiable)null);
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
      case EModelPatchPackage.EMODEL_PATCH_ENTRY__DIRECTION:
        return direction != DIRECTION_EDEFAULT;
      case EModelPatchPackage.EMODEL_PATCH_ENTRY__CONTEXT:
        return context != null;
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
    result.append(" (direction: ");
    result.append(direction);
    result.append(')');
    return result.toString();
  }

} //EModelPatchEntryImpl
