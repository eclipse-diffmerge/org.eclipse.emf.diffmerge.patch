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

import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EAttributeEntry;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EChangeDirection;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EElementEntry;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EIdentifiable;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEMFMetadata;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchElement;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchFactory;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EReferenceEntry;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EStructuralFeatureEntry;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EModelPatchPackageImpl extends EPackageImpl implements EModelPatchPackage {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eModelPatchEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eModelPatchMetadataEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eModelPatchEntryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eIdentifiableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eElementEntryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eModelPatchEMFMetadataEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eStructuralFeatureEntryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eReferenceEntryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eAttributeEntryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eModelPatchElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum eChangeDirectionEEnum = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private EModelPatchPackageImpl() {
    super(eNS_URI, EModelPatchFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   *
   * <p>This method is used to initialize {@link EModelPatchPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static EModelPatchPackage init() {
    if (isInited) return (EModelPatchPackage)EPackage.Registry.INSTANCE.getEPackage(EModelPatchPackage.eNS_URI);

    // Obtain or create and register package
    EModelPatchPackageImpl theEModelPatchPackage = (EModelPatchPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EModelPatchPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new EModelPatchPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theEModelPatchPackage.createPackageContents();

    // Initialize created meta-data
    theEModelPatchPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theEModelPatchPackage.freeze();


    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(EModelPatchPackage.eNS_URI, theEModelPatchPackage);
    return theEModelPatchPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEModelPatch() {
    return eModelPatchEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEModelPatch_Entries() {
    return (EReference)eModelPatchEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEModelPatch_Metadata() {
    return (EReference)eModelPatchEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEModelPatch_Elements() {
    return (EReference)eModelPatchEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEModelPatch_Features() {
    return (EReference)eModelPatchEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEModelPatch_Types() {
    return (EReference)eModelPatchEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEModelPatchMetadata() {
    return eModelPatchMetadataEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEModelPatchMetadata_Author() {
    return (EAttribute)eModelPatchMetadataEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEModelPatchMetadata_CreatedAt() {
    return (EAttribute)eModelPatchMetadataEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEModelPatchMetadata_Description() {
    return (EAttribute)eModelPatchMetadataEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEModelPatchMetadata_Input() {
    return (EAttribute)eModelPatchMetadataEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEModelPatchEntry() {
    return eModelPatchEntryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEModelPatchEntry_Direction() {
    return (EAttribute)eModelPatchEntryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEModelPatchEntry_Context() {
    return (EReference)eModelPatchEntryEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEIdentifiable() {
    return eIdentifiableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEIdentifiable_TargetIdentifier() {
    return (EAttribute)eIdentifiableEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEIdentifiable_Description() {
    return (EAttribute)eIdentifiableEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEElementEntry() {
    return eElementEntryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEElementEntry_Type() {
    return (EReference)eElementEntryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEModelPatchEMFMetadata() {
    return eModelPatchEMFMetadataEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEModelPatchEMFMetadata_ModelUris() {
    return (EAttribute)eModelPatchEMFMetadataEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEModelPatchEMFMetadata_UsedNamespaceUris() {
    return (EAttribute)eModelPatchEMFMetadataEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEStructuralFeatureEntry() {
    return eStructuralFeatureEntryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEStructuralFeatureEntry_Feature() {
    return (EReference)eStructuralFeatureEntryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEReferenceEntry() {
    return eReferenceEntryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEReferenceEntry_Target() {
    return (EReference)eReferenceEntryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEAttributeEntry() {
    return eAttributeEntryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEAttributeEntry_Value() {
    return (EAttribute)eAttributeEntryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEModelPatchElement() {
    return eModelPatchElementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEModelPatchElement_ElementIdentifier() {
    return (EAttribute)eModelPatchElementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getEChangeDirection() {
    return eChangeDirectionEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EModelPatchFactory getEModelPatchFactory() {
    return (EModelPatchFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents() {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    eModelPatchEClass = createEClass(EMODEL_PATCH);
    createEReference(eModelPatchEClass, EMODEL_PATCH__ENTRIES);
    createEReference(eModelPatchEClass, EMODEL_PATCH__METADATA);
    createEReference(eModelPatchEClass, EMODEL_PATCH__ELEMENTS);
    createEReference(eModelPatchEClass, EMODEL_PATCH__FEATURES);
    createEReference(eModelPatchEClass, EMODEL_PATCH__TYPES);

    eModelPatchMetadataEClass = createEClass(EMODEL_PATCH_METADATA);
    createEAttribute(eModelPatchMetadataEClass, EMODEL_PATCH_METADATA__AUTHOR);
    createEAttribute(eModelPatchMetadataEClass, EMODEL_PATCH_METADATA__CREATED_AT);
    createEAttribute(eModelPatchMetadataEClass, EMODEL_PATCH_METADATA__DESCRIPTION);
    createEAttribute(eModelPatchMetadataEClass, EMODEL_PATCH_METADATA__INPUT);

    eModelPatchEntryEClass = createEClass(EMODEL_PATCH_ENTRY);
    createEAttribute(eModelPatchEntryEClass, EMODEL_PATCH_ENTRY__DIRECTION);
    createEReference(eModelPatchEntryEClass, EMODEL_PATCH_ENTRY__CONTEXT);

    eIdentifiableEClass = createEClass(EIDENTIFIABLE);
    createEAttribute(eIdentifiableEClass, EIDENTIFIABLE__TARGET_IDENTIFIER);
    createEAttribute(eIdentifiableEClass, EIDENTIFIABLE__DESCRIPTION);

    eElementEntryEClass = createEClass(EELEMENT_ENTRY);
    createEReference(eElementEntryEClass, EELEMENT_ENTRY__TYPE);

    eModelPatchEMFMetadataEClass = createEClass(EMODEL_PATCH_EMF_METADATA);
    createEAttribute(eModelPatchEMFMetadataEClass, EMODEL_PATCH_EMF_METADATA__MODEL_URIS);
    createEAttribute(eModelPatchEMFMetadataEClass, EMODEL_PATCH_EMF_METADATA__USED_NAMESPACE_URIS);

    eStructuralFeatureEntryEClass = createEClass(ESTRUCTURAL_FEATURE_ENTRY);
    createEReference(eStructuralFeatureEntryEClass, ESTRUCTURAL_FEATURE_ENTRY__FEATURE);

    eReferenceEntryEClass = createEClass(EREFERENCE_ENTRY);
    createEReference(eReferenceEntryEClass, EREFERENCE_ENTRY__TARGET);

    eAttributeEntryEClass = createEClass(EATTRIBUTE_ENTRY);
    createEAttribute(eAttributeEntryEClass, EATTRIBUTE_ENTRY__VALUE);

    eModelPatchElementEClass = createEClass(EMODEL_PATCH_ELEMENT);
    createEAttribute(eModelPatchElementEClass, EMODEL_PATCH_ELEMENT__ELEMENT_IDENTIFIER);

    // Create enums
    eChangeDirectionEEnum = createEEnum(ECHANGE_DIRECTION);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents() {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    eModelPatchEClass.getESuperTypes().add(this.getEModelPatchElement());
    eModelPatchMetadataEClass.getESuperTypes().add(this.getEModelPatchElement());
    eModelPatchEntryEClass.getESuperTypes().add(this.getEModelPatchElement());
    eIdentifiableEClass.getESuperTypes().add(this.getEModelPatchElement());
    eElementEntryEClass.getESuperTypes().add(this.getEModelPatchEntry());
    eModelPatchEMFMetadataEClass.getESuperTypes().add(this.getEModelPatchMetadata());
    eStructuralFeatureEntryEClass.getESuperTypes().add(this.getEModelPatchEntry());
    eReferenceEntryEClass.getESuperTypes().add(this.getEStructuralFeatureEntry());
    eAttributeEntryEClass.getESuperTypes().add(this.getEStructuralFeatureEntry());

    // Initialize classes, features, and operations; add parameters
    initEClass(eModelPatchEClass, EModelPatch.class, "EModelPatch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEModelPatch_Entries(), this.getEModelPatchEntry(), null, "entries", null, 0, -1, EModelPatch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEModelPatch_Metadata(), this.getEModelPatchMetadata(), null, "metadata", null, 0, 1, EModelPatch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEModelPatch_Elements(), this.getEIdentifiable(), null, "elements", null, 0, -1, EModelPatch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEModelPatch_Features(), this.getEIdentifiable(), null, "features", null, 0, -1, EModelPatch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEModelPatch_Types(), this.getEIdentifiable(), null, "types", null, 0, -1, EModelPatch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eModelPatchMetadataEClass, EModelPatchMetadata.class, "EModelPatchMetadata", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEModelPatchMetadata_Author(), ecorePackage.getEString(), "author", null, 0, 1, EModelPatchMetadata.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEModelPatchMetadata_CreatedAt(), ecorePackage.getEString(), "createdAt", null, 0, 1, EModelPatchMetadata.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEModelPatchMetadata_Description(), ecorePackage.getEString(), "description", null, 0, 1, EModelPatchMetadata.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEModelPatchMetadata_Input(), ecorePackage.getEString(), "input", null, 0, 1, EModelPatchMetadata.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eModelPatchEntryEClass, EModelPatchEntry.class, "EModelPatchEntry", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEModelPatchEntry_Direction(), this.getEChangeDirection(), "direction", null, 0, 1, EModelPatchEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEModelPatchEntry_Context(), this.getEIdentifiable(), null, "context", null, 0, 1, EModelPatchEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eIdentifiableEClass, EIdentifiable.class, "EIdentifiable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEIdentifiable_TargetIdentifier(), ecorePackage.getEString(), "targetIdentifier", null, 0, 1, EIdentifiable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEIdentifiable_Description(), ecorePackage.getEString(), "description", null, 0, 1, EIdentifiable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eElementEntryEClass, EElementEntry.class, "EElementEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEElementEntry_Type(), this.getEIdentifiable(), null, "type", null, 0, 1, EElementEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eModelPatchEMFMetadataEClass, EModelPatchEMFMetadata.class, "EModelPatchEMFMetadata", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEModelPatchEMFMetadata_ModelUris(), ecorePackage.getEString(), "modelUris", null, 0, -1, EModelPatchEMFMetadata.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEModelPatchEMFMetadata_UsedNamespaceUris(), ecorePackage.getEString(), "usedNamespaceUris", null, 0, -1, EModelPatchEMFMetadata.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eStructuralFeatureEntryEClass, EStructuralFeatureEntry.class, "EStructuralFeatureEntry", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEStructuralFeatureEntry_Feature(), this.getEIdentifiable(), null, "feature", null, 0, 1, EStructuralFeatureEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eReferenceEntryEClass, EReferenceEntry.class, "EReferenceEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEReferenceEntry_Target(), this.getEIdentifiable(), null, "target", null, 0, 1, EReferenceEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eAttributeEntryEClass, EAttributeEntry.class, "EAttributeEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEAttributeEntry_Value(), ecorePackage.getEString(), "value", null, 0, 1, EAttributeEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eModelPatchElementEClass, EModelPatchElement.class, "EModelPatchElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEModelPatchElement_ElementIdentifier(), ecorePackage.getEString(), "elementIdentifier", null, 0, 1, EModelPatchElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(eChangeDirectionEEnum, EChangeDirection.class, "EChangeDirection");
    addEEnumLiteral(eChangeDirectionEEnum, EChangeDirection.ADD);
    addEEnumLiteral(eChangeDirectionEEnum, EChangeDirection.REMOVE);

    // Create resource
    createResource(eNS_URI);
  }

} //EModelPatchPackageImpl
