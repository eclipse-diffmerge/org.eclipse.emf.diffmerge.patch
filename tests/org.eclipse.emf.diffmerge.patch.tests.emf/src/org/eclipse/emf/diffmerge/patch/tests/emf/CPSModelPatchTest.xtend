/*******************************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Balazs Grill, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.tests.emf

import org.apache.log4j.Logger
import org.eclipse.emf.diffmerge.patch.runtime.EMFModelPatchApplier
import org.eclipse.emf.diffmerge.patch.runtime.identifier.BaseIndexEObjectLocator
import org.eclipse.emf.diffmerge.patch.runtime.modelaccess.SimpleReflectiveEMFModelAccess
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.CyberPhysicalSystemPackage
import org.eclipse.viatra.query.runtime.base.api.BaseIndexOptions
import org.eclipse.viatra.query.runtime.base.api.IndexingLevel
import org.eclipse.viatra.query.runtime.base.api.ViatraBaseFactory

class CPSModelPatchTest {

  public static val MODEL_FOLDER = "../org.eclipse.emf.diffmerge.patch.tests.emf/model"
  public static val PATCH_FILE_EXTENSION = "modelpatch"
  public static val MODEL_FILE_EXTENSION = "cyberphysicalsystem";
  public static val DIFF_FILE_EXTENSION = "diffdata"

  def preparePatchApplier(ResourceSet resourceSet) {
    val modelManipulator = new SimpleReflectiveEMFModelAccess
    val patchApplier = new EMFModelPatchApplier(modelManipulator)
    val navigationHelper = ViatraBaseFactory.getInstance().createNavigationHelper(resourceSet, new BaseIndexOptions(), Logger.getLogger(CPSModelPatchApplierTest));
    val cpsId = CyberPhysicalSystemPackage.eINSTANCE.getIdentifiable_Identifier();
    navigationHelper.registerEStructuralFeatures(#{cpsId}, IndexingLevel.FULL);
    patchApplier.locator.EObjectLocator = new BaseIndexEObjectLocator(navigationHelper)
    patchApplier
  }

}
