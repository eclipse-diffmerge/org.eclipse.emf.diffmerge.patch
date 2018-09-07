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
package org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchFactory
 * @model kind="package"
 * @generated
 */
public interface EModelPatchPackage extends EPackage {
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "emodelpatch";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/emf/diffmerge/1.0.0/patch";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "org.eclipse.emf.diffmerge.patch";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  EModelPatchPackage eINSTANCE = org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchElementImpl <em>Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchElementImpl
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEModelPatchElement()
   * @generated
   */
  int EMODEL_PATCH_ELEMENT = 9;

  /**
   * The feature id for the '<em><b>Element Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_ELEMENT__ELEMENT_IDENTIFIER = 0;

  /**
   * The number of structural features of the '<em>Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_ELEMENT_FEATURE_COUNT = 1;

  /**
   * The number of operations of the '<em>Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_ELEMENT_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchImpl <em>EModel Patch</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchImpl
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEModelPatch()
   * @generated
   */
  int EMODEL_PATCH = 0;

  /**
   * The feature id for the '<em><b>Element Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH__ELEMENT_IDENTIFIER = EMODEL_PATCH_ELEMENT__ELEMENT_IDENTIFIER;

  /**
   * The feature id for the '<em><b>Entries</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH__ENTRIES = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Metadata</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH__METADATA = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH__ELEMENTS = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH__FEATURES = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH__TYPES = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>EModel Patch</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_FEATURE_COUNT = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 5;

  /**
   * The number of operations of the '<em>EModel Patch</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_OPERATION_COUNT = EMODEL_PATCH_ELEMENT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchMetadataImpl <em>Metadata</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchMetadataImpl
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEModelPatchMetadata()
   * @generated
   */
  int EMODEL_PATCH_METADATA = 1;

  /**
   * The feature id for the '<em><b>Element Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_METADATA__ELEMENT_IDENTIFIER = EMODEL_PATCH_ELEMENT__ELEMENT_IDENTIFIER;

  /**
   * The feature id for the '<em><b>Author</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_METADATA__AUTHOR = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Created At</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_METADATA__CREATED_AT = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_METADATA__DESCRIPTION = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Input</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_METADATA__INPUT = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Metadata</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_METADATA_FEATURE_COUNT = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The number of operations of the '<em>Metadata</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_METADATA_OPERATION_COUNT = EMODEL_PATCH_ELEMENT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchEntryImpl <em>Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchEntryImpl
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEModelPatchEntry()
   * @generated
   */
  int EMODEL_PATCH_ENTRY = 2;

  /**
   * The feature id for the '<em><b>Element Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_ENTRY__ELEMENT_IDENTIFIER = EMODEL_PATCH_ELEMENT__ELEMENT_IDENTIFIER;

  /**
   * The feature id for the '<em><b>Direction</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_ENTRY__DIRECTION = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Context</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_ENTRY__CONTEXT = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_ENTRY_FEATURE_COUNT = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of operations of the '<em>Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_ENTRY_OPERATION_COUNT = EMODEL_PATCH_ELEMENT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EIdentifiableImpl <em>EIdentifiable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EIdentifiableImpl
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEIdentifiable()
   * @generated
   */
  int EIDENTIFIABLE = 3;

  /**
   * The feature id for the '<em><b>Element Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EIDENTIFIABLE__ELEMENT_IDENTIFIER = EMODEL_PATCH_ELEMENT__ELEMENT_IDENTIFIER;

  /**
   * The feature id for the '<em><b>Target Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EIDENTIFIABLE__TARGET_IDENTIFIER = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EIDENTIFIABLE__DESCRIPTION = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>EIdentifiable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EIDENTIFIABLE_FEATURE_COUNT = EMODEL_PATCH_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of operations of the '<em>EIdentifiable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EIDENTIFIABLE_OPERATION_COUNT = EMODEL_PATCH_ELEMENT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EElementEntryImpl <em>EElement Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EElementEntryImpl
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEElementEntry()
   * @generated
   */
  int EELEMENT_ENTRY = 4;

