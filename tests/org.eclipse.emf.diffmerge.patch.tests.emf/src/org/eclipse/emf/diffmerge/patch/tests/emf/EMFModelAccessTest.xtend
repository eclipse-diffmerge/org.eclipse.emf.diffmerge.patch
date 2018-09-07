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

import com.google.common.base.Optional
import java.util.Collection
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.diffmerge.patch.api.ModelPatchException
import org.eclipse.emf.diffmerge.patch.runtime.modelaccess.EMFModelAccess
import org.eclipse.emf.diffmerge.patch.runtime.modelaccess.SimpleReflectiveEMFModelAccess
import org.eclipse.emf.diffmerge.patch.runtime.modelaccess.ViatraModelAccess
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.emf.transaction.TransactionalEditingDomain
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.CyberPhysicalSystem
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.CyberPhysicalSystemFactory
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.CyberPhysicalSystemPackage
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.HostInstance
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Transition
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine
import org.eclipse.viatra.query.runtime.emf.EMFScope
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.ModelManipulationWithEditingDomain
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.SimpleModelManipulations
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter

import static org.junit.Assert.*
import org.eclipse.emf.diffmerge.patch.runtime.modelaccess.ModelAccessProvider
import org.eclipse.emf.diffmerge.patch.runtime.modelaccess.ModelAccessTypes

@RunWith(Parameterized)
class EMFModelAccessTest extends CPSModelPatchTest {
  private extension val CyberPhysicalSystemFactory cpsFactory = CyberPhysicalSystemFactory::eINSTANCE
  public static val ORIGINAL_MODEL_PATH = '''«MODEL_FOLDER»/demo_original.«MODEL_FILE_EXTENSION»'''

  public static val FIRST_HOSTCLASS_FIRST_INSTANCE_ID = "simple.cps.host.FirstHostClass0.inst0"
  public static val FIRST_HOSTCLASS_SECOND_INSTANCE_ID = "simple.cps.host.FirstHostClass0.inst2"
  public static val SECOND_HOSTCLASS_FIRST_INSTANCE_ID = "simple.cps.host.SecondHostClass0.inst0"
  public static val TEMPORARY_RESOURCE_URI = URI.createURI('''temporary_resource.«MODEL_FILE_EXTENSION»''')

