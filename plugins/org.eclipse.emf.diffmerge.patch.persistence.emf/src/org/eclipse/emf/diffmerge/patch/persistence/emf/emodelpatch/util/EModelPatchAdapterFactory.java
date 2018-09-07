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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.*;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage
 * @generated
 */
public class EModelPatchAdapterFactory extends AdapterFactoryImpl {
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static EModelPatchPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EModelPatchAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = EModelPatchPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EModelPatchSwitch<Adapter> modelSwitch =
    new EModelPatchSwitch<Adapter>() {
      @Override
      public Adapter caseEModelPatch(EModelPatch object) {
        return createEModelPatchAdapter();
      }
      @Override
      public Adapter caseEModelPatchMetadata(EModelPatchMetadata object) {
        return createEModelPatchMetadataAdapter();
      }
      @Override
      public Adapter caseEModelPatchEntry(EModelPatchEntry object) {
        return createEModelPatchEntryAdapter();
      }
      @Override
      public Adapter caseEIdentifiable(EIdentifiable object) {
        return createEIdentifiableAdapter();
      }
      @Override
      public Adapter caseEElementEntry(EElementEntry object) {
        return createEElementEntryAdapter();
      }
      @Override
      public Adapter caseEModelPatchEMFMetadata(EModelPatchEMFMetadata object) {
        return createEModelPatchEMFMetadataAdapter();
      }
      @Override
      public Adapter caseEStructuralFeatureEntry(EStructuralFeatureEntry object) {
        return createEStructuralFeatureEntryAdapter();
      }
      @Override
      public Adapter caseEReferenceEntry(EReferenceEntry object) {
        return createEReferenceEntryAdapter();
      }
      @Override
      public Adapter caseEAttributeEntry(EAttributeEntry object) {
        return createEAttributeEntryAdapter();
      }
      @Override
      public Adapter caseEModelPatchElement(EModelPatchElement object) {
        return createEModelPatchElementAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object) {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target) {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch <em>EModel Patch</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch
   * @generated
   */
  public Adapter createEModelPatchAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata <em>Metadata</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata
   * @generated
   */
  public Adapter createEModelPatchMetadataAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry <em>Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry
   * @generated
   */
  public Adapter createEModelPatchEntryAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EIdentifiable <em>EIdentifiable</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EIdentifiable
   * @generated
   */
  public Adapter createEIdentifiableAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EElementEntry <em>EElement Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EElementEntry
   * @generated
   */
  public Adapter createEElementEntryAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEMFMetadata <em>EMF Metadata</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEMFMetadata
   * @generated
   */
  public Adapter createEModelPatchEMFMetadataAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EStructuralFeatureEntry <em>EStructural Feature Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EStructuralFeatureEntry
   * @generated
   */
  public Adapter createEStructuralFeatureEntryAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EReferenceEntry <em>EReference Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EReferenceEntry
   * @generated
   */
  public Adapter createEReferenceEntryAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EAttributeEntry <em>EAttribute Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EAttributeEntry
   * @generated
   */
  public Adapter createEAttributeEntryAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchElement <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchElement
   * @generated
   */
  public Adapter createEModelPatchElementAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter() {
    return null;
  }

} //EModelPatchAdapterFactory
