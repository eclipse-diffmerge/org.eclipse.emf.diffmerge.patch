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

import java.io.File
import java.text.SimpleDateFormat
import java.util.Collection
import java.util.Date
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.diffmerge.api.IComparison
import org.eclipse.emf.diffmerge.api.IDiffPolicy
import org.eclipse.emf.diffmerge.api.IMatchPolicy
import org.eclipse.emf.diffmerge.api.IMergePolicy
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl
import org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy
import org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy
import org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope
import org.eclipse.emf.diffmerge.patch.runtime.ModelPatchRecorder
import org.eclipse.emf.diffmerge.patch.serializer.gson.GsonModelPatchSerializer
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.CyberPhysicalSystemPackage
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter
import org.junit.runners.Parameterized.Parameters

import static org.junit.Assert.*

@RunWith(Parameterized)
class ModelPatchExporterTest extends CPSModelPatchTest {
  private static val RESULTS_FOLDER = "results"

  @Parameters(name = "{0}")
  def static Collection<Object[]> testData() {
    return newArrayList(
      #[
        "Demo model",
        '''«MODEL_FOLDER»/demo_original.«MODEL_FILE_EXTENSION»''',
        '''«MODEL_FOLDER»/demo_modified.«MODEL_FILE_EXTENSION»''',
        '''«RESULTS_FOLDER»/demo'''
      ],
      #[
        "Extra containment tree in modified compared to modified (as ref)",
        '''«MODEL_FOLDER»/demo_withExtraContainmentTree.«MODEL_FILE_EXTENSION»''',
        '''«MODEL_FOLDER»/demo_modified.«MODEL_FILE_EXTENSION»''',
        '''«RESULTS_FOLDER»/extraContInRef'''
      ],
      #[
        "Extra containment tree in modified compared to modified (as target)",
        '''«MODEL_FOLDER»/demo_modified.«MODEL_FILE_EXTENSION»''',
        '''«MODEL_FOLDER»/demo_withExtraContainmentTree.«MODEL_FILE_EXTENSION»''',
        '''«RESULTS_FOLDER»/extraContInTarget'''
      ],
      #[
        "Extra containment tree and reference to an outer element in modified compared to modified (as ref)",
        '''«MODEL_FOLDER»/demo_withExtraContainmentTreeAndReferenceToOut.«MODEL_FILE_EXTENSION»''',
        '''«MODEL_FOLDER»/demo_modified.«MODEL_FILE_EXTENSION»''',
        '''«RESULTS_FOLDER»/extraContAndRefToOutInRef'''
      ],
      #[
        "Extra containment tree and reference from an outer element in modified compared to modified (as ref)",
        '''«MODEL_FOLDER»/demo_withExtraContainmentTreeAndReferenceFromOut.«MODEL_FILE_EXTENSION»''',
        '''«MODEL_FOLDER»/demo_modified.«MODEL_FILE_EXTENSION»''',
        '''«RESULTS_FOLDER»/extraContAndRefFromOutInRef'''
      ],
      #[
        "There is a modified two way reference (0..1 <--> 0..*)",
        '''«MODEL_FOLDER»/demo_withModifiedRefWhichHasEOpposite.«MODEL_FILE_EXTENSION»''',
        '''«MODEL_FOLDER»/demo_modified.«MODEL_FILE_EXTENSION»''',
        '''«RESULTS_FOLDER»/extraContAndRefFromOutInRef'''
      ],
      #[
        "Models with lots of differences",
        '''«MODEL_FOLDER»/demo2_original.«MODEL_FILE_EXTENSION»''',
        '''«MODEL_FOLDER»/demo2_modified.«MODEL_FILE_EXTENSION»''',
        '''«RESULTS_FOLDER»/demo2'''
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
  public String diffFilePath

  @BeforeClass
  public static def void initCPS() {
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(MODEL_FILE_EXTENSION, new XMIResourceFactoryImpl())
    CyberPhysicalSystemPackage.eINSTANCE.eClass()
  }

  @Test
  public def void test() {
    // Instantiate the scopes to compare
    val originalResourceSet = new ResourceSetImpl()
    val originalResource = originalResourceSet.getResource(URI.createFileURI(originalModelPath), true)
    var IEditableModelScope referenceScope = new FragmentedModelScope(originalResource, true)
    val modifiedResourceSet = new ResourceSetImpl()
    val modifiedResource = modifiedResourceSet.getResource(URI.createFileURI(modifiedModelPath), true)
    var IEditableModelScope targetScope = new FragmentedModelScope(modifiedResource, true)

    // Compute comparison
    val IProgressMonitor aProgressMonitor = new NullProgressMonitor
    val IMergePolicy aMergePolicy = new DefaultMergePolicy
    val IDiffPolicy aDiffPolicy = new DefaultDiffPolicy
    val IMatchPolicy aMatchPolicy = new DefaultMatchPolicy
    var IComparison comparison = new EComparisonImpl(targetScope, referenceScope)
    comparison.compute(aMatchPolicy, aDiffPolicy, aMergePolicy, aProgressMonitor)

    // Test
    val filePath = '''«diffFilePath»_«currentTime».«PATCH_FILE_EXTENSION»'''
    val modelPatchRecorder = new ModelPatchRecorder
    val modelPatch = modelPatchRecorder.generateModelPatch(comparison)
    val patchFile = new File(filePath)
    GsonModelPatchSerializer.create(this.class.classLoader).serialize(modelPatch,patchFile)
    val generatedFile = new File(filePath)

    // Check the result
    assertTrue("There is no generated file", generatedFile.exists)
    val generatedPatch = GsonModelPatchSerializer.create(this.class.classLoader).load(generatedFile)
    assertFalse("No entry was generated", generatedPatch.entries.size==0)
  }

  def String currentTime() { (new SimpleDateFormat("yyyyMMdd_HHmmss")).format(new Date) }
}
