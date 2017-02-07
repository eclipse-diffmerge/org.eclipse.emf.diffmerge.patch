/*******************************************************************************
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Abel Hegedus, Tamas Borbas, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.tests.emf.identifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.diffmerge.patch.runtime.identifier.EMFIdentifierProvider;
import org.eclipse.emf.diffmerge.patch.runtime.identifier.IdentifiedEMFObjectLocator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Test;

import com.google.common.base.Optional;

public class IdentifiedEMFObjectLocatorTest {

  EMFIdentifierProvider identifierProvider = new EMFIdentifierProvider();
  IdentifiedEMFObjectLocator objectLocator = new IdentifiedEMFObjectLocator();

  @Test
  public void locateEPackage() {
    EcorePackage ecorePackage = EcorePackage.eINSTANCE;
    String identify = identifierProvider.identify(ecorePackage);

    Optional<EPackage> located = objectLocator.locateEPackage(identify);
    assertLocatedValue(ecorePackage, located);
  }

  @Test
  public void locateEClass() {
    EClass eClass = EcorePackage.Literals.ECLASS;
    String identify = identifierProvider.identify(eClass);

    Optional<EClassifier> located = objectLocator.locateEClassifier(identify);
    assertLocatedValue(eClass, located);
  }

  @Test
  public void locateEStructuralFeature() {
    EAttribute eAttribute = EcorePackage.Literals.ENAMED_ELEMENT__NAME;
    String identify = identifierProvider.identify(eAttribute);

    Optional<EStructuralFeature> located = objectLocator.locateEStructuralFeature(identify);
    assertLocatedValue(eAttribute, located);
  }

  @Test
  public void locateEEnumLiteral() {
    EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
    EPackage ePackage = ecoreFactory.createEPackage();
    ePackage.setNsURI("testUri");
    EEnum eEnum = ecoreFactory.createEEnum();
    eEnum.setName("testEnum");
    ePackage.getEClassifiers().add(eEnum);
    EEnumLiteral literal = ecoreFactory.createEEnumLiteral();
    literal.setName("testLiteral");
    eEnum.getELiterals().add(literal);

    String identify = identifierProvider.identify(literal);

    objectLocator.registerEPackage(ePackage);
    Optional<EEnumLiteral> located = objectLocator.locateEEnumLiteral(identify);
    assertLocatedValue(literal, located);
  }

  private <T> void assertLocatedValue(T value, Optional<T> located) {
    assertTrue("Locate did not return value",located.isPresent());
    assertEquals(value, located.get());
  }
}
