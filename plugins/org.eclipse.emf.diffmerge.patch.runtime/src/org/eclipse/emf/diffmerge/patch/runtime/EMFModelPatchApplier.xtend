/*******************************************************************************
 * Copyright (c) 2016-2017, Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Balazs Grill, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.runtime

import com.google.common.base.Preconditions
import org.eclipse.emf.diffmerge.patch.api.ModelPatch
import org.eclipse.emf.diffmerge.patch.api.PatchApplication
import org.eclipse.emf.diffmerge.patch.runtime.identifier.IdentifiedEMFObjectLocator
import org.eclipse.emf.diffmerge.patch.runtime.modelaccess.EMFModelAccess
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtend.lib.annotations.Accessors

class EMFModelPatchApplier {
  EMFModelAccess modelAccess
  @Accessors
  IdentifiedEMFObjectLocator locator

  new(EMFModelAccess modelAccess) {
    this.modelAccess=modelAccess
    this.locator = new IdentifiedEMFObjectLocator()
  }

  def PatchApplication apply(ModelPatch modelPatch, ResourceSet input) {
    Preconditions.checkNotNull(input, "Input cannot be null")
    val patchApplication = new EMFPatchApplication(modelPatch, modelAccess, input)
    patchApplication.locator = locator
    patchApplication.apply()

    return patchApplication
  }
}
