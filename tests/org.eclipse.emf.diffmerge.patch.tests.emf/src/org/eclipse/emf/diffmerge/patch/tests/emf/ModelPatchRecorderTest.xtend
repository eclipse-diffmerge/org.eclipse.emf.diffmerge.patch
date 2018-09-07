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
package org.eclipse.emf.diffmerge.patch.tests.emf

import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.diffmerge.api.IComparison
import org.eclipse.emf.diffmerge.api.IDiffPolicy
import org.eclipse.emf.diffmerge.api.IMatchPolicy
import org.eclipse.emf.diffmerge.api.IMergePolicy
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl
import org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy
import org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy
import org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope
import org.eclipse.emf.diffmerge.patch.AttributeEntry
import org.eclipse.emf.diffmerge.patch.ReferenceEntry
import org.eclipse.emf.diffmerge.patch.api.ChangeDirection
import org.eclipse.emf.diffmerge.patch.api.EntryType
import org.eclipse.emf.diffmerge.patch.runtime.ModelPatchRecorder
import org.eclipse.emf.diffmerge.patch.runtime.identifier.EMFIdentifierProvider
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.ApplicationInstance
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.ApplicationType
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.CyberPhysicalSystem
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.CyberPhysicalSystemFactory
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.CyberPhysicalSystemPackage
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.HostInstance
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.StateMachine
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import static org.junit.Assert.*

class ModelPatchRecorderTest extends CPSModelPatchTest {
  private extension val CyberPhysicalSystemFactory cpsFactory = CyberPhysicalSystemFactory::eINSTANCE
  private extension val EMFIdentifierProvider identifierProvider = new EMFIdentifierProvider

  private static val referenceModelPath = '''«MODEL_FOLDER»/demo_original.«MODEL_FILE_EXTENSION»'''

  public static val TRANSITION_ID = '''simple.cps.app.FirstAppClass0.sm0.s0.t0'''
  public static val FIRST_APP_CLASS_ID = '''simple.cps.app.FirstAppClass0'''
  public static val FIRST_APP_CLASS_FIRST_INSTANCE_ID = '''simple.cps.app.FirstAppClass0.inst0'''
  public static val SECOND_APP_CLASS_FIRST_INSTANCE_ID = '''simple.cps.app.SecondAppClass0.inst0'''
  public static val SECOND_HOST_CLASS_SECOND_INSTANCE_ID = '''simple.cps.host.SecondHostClass0.inst1'''
  public static val STATEMACHINE_ID = '''simple.cps.app.FirstAppClass0.sm0'''
  public static val STATEMACHINE_CHILD_STATE_ID = '''simple.cps.app.FirstAppClass0.sm0.s0'''
  public static val STATE_CHILD_TRANSITION_ID = '''simple.cps.app.FirstAppClass0.sm0.s0.t0'''

  public static val FIRST_APP_CLASS_NEW_INSTANCE_ID = '''simple.cps.app.FirstAppClass0.inst666'''

  private CyberPhysicalSystem referenceModel
  private FragmentedModelScope referenceScope

  CyberPhysicalSystem modifiedModel = null
  FragmentedModelScope targetScope = null
  ModelPatchRecorder modelPatchRecorder

  @BeforeClass
  public static def void initializeTests() {
    initCPS
  }

  @Before
  def void initTestCase() {
    modelPatchRecorder = new ModelPatchRecorder
    loadReferenceModel
    initModifiedModel
  }

  @Test
  def singleElementRemoved_HierarchyRemoved() {
    // Arrange
    val statemachine = modifiedModel.eAllContents.filter(StateMachine).findFirst[identifier == STATEMACHINE_ID]
    EcoreUtil.remove(statemachine)

    val comparison = computeComparison

    // Act
    val modelPatch = modelPatchRecorder.generateModelPatch(comparison)

    // Assert
    val removeStatemachineEntries =  modelPatch.entries.filter[
      it.direction == ChangeDirection.REMOVE &&
      it.entryType == EntryType.ELEMENT &&
      it.context.identifier == STATEMACHINE_ID
    ]
    val removeStateEntries =  modelPatch.entries.filter[
      it.direction == ChangeDirection.REMOVE &&
      it.entryType == EntryType.ELEMENT &&
      it.context.identifier == STATEMACHINE_CHILD_STATE_ID
    ]
    val removeTransitionEntries =  modelPatch.entries.filter[
      it.direction == ChangeDirection.REMOVE &&
      it.entryType == EntryType.ELEMENT &&
      it.context.identifier == STATE_CHILD_TRANSITION_ID
    ]

    assertEquals('''Remove element entry is not added''', 1, removeStatemachineEntries.size)
    assertEquals('''Remove element entry is not added for child element''', 1, removeStateEntries.size)
    assertEquals('''Remove element entry is not added for grandchild element''', 1, removeTransitionEntries.size)
  }

