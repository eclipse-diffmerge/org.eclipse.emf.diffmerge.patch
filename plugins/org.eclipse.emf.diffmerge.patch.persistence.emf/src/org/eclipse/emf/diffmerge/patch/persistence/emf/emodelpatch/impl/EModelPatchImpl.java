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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EIdentifiable;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EModel Patch</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchImpl#getEntries <em>Entries</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchImpl#getMetadata <em>Metadata</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchImpl#getFeatures <em>Features</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchImpl#getTypes <em>Types</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EModelPatchImpl extends EModelPatchElementImpl implements EModelPatch {
  /**
   * The cached value of the '{@link #getEntries() <em>Entries</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntries()
   * @generated
   * @ordered
   */
  protected EList<EModelPatchEntry> entries;

  /**
   * The cached value of the '{@link #getMetadata() <em>Metadata</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMetadata()
   * @generated
   * @ordered
   */
  protected EModelPatchMetadata metadata;

  /**
   * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElements()
   * @generated
   * @ordered
   */
  protected EList<EIdentifiable> elements;

  /**
   * The cached value of the '{@link #getFeatures() <em>Features</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatures()
   * @generated
   * @ordered
   */
  protected EList<EIdentifiable> features;

  /**
   * The cached value of the '{@link #getTypes() <em>Types</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypes()
   * @generated
   * @ordered
   */
  protected EList<EIdentifiable> types;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EModelPatchImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return EModelPatchPackage.Literals.EMODEL_PATCH;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EModelPatchEntry> getEntries() {
    if (entries == null) {
      entries = new EObjectContainmentEList<EModelPatchEntry>(EModelPatchEntry.class, this, EModelPatchPackage.EMODEL_PATCH__ENTRIES);
    }
    return entries;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EModelPatchMetadata getMetadata() {
    return metadata;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMetadata(EModelPatchMetadata newMetadata, NotificationChain msgs) {
    EModelPatchMetadata oldMetadata = metadata;
    metadata = newMetadata;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EModelPatchPackage.EMODEL_PATCH__METADATA, oldMetadata, newMetadata);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMetadata(EModelPatchMetadata newMetadata) {
    if (newMetadata != metadata) {
      NotificationChain msgs = null;
      if (metadata != null)
        msgs = ((InternalEObject)metadata).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EModelPatchPackage.EMODEL_PATCH__METADATA, null, msgs);
      if (newMetadata != null)
        msgs = ((InternalEObject)newMetadata).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EModelPatchPackage.EMODEL_PATCH__METADATA, null, msgs);
      msgs = basicSetMetadata(newMetadata, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EModelPatchPackage.EMODEL_PATCH__METADATA, newMetadata, newMetadata));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EIdentifiable> getElements() {
    if (elements == null) {
      elements = new EObjectContainmentEList<EIdentifiable>(EIdentifiable.class, this, EModelPatchPackage.EMODEL_PATCH__ELEMENTS);
    }
    return elements;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EIdentifiable> getFeatures() {
    if (features == null) {
      features = new EObjectContainmentEList<EIdentifiable>(EIdentifiable.class, this, EModelPatchPackage.EMODEL_PATCH__FEATURES);
    }
    return features;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EIdentifiable> getTypes() {
    if (types == null) {
      types = new EObjectContainmentEList<EIdentifiable>(EIdentifiable.class, this, EModelPatchPackage.EMODEL_PATCH__TYPES);
    }
    return types;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case EModelPatchPackage.EMODEL_PATCH__ENTRIES:
        return ((InternalEList<?>)getEntries()).basicRemove(otherEnd, msgs);
      case EModelPatchPackage.EMODEL_PATCH__METADATA:
        return basicSetMetadata(null, msgs);
      case EModelPatchPackage.EMODEL_PATCH__ELEMENTS:
        return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
      case EModelPatchPackage.EMODEL_PATCH__FEATURES:
        return ((InternalEList<?>)getFeatures()).basicRemove(otherEnd, msgs);
      case EModelPatchPackage.EMODEL_PATCH__TYPES:
        return ((InternalEList<?>)getTypes()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case EModelPatchPackage.EMODEL_PATCH__ENTRIES:
        return getEntries();
      case EModelPatchPackage.EMODEL_PATCH__METADATA:
        return getMetadata();
      case EModelPatchPackage.EMODEL_PATCH__ELEMENTS:
        return getElements();
      case EModelPatchPackage.EMODEL_PATCH__FEATURES:
        return getFeatures();
      case EModelPatchPackage.EMODEL_PATCH__TYPES:
        return getTypes();
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
      case EModelPatchPackage.EMODEL_PATCH__ENTRIES:
        getEntries().clear();
        getEntries().addAll((Collection<? extends EModelPatchEntry>)newValue);
        return;
      case EModelPatchPackage.EMODEL_PATCH__METADATA:
        setMetadata((EModelPatchMetadata)newValue);
        return;
      case EModelPatchPackage.EMODEL_PATCH__ELEMENTS:
        getElements().clear();
        getElements().addAll((Collection<? extends EIdentifiable>)newValue);
        return;
      case EModelPatchPackage.EMODEL_PATCH__FEATURES:
        getFeatures().clear();
        getFeatures().addAll((Collection<? extends EIdentifiable>)newValue);
        return;
      case EModelPatchPackage.EMODEL_PATCH__TYPES:
        getTypes().clear();
        getTypes().addAll((Collection<? extends EIdentifiable>)newValue);
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
      case EModelPatchPackage.EMODEL_PATCH__ENTRIES:
        getEntries().clear();
        return;
      case EModelPatchPackage.EMODEL_PATCH__METADATA:
        setMetadata((EModelPatchMetadata)null);
        return;
      case EModelPatchPackage.EMODEL_PATCH__ELEMENTS:
        getElements().clear();
        return;
      case EModelPatchPackage.EMODEL_PATCH__FEATURES:
        getFeatures().clear();
        return;
      case EModelPatchPackage.EMODEL_PATCH__TYPES:
        getTypes().clear();
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
      case EModelPatchPackage.EMODEL_PATCH__ENTRIES:
        return entries != null && !entries.isEmpty();
      case EModelPatchPackage.EMODEL_PATCH__METADATA:
        return metadata != null;
      case EModelPatchPackage.EMODEL_PATCH__ELEMENTS:
        return elements != null && !elements.isEmpty();
      case EModelPatchPackage.EMODEL_PATCH__FEATURES:
        return features != null && !features.isEmpty();
      case EModelPatchPackage.EMODEL_PATCH__TYPES:
        return types != null && !types.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //EModelPatchImpl