  @Parameterized.Parameters(name = "{0}")
  def static Collection<Object[]> testData() {
    return newArrayList(
      #[
        "ViatraSimpleModelManipulations",
        [ResourceSet resourceSet|
          val modelAccess = new ModelAccessProvider().getSelectedModelAccess(ModelAccessTypes.VIATRA, resourceSet)
          return modelAccess
        ]
      ],
      #[
        "ViatraModelManipulationWithEditingDomain",
        [ResourceSet resourceSet|
          val temporaryResource = resourceSet.createResource(TEMPORARY_RESOURCE_URI)
          val scope = new EMFScope(resourceSet)
          val engine = ViatraQueryEngine.on(scope)

          val domain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(resourceSet)

          val viatraModelManipulator = new ModelManipulationWithEditingDomain(engine, domain)
          val modelAccess = new ViatraModelAccess(viatraModelManipulator, temporaryResource)
          return modelAccess
        ]
      ],
      #[
        "SimpleReflectiveEMFModelAccess",
        [ResourceSet resourceSet|
          val modelAccess = new ModelAccessProvider().getSelectedModelAccess(ModelAccessTypes.EMF_REFLECTIVE, resourceSet)
          return modelAccess
        ]
      ]
    )
  }


  @Parameter(0)
  public String testName
  @Parameter(1)
  public (ResourceSet) => EMFModelAccess initializeModelAccess



  private var ResourceSet resourceSet
  private var Resource resource
  private var EMFModelAccess modelAccess


  @BeforeClass
  public static def void initCPS() {
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl())
    CyberPhysicalSystemPackage.eINSTANCE.eClass()

  }

  @Before
  public def void initialize() {
    resourceSet = new ResourceSetImpl()
    resource = resourceSet.getResource(URI.createFileURI(ORIGINAL_MODEL_PATH), true)
    modelAccess = initializeModelAccess.apply(resourceSet)
  }

  @After
  public def void cleanup() {
    resourceSet = null
    resource = null
    modelAccess = null
  }

  @Test
  public def void create_withEClassType_elementReturned() {
    // --Arrange--
    val elementType = cyberPhysicalSystemPackage.hostType
    // --Act--
    val element = modelAccess.create(elementType)

    // --Assert--
    assertNotNull("Element is not created", element)
  }

  @Test(expected=IllegalArgumentException)
  public def void create_withEEnumType_exceptionThrown() {
    // --Arrange--
    val elementType = cyberPhysicalSystemPackage.appState
    // --Act--
    val element = modelAccess.create(elementType)
    // --Assert--
    // IllegalArgumentException is thrown
  }

  @Test(expected=IllegalArgumentException)
  public def void create_withNullType_exceptionThrown() {
    // --Arrange--
    // --Act--
    val element = modelAccess.create(null)
    // --Assert--
    // IllegalArgumentException is thrown
  }

  @Test
  public def void add_containmentReference_referenceAdded() {
    // --Arrange--
    val cps = resource.allContents.filter(CyberPhysicalSystem).head
    val hostType = modelAccess.create(cyberPhysicalSystemPackage.hostType)
    // --Act--
    modelAccess.add(cps, cyberPhysicalSystemPackage.cyberPhysicalSystem_HostTypes, hostType, Optional.absent)

    // --Assert--
    assertTrue("Created element is not added to the containment reference", cps.hostTypes.contains(hostType))
  }

  @Test
  public def void add_containmentReferenceWithIndex_referenceAdded() {
    // --Arrange--
    val cps = resource.allContents.filter(CyberPhysicalSystem).head
    val hostType = modelAccess.create(cyberPhysicalSystemPackage.hostType)
    assertFalse("There is no element left in the containment list, test for inserting at a specific index is invalid", cps.hostTypes.isNullOrEmpty)
    val index = Optional.of(0)
    // --Act--
    modelAccess.add(cps, cyberPhysicalSystemPackage.cyberPhysicalSystem_HostTypes, hostType, index)

    // --Assert--
    assertTrue("Created element is not added to the containment reference", cps.hostTypes.contains(hostType))
    assertEquals("The element is not added at the specified index", index.get, cps.hostTypes.indexOf(hostType))
  }

  @Test
  public def void add_containmentReferenceManuallyCreatedElement_referenceAdded() {
    // --Arrange--
    val cps = resource.allContents.filter(CyberPhysicalSystem).head
    val hostType = cpsFactory.createHostType
    // --Act--
    modelAccess.add(cps, cyberPhysicalSystemPackage.cyberPhysicalSystem_HostTypes, hostType, Optional.absent)

    // --Assert--
    assertTrue("Created element is not added to the containment reference", cps.hostTypes.contains(hostType))
  }

  @Test
  public def void add_SingleCrossreference_referenceAdded() {
    // --Arrange--
    val transition = resource.allContents.filter(Transition).head
    val target = transition.targetState
    val reference = cyberPhysicalSystemPackage.transition_TargetState
    modelAccess.remove(transition, reference, target, Optional.absent)
    assertNull("Prerequisite operation error: crossreference is not removed", transition.targetState)

    // --Act--
    modelAccess.add(transition, reference, target, Optional.absent)

    // --Assert--
    assertEquals("Reference is not set", target, transition.targetState)
  }

  @Test
  public def void add_SingleCrossreferenceWithIndex_indexIgnored() {
    // --Arrange--
    val transition = resource.allContents.filter(Transition).head
    val target = transition.targetState
    val reference = cyberPhysicalSystemPackage.transition_TargetState
    modelAccess.remove(transition, reference, target, Optional.absent)
    assertNull("Prerequisite operation error: crossreference is not removed", transition.targetState)
    val index = Optional.of(0)

    // --Act--
    modelAccess.add(transition, reference, target, index)

    // --Assert--
    assertEquals("Reference is not set", target, transition.targetState)
  }


  @Test
  public def void add_SingleAttribute_attributeAdded() {
    // --Arrange--
    val hostInstance1 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_FIRST_INSTANCE_ID]
    val attribute = cyberPhysicalSystemPackage.hostInstance_TotalCpu
    val cpuCount = 16
    assertNotEquals("Incorrect testmodel: \"totalCpu\" should not be set", cpuCount, hostInstance1.totalCpu)

    // --Act--
    modelAccess.add(hostInstance1, attribute, cpuCount, Optional.absent)

    // --Assert--
    assertEquals("Attribute is not set", cpuCount, hostInstance1.totalCpu)
  }

  @Test
  public def void add_ManyCrossreference_referenceAdded() {
    // --Arrange--
    val hostInstance1 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_FIRST_INSTANCE_ID]
    val hostInstance2 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_SECOND_INSTANCE_ID]
    assertFalse("Incorrect testmodel: \"communicates with\" should not be set", hostInstance1.communicateWith.contains(hostInstance2))

    // --Act--
    modelAccess.add(hostInstance1, cyberPhysicalSystemPackage.hostInstance_CommunicateWith, hostInstance2, Optional.absent)

    // --Assert--
    assertTrue("Reference is not added", hostInstance1.communicateWith.contains(hostInstance2))
  }

  @Test(expected = ModelPatchException)
  public def void add_duplicateToUniqueList_exceptionThrown() {
    // --Arrange--
    val hostInstance1 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_FIRST_INSTANCE_ID]
    val hostInstance2 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_SECOND_INSTANCE_ID]
    assertTrue('''Incorrect testmodel: "communicates with should be a unique list"''', cyberPhysicalSystemPackage.hostInstance_CommunicateWith.isUnique)
    assertFalse("Incorrect testmodel: \"communicates with\" should not be set", hostInstance1.communicateWith.contains(hostInstance2))
    modelAccess.add(hostInstance1, cyberPhysicalSystemPackage.hostInstance_CommunicateWith, hostInstance2, Optional.absent)
    assertTrue("Prerequisite operation error: \"communicates with\" is not set", hostInstance1.communicateWith.contains(hostInstance2))

    // --Act--
    modelAccess.add(hostInstance1, cyberPhysicalSystemPackage.hostInstance_CommunicateWith, hostInstance2, Optional.absent)

    // --Assert--
    // ModelPatchException is thrown
  }

  @Test(expected = ModelPatchException)
  public def void add_duplicateToUniqueListWithIndex_exceptionThrown() {
    // --Arrange--
    val hostInstance1 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_FIRST_INSTANCE_ID]
    val hostInstance2 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_SECOND_INSTANCE_ID]
    assertTrue('''Incorrect testmodel: "communicates with should be a unique list"''', cyberPhysicalSystemPackage.hostInstance_CommunicateWith.isUnique)
    assertFalse("Incorrect testmodel: \"communicates with\" should not be set", hostInstance1.communicateWith.contains(hostInstance2))
    val firstIndex = Optional.of(0)
    modelAccess.add(hostInstance1, cyberPhysicalSystemPackage.hostInstance_CommunicateWith, hostInstance2, firstIndex)
    assertTrue("Prerequisite operation error: \"communicates with\" is not set", hostInstance1.communicateWith.contains(hostInstance2))

    val secondIndex = Optional.of(1)

    // --Act--
    modelAccess.add(hostInstance1, cyberPhysicalSystemPackage.hostInstance_CommunicateWith, hostInstance2, secondIndex)

    // --Assert--
    // ModelPatchException is thrown
  }

  @Test
  public def void add_ManyCrossreferenceWithIndex_referenceAdded() {
    // --Arrange--
    val hostInstance1 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_FIRST_INSTANCE_ID]
    val hostInstance2 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_SECOND_INSTANCE_ID]
    val hostInstance3 = resource.allContents.filter(HostInstance).findFirst[identifier == SECOND_HOSTCLASS_FIRST_INSTANCE_ID]
    modelAccess.add(hostInstance1, cyberPhysicalSystemPackage.hostInstance_CommunicateWith, hostInstance3, Optional.absent)
    assertFalse("Incorrect testmodel: \"communicates with\" should not be set", hostInstance1.communicateWith.contains(hostInstance2))
    assertFalse("There is no element left in the reference list, test for inserting at a specific index is invalid", hostInstance1.communicateWith.isNullOrEmpty)
    val index = Optional.of(0)
    // --Act--
    modelAccess.add(hostInstance1, cyberPhysicalSystemPackage.hostInstance_CommunicateWith, hostInstance2, index)

    // --Assert--
    assertTrue("Reference is not added", hostInstance1.communicateWith.contains(hostInstance2))
    assertEquals("The element is not added at the specified index", index.get, hostInstance1.communicateWith.indexOf(hostInstance2))
  }

  @Test
  public def void remove_fromModel_elementRemoved() {
    // --Arrange--
    val hostInstance1 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_FIRST_INSTANCE_ID]
    assertNotNull("Incorrect testmodel: element is not contained", hostInstance1.eContainer)

    // --Act--
    modelAccess.remove(hostInstance1)

    // --Assert--
    assertNull("Reference is not removed from the containment hierarchy", hostInstance1.eContainer)
  }

  @Test(expected=NullPointerException)
  public def void remove_nullFromModel_exceptionThrown() {
    // --Arrange--

    // --Act--
    modelAccess.remove(null)

    // --Assert--
    // NPE is thrown
  }

  @Test
  public def void remove_rootElement_elementRemoved() {
    // --Arrange--
    val rootElement = resource.contents.head
    assertNotNull("Incorrect testmodel: the resource is empty", rootElement)

    // --Act--
    modelAccess.remove(rootElement)

    // --Assert--
    assertFalse("Root element is not removed from the resource", resource.contents.contains(rootElement))
  }

  @Test
  public def void remove_fromSingleCrossreference_referenceRemoved() {
    // --Arrange--
    val transition = resource.allContents.filter(Transition).head
    val target = transition.targetState
    assertNotNull("Incorrect testmodel: target of transition is not set", target)

    // --Act--
    modelAccess.remove(transition, cyberPhysicalSystemPackage.transition_TargetState, target, Optional.absent)

    // --Assert--
    assertNull("Crossreference is not removed", transition.targetState)
    assertNotNull("Containment is removed", target.eContainer)
  }

  @Test
  public def void remove_fromSingleCrossreferenceWithIndex_indexIgnored() {
    // --Arrange--
    val transition = resource.allContents.filter(Transition).head
    val target = transition.targetState
    assertNotNull("Incorrect testmodel: target of transition is not set", target)
    val index = Optional.of(0)

    // --Act--
    modelAccess.remove(transition, cyberPhysicalSystemPackage.transition_TargetState, target, index)

    // --Assert--
    assertNull("Crossreference is not removed", transition.targetState)
    assertNotNull("Containment is removed", target.eContainer)
  }

  @Test
  public def void remove_fromManyCrossreference_referenceRemoved() {
    // --Arrange--
    val hostInstance1 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_FIRST_INSTANCE_ID]
    val hostInstance2 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_SECOND_INSTANCE_ID]
    val reference = cyberPhysicalSystemPackage.hostInstance_CommunicateWith
    modelAccess.add(hostInstance1, reference, hostInstance2, Optional.absent)
    assertTrue("Prerequisite operation error: crossreference is not added", hostInstance1.communicateWith.contains(hostInstance2))
    // --Act--
    modelAccess.remove(hostInstance1, reference, hostInstance2, Optional.absent)

    // --Assert--
    assertFalse("Crossreference is not removed", hostInstance1.communicateWith.contains(hostInstance2))
    assertNotNull("Containment is removed", hostInstance2.eContainer)
  }

  @Test
  public def void remove_fromManyCrossreferenceWithIndex_referenceRemoved() {
    // --Arrange--
    val hostInstance1 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_FIRST_INSTANCE_ID]
    val hostInstance2 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_SECOND_INSTANCE_ID]
    val reference = cyberPhysicalSystemPackage.hostInstance_CommunicateWith
    val index = Optional.of(0)
    modelAccess.add(hostInstance1, reference, hostInstance2, index)
    assertTrue("Prerequisite operation error: crossreference is not added twice", hostInstance1.communicateWith.contains(hostInstance2))
    // --Act--
    modelAccess.remove(hostInstance1, reference, hostInstance2, index)

    // --Assert--
    assertFalse("Crossreference is not removed", hostInstance1.communicateWith.contains(hostInstance2))
    assertNotNull("Containment is removed", hostInstance2.eContainer)
  }

  @Test(expected = ModelPatchException)
  public def void remove_nonexistentSingleCrossreference_exceptionThrow() {
    // --Arrange--
    val transition = resource.allContents.filter(Transition).head
    val target = transition.targetState
    val reference = cyberPhysicalSystemPackage.transition_TargetState
    modelAccess.remove(transition, reference, target, Optional.absent)
    assertNull("Prerequisite operation error: crossreference is not removed", transition.targetState)

    // --Act--
    modelAccess.remove(transition, reference, target, Optional.absent)

    // --Assert--
    // ModelPatchException is thrown
  }

  @Test(expected = ModelPatchException)
  public def void remove_nonexistentSingleCrossreferenceWithIndex_exceptionThrow() {
    // --Arrange--
    val transition = resource.allContents.filter(Transition).head
    val target = transition.targetState
    val reference = cyberPhysicalSystemPackage.transition_TargetState
    modelAccess.remove(transition, reference, target, Optional.absent)
    assertNull("Prerequisite operation error: crossreference is not removed", transition.targetState)
    val index = Optional.of(0)

    // --Act--
    modelAccess.remove(transition, reference, target, index)

    // --Assert--
    // ModelPatchException is thrown
  }

  @Test(expected = ModelPatchException)
  public def void remove_nonexistentManyCrossreference_exceptionThrown() {
    // --Arrange--
    val hostInstance1 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_FIRST_INSTANCE_ID]
    val hostInstance2 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_SECOND_INSTANCE_ID]
    val reference = cyberPhysicalSystemPackage.hostInstance_CommunicateWith
    assertFalse("Incorrect testmodel: crossreference is set", hostInstance1.communicateWith.contains(hostInstance2))

    // --Act--
    modelAccess.remove(hostInstance1, reference, hostInstance2, Optional.absent)

    // --Assert--
    // ModelPatchException is thrown
  }

  @Test(expected = ModelPatchException)
  public def void remove_nonexistentManyCrossreferenceWithIndex_exceptionThrow() {
    // --Arrange--
    val hostInstance1 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_FIRST_INSTANCE_ID]
    val hostInstance2 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_SECOND_INSTANCE_ID]
    val reference = cyberPhysicalSystemPackage.hostInstance_CommunicateWith
    assertFalse("Incorrect testmodel: crossreference is set", hostInstance1.communicateWith.contains(hostInstance2))
    val index = Optional.of(0)

    // --Act--
    modelAccess.remove(hostInstance1, reference, hostInstance2, index)

    // --Assert--
    // ModelPatchException is thrown
  }

  @Test
  public def void changeIndex_validIndex_indexChanged() {
    // --Arrange--
    val hostInstance1 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_FIRST_INSTANCE_ID]
    val hostInstance2 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_SECOND_INSTANCE_ID]
    val hostInstance3 = resource.allContents.filter(HostInstance).findFirst[identifier == SECOND_HOSTCLASS_FIRST_INSTANCE_ID]
    val reference = cyberPhysicalSystemPackage.hostInstance_CommunicateWith
    val initialIndex = Optional.of(1)
    modelAccess.add(hostInstance1, cyberPhysicalSystemPackage.hostInstance_CommunicateWith, hostInstance2, Optional.absent)
    modelAccess.add(hostInstance1, cyberPhysicalSystemPackage.hostInstance_CommunicateWith, hostInstance3, initialIndex)
    assertTrue("Prerequisite operation error: element is not added to reference", hostInstance1.communicateWith.contains(hostInstance2))
    assertEquals("Prerequisite operation error: element is not added at specified index",
      initialIndex.get,
      hostInstance1.communicateWith.indexOf(hostInstance3)
    )

    val modifiedIndex = Optional.of(0)

    // --Act--
    modelAccess.changeIndex(hostInstance1, reference, hostInstance3, modifiedIndex)

    // --Assert--
    assertEquals("Index is not changed",
      modifiedIndex.get,
      hostInstance1.communicateWith.indexOf(hostInstance3)
    )
  }

  @Test
  public def void changeIndex_emptyIndex_nothingHappens() {
    // --Arrange--
    val hostInstance1 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_FIRST_INSTANCE_ID]
    val hostInstance2 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_SECOND_INSTANCE_ID]
    val hostInstance3 = resource.allContents.filter(HostInstance).findFirst[identifier == SECOND_HOSTCLASS_FIRST_INSTANCE_ID]
    val reference = cyberPhysicalSystemPackage.hostInstance_CommunicateWith
    val initialIndex = Optional.of(1)
    modelAccess.add(hostInstance1, cyberPhysicalSystemPackage.hostInstance_CommunicateWith, hostInstance2, Optional.absent)
    modelAccess.add(hostInstance1, cyberPhysicalSystemPackage.hostInstance_CommunicateWith, hostInstance3, initialIndex)
    assertTrue("Prerequisite operation error: element is not added to reference", hostInstance1.communicateWith.contains(hostInstance2))
    assertEquals("Prerequisite operation error: element is not added at specified index",
      initialIndex.get,
      hostInstance1.communicateWith.indexOf(hostInstance3)
    )

    val modifiedIndex = Optional.absent

    // --Act--
    modelAccess.changeIndex(hostInstance1, reference, hostInstance3, modifiedIndex)

    // --Assert--
    assertEquals("Index is changed",
      initialIndex.get,
      hostInstance1.communicateWith.indexOf(hostInstance3)
    )
  }

  @Test
  public def void changeIndex_invalidIndex_nothingHappens() {
    // --Arrange--
    val hostInstance1 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_FIRST_INSTANCE_ID]
    val hostInstance2 = resource.allContents.filter(HostInstance).findFirst[identifier == FIRST_HOSTCLASS_SECOND_INSTANCE_ID]
    val hostInstance3 = resource.allContents.filter(HostInstance).findFirst[identifier == SECOND_HOSTCLASS_FIRST_INSTANCE_ID]
    val reference = cyberPhysicalSystemPackage.hostInstance_CommunicateWith
    val initialIndex = Optional.of(1)
    modelAccess.add(hostInstance1, cyberPhysicalSystemPackage.hostInstance_CommunicateWith, hostInstance2, Optional.absent)
    modelAccess.add(hostInstance1, cyberPhysicalSystemPackage.hostInstance_CommunicateWith, hostInstance3, initialIndex)
    assertTrue("Prerequisite operation error: element is not added to reference", hostInstance1.communicateWith.contains(hostInstance2))
    assertEquals("Prerequisite operation error: element is not added at specified index",
      initialIndex.get,
      hostInstance1.communicateWith.indexOf(hostInstance3)
    )

    val modifiedIndex = Optional.of(666)

    // --Act--
    modelAccess.changeIndex(hostInstance1, reference, hostInstance3, modifiedIndex)

    // --Assert--
    assertEquals("Index is changed",
      initialIndex.get,
      hostInstance1.communicateWith.indexOf(hostInstance3)
    )
  }

}
