/*******************************************************************************
 * Copyright (c) 2016-2017, Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Balazs Grill, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.runtime.modelaccess

import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine
import org.eclipse.viatra.query.runtime.emf.EMFScope
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.ModelManipulationWithEditingDomain
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.SimpleModelManipulations

public class ModelAccessProvider {
  public def EMFModelAccess getSelectedModelAccess(String pref, ResourceSet resourceSet){
    val accessType = ModelAccessTypes.valueOf(pref)

    return accessType.getSelectedModelAccess(resourceSet)
  }

  public def EMFModelAccess getSelectedModelAccess(ModelAccessTypes accessType, ResourceSet resourceSet){
    var EMFModelAccess access
    switch (accessType) {
      case EMF_REFLECTIVE: {
        access = new SimpleReflectiveEMFModelAccess
      }
      case VIATRA: {
        val temporaryResource = resourceSet.createResource(URI.createURI("temporary_resource"))
        val scope = new EMFScope(resourceSet)
        val engine = ViatraQueryEngine.on(scope)

        val domain = AdapterFactoryEditingDomain.getEditingDomainFor(resourceSet)
        if(domain !== null){
          val viatraModelManipulator = new ModelManipulationWithEditingDomain(engine, domain)
          access = new ViatraModelAccess(viatraModelManipulator, temporaryResource)
        }else{
          val viatraModelManipulator = new SimpleModelManipulations(engine)
          access = new ViatraModelAccess(viatraModelManipulator, temporaryResource)
        }
      }
      default: {
        access = new SimpleReflectiveEMFModelAccess
      }
    }
  }
}