  /**
   * The feature id for the '<em><b>Element Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_ENTRY__ELEMENT_IDENTIFIER = EMODEL_PATCH_ENTRY__ELEMENT_IDENTIFIER;

  /**
   * The feature id for the '<em><b>Direction</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_ENTRY__DIRECTION = EMODEL_PATCH_ENTRY__DIRECTION;

  /**
   * The feature id for the '<em><b>Context</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_ENTRY__CONTEXT = EMODEL_PATCH_ENTRY__CONTEXT;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_ENTRY__TYPE = EMODEL_PATCH_ENTRY_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>EElement Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_ENTRY_FEATURE_COUNT = EMODEL_PATCH_ENTRY_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>EElement Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_ENTRY_OPERATION_COUNT = EMODEL_PATCH_ENTRY_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchEMFMetadataImpl <em>EMF Metadata</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchEMFMetadataImpl
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEModelPatchEMFMetadata()
   * @generated
   */
  int EMODEL_PATCH_EMF_METADATA = 5;

  /**
   * The feature id for the '<em><b>Element Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_EMF_METADATA__ELEMENT_IDENTIFIER = EMODEL_PATCH_METADATA__ELEMENT_IDENTIFIER;

  /**
   * The feature id for the '<em><b>Author</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_EMF_METADATA__AUTHOR = EMODEL_PATCH_METADATA__AUTHOR;

  /**
   * The feature id for the '<em><b>Created At</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_EMF_METADATA__CREATED_AT = EMODEL_PATCH_METADATA__CREATED_AT;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_EMF_METADATA__DESCRIPTION = EMODEL_PATCH_METADATA__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Input</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_EMF_METADATA__INPUT = EMODEL_PATCH_METADATA__INPUT;

  /**
   * The feature id for the '<em><b>Model Uris</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_EMF_METADATA__MODEL_URIS = EMODEL_PATCH_METADATA_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Used Namespace Uris</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_EMF_METADATA__USED_NAMESPACE_URIS = EMODEL_PATCH_METADATA_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>EMF Metadata</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_EMF_METADATA_FEATURE_COUNT = EMODEL_PATCH_METADATA_FEATURE_COUNT + 2;

  /**
   * The number of operations of the '<em>EMF Metadata</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMODEL_PATCH_EMF_METADATA_OPERATION_COUNT = EMODEL_PATCH_METADATA_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EStructuralFeatureEntryImpl <em>EStructural Feature Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EStructuralFeatureEntryImpl
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEStructuralFeatureEntry()
   * @generated
   */
  int ESTRUCTURAL_FEATURE_ENTRY = 6;

  /**
   * The feature id for the '<em><b>Element Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTRUCTURAL_FEATURE_ENTRY__ELEMENT_IDENTIFIER = EMODEL_PATCH_ENTRY__ELEMENT_IDENTIFIER;

  /**
   * The feature id for the '<em><b>Direction</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTRUCTURAL_FEATURE_ENTRY__DIRECTION = EMODEL_PATCH_ENTRY__DIRECTION;

  /**
   * The feature id for the '<em><b>Context</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTRUCTURAL_FEATURE_ENTRY__CONTEXT = EMODEL_PATCH_ENTRY__CONTEXT;

  /**
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTRUCTURAL_FEATURE_ENTRY__FEATURE = EMODEL_PATCH_ENTRY_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>EStructural Feature Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTRUCTURAL_FEATURE_ENTRY_FEATURE_COUNT = EMODEL_PATCH_ENTRY_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>EStructural Feature Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTRUCTURAL_FEATURE_ENTRY_OPERATION_COUNT = EMODEL_PATCH_ENTRY_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EReferenceEntryImpl <em>EReference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EReferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEReferenceEntry()
   * @generated
   */
  int EREFERENCE_ENTRY = 7;

  /**
   * The feature id for the '<em><b>Element Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_ENTRY__ELEMENT_IDENTIFIER = ESTRUCTURAL_FEATURE_ENTRY__ELEMENT_IDENTIFIER;

  /**
   * The feature id for the '<em><b>Direction</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_ENTRY__DIRECTION = ESTRUCTURAL_FEATURE_ENTRY__DIRECTION;

  /**
   * The feature id for the '<em><b>Context</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_ENTRY__CONTEXT = ESTRUCTURAL_FEATURE_ENTRY__CONTEXT;

  /**
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_ENTRY__FEATURE = ESTRUCTURAL_FEATURE_ENTRY__FEATURE;

  /**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_ENTRY__TARGET = ESTRUCTURAL_FEATURE_ENTRY_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>EReference Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_ENTRY_FEATURE_COUNT = ESTRUCTURAL_FEATURE_ENTRY_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>EReference Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_ENTRY_OPERATION_COUNT = ESTRUCTURAL_FEATURE_ENTRY_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EAttributeEntryImpl <em>EAttribute Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EAttributeEntryImpl
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEAttributeEntry()
   * @generated
   */
  int EATTRIBUTE_ENTRY = 8;

