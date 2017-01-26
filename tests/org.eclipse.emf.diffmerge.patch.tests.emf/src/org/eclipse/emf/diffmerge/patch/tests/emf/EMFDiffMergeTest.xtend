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

import java.text.SimpleDateFormat
import java.util.Collection
import java.util.Collections
import java.util.Date
import java.util.List
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.diffmerge.api.IDiffPolicy
import org.eclipse.emf.diffmerge.api.IMatchPolicy
import org.eclipse.emf.diffmerge.api.IMergePolicy
import org.eclipse.emf.diffmerge.api.Role
import org.eclipse.emf.diffmerge.api.diff.IDifference
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope
import org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence
import org.eclipse.emf.diffmerge.diffdata.EElementPresence
import org.eclipse.emf.diffmerge.diffdata.EMatch
import org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl
import org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy
import org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy
import org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope
import org.eclipse.emf.diffmerge.patch.api.ChangeDirection
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.CyberPhysicalSystemPackage
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized)
class EMFDiffMergeTest extends CPSModelPatchTest {
  private static val RESULTS_FOLDER = "results"

  @BeforeClass
  public static def void initCPS() {
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(MODEL_FILE_EXTENSION, new XMIResourceFactoryImpl())
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(DIFF_FILE_EXTENSION, new XMIResourceFactoryImpl())
    CyberPhysicalSystemPackage.eINSTANCE.eClass()
  }

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


  @Test
  public def void test() {
    // Instantiate the scopes to compare
    val aResourceSet = new ResourceSetImpl()
    val aResource = aResourceSet.getResource(URI.createFileURI(modifiedModelPath), true)
    val bResourceSet = new ResourceSetImpl()
    val bResource = bResourceSet.getResource(URI.createFileURI(originalModelPath), true)
    var IEditableModelScope targetScope = new FragmentedModelScope(aResource, true) // For example
    var IEditableModelScope referenceScope = new FragmentedModelScope(bResource, true) // For example

    // Compute comparison
    val IProgressMonitor aProgressMonitor = new NullProgressMonitor()
    val IMergePolicy aMergePolicy = new DefaultMergePolicy()
    val IDiffPolicy aDiffPolicy = new DefaultDiffPolicy()
    val IMatchPolicy aMatchPolicy = new DefaultMatchPolicy()
    val comparison = new EComparisonImpl(targetScope, referenceScope)
    comparison.compute(aMatchPolicy, aDiffPolicy, aMergePolicy, aProgressMonitor)

    // Turn comparison into an input for viewers
//		val EMFDiffNode diffNode = new EMFDiffNode(comparison as EComparison)
    val toAnalyzeFromSource = comparison.getDifferences(Role.REFERENCE).toList
    val toAnalyzeFromTarget = comparison.getDifferences(Role.TARGET).toList
    val allDiffs = <IDifference>newArrayList => [
      it += toAnalyzeFromSource
      it += toAnalyzeFromTarget
    ]
    allDiffs.print
    val res = (new ResourceSetImpl()).createResource(URI.createFileURI('''«diffFilePath»_«currentTime».«DIFF_FILE_EXTENSION»'''))
    comparison.merge(#[allDiffs.get(2)], Role.REFERENCE, true, aProgressMonitor)
    res.contents.add(comparison)
    res.save(Collections.EMPTY_MAP)
    // Show the comparison in a dialog
    toAnalyzeFromSource.print
    toAnalyzeFromTarget.print
//		val Display display = Display.getDefault()
//		display.syncExec(new Runnable() {
//			override void run() {
//				val DiffMergeDialog dialog = new DiffMergeDialog(display.getActiveShell(), "Your Title", diffNode)
//				dialog.open();
//			}
//		})
  }

  def void print(List<IDifference> differences) {
    differences.forEach[ diff |
      println(diff.textDiff)
      println()
    ]
  }

  private dispatch def String textDiff(IDifference diff) {
    diff.toString
  }
  private dispatch def String textDiff(EAttributeValuePresence diff) {
    '''AttributePresence
    Feature: «EcoreUtil.getURI(diff.feature)»
    Owner: «diff.elementMatch.getIdentifiable(diff.presenceRole).id»
    Value: «diff.value»
    Direction: «diff.presenceRole.changeDirection»
    '''
  }

  private dispatch def String textDiff(EReferenceValuePresence diff) {
    '''ReferencePresence
    Feature: «EcoreUtil.getURI(diff.feature)»
    Source: «diff.elementMatch.getIdentifiable(diff.presenceRole).id»
    Target: «diff.valueMatch.getIdentifiable(diff.presenceRole).id»
    Direction: «diff.presenceRole.changeDirection»
    '''
  }

  private dispatch def String textDiff(EElementPresence diff) {
    '''ElementPresence
    Owner: «diff.elementMatch.getIdentifiable(diff.presenceRole).id»
    Type: «diff.elementMatch.getIdentifiable(diff.presenceRole).eClass.name»
    Direction: «diff.presenceRole.changeDirection»
    '''
  }

  private def getIdentifiable(EMatch match, Role role) {
    if(Role.REFERENCE==role) {
      return match.reference
    }
    return match.target
  }

  private def String getId(EObject semanticElementDiff) {
    val eidAttribute = semanticElementDiff.eClass.EIDAttribute
    if(eidAttribute != null){
      return semanticElementDiff.eGet(eidAttribute).toString
    }
    return "NO_ID"
  }

  private def ChangeDirection getChangeDirection(Role scopeDiff) {
    if(scopeDiff == Role.TARGET){
      return ChangeDirection.ADD
    }
    return ChangeDirection.REMOVE
  }

  def String currentTime() { (new SimpleDateFormat("yyyyMMdd_HHmmss")).format(new Date) }
}
