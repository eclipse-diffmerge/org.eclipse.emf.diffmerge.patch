/*******************************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Peter Lunk (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.tests.performance

import com.google.common.base.Stopwatch
import java.util.ArrayList
import java.util.List
import java.util.concurrent.TimeUnit
import org.apache.log4j.Logger
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.emf.diffmerge.api.IDiffPolicy
import org.eclipse.emf.diffmerge.api.IMatchPolicy
import org.eclipse.emf.diffmerge.api.IMergePolicy
import org.eclipse.emf.diffmerge.api.Role
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl
import org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy
import org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy
import org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope
import org.eclipse.emf.diffmerge.patch.runtime.EMFModelPatchApplier
import org.eclipse.emf.diffmerge.patch.runtime.ModelPatchRecorder
import org.eclipse.emf.diffmerge.patch.runtime.identifier.BaseIndexEObjectLocator
import org.eclipse.emf.diffmerge.patch.runtime.modelaccess.SimpleReflectiveEMFModelAccess
import org.eclipse.emf.diffmerge.patch.serializer.IModelPatchSerializer
import org.eclipse.emf.diffmerge.patch.serializer.gson.GsonModelPatchSerializer
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.CyberPhysicalSystemPackage
import org.eclipse.viatra.query.runtime.base.api.BaseIndexOptions
import org.eclipse.viatra.query.runtime.base.api.IndexingLevel
import org.eclipse.viatra.query.runtime.base.api.ViatraBaseFactory
import org.junit.Assert
import org.junit.Test

class ModelPatchPerformanceTests {
  public static int MODELSIZE = 16
  public static int NUMBER_OF_TESTS = 1

  public static String MODELNAME = '''modelpatch_test_«MODELSIZE»'''
  public static String MODIFIED_MODELNAME = '''modelpatch_test_«MODELSIZE»_modified'''
  public static String MODELPATH = '''resources/complex/«MODELNAME»/«MODELNAME».cyberphysicalsystem'''
  public static String MODIFIED_MODELPATH = '''resources/complex/«MODELNAME»/«MODIFIED_MODELNAME».cyberphysicalsystem'''


  public static String WARMUP_MODELNAME = '''modelpatch_test_1'''
  public static String WARMUP_MODIFIED_MODELNAME = '''modelpatch_test_1_modified'''
  public static String WARMUP_MODELPATH = '''resources/complex/«WARMUP_MODELNAME»/«WARMUP_MODELNAME».cyberphysicalsystem'''
  public static String WARMUP_MODIFIED_MODELPATH = '''resources/complex/«WARMUP_MODELNAME»/«WARMUP_MODIFIED_MODELNAME».cyberphysicalsystem'''



  @Test
  def void performanceTest() {
    //WARMUP
    warmUp


    var int comparisonSize
    var int patchSize

    //TESTS
    for (i : 0 ..< NUMBER_OF_TESTS) {
        val results = new ArrayList<Long>()

      //********** LOAD MODELS **********

      val watch = Stopwatch.createStarted


      val cpsOriginal = CpsModelLoader.INSTANCE.generateCPSmodel(MODELSIZE, MODELNAME, MODELPATH, 11111)
      val cpsModified = CpsModelLoader.INSTANCE.modifyModel(cpsOriginal)

      results.add(watch.elapsed(TimeUnit.MICROSECONDS))
      println("Input models created")
      //********** EMFDIFF **********

      var IEditableModelScope targetScope = new FragmentedModelScope(cpsModified.eResource, true) // For example
      var IEditableModelScope referenceScope = new FragmentedModelScope(cpsOriginal.eResource, true) // For example

      // Compute comparison
      val IProgressMonitor aProgressMonitor = new NullProgressMonitor()
      val IMergePolicy aMergePolicy = new DefaultMergePolicy()
      val IDiffPolicy aDiffPolicy = new DefaultDiffPolicy()
      val IMatchPolicy aMatchPolicy = new DefaultMatchPolicy()
      val comparison = new EComparisonImpl(targetScope, referenceScope)

      watch.reset.start
      comparison.compute(aMatchPolicy, aDiffPolicy, aMergePolicy, aProgressMonitor)
      results.add(watch.elapsed(TimeUnit.MICROSECONDS))
      comparisonSize = comparison.getDifferences(Role.TARGET).size + comparison.getDifferences(Role.REFERENCE).size
      println("Comparison created")
      //********** Create Patch **********
      watch.reset.start
      val modelPatchRecorder = new ModelPatchRecorder
      val modelPatch = modelPatchRecorder.generateModelPatch(comparison)
      results.add(watch.elapsed(TimeUnit.MICROSECONDS))
      patchSize = modelPatch.entries.size
      println("Patch created")
      //********** Serialize Patch **********

      var IModelPatchSerializer serializer = GsonModelPatchSerializer.create(this.class.classLoader)
      watch.reset.start

      val serializedPatch = serializer.serialize(modelPatch)
      results.add(watch.elapsed(TimeUnit.MICROSECONDS))

      println("Patch serialized")
      //********** Deserialize Patch **********
      watch.reset.start
      val readPatch = serializer.load(serializedPatch)
      results.add(watch.elapsed(TimeUnit.MICROSECONDS))
      println("Patch deserialized")
      //********** Apply Patch **********

//			val tempCps = CpsModelLoader.INSTANCE.generateCPSmodel(MODELSIZE, MODELNAME, MODELPATH, 11111)


      watch.reset.start
      val patchApplier = preparePatchApplier(cpsOriginal.eResource)
      results.add(watch.elapsed(TimeUnit.MICROSECONDS))

      watch.reset.start
        val application = patchApplier.apply(readPatch, cpsOriginal.eResource.resourceSet)
        val diag = application.diagnostics
        Assert.assertTrue(diag.diagnosticElements.empty);
      results.add(watch.elapsed(TimeUnit.MICROSECONDS))
      println("Patch applied")
      //MERGE
      watch.reset.start
      comparison.merge(Role.TARGET, false, null)
      results.add(watch.elapsed(TimeUnit.MICROSECONDS))
      println("Changes merged")

      results.printResults

    }

    println('''EMFDIFF comparison size	«comparisonSize»''')
    println('''Model Patch size	«patchSize»''')

  }

  private def warmUp(){
    for (i : 0 ..< 10) {
      //********** LOAD MODELS **********
      val cpsOriginal = CpsModelLoader.INSTANCE.generateCPSmodel(1, MODELNAME, MODELPATH, 11111)
      val cpsModified = CpsModelLoader.INSTANCE.modifyModel(cpsOriginal)
      //********** EMFDIFF **********
      var IEditableModelScope targetScope = new FragmentedModelScope(cpsModified.eResource, true) // For example
      var IEditableModelScope referenceScope = new FragmentedModelScope(cpsOriginal.eResource, true) // For example
      // Compute comparison
      val IProgressMonitor aProgressMonitor = new NullProgressMonitor()
      val IMergePolicy aMergePolicy = new DefaultMergePolicy()
      val IDiffPolicy aDiffPolicy = new DefaultDiffPolicy()
      val IMatchPolicy aMatchPolicy = new DefaultMatchPolicy()
      val comparison = new EComparisonImpl(targetScope, referenceScope)
      comparison.compute(aMatchPolicy, aDiffPolicy, aMergePolicy, aProgressMonitor)
      //********** Create Patch **********
      val modelPatchRecorder = new ModelPatchRecorder
      val modelPatch = modelPatchRecorder.generateModelPatch(comparison)
      //********** Serialize Patch **********
      var IModelPatchSerializer serializer = GsonModelPatchSerializer.create(this.class.classLoader)
      val patchOutput = serializer.serialize(modelPatch)
      //********** Deserialize Patch **********
      val readPatch = serializer.load(patchOutput)
      //********** Apply Patch **********
      val originalRes = cpsOriginal.eResource
      val patchApplier = preparePatchApplier(originalRes)

        patchApplier.apply(readPatch, originalRes.resourceSet)

        comparison.merge(Role.TARGET, false, null)

    }
  }



  private def printResults(List<Long> results){
    println('''«FOR l : results SEPARATOR '	'»«l»«ENDFOR»''')
  }

  private def preparePatchApplier(Resource resource) {
    val modelManipulator = new SimpleReflectiveEMFModelAccess
    val patchApplier = new EMFModelPatchApplier(modelManipulator)
    val navigationHelper = ViatraBaseFactory.getInstance().createNavigationHelper(resource, new BaseIndexOptions(), Logger.getLogger(ModelPatchPerformanceTests));
    val cpsId = CyberPhysicalSystemPackage.eINSTANCE.getIdentifiable_Identifier();
    navigationHelper.registerEStructuralFeatures(#{cpsId}, IndexingLevel.FULL);
    patchApplier.locator.EObjectLocator = new BaseIndexEObjectLocator(navigationHelper)
    patchApplier
  }

}
