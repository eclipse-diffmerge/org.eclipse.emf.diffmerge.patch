/*******************************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
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

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

public class IdentifiedEMFObjectLocator {

  protected Map<String, EPackage> packageRegistry;
  private EObjectLocator eObjectLocator;

  public IdentifiedEMFObjectLocator() {
    packageRegistry = Maps.newHashMap();
  }

  public void registerEPackage(EPackage ePackage) {
    Preconditions.checkNotNull("EPackage cannot be null", ePackage);
    String nsURI = ePackage.getNsURI();
    checkNotNullIdentifier(nsURI);
    packageRegistry.put(nsURI, ePackage);
  }

  public Optional<EObject> locateEObject(String identifier) {
    checkNotNullIdentifier(identifier);
    Optional<EObject> locate = Optional.absent();
    EObjectLocator locator = getEObjectLocator();
    if(locator != null) {
      locate = locator.locate(identifier);
    }
    return locate;
  }

  public Optional<EPackage> locateEPackage(String identifier) {
    checkNotNullIdentifier(identifier);
    EPackage ePackage = packageRegistry.get(identifier);
    if(ePackage == null) {
      ePackage = EPackage.Registry.INSTANCE.getEPackage(identifier);
    }
    return Optional.fromNullable(ePackage);
  }

  public Optional<EClassifier> locateEClassifier(String identifier) {
    checkNotNullIdentifier(identifier);
    EClassifier eClassifier = null;
    String separator = EMFIdentifierProvider.PACKAGED_ELEMENT_SEPARATOR;
    int lastIndexOf = identifier.lastIndexOf(separator);
    if(lastIndexOf > separator.length()){
      String packageIdentifier = identifier.substring(0, lastIndexOf);
      Optional<EPackage> locatedEPackage = locateEPackage(packageIdentifier);
      if(locatedEPackage.isPresent() && lastIndexOf < identifier.length()){
        String eClassIdentifier = identifier.substring(lastIndexOf + separator.length());
        if(!eClassIdentifier.isEmpty()){
          eClassifier = locatedEPackage.get().getEClassifier(eClassIdentifier);
        }
      }
    }
    return Optional.fromNullable(eClassifier);

  }

  public Optional<EStructuralFeature> locateEStructuralFeature(String identifier) {
    checkNotNullIdentifier(identifier);
    EStructuralFeature feature = null;
    String separator = EMFIdentifierProvider.ECLASS_FEATURE_SEPARATOR;
    int lastIndexOf = identifier.lastIndexOf(separator);
    if(lastIndexOf > separator.length()){
      String eClassIdentifier = identifier.substring(0, lastIndexOf);
      Optional<EClassifier> locatedEClassifier = locateEClassifier(eClassIdentifier);
      if(locatedEClassifier.isPresent() && lastIndexOf < identifier.length()){
        String eStructuralFeatureIdentifier = identifier.substring(lastIndexOf + separator.length());
        if(!eStructuralFeatureIdentifier.isEmpty()){
          EClassifier eClassifier = locatedEClassifier.get();
          if(eClassifier instanceof EClass){
            feature = ((EClass) eClassifier).getEStructuralFeature(eStructuralFeatureIdentifier);
          }
        }
      }
    }
    return Optional.fromNullable(feature);
  }

  public Optional<EEnumLiteral> locateEEnumLiteral(String identifier) {
    checkNotNullIdentifier(identifier);
    EEnumLiteral literal = null;
    String separator = EMFIdentifierProvider.ENUM_LITERAL_SEPARATOR;
    int lastIndexOf = identifier.lastIndexOf(separator);
    if(lastIndexOf > separator.length()){
      String eEnumIdentifier = identifier.substring(0, lastIndexOf);
      Optional<EClassifier> locatedEEnum = locateEClassifier(eEnumIdentifier);
      if(locatedEEnum.isPresent() && lastIndexOf < identifier.length()){
        String literalIdentifier = identifier.substring(lastIndexOf + separator.length());
        if(!literalIdentifier.isEmpty()){
          EClassifier eClassifier = locatedEEnum.get();
          if(eClassifier instanceof EEnum){
            literal = ((EEnum) eClassifier).getEEnumLiteral(literalIdentifier);
          }
        }
      }
    }
    return Optional.fromNullable(literal);
  }

  private void checkNotNullIdentifier(String identifier) {
    Preconditions.checkNotNull(identifier, "Identifier cannot be null!");
  }

  public EObjectLocator getEObjectLocator() {
    return eObjectLocator;
  }

  public void setEObjectLocator(EObjectLocator eObjectLocator) {
    this.eObjectLocator = eObjectLocator;
  }

}
