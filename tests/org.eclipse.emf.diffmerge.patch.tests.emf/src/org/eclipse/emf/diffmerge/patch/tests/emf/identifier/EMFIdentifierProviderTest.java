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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.patch.runtime.identifier.EMFIdentifierProvider;
import org.eclipse.emf.diffmerge.patch.runtime.identifier.EObjectIdentifierProvider;
import org.eclipse.emf.diffmerge.patch.runtime.identifier.TreeIteratorEObjectLocator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.CyberPhysicalSystem;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.CyberPhysicalSystemFactory;
import org.junit.Test;

public class EMFIdentifierProviderTest {

  private EMFIdentifierProvider identifierProvider = new EMFIdentifierProvider();

  @Test
  public void identifyEPackage() {
    EcorePackage ecorePackage = EcorePackage.eINSTANCE;
    String identify = identifierProvider.identify(ecorePackage);

    assertEquals(EcorePackage.eNS_URI, identify);

  }

  @Test
  public void identifyEClass() {
    EClass eClass = EcorePackage.Literals.ECLASS;
    String identify = identifierProvider.identify(eClass);

    assertTrue(identify.startsWith(eClass.getEPackage().getNsURI()));
    assertTrue(identify.endsWith(eClass.getName()));
  }

  @Test
  public void identifyEStructuralFeature() {
    EAttribute eAttribute = EcorePackage.Literals.ENAMED_ELEMENT__NAME;
    String identify = identifierProvider.identify(eAttribute);

    assertTrue(identify.startsWith(eAttribute.getEContainingClass().getEPackage().getNsURI()));
    assertTrue(identify.contains(eAttribute.getEContainingClass().getName()));
    assertTrue(identify.endsWith(eAttribute.getName()));

  }

  @Test
  public void identifyEOperation() {
    EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
    EPackage ePackage = ecoreFactory.createEPackage();
    ePackage.setNsURI("testUri");
    EClass eClass = ecoreFactory.createEClass();
    eClass.setName("TestClass");
    ePackage.getEClassifiers().add(eClass);
    EOperation eOperation = ecoreFactory.createEOperation();
    eOperation.setName("testOp");
    eClass.getEOperations().add(eOperation);
    String identify = identifierProvider.identify(eOperation);

    assertTrue(identify.startsWith(eOperation.getEContainingClass().getEPackage().getNsURI()));
    assertTrue(identify.contains(eOperation.getEContainingClass().getName()));
    assertTrue(identify.endsWith(eOperation.getName()));

  }

  @Test
  public void identifyEDataType() {
    EDataType eBoolean = EcorePackage.Literals.EBOOLEAN;
    String identify = identifierProvider.identify(eBoolean);

    assertTrue(identify.startsWith(eBoolean.getEPackage().getNsURI()));
    assertTrue(identify.endsWith(eBoolean.getName()));

  }

  @Test
  public void identifyEEnum() {
    EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
    EPackage ePackage = ecoreFactory.createEPackage();
    ePackage.setNsURI("testUri");
    EEnum eEnum = ecoreFactory.createEEnum();
    eEnum.setName("testEnum");
    ePackage.getEClassifiers().add(eEnum);
    String identify = identifierProvider.identify(eEnum);

    assertTrue(identify.startsWith(eEnum.getEPackage().getNsURI()));
    assertTrue(identify.endsWith(eEnum.getName()));

  }

  @Test
  public void identifyEEnumLiteral() {
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

    assertTrue(identify.startsWith(eEnum.getEPackage().getNsURI()));
    assertTrue(identify.contains(eEnum.getName()));
    assertTrue(identify.endsWith(literal.getName()));
  }

  @Test
  public void identifyEObject() {
    CyberPhysicalSystemFactory factory = CyberPhysicalSystemFactory.eINSTANCE;
    CyberPhysicalSystem cps = factory.createCyberPhysicalSystem();
    cps.setIdentifier("cps");

    identifierProvider.setEObjectIdentifierProvider(new EObjectIdentifierProvider() {

      @Override
      public String identify(EObject eObject) {
        return EcoreUtil.getID(eObject);
      }
    });
    String identify = identifierProvider.identifyEObject(cps);

    assertTrue(identify.endsWith(cps.getIdentifier()));
  }
}
