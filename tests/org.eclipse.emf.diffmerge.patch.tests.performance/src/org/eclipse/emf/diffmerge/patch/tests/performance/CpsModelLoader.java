/*******************************************************************************
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Abel Hegedus, Tamas Borbas, Peter Lunk (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.tests.performance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.ApplicationType;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.CyberPhysicalSystem;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.CyberPhysicalSystemFactory;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.HostInstance;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.HostType;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.State;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.StateMachine;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Transition;
import org.eclipse.viatra.examples.cps.generator.CPSPlanBuilder;
import org.eclipse.viatra.examples.cps.generator.dtos.AppClass;
import org.eclipse.viatra.examples.cps.generator.dtos.BuildableCPSConstraint;
import org.eclipse.viatra.examples.cps.generator.dtos.CPSFragment;
import org.eclipse.viatra.examples.cps.generator.dtos.CPSGeneratorInput;
import org.eclipse.viatra.examples.cps.generator.dtos.GeneratorPlan;
import org.eclipse.viatra.examples.cps.generator.dtos.HostClass;
import org.eclipse.viatra.examples.cps.generator.dtos.MinMaxData;
import org.eclipse.viatra.examples.cps.generator.dtos.Percentage;
import org.eclipse.viatra.examples.cps.generator.exceptions.ModelGeneratorException;
import org.eclipse.viatra.examples.cps.generator.interfaces.ICPSConstraints;
import org.eclipse.viatra.examples.cps.generator.utils.CPSModelBuilderUtil;
import org.eclipse.viatra.examples.cps.planexecutor.PlanExecutor;
import org.eclipse.viatra.examples.cps.traceability.CPSToDeployment;

public enum CpsModelLoader {

  INSTANCE;

  private Random random = new Random(1);

  public CyberPhysicalSystem generateCPSmodel(int modelSize, String modelName, String modelDir, int seed) {
    CPSModelBuilderUtil modelBuilderUtil = new CPSModelBuilderUtil();

    ////////////////////////////////////
    ////// EMF initialization phase
    ////////////////////////////////////

    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,
        new XMIResourceFactoryImpl());

    CPSToDeployment cps2dep = modelBuilderUtil.preparePersistedCPSModel(URI.createURI(modelDir + "/" + modelName), modelName);

    ////////////////////////////////////
    ////// Generation phase
    ////////////////////////////////////

    CPSGeneratorInput input = new CPSGeneratorInput(seed, getConstraints(modelSize), cps2dep.getCps());
    GeneratorPlan plan = CPSPlanBuilder.buildCharacteristicBasedPlan();
    PlanExecutor<CPSFragment, CPSGeneratorInput> generator = new PlanExecutor<CPSFragment, CPSGeneratorInput>();

    CPSFragment fragment = generator.process(plan, input);

    fragment.getEngine().dispose();
    return cps2dep.getCps();
  }

  public CyberPhysicalSystem loadModel(String location, ResourceSet rs) {
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,
        new XMIResourceFactoryImpl());

    URI locationURI = URI.createURI(location);

    Resource resource = rs.getResource(locationURI, true);
    return (CyberPhysicalSystem) resource.getContents().get(0);
  }

  public CyberPhysicalSystem modifyModel(CyberPhysicalSystem system) {
    CyberPhysicalSystem modified = EcoreUtil.copy(system);
    addRandomAppTypes(modified);
    modifyStateMachines(modified);
    modifyHosts(modified);

    Resource modifiedResource = system.eResource().getResourceSet()
        .createResource(URI.createURI("_modified.cyberphysicalsystem"));
    modifiedResource.getContents().add(modified);

    return modified;
  }

  private void addRandomAppTypes(CyberPhysicalSystem system) {
    int randomsize = random.nextInt(system.getAppTypes().size());
    for (int i = 0; i < randomsize; i++) {
      ApplicationType apptype = CyberPhysicalSystemFactory.eINSTANCE.createApplicationType();
      apptype.setIdentifier("AddedAppType" + System.nanoTime());
      apptype.setExeFileLocation("AddedLocation");
      apptype.setCps(system);
      system.getAppTypes().add(apptype);
    }
  }

  private void modifyHosts(CyberPhysicalSystem system) {
    for (HostType type : system.getHostTypes()) {
      // Modify host attribute
      if (random.nextInt(3) == 0) {
        type.setDefaultCpu(111111);
        type.setDefaultHdd(222222);
      }
      // Modify random host instance
      for (HostInstance instance : type.getInstances()) {
        if (random.nextInt(3) == 0) {
          instance.setNodeIp("MODIFIED_IP" + System.nanoTime());
        }
      }
      // Add random host instance
      if (random.nextInt(3) == 0) {
        HostInstance newInstance = CyberPhysicalSystemFactory.eINSTANCE.createHostInstance();
        newInstance.setAvailableCpu(0);
        newInstance.setAvailableHdd(0);
        newInstance.setAvailableRam(0);
        newInstance.setIdentifier("NEW_HOST_INSTANCE" + System.nanoTime());
        newInstance.setNodeIp("NEW_IP");
        newInstance.setTotalCpu(0);
        newInstance.setTotalHdd(0);
        newInstance.setTotalRam(0);
        type.getInstances().add(newInstance);
      }
    }
    // Add host type
    if (random.nextInt(3) == 0) {
      HostType newType = CyberPhysicalSystemFactory.eINSTANCE.createHostType();

      newType.setIdentifier("NEW_HOST_TYPE" + System.nanoTime());
      newType.setDefaultCpu(0);
      newType.setDefaultHdd(0);
      newType.setDefaultRam(0);
      newType.setCps(system);
      system.getHostTypes().add(newType);
    }

  }

  private void modifyStateMachines(CyberPhysicalSystem system) {
    for (StateMachine sm : getAllContentsOfType(system, StateMachine.class)) {
      for (Transition trans : getAllContentsOfType(sm, Transition.class)) {
        // Randomly change action
        if (random.nextInt(3) == 0) {
          trans.setAction("MODIFIED_ACTION" + System.nanoTime());
        }
        // Randomly add new states, and rearrange transitions
        if (random.nextInt(3) == 0) {
          State newState = CyberPhysicalSystemFactory.eINSTANCE.createState();
          newState.setIdentifier("State" + System.nanoTime());
          sm.getStates().add(newState);
          trans.setTargetState(newState);
        }
      }
      // randomly add new loop transitions
      for (State state : sm.getStates()) {
        if (random.nextInt(3) == 0) {
          Transition newTrans = CyberPhysicalSystemFactory.eINSTANCE.createTransition();
          newTrans.setAction("ACTION");
          newTrans.setIdentifier("Transition" + System.nanoTime());
          newTrans.setTargetState(state);
          state.getOutgoingTransitions().add(newTrans);
        }
      }
      // randomly change initial state
      if (random.nextInt(3) == 0) {
        sm.setInitial(sm.getStates().get(random.nextInt(sm.getStates().size())));
      }
    }

  }

  /**
   * Copied from org.eclipse.xtext.EcoreUtil2
   */
  @SuppressWarnings("unchecked")
  private static <T extends EObject> List<T> getAllContentsOfType(EObject ele, Class<T> type) {
    List<T> result = new ArrayList<T>();
    TreeIterator<EObject> allContents = ele.eAllContents();
    while (allContents.hasNext()) {
      EObject object = allContents.next();
      if (type.isAssignableFrom(object.getClass())) {
        result.add((T) object);
      }
    }
    return result;
  }


  protected ICPSConstraints getConstraints(int scale) {
    List<HostClass> classList = createHostClassList(scale);

    int signalCount = 143;
    BuildableCPSConstraint cons = new BuildableCPSConstraint("Statistics-based Case",
        new MinMaxData<Integer>(signalCount, signalCount), createAppClassList(scale, classList), classList);

    return cons;
  }

  private List<HostClass> createHostClassList(int scale) {
    List<HostClass> hostClasses = new ArrayList<>();

    // 1 for the empty, and scale for the host instances with allocated
    // application instances
    int instEmptyCount = scale * 22;
    int instAppContainerCount = scale * 4;
    int comCount = instAppContainerCount - 1;

    // TODO should we randomize the number of host communication for the
    // hosts without allocated applications
    int emptyHostCommunicationCount = scale * 2;

    Map<HostClass, Integer> emptyHostConnection = new HashMap<>();
    HostClass emptyHostClass = new HostClass("HC_empty", // name
        new MinMaxData<Integer>(1, 1), // Type
        new MinMaxData<Integer>(instEmptyCount, instEmptyCount), // Instance
        new MinMaxData<Integer>(emptyHostCommunicationCount, emptyHostCommunicationCount), // ComLines
        emptyHostConnection);
    hostClasses.add(emptyHostClass);

    List<HostClass> appContainerClasses = new ArrayList<>();
    for (int i = 0; i < scale; i++) {

      // The application container host instances of the same type will
      // form a complete graph of 4
      // when only taking the communicatesWith relation
      Map<HostClass, Integer> appContainerConnection = new HashMap<>();
      HostClass appContainerHostClass = new HostClass("HC_appContainer" + i, // name
          new MinMaxData<Integer>(1, 1), // Type
          new MinMaxData<Integer>(instAppContainerCount, instAppContainerCount), // Instance
          new MinMaxData<Integer>(comCount, comCount), // ComLines
          appContainerConnection);
      appContainerConnection.put(appContainerHostClass, 1);

      hostClasses.add(appContainerHostClass);
      appContainerClasses.add(appContainerHostClass);
    }

    // Communications:
    // App containers only communicate with each other, the empty hosts
    // might communicate with any instance
    emptyHostConnection.put(emptyHostClass, 1);
    for (HostClass appContainerClass : appContainerClasses) {
      emptyHostConnection.put(appContainerClass, 1);
    }

    return hostClasses;
  }

  private List<AppClass> createAppClassList(int scale, List<HostClass> hostClasses) {
    List<AppClass> appClasses = new ArrayList<>();

    double expectedValueOfTypes = scale * 52;

    // Every class will have 1 or 2 types, so that the expected value of the
    // appTypes will be the
    // expectedValueOfTypes using the formula below
    double appClassCount = 2 * expectedValueOfTypes / 3;

    // alloc ratios - allocate only to the second host type
    Map<HostClass, Integer> allocRatios = new HashMap<>();
    List<HostClass> hostClassesList = hostClasses;
    HostClass emptyHostClass = hostClassesList.get(0);

    // The first in the list is the empty host class, the instances of the
    // others should contain app instances
    for (HostClass hostClass : hostClasses) {
      if (hostClass.equals(emptyHostClass)) {
        allocRatios.put(hostClass, 0);
      } else {
        allocRatios.put(hostClass, 1);
      }
    }

    int appTypeMinCount = 1;
    int appTypeMaxCount = 2;

    // Each app type will have 1 instance to have an assignment between
    // AppType and HostInstance
    int appInstCount = 1;

    try {
      // Half of the app types will not have state machine, the other half
      // will have
      for (int i = 0; i < appClassCount / 2; i++) {
        appClasses.add(new AppClass("AC_withoutStateMachine" + i,
            new MinMaxData<Integer>(appTypeMinCount, appTypeMaxCount), // AppTypes
            new MinMaxData<Integer>(appInstCount, appInstCount), // AppInstances
            new MinMaxData<Integer>(0, 0), // States
            new MinMaxData<Integer>(0, 0), // Transitions
            new Percentage(100), // Alloc
            allocRatios, new Percentage(0), // Action
            new Percentage(0) // Send
        ));
      }
      for (int i = 0; i < appClassCount / 2; i++) {
        appClasses.add(new AppClass("AC_withStateMachine" + i,
            new MinMaxData<Integer>(appTypeMinCount, appTypeMaxCount), // AppTypes
            new MinMaxData<Integer>(appInstCount, appInstCount), // AppInstances
            new MinMaxData<Integer>(3, 3), // States
            new MinMaxData<Integer>(7, 8), // Transitions
            new Percentage(100), // Alloc
            allocRatios, new Percentage(50), // Action
            new Percentage(50) // Send
        ));
      }
    } catch (ModelGeneratorException e) {
      e.printStackTrace();
    }

    return appClasses;
  }

}
