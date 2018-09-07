/*******************************************************************************
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *      Abel Hegedus, Tamas Borbas, Balazs Grill, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.runtime.identifier;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EMFIdentifierProvider {

  public static final String ECLASS_FEATURE_SEPARATOR = ".";
  public static final String ECLASS_OPERATION_SEPARATOR = ":";
  public static final String ENUM_LITERAL_SEPARATOR = "::";
  public static final String PACKAGED_ELEMENT_SEPARATOR = "#";

  private EObjectIdentifierProvider eObjectIdentifierProvider = new IDAttributeBasedEObjectIdentifierProvider();

  /**
   * 
   * @param eObject
   * @return the identifier of the EObject, using the
   *         {@link #getEObjectIdentifierProvider()} if set, NO_ID if not found
   */
  public String identifyEObject(EObject eObject) {
    String identifier = null;
    EObjectIdentifierProvider provider = getEObjectIdentifierProvider();
    if (provider != null) {
      identifier = provider.identify(eObject);
    }
    if (identifier == null) {
      identifier = "NO_ID";
    }
    return identifier;
  }

  public String identify(EPackage ePackage) {
    String identifier = ePackage.getNsURI();
    return identifier;
  }

  public String identify(EClassifier eClassifier) {
    EPackage ePackage = eClassifier.getEPackage();
    String identifier = identifyNamedElementInPackage(eClassifier, ePackage);
    return identifier;
  }

  public String identify(EOperation eOperation) {
    EClass eContainingClass = eOperation.getEContainingClass();
    String eClassIdentifier = identify(eContainingClass);
    String name = eOperation.getName();
    String identifier = eClassIdentifier + ECLASS_OPERATION_SEPARATOR + name;
    return identifier;
  }

  public String identify(EStructuralFeature eStructuralFeature) {
    EClass eContainingClass = eStructuralFeature.getEContainingClass();
    String eClassIdentifier = identify(eContainingClass);
    String name = eStructuralFeature.getName();
    String identifier = eClassIdentifier + ECLASS_FEATURE_SEPARATOR + name;
    return identifier;
  }

  public String identify(EEnumLiteral eEnumLiteral) {
    EEnum eEnum = eEnumLiteral.getEEnum();
    String eEnumIdentifier = identify(eEnum);
    String name = eEnumLiteral.getName();
    String identifier = eEnumIdentifier + ENUM_LITERAL_SEPARATOR + name;
    return identifier;
  }

  private String identifyNamedElementInPackage(ENamedElement namedElement, EPackage ePackage) {
    String ePackageIdentifier = identify(ePackage);
    String name = namedElement.getName();
    String identifier = ePackageIdentifier + PACKAGED_ELEMENT_SEPARATOR + name;
    return identifier;
  }

  public EObjectIdentifierProvider getEObjectIdentifierProvider() {
    return eObjectIdentifierProvider;
  }

  public void setEObjectIdentifierProvider(EObjectIdentifierProvider eObjectIdentifierProvider) {
    this.eObjectIdentifierProvider = eObjectIdentifierProvider;
  }
}
