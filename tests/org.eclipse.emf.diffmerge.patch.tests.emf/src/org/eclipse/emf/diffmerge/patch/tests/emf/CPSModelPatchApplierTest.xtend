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
package org.eclipse.emf.diffmerge.patch.tests.emf

import java.io.File
import java.util.Collection
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.diffmerge.patch.api.EntryType
import org.eclipse.emf.diffmerge.patch.api.ModelPatchFilterApplier
import org.eclipse.emf.diffmerge.patch.api.ModelPatchReverser
import org.eclipse.emf.diffmerge.patch.api.filters.EntryTypeFilter
import org.eclipse.emf.diffmerge.patch.serializer.gson.GsonModelPatchSerializer
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.AppState
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.ApplicationInstance
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.CyberPhysicalSystemPackage
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.HostInstance
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Identifiable
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter

import static org.junit.Assert.*

@RunWith(Parameterized)
class CPSModelPatchApplierTest extends CPSModelPatchTest{
  private extension val ModelPatchReverser modelPatchReverser = ModelPatchReverser.INSTANCE

  public static val FIRSTAPPCLASS_INSTANCE_ID = "simple.cps.app.FirstAppClass0.inst0"
  public static val FIRSTHOSTCLASS_INSTANCE_ID = "simple.cps.host.FirstHostClass0.inst0"
  public static val SECONDHOSTCLASS_INSTANCE_ID = "simple.cps.host.SecondHostClass0.inst0"


  @Parameterized.Parameters(name = "{0}")
  def static Collection<Object[]> testData() {
    return newArrayList(
      #[
        "Demo model",
        '''«MODEL_FOLDER»/demo_original.«MODEL_FILE_EXTENSION»''',
        '''«MODEL_FOLDER»/demo_modified.«MODEL_FILE_EXTENSION»''',
        '''«MODEL_FOLDER»/demo-patch-generated.«PATCH_FILE_EXTENSION»'''
      ]
    )
  }


  @Parameter(0)
  public String testName
  @Parameter(1)
  public String originalModelPath
  @Parameter(2)
  public String modifiedModelPath
  @Parameter(3)
  public String patchFilePath

  @BeforeClass
  public static def void initCPS() {
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(MODEL_FILE_EXTENSION, new XMIResourceFactoryImpl());
    CyberPhysicalSystemPackage.eINSTANCE.eClass();
  }

  @Test
  public def void applyPatch() {
    // Load model
    val resourceSet = new ResourceSetImpl();
    val resource = resourceSet.getResource(URI.createFileURI(originalModelPath), true);

    // Load patch file
    val patchFile = new File(patchFilePath);
    val modelPatch = GsonModelPatchSerializer.create(this.class.classLoader).load(patchFile);

    // Apply patch
    val patchApplier = preparePatchApplier(resourceSet)
    val app = patchApplier.apply(modelPatch, resourceSet)

    // Check the result
    val cpsElements = resource.allContents.toList.filter(Identifiable)
    assertEquals(AppState.STOPPED, (cpsElements.findFirst[FIRSTAPPCLASS_INSTANCE_ID == it.identifier] as ApplicationInstance).state)
    assertTrue("Missing application instance reference from host instance",
      (cpsElements.findFirst[SECONDHOSTCLASS_INSTANCE_ID == it.identifier] as HostInstance)
        .applications.exists[FIRSTAPPCLASS_INSTANCE_ID == it.identifier]
    )
    assertFalse("Host class has not been removed", cpsElements.exists[FIRSTHOSTCLASS_INSTANCE_ID == it.identifier])
  }



  @Test
  public def void applyReversePatch() {
    // Load model
    val resourceSet = new ResourceSetImpl();
    val resource = resourceSet.getResource(URI.createFileURI(modifiedModelPath), true);

    // Load and reverse patch file
    val patchFile = new File(patchFilePath);
    val modelPatch = GsonModelPatchSerializer.create(this.class.classLoader).load(patchFile);
    val reversePatch = modelPatch.reverse

    // Apply reverse patch
    val patchApplier = preparePatchApplier(resourceSet)
    patchApplier.apply(reversePatch, resourceSet)

    // Check the result
    val cpsElements = resource.allContents.toList.filter(Identifiable)
    assertEquals(AppState.RUNNING, (cpsElements.findFirst[FIRSTAPPCLASS_INSTANCE_ID == it.identifier] as ApplicationInstance).state)
    assertFalse("Application instance reference has not been removed from host instance",
      (cpsElements.findFirst[SECONDHOSTCLASS_INSTANCE_ID == it.identifier] as HostInstance)
        .applications.exists[FIRSTAPPCLASS_INSTANCE_ID == it.identifier]
    )
    assertTrue("Host class has not been added", cpsElements.exists[FIRSTHOSTCLASS_INSTANCE_ID == it.identifier])
  }

  @Test
  public def void applyReversePatchOnOriginal() {
    // Load model
    val resourceSet = new ResourceSetImpl();
    val resource = resourceSet.getResource(URI.createFileURI(originalModelPath), true);

    // Load and reverse patch file
    val patchFile = new File(patchFilePath);
    val modelPatch = GsonModelPatchSerializer.create(this.class.classLoader).load(patchFile);
    val reversePatch = modelPatch.reverse

    // Apply reverse patch
    val patchApplier = preparePatchApplier(resourceSet)
    val patchApplication = patchApplier.apply(reversePatch, resourceSet)

    // Assert
    assertTrue(patchApplication.diagnostics.numOfExceptions > 0);
  }

  @Test
  public def void applyFilteredPatch() {
    // Load model
    val resourceSet = new ResourceSetImpl()
    val resource = resourceSet.getResource(URI.createFileURI(originalModelPath), true)

    // Load patch file
    val patchFile = new File(patchFilePath)
    val ModelPatchFilterApplier filterApplier = new ModelPatchFilterApplier
    val originalPatch = GsonModelPatchSerializer.create(this.class.classLoader).load(patchFile)
    val filteredPatch = filterApplier.applyFilter(originalPatch, new EntryTypeFilter(EntryType.ELEMENT, EntryType.REFERENCE))

    // Apply patch
    val patchApplier = preparePatchApplier(resourceSet)
    val patchApplication = patchApplier.apply(filteredPatch, resourceSet)
    val diags = patchApplication.diagnostics

    // Check the result
    val cpsElements = resource.allContents.toList.filter(Identifiable)
    assertEquals(AppState.STOPPED, (cpsElements.findFirst[FIRSTAPPCLASS_INSTANCE_ID == it.identifier] as ApplicationInstance).state)
    assertFalse("Reference should not been set up (REFERNCE entries are filtered)",
      (cpsElements.findFirst[SECONDHOSTCLASS_INSTANCE_ID == it.identifier] as HostInstance)
        .applications.exists[FIRSTAPPCLASS_INSTANCE_ID == it.identifier]
    )
    assertTrue("Host class should not been removed", cpsElements.exists[FIRSTHOSTCLASS_INSTANCE_ID == it.identifier])
  }
}