  /**
   * The feature id for the '<em><b>Element Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_ENTRY__ELEMENT_IDENTIFIER = ESTRUCTURAL_FEATURE_ENTRY__ELEMENT_IDENTIFIER;

  /**
   * The feature id for the '<em><b>Direction</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_ENTRY__DIRECTION = ESTRUCTURAL_FEATURE_ENTRY__DIRECTION;

  /**
   * The feature id for the '<em><b>Context</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_ENTRY__CONTEXT = ESTRUCTURAL_FEATURE_ENTRY__CONTEXT;

  /**
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_ENTRY__FEATURE = ESTRUCTURAL_FEATURE_ENTRY__FEATURE;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_ENTRY__VALUE = ESTRUCTURAL_FEATURE_ENTRY_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>EAttribute Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_ENTRY_FEATURE_COUNT = ESTRUCTURAL_FEATURE_ENTRY_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>EAttribute Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_ENTRY_OPERATION_COUNT = ESTRUCTURAL_FEATURE_ENTRY_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EChangeDirection <em>EChange Direction</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EChangeDirection
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEChangeDirection()
   * @generated
   */
  int ECHANGE_DIRECTION = 10;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch <em>EModel Patch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EModel Patch</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch
   * @generated
   */
  EClass getEModelPatch();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch#getEntries <em>Entries</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entries</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch#getEntries()
   * @see #getEModelPatch()
   * @generated
   */
  EReference getEModelPatch_Entries();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch#getMetadata <em>Metadata</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Metadata</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch#getMetadata()
   * @see #getEModelPatch()
   * @generated
   */
  EReference getEModelPatch_Metadata();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch#getElements()
   * @see #getEModelPatch()
   * @generated
   */
  EReference getEModelPatch_Elements();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Features</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch#getFeatures()
   * @see #getEModelPatch()
   * @generated
   */
  EReference getEModelPatch_Features();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch#getTypes <em>Types</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Types</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch#getTypes()
   * @see #getEModelPatch()
   * @generated
   */
  EReference getEModelPatch_Types();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata <em>Metadata</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Metadata</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata
   * @generated
   */
  EClass getEModelPatchMetadata();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata#getAuthor <em>Author</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Author</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata#getAuthor()
   * @see #getEModelPatchMetadata()
   * @generated
   */
  EAttribute getEModelPatchMetadata_Author();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata#getCreatedAt <em>Created At</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Created At</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata#getCreatedAt()
   * @see #getEModelPatchMetadata()
   * @generated
   */
  EAttribute getEModelPatchMetadata_CreatedAt();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata#getDescription()
   * @see #getEModelPatchMetadata()
   * @generated
   */
  EAttribute getEModelPatchMetadata_Description();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata#getInput <em>Input</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Input</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata#getInput()
   * @see #getEModelPatchMetadata()
   * @generated
   */
  EAttribute getEModelPatchMetadata_Input();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry <em>Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entry</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry
   * @generated
   */
  EClass getEModelPatchEntry();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry#getDirection <em>Direction</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Direction</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry#getDirection()
   * @see #getEModelPatchEntry()
   * @generated
   */
  EAttribute getEModelPatchEntry_Direction();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry#getContext <em>Context</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Context</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry#getContext()
   * @see #getEModelPatchEntry()
   * @generated
   */
  EReference getEModelPatchEntry_Context();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EIdentifiable <em>EIdentifiable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EIdentifiable</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EIdentifiable
   * @generated
   */
  EClass getEIdentifiable();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EIdentifiable#getTargetIdentifier <em>Target Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Identifier</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EIdentifiable#getTargetIdentifier()
   * @see #getEIdentifiable()
   * @generated
   */
  EAttribute getEIdentifiable_TargetIdentifier();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EIdentifiable#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EIdentifiable#getDescription()
   * @see #getEIdentifiable()
   * @generated
   */
  EAttribute getEIdentifiable_Description();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EElementEntry <em>EElement Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EElement Entry</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EElementEntry
   * @generated
   */
  EClass getEElementEntry();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EElementEntry#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EElementEntry#getType()
   * @see #getEElementEntry()
   * @generated
   */
  EReference getEElementEntry_Type();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEMFMetadata <em>EMF Metadata</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EMF Metadata</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEMFMetadata
   * @generated
   */
  EClass getEModelPatchEMFMetadata();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEMFMetadata#getModelUris <em>Model Uris</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Model Uris</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEMFMetadata#getModelUris()
   * @see #getEModelPatchEMFMetadata()
   * @generated
   */
  EAttribute getEModelPatchEMFMetadata_ModelUris();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEMFMetadata#getUsedNamespaceUris <em>Used Namespace Uris</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Used Namespace Uris</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEMFMetadata#getUsedNamespaceUris()
   * @see #getEModelPatchEMFMetadata()
   * @generated
   */
  EAttribute getEModelPatchEMFMetadata_UsedNamespaceUris();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EStructuralFeatureEntry <em>EStructural Feature Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EStructural Feature Entry</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EStructuralFeatureEntry
   * @generated
   */
  EClass getEStructuralFeatureEntry();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EStructuralFeatureEntry#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Feature</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EStructuralFeatureEntry#getFeature()
   * @see #getEStructuralFeatureEntry()
   * @generated
   */
  EReference getEStructuralFeatureEntry_Feature();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EReferenceEntry <em>EReference Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EReference Entry</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EReferenceEntry
   * @generated
   */
  EClass getEReferenceEntry();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EReferenceEntry#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EReferenceEntry#getTarget()
   * @see #getEReferenceEntry()
   * @generated
   */
  EReference getEReferenceEntry_Target();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EAttributeEntry <em>EAttribute Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EAttribute Entry</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EAttributeEntry
   * @generated
   */
  EClass getEAttributeEntry();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EAttributeEntry#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EAttributeEntry#getValue()
   * @see #getEAttributeEntry()
   * @generated
   */
  EAttribute getEAttributeEntry_Value();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchElement <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchElement
   * @generated
   */
  EClass getEModelPatchElement();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchElement#getElementIdentifier <em>Element Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Element Identifier</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchElement#getElementIdentifier()
   * @see #getEModelPatchElement()
   * @generated
   */
  EAttribute getEModelPatchElement_ElementIdentifier();

