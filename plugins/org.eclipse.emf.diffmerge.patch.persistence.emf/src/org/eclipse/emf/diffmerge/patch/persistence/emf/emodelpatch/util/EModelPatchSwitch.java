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
package org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.util;

import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage
 * @generated
 */
public class EModelPatchSwitch<T> extends Switch<T> {
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static EModelPatchPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EModelPatchSwitch() {
    if (modelPackage == null) {
      modelPackage = EModelPatchPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage) {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
      case EModelPatchPackage.EMODEL_PATCH: {
        EModelPatch eModelPatch = (EModelPatch)theEObject;
        T result = caseEModelPatch(eModelPatch);
        if (result == null) result = caseEModelPatchElement(eModelPatch);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EModelPatchPackage.EMODEL_PATCH_METADATA: {
        EModelPatchMetadata eModelPatchMetadata = (EModelPatchMetadata)theEObject;
        T result = caseEModelPatchMetadata(eModelPatchMetadata);
        if (result == null) result = caseEModelPatchElement(eModelPatchMetadata);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EModelPatchPackage.EMODEL_PATCH_ENTRY: {
        EModelPatchEntry eModelPatchEntry = (EModelPatchEntry)theEObject;
        T result = caseEModelPatchEntry(eModelPatchEntry);
        if (result == null) result = caseEModelPatchElement(eModelPatchEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EModelPatchPackage.EIDENTIFIABLE: {
        EIdentifiable eIdentifiable = (EIdentifiable)theEObject;
        T result = caseEIdentifiable(eIdentifiable);
        if (result == null) result = caseEModelPatchElement(eIdentifiable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EModelPatchPackage.EELEMENT_ENTRY: {
        EElementEntry eElementEntry = (EElementEntry)theEObject;
        T result = caseEElementEntry(eElementEntry);
        if (result == null) result = caseEModelPatchEntry(eElementEntry);
        if (result == null) result = caseEModelPatchElement(eElementEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EModelPatchPackage.EMODEL_PATCH_EMF_METADATA: {
        EModelPatchEMFMetadata eModelPatchEMFMetadata = (EModelPatchEMFMetadata)theEObject;
        T result = caseEModelPatchEMFMetadata(eModelPatchEMFMetadata);
        if (result == null) result = caseEModelPatchMetadata(eModelPatchEMFMetadata);
        if (result == null) result = caseEModelPatchElement(eModelPatchEMFMetadata);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EModelPatchPackage.ESTRUCTURAL_FEATURE_ENTRY: {
        EStructuralFeatureEntry eStructuralFeatureEntry = (EStructuralFeatureEntry)theEObject;
        T result = caseEStructuralFeatureEntry(eStructuralFeatureEntry);
        if (result == null) result = caseEModelPatchEntry(eStructuralFeatureEntry);
        if (result == null) result = caseEModelPatchElement(eStructuralFeatureEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EModelPatchPackage.EREFERENCE_ENTRY: {
        EReferenceEntry eReferenceEntry = (EReferenceEntry)theEObject;
        T result = caseEReferenceEntry(eReferenceEntry);
        if (result == null) result = caseEStructuralFeatureEntry(eReferenceEntry);
        if (result == null) result = caseEModelPatchEntry(eReferenceEntry);
        if (result == null) result = caseEModelPatchElement(eReferenceEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EModelPatchPackage.EATTRIBUTE_ENTRY: {
        EAttributeEntry eAttributeEntry = (EAttributeEntry)theEObject;
        T result = caseEAttributeEntry(eAttributeEntry);
        if (result == null) result = caseEStructuralFeatureEntry(eAttributeEntry);
        if (result == null) result = caseEModelPatchEntry(eAttributeEntry);
        if (result == null) result = caseEModelPatchElement(eAttributeEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EModelPatchPackage.EMODEL_PATCH_ELEMENT: {
        EModelPatchElement eModelPatchElement = (EModelPatchElement)theEObject;
        T result = caseEModelPatchElement(eModelPatchElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EModel Patch</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EModel Patch</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEModelPatch(EModelPatch object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Metadata</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Metadata</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEModelPatchMetadata(EModelPatchMetadata object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEModelPatchEntry(EModelPatchEntry object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EIdentifiable</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EIdentifiable</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEIdentifiable(EIdentifiable object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EElement Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EElement Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEElementEntry(EElementEntry object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EMF Metadata</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EMF Metadata</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEModelPatchEMFMetadata(EModelPatchEMFMetadata object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EStructural Feature Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EStructural Feature Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEStructuralFeatureEntry(EStructuralFeatureEntry object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EReference Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EReference Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEReferenceEntry(EReferenceEntry object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EAttribute Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EAttribute Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEAttributeEntry(EAttributeEntry object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEModelPatchElement(EModelPatchElement object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object) {
    return null;
  }

} //EModelPatchSwitch