  @Test
  def singleElementRemoved_ContainmentReferencesRemoved() {
    // Arrange
    val statemachine = modifiedModel.eAllContents.filter(StateMachine).findFirst[identifier == STATEMACHINE_ID]
    EcoreUtil.remove(statemachine)

    val comparison = computeComparison

    // Act
    val modelPatch = modelPatchRecorder.generateModelPatch(comparison)

    // Assert
    val behaviorReferenceName = cyberPhysicalSystemPackage.applicationType_Behavior.identify
    val removeChildContainmentEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.REMOVE &&
      it.entryType == EntryType.REFERENCE &&
      it.context.identifier == FIRST_APP_CLASS_ID &&
      (it as ReferenceEntry).target.identifier == STATEMACHINE_ID &&
      (it as ReferenceEntry).feature.identifier == behaviorReferenceName
    ]

    val stateReferenceName = cyberPhysicalSystemPackage.stateMachine_States.identify
    val removeGrandchildContainmentEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.REMOVE &&
      it.entryType == EntryType.REFERENCE &&
      it.context.identifier == STATEMACHINE_ID &&
      (it as ReferenceEntry).target.identifier == STATEMACHINE_CHILD_STATE_ID &&
      (it as ReferenceEntry).feature.identifier == stateReferenceName
    ]

    assertEquals('''Remove containment reference entry is not added for child''', 1, removeChildContainmentEntries.size)
    assertEquals('''Remove containment reference entry is not added for grandchild''', 1, removeGrandchildContainmentEntries.size)
  }

  @Test
  def singleElementRemoved_CrossreferencesRemoved() {
    // Arrange
    val appInstance = modifiedModel.eAllContents.filter(ApplicationInstance).findFirst[identifier == FIRST_APP_CLASS_FIRST_INSTANCE_ID]
    EcoreUtil.remove(appInstance)

    val comparison = computeComparison

    // Act
    val modelPatch = modelPatchRecorder.generateModelPatch(comparison)

    // Assert
    val applicationsReferenceName = cyberPhysicalSystemPackage.hostInstance_Applications.identify
    val removeCrossreferenceEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.REMOVE &&
      it.entryType == EntryType.REFERENCE &&
      it.context.identifier == SECOND_HOST_CLASS_SECOND_INSTANCE_ID &&
      (it as ReferenceEntry).target.identifier == FIRST_APP_CLASS_FIRST_INSTANCE_ID &&
      (it as ReferenceEntry).feature.identifier == applicationsReferenceName
    ]

    assertEquals('''Remove crossreference entry is not added''', 1, removeCrossreferenceEntries.size)
  }

  @Test
  def elementAndReferenceRemoved_NoDuplicateEntry() {
    // Arrange
    val appInstance = modifiedModel.eAllContents.filter(ApplicationInstance).findFirst[identifier == FIRST_APP_CLASS_FIRST_INSTANCE_ID]
    val hostInstance = modifiedModel.eAllContents.filter(HostInstance).findFirst[identifier == SECOND_HOST_CLASS_SECOND_INSTANCE_ID]
    EcoreUtil.remove(appInstance)
    hostInstance.applications.remove(appInstance)

    val comparison = computeComparison

    // Act
    val modelPatch = modelPatchRecorder.generateModelPatch(comparison)

    // Assert
    val applicationsReferenceName = cyberPhysicalSystemPackage.hostInstance_Applications.identify
    val removeCrossreferenceEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.REMOVE &&
      it.entryType == EntryType.REFERENCE &&
      it.context.identifier == SECOND_HOST_CLASS_SECOND_INSTANCE_ID &&
      (it as ReferenceEntry).target.identifier == FIRST_APP_CLASS_FIRST_INSTANCE_ID &&
      (it as ReferenceEntry).feature.identifier == applicationsReferenceName
    ]

    assertEquals('''Remove crossreference entry is not added''', 1, removeCrossreferenceEntries.size)
  }

  @Test
  def singleReferenceRemoved_ReferenceRemoved() {
    // Arrange
    val appInstance = modifiedModel.eAllContents.filter(ApplicationInstance).findFirst[identifier == FIRST_APP_CLASS_FIRST_INSTANCE_ID]
    appInstance.allocatedTo = null

    val comparison = computeComparison

    // Act
    val modelPatch = modelPatchRecorder.generateModelPatch(comparison)

    // Assert
    val allocatedToReferenceName = cyberPhysicalSystemPackage.applicationInstance_AllocatedTo.identify
    val removeReferenceEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.REMOVE &&
      it.entryType == EntryType.REFERENCE &&
      it.context.identifier == FIRST_APP_CLASS_FIRST_INSTANCE_ID &&
      (it as ReferenceEntry).target.identifier == SECOND_HOST_CLASS_SECOND_INSTANCE_ID &&
      (it as ReferenceEntry).feature.identifier == allocatedToReferenceName
    ]

    assertEquals('''Remove reference entry is not added''', 1, removeReferenceEntries.size)
  }

  @Test
  def singleReferenceRemoved_EOppositeRemoved() {
    // Arrange
    val appInstance = modifiedModel.eAllContents.filter(ApplicationInstance).findFirst[identifier == FIRST_APP_CLASS_FIRST_INSTANCE_ID]
    appInstance.allocatedTo = null

    val comparison = computeComparison

    // Act
    val modelPatch = modelPatchRecorder.generateModelPatch(comparison)

    // Assert
    val applicationsReferenceName = cyberPhysicalSystemPackage.hostInstance_Applications.identify
    val removeEOppositeEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.REMOVE &&
      it.entryType == EntryType.REFERENCE &&
      it.context.identifier == SECOND_HOST_CLASS_SECOND_INSTANCE_ID &&
      (it as ReferenceEntry).target.identifier == FIRST_APP_CLASS_FIRST_INSTANCE_ID &&
      (it as ReferenceEntry).feature.identifier == applicationsReferenceName
    ]

    assertEquals('''Remove reference entry is not added for EOpposite''', 1, removeEOppositeEntries.size)
  }

  @Test
  def singleAttributeRemoved_AttributeRemoved() {
    // Arrange
    val appInstance = modifiedModel.eAllContents.filter(ApplicationInstance).findFirst[identifier == FIRST_APP_CLASS_FIRST_INSTANCE_ID]
    appInstance.dbUser = null

    val comparison = computeComparison

    // Act
    val modelPatch = modelPatchRecorder.generateModelPatch(comparison)

    // Assert
    val dbUserAttributeName = cyberPhysicalSystemPackage.applicationInstance_DbUser.identify
    val removeAttributeEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.REMOVE &&
      it.entryType == EntryType.ATTRIBUTE &&
      it.context.identifier == FIRST_APP_CLASS_FIRST_INSTANCE_ID &&
      (it as AttributeEntry).feature.identifier == dbUserAttributeName
    ]

    assertEquals('''Remove attribute entry is not added''', 1, removeAttributeEntries.size)
  }

  @Test
  def singleElementAdded_ElementAndContainmentReferenceAdded() {
    // Arrange
    val appType = modifiedModel.eAllContents.filter(ApplicationType).findFirst[identifier == FIRST_APP_CLASS_ID]
    appType.instances += createApplicationInstance => [
      it.identifier = FIRST_APP_CLASS_NEW_INSTANCE_ID
    ]

    val comparison = computeComparison

    // Act
    val modelPatch = modelPatchRecorder.generateModelPatch(comparison)

    // Assert
    val addElementEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.ADD &&
      it.entryType == EntryType.ELEMENT &&
      it.context.identifier == FIRST_APP_CLASS_NEW_INSTANCE_ID
    ]

    val instancesReferenceName = cyberPhysicalSystemPackage.applicationType_Instances.identify
    val addContainmentReferenceEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.ADD &&
      it.entryType == EntryType.REFERENCE &&
      it.context.identifier == FIRST_APP_CLASS_ID &&
      (it as ReferenceEntry).feature.identifier == instancesReferenceName &&
      (it as ReferenceEntry).target.identifier == FIRST_APP_CLASS_NEW_INSTANCE_ID
    ]

    val appTypeReferenceName = cyberPhysicalSystemPackage.applicationInstance_Type.identify
    val addEOppositeReferenceEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.ADD &&
      it.entryType == EntryType.REFERENCE &&
      it.context.identifier == FIRST_APP_CLASS_NEW_INSTANCE_ID &&
      (it as ReferenceEntry).feature.identifier == appTypeReferenceName &&
      (it as ReferenceEntry).target.identifier == FIRST_APP_CLASS_ID
    ]

    assertEquals('''Add element entry is not added''', 1, addElementEntries.size)
    assertEquals('''Add containment reference entry is not added''', 1, addContainmentReferenceEntries.size)
    assertEquals('''Add reference entry is added for EOpposite of containment reference''', 0, addEOppositeReferenceEntries.size)
  }

  @Test
  def singleReferenceAdded_ReferenceAdded() {
    // Arrange
    val appInstance = modifiedModel.eAllContents.filter(ApplicationInstance).findFirst[identifier == FIRST_APP_CLASS_FIRST_INSTANCE_ID]
    val secondAppInstance = modifiedModel.eAllContents.filter(ApplicationInstance).findFirst[identifier == SECOND_APP_CLASS_FIRST_INSTANCE_ID]
    appInstance.dependOn += secondAppInstance

    val comparison = computeComparison

    // Act
    val modelPatch = modelPatchRecorder.generateModelPatch(comparison)

    // Assert
    val dependOnReferenceName = cyberPhysicalSystemPackage.applicationInstance_DependOn.identify
    val addReferenceEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.ADD &&
      it.entryType == EntryType.REFERENCE &&
      it.context.identifier == FIRST_APP_CLASS_FIRST_INSTANCE_ID &&
      (it as ReferenceEntry).feature.identifier == dependOnReferenceName &&
      (it as ReferenceEntry).target.identifier == SECOND_APP_CLASS_FIRST_INSTANCE_ID
    ]

    assertEquals('''Add reference entry is not added''', 1, addReferenceEntries.size)
  }

  @Test
  def singleAttributeAdded_AttributeAdded() {
    // Arrange
    val appInstance = modifiedModel.eAllContents.filter(ApplicationInstance).findFirst[identifier == FIRST_APP_CLASS_FIRST_INSTANCE_ID]
    appInstance.dbPassword = "newPassword"

    val comparison = computeComparison

    // Act
    val modelPatch = modelPatchRecorder.generateModelPatch(comparison)

    // Assert
    val dbPasswordAttributeName = cyberPhysicalSystemPackage.applicationInstance_DbPassword.identify
    val addAttributeEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.ADD &&
      it.entryType == EntryType.ATTRIBUTE &&
      it.context.identifier == FIRST_APP_CLASS_FIRST_INSTANCE_ID &&
      (it as AttributeEntry).feature.identifier == dbPasswordAttributeName
    ]

    assertEquals('''Add attribute entry is not added''', 1, addAttributeEntries.size)
  }
  
  @Test
  def multiValueAttributeAdded_AttributeAdded() {
    // Arrange
    referenceModel.requests += createRequest => [
      identifier = "request_id"
      requirements += createRequirement => [
        identifier = "req_id"
      ]
    ] 
    modifiedModel.requests += createRequest => [
      identifier = "request_id"
      requirements += createRequirement => [
        identifier = "req_id"
        availablePorts += 1
        availablePorts += 2
      ]
    ]

    val comparison = computeComparison

    // Act
    val modelPatch = modelPatchRecorder.generateModelPatch(comparison)

    // Assert
    val availablePortsAttributeName = cyberPhysicalSystemPackage.requirement_AvailablePorts.identify
    val addAttributeEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.ADD &&
      it.entryType == EntryType.ATTRIBUTE &&
      it.context.identifier == "req_id" &&
      (it as AttributeEntry).feature.identifier == availablePortsAttributeName
    ]

    assertEquals('''Add attribute entry is not added''', 2, addAttributeEntries.size)
  }
  
  @Test
  def multiValueAttributeAdded_HierarchyAdded() {
    // Arrange
    modifiedModel.requests += createRequest => [
      identifier = "request_id"
      requirements += createRequirement => [
        identifier = "req_id"
        availablePorts += 1
        availablePorts += 2
      ]
    ]

    val comparison = computeComparison

    // Act
    val modelPatch = modelPatchRecorder.generateModelPatch(comparison)

    // Assert
    val availablePortsAttributeName = cyberPhysicalSystemPackage.requirement_AvailablePorts.identify
    val addAttributeEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.ADD &&
      it.entryType == EntryType.ATTRIBUTE &&
      it.context.identifier == "req_id" &&
      (it as AttributeEntry).feature.identifier == availablePortsAttributeName
    ]

    assertEquals('''Add attribute entry is not added''', 2, addAttributeEntries.size)
  }
  
  @Test
  def multiValueAttributeRemoved_AttributeRemoved() {
    // Arrange
    referenceModel.requests += createRequest => [
      identifier = "request_id"
      requirements += createRequirement => [
        identifier = "req_id"
        availablePorts += 1
        availablePorts += 2
      ]
    ] 
    modifiedModel.requests += createRequest => [
      identifier = "request_id"
      requirements += createRequirement => [
        identifier = "req_id"
      ]
    ]

    val comparison = computeComparison

    // Act
    val modelPatch = modelPatchRecorder.generateModelPatch(comparison)

    // Assert
    val availablePortsAttributeName = cyberPhysicalSystemPackage.requirement_AvailablePorts.identify
    val removeAttributeEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.REMOVE &&
      it.entryType == EntryType.ATTRIBUTE &&
      it.context.identifier == "req_id" &&
      (it as AttributeEntry).feature.identifier == availablePortsAttributeName
    ]

    assertEquals('''Remove attribute entry is not added''', 2, removeAttributeEntries.size)
  }
  
    @Test
  def multiValueAttributeRemoved_AttributeChanged() {
    // Arrange
    referenceModel.requests += createRequest => [
      identifier = "request_id"
      requirements += createRequirement => [
        identifier = "req_id"
        availablePorts += 1
        availablePorts += 2
      ]
    ] 
    val req = 
    modifiedModel.requests += createRequest => [
      identifier = "request_id"
      requirements += createRequirement => [
        identifier = "req_id"
        availablePorts += 3
        availablePorts += 2
      ]
    ]

    val comparison = computeComparison

    // Act
    val modelPatch = modelPatchRecorder.generateModelPatch(comparison)

    // Assert
    val availablePortsAttributeName = cyberPhysicalSystemPackage.requirement_AvailablePorts.identify
    val removeAttributeEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.REMOVE &&
      it.entryType == EntryType.ATTRIBUTE &&
      it.context.identifier == "req_id" &&
      (it as AttributeEntry).feature.identifier == availablePortsAttributeName
    ]
    val addAttributeEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.ADD &&
      it.entryType == EntryType.ATTRIBUTE &&
      it.context.identifier == "req_id" &&
      (it as AttributeEntry).feature.identifier == availablePortsAttributeName
    ]

    assertEquals('''Add attribute entry is not added''', 1, addAttributeEntries.size)
    assertEquals('''Remove attribute entry is not added''', 1, removeAttributeEntries.size)
  }
  
  @Test
  def multiValueAttributeRemoved_HierarchyRemoved() {
    // Arrange
    referenceModel.requests += createRequest => [
      identifier = "request_id"
      requirements += createRequirement => [
        identifier = "req_id"
        availablePorts += 1
        availablePorts += 2
      ]
    ]

    val comparison = computeComparison

    // Act
    val modelPatch = modelPatchRecorder.generateModelPatch(comparison)

    // Assert
    val availablePortsAttributeName = cyberPhysicalSystemPackage.requirement_AvailablePorts.identify
    val removeAttributeEntries = modelPatch.entries.filter[
      it.direction == ChangeDirection.REMOVE &&
      it.entryType == EntryType.ATTRIBUTE &&
      it.context.identifier == "req_id" &&
      (it as AttributeEntry).feature.identifier == availablePortsAttributeName
    ]

    assertEquals('''Remove attribute entry is not added''', 2, removeAttributeEntries.size)
  }

  private static def void initCPS() {
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(MODEL_FILE_EXTENSION, new XMIResourceFactoryImpl())
    CyberPhysicalSystemPackage.eINSTANCE.eClass()
  }

  private def void loadReferenceModel() {
    val referenceResourceSet = new ResourceSetImpl()
    val referenceResource = referenceResourceSet.getResource(URI.createFileURI(referenceModelPath), true)
    referenceModel = referenceResource.contents.filter(CyberPhysicalSystem).head
    referenceScope = new FragmentedModelScope(referenceResource, true)

    val appInstance = referenceModel.eAllContents.filter(ApplicationInstance).findFirst[identifier == FIRST_APP_CLASS_FIRST_INSTANCE_ID]
    appInstance.dbUser = "testUser"
  }

  private def initModifiedModel() {
    val modifiedResourceSet = new ResourceSetImpl()
    val modifiedResource = modifiedResourceSet.createResource(URI.createURI("_modified.cyberphysicalsystem"));

    modifiedModel = EcoreUtil.copy(referenceModel)
    modifiedResource.getContents().add(modifiedModel);

    targetScope = new FragmentedModelScope(modifiedResource, true)
  }

  private def IComparison computeComparison(){
    val IProgressMonitor aProgressMonitor = new NullProgressMonitor
    val IMergePolicy aMergePolicy = new DefaultMergePolicy
    val IDiffPolicy aDiffPolicy = new DefaultDiffPolicy
    val IMatchPolicy aMatchPolicy = new DefaultMatchPolicy
    var IComparison comparison = new EComparisonImpl(targetScope, referenceScope)
    comparison.compute(aMatchPolicy, aDiffPolicy, aMergePolicy, aProgressMonitor)
    return comparison
  }
}
