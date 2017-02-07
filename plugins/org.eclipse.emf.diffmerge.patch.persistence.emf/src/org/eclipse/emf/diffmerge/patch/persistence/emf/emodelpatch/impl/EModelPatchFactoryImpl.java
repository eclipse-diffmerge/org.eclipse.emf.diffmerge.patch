/**
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Abel Hegedus (IncQuery Labs Ltd.) - initial API and implementation
 */
package org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl;

import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EModelPatchFactoryImpl extends EFactoryImpl implements EModelPatchFactory {
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static EModelPatchFactory init() {
    try {
      EModelPatchFactory theEModelPatchFactory = (EModelPatchFactory)EPackage.Registry.INSTANCE.getEFactory(EModelPatchPackage.eNS_URI);
      if (theEModelPatchFactory != null) {
        return theEModelPatchFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new EModelPatchFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EModelPatchFactoryImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
      case EModelPatchPackage.EMODEL_PATCH: return createEModelPatch();
      case EModelPatchPackage.EMODEL_PATCH_METADATA: return createEModelPatchMetadata();
      case EModelPatchPackage.EIDENTIFIABLE: return createEIdentifiable();
      case EModelPatchPackage.EELEMENT_ENTRY: return createEElementEntry();
      case EModelPatchPackage.EMODEL_PATCH_EMF_METADATA: return createEModelPatchEMFMetadata();
      case EModelPatchPackage.EREFERENCE_ENTRY: return createEReferenceEntry();
      case EModelPatchPackage.EATTRIBUTE_ENTRY: return createEAttributeEntry();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue) {
    switch (eDataType.getClassifierID()) {
      case EModelPatchPackage.ECHANGE_DIRECTION:
        return createEChangeDirectionFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue) {
    switch (eDataType.getClassifierID()) {
      case EModelPatchPackage.ECHANGE_DIRECTION:
        return convertEChangeDirectionToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EModelPatch createEModelPatch() {
    EModelPatchImpl eModelPatch = new EModelPatchImpl();
    return eModelPatch;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EModelPatchMetadata createEModelPatchMetadata() {
    EModelPatchMetadataImpl eModelPatchMetadata = new EModelPatchMetadataImpl();
    return eModelPatchMetadata;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EIdentifiable createEIdentifiable() {
    EIdentifiableImpl eIdentifiable = new EIdentifiableImpl();
    return eIdentifiable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EElementEntry createEElementEntry() {
    EElementEntryImpl eElementEntry = new EElementEntryImpl();
    return eElementEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EModelPatchEMFMetadata createEModelPatchEMFMetadata() {
    EModelPatchEMFMetadataImpl eModelPatchEMFMetadata = new EModelPatchEMFMetadataImpl();
    return eModelPatchEMFMetadata;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReferenceEntry createEReferenceEntry() {
    EReferenceEntryImpl eReferenceEntry = new EReferenceEntryImpl();
    return eReferenceEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttributeEntry createEAttributeEntry() {
    EAttributeEntryImpl eAttributeEntry = new EAttributeEntryImpl();
    return eAttributeEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EChangeDirection createEChangeDirectionFromString(EDataType eDataType, String initialValue) {
    EChangeDirection result = EChangeDirection.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertEChangeDirectionToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EModelPatchPackage getEModelPatchPackage() {
    return (EModelPatchPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static EModelPatchPackage getPackage() {
    return EModelPatchPackage.eINSTANCE;
  }

} //EModelPatchFactoryImpl
