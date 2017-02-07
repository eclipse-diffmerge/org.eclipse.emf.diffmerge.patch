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
package org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchPackage
 * @generated
 */
public interface EModelPatchFactory extends EFactory {
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  EModelPatchFactory eINSTANCE = org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.impl.EModelPatchFactoryImpl.init();

  /**
   * Returns a new object of class '<em>EModel Patch</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EModel Patch</em>'.
   * @generated
   */
  EModelPatch createEModelPatch();

  /**
   * Returns a new object of class '<em>Metadata</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Metadata</em>'.
   * @generated
   */
  EModelPatchMetadata createEModelPatchMetadata();

  /**
   * Returns a new object of class '<em>EIdentifiable</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EIdentifiable</em>'.
   * @generated
   */
  EIdentifiable createEIdentifiable();

  /**
   * Returns a new object of class '<em>EElement Entry</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EElement Entry</em>'.
   * @generated
   */
  EElementEntry createEElementEntry();

  /**
   * Returns a new object of class '<em>EMF Metadata</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EMF Metadata</em>'.
   * @generated
   */
  EModelPatchEMFMetadata createEModelPatchEMFMetadata();

  /**
   * Returns a new object of class '<em>EReference Entry</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EReference Entry</em>'.
   * @generated
   */
  EReferenceEntry createEReferenceEntry();

  /**
   * Returns a new object of class '<em>EAttribute Entry</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EAttribute Entry</em>'.
   * @generated
   */
  EAttributeEntry createEAttributeEntry();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  EModelPatchPackage getEModelPatchPackage();

} //EModelPatchFactory
