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
package org.eclipse.emf.diffmerge.patch.ui.utils

import com.google.common.io.Files
import java.io.File
import org.apache.log4j.Logger
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.emf.common.command.AbstractCommand
import org.eclipse.emf.common.command.Command
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.diffmerge.api.IDiffPolicy
import org.eclipse.emf.diffmerge.api.IMatchPolicy
import org.eclipse.emf.diffmerge.api.IMergePolicy
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope
import org.eclipse.emf.diffmerge.diffdata.EComparison
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl
import org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy
import org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy
import org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope
import org.eclipse.emf.diffmerge.patch.api.IModelPatchEntryFilter
import org.eclipse.emf.diffmerge.patch.api.ModelPatch
import org.eclipse.emf.diffmerge.patch.api.ModelPatchFilterApplier
import org.eclipse.emf.diffmerge.patch.api.ModelPatchReverser
import org.eclipse.emf.diffmerge.patch.api.PatchApplication
import org.eclipse.emf.diffmerge.patch.runtime.EMFModelPatchApplier
import org.eclipse.emf.diffmerge.patch.runtime.identifier.BaseIndexEObjectLocator
import org.eclipse.emf.diffmerge.patch.runtime.modelaccess.EMFModelAccess
import org.eclipse.emf.diffmerge.patch.runtime.modelaccess.ModelAccessProvider
import org.eclipse.emf.diffmerge.patch.ui.preferences.ModelPatchPreferenceProvider
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.viatra.query.runtime.base.api.BaseIndexOptions
import org.eclipse.viatra.query.runtime.base.api.IndexingLevel
import org.eclipse.viatra.query.runtime.base.api.ViatraBaseFactory

import static com.google.common.base.Preconditions.*

class ModelpatchApplicationDTO {
  private extension ModelPatchFilterApplier applier = new ModelPatchFilterApplier
  private extension ModelAccessProvider provider = new ModelAccessProvider
  private extension SerializerProvider serializerProvider = new SerializerProvider
  private extension ModelPatchPreferenceProvider = ModelPatchPreferenceProvider.INSTANCE

  public Resource originalModel
  public Resource modifiedModel

  public String patchPath
  public ModelPatch originalModelPatch
  public ModelPatch modifiedModelPatch

  public PatchApplication patchApplication

  public EMFDiffNode diffNode
  Command lastCommand
  EditingDomain editingDomain

  new(Resource originalModel) {
    checkNotNull(originalModel, "Model cannot be null.")
    this.originalModel = originalModel
  }

  def boolean loadPatch(String patchPath) {
    if (!patchPath.isNullOrEmpty) {
      try {
        val file = new File(patchPath)
        val serializerByFileExtension = serializerProvider.getSerializerByFileExtension(Files.getFileExtension(patchPath))
        val serializer = serializerByFileExtension ?: serializationType.selectedSerializer
        originalModelPatch = (serializer).load(file)
        modifiedModelPatch = originalModelPatch
      } catch (Exception ex) {
        originalModelPatch = null
        this.patchPath = patchPath
        throw ex
      }
    } else {
      originalModelPatch = null
      return false
    }
    this.patchPath = patchPath
    return true
  }

  def ModelPatch modifyPatch(boolean isReverse, IModelPatchEntryFilter... filters) {
    if (isReverse) {
      modifiedModelPatch = ModelPatchReverser.INSTANCE.reverse(originalModelPatch)
    } else {
      modifiedModelPatch = originalModelPatch
    }
    if (filters !== null) {
      for (filter : filters) {
        if(filter !== null) modifiedModelPatch = modifiedModelPatch.applyFilter(filter)
      }
    }
    return modifiedModelPatch
  }

  def PatchApplication applyPatch() {
    modifiedModel = originalModel.createCopy('''Resolved Model''')
    patchApplication = modifiedModel.applyPatch
    return patchApplication
  }

  def void saveModel() {
    val patchCommand = new AbstractCommand() {
      public ModelPatch patch
      public ModelPatch reversePatch
      public Resource model

      override execute() {
        model.executePatchApplication(patch)
      }

      override redo() {
        model.executePatchApplication(patch)
      }

      override undo() {
        model.executePatchApplication(reversePatch)
      }

      override protected prepare() {
        return true
      }

      private def void executePatchApplication(Resource resource, ModelPatch patch) {
        val patchApplier = preparePatchApplier(resource.resourceSet)
        patchApplication = patchApplier.apply(patch, resource.resourceSet)
      }
    }
    patchCommand.model = originalModel
    patchCommand.patch = modifiedModelPatch
    patchCommand.reversePatch = ModelPatchReverser.INSTANCE.reverse(modifiedModelPatch)
    AdapterFactoryEditingDomain.getEditingDomainFor(originalModel.contents.get(0)).commandStack.execute(patchCommand)
  }

  def EMFDiffNode prepareDiffNode() {
    val IEditableModelScope targetScope = new FragmentedModelScope(modifiedModel, true)
    editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(originalModel.contents.get(0))
    lastCommand = editingDomain.commandStack.undoCommand
    val IEditableModelScope referenceScope = new FragmentedModelScope(originalModel, false)

    // Compute comparison
    val IProgressMonitor aProgressMonitor = new NullProgressMonitor()
    val IMergePolicy aMergePolicy = new DefaultMergePolicy()
    val IDiffPolicy aDiffPolicy = new DefaultDiffPolicy()
    val IMatchPolicy aMatchPolicy = new DefaultMatchPolicy()
    val comparison = new EComparisonImpl(targetScope, referenceScope)
    comparison.compute(aMatchPolicy, aDiffPolicy, aMergePolicy, aProgressMonitor)

    // Turn comparison into an input for viewers
    diffNode = new EMFDiffNode(comparison as EComparison, editingDomain, false, true) => [
      it.setEditable(false, true) // Lock the tmp model in EMF Diff/Merge
    ]
    return diffNode
  }

  def void undoEDMModifications() {
    if (lastCommand === null) {
      while (lastCommand != editingDomain.commandStack.undoCommand && editingDomain.commandStack.canUndo) {
        editingDomain.commandStack.undo
      }
    } else {
      while (editingDomain.commandStack.canUndo) {
        editingDomain.commandStack.undo
      }
    }
  }

  private def PatchApplication applyPatch(Resource resource) {

    val patchApplier = preparePatchApplier(resource.resourceSet)
    return patchApplier.apply(modifiedModelPatch, resource.resourceSet)
  }

  def preparePatchApplier(ResourceSet resourceSet) {
    val EMFModelAccess access = modelAccessType.getSelectedModelAccess(resourceSet)

    val patchApplier = new EMFModelPatchApplier(access)
    val navigationHelper = ViatraBaseFactory.getInstance().createNavigationHelper(resourceSet,
      new BaseIndexOptions(false, IndexingLevel.FULL), Logger.getLogger(class));
    patchApplier.locator.EObjectLocator = new BaseIndexEObjectLocator(navigationHelper)
    patchApplier
  }

  private def Resource createCopy(Resource copiable, String copyPath) {
    val copyOfOriginalRS = new ResourceSetImpl
    val copyRes = copyOfOriginalRS.createResource(URI.createURI(copyPath))
    return copiable.copyTo(copyRes)
  }

  private def Resource copyTo(Resource copiable, Resource target) {
    val copier = new EcoreUtil.Copier
    if (target.contents !== null && target.contents.size > 0) {
      target.contents.clear
    }
    target.contents.addAll(copier.copyAll(copiable.contents))
    copier.copyReferences
    return target
  }
}