  /**
   * Returns the meta object for enum '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EChangeDirection <em>EChange Direction</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>EChange Direction</em>'.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EChangeDirection
   * @generated
   */
  EEnum getEChangeDirection();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  EModelPatchFactory getEModelPatchFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each operation of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals {
    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchImpl <em>EModel Patch</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchImpl
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEModelPatch()
     * @generated
     */
    EClass EMODEL_PATCH = eINSTANCE.getEModelPatch();

    /**
     * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMODEL_PATCH__ENTRIES = eINSTANCE.getEModelPatch_Entries();

    /**
     * The meta object literal for the '<em><b>Metadata</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMODEL_PATCH__METADATA = eINSTANCE.getEModelPatch_Metadata();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMODEL_PATCH__ELEMENTS = eINSTANCE.getEModelPatch_Elements();

    /**
     * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMODEL_PATCH__FEATURES = eINSTANCE.getEModelPatch_Features();

    /**
     * The meta object literal for the '<em><b>Types</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMODEL_PATCH__TYPES = eINSTANCE.getEModelPatch_Types();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchMetadataImpl <em>Metadata</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchMetadataImpl
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEModelPatchMetadata()
     * @generated
     */
    EClass EMODEL_PATCH_METADATA = eINSTANCE.getEModelPatchMetadata();

    /**
     * The meta object literal for the '<em><b>Author</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMODEL_PATCH_METADATA__AUTHOR = eINSTANCE.getEModelPatchMetadata_Author();

    /**
     * The meta object literal for the '<em><b>Created At</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMODEL_PATCH_METADATA__CREATED_AT = eINSTANCE.getEModelPatchMetadata_CreatedAt();

    /**
     * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMODEL_PATCH_METADATA__DESCRIPTION = eINSTANCE.getEModelPatchMetadata_Description();

    /**
     * The meta object literal for the '<em><b>Input</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMODEL_PATCH_METADATA__INPUT = eINSTANCE.getEModelPatchMetadata_Input();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchEntryImpl <em>Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchEntryImpl
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEModelPatchEntry()
     * @generated
     */
    EClass EMODEL_PATCH_ENTRY = eINSTANCE.getEModelPatchEntry();

    /**
     * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMODEL_PATCH_ENTRY__DIRECTION = eINSTANCE.getEModelPatchEntry_Direction();

    /**
     * The meta object literal for the '<em><b>Context</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMODEL_PATCH_ENTRY__CONTEXT = eINSTANCE.getEModelPatchEntry_Context();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EIdentifiableImpl <em>EIdentifiable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EIdentifiableImpl
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEIdentifiable()
     * @generated
     */
    EClass EIDENTIFIABLE = eINSTANCE.getEIdentifiable();

    /**
     * The meta object literal for the '<em><b>Target Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EIDENTIFIABLE__TARGET_IDENTIFIER = eINSTANCE.getEIdentifiable_TargetIdentifier();

    /**
     * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EIDENTIFIABLE__DESCRIPTION = eINSTANCE.getEIdentifiable_Description();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EElementEntryImpl <em>EElement Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EElementEntryImpl
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEElementEntry()
     * @generated
     */
    EClass EELEMENT_ENTRY = eINSTANCE.getEElementEntry();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EELEMENT_ENTRY__TYPE = eINSTANCE.getEElementEntry_Type();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchEMFMetadataImpl <em>EMF Metadata</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchEMFMetadataImpl
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEModelPatchEMFMetadata()
     * @generated
     */
    EClass EMODEL_PATCH_EMF_METADATA = eINSTANCE.getEModelPatchEMFMetadata();

    /**
     * The meta object literal for the '<em><b>Model Uris</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMODEL_PATCH_EMF_METADATA__MODEL_URIS = eINSTANCE.getEModelPatchEMFMetadata_ModelUris();

    /**
     * The meta object literal for the '<em><b>Used Namespace Uris</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMODEL_PATCH_EMF_METADATA__USED_NAMESPACE_URIS = eINSTANCE.getEModelPatchEMFMetadata_UsedNamespaceUris();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EStructuralFeatureEntryImpl <em>EStructural Feature Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EStructuralFeatureEntryImpl
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEStructuralFeatureEntry()
     * @generated
     */
    EClass ESTRUCTURAL_FEATURE_ENTRY = eINSTANCE.getEStructuralFeatureEntry();

    /**
     * The meta object literal for the '<em><b>Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ESTRUCTURAL_FEATURE_ENTRY__FEATURE = eINSTANCE.getEStructuralFeatureEntry_Feature();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EReferenceEntryImpl <em>EReference Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EReferenceEntryImpl
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEReferenceEntry()
     * @generated
     */
    EClass EREFERENCE_ENTRY = eINSTANCE.getEReferenceEntry();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EREFERENCE_ENTRY__TARGET = eINSTANCE.getEReferenceEntry_Target();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EAttributeEntryImpl <em>EAttribute Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EAttributeEntryImpl
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEAttributeEntry()
     * @generated
     */
    EClass EATTRIBUTE_ENTRY = eINSTANCE.getEAttributeEntry();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EATTRIBUTE_ENTRY__VALUE = eINSTANCE.getEAttributeEntry_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchElementImpl <em>Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchElementImpl
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEModelPatchElement()
     * @generated
     */
    EClass EMODEL_PATCH_ELEMENT = eINSTANCE.getEModelPatchElement();

    /**
     * The meta object literal for the '<em><b>Element Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMODEL_PATCH_ELEMENT__ELEMENT_IDENTIFIER = eINSTANCE.getEModelPatchElement_ElementIdentifier();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EChangeDirection <em>EChange Direction</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EChangeDirection
     * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchPackageImpl#getEChangeDirection()
     * @generated
     */
    EEnum ECHANGE_DIRECTION = eINSTANCE.getEChangeDirection();

  }

} //EModelPatchPackage
