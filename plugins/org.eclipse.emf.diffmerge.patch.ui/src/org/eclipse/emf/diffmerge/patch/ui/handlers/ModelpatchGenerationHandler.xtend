/*******************************************************************************
 * Copyright (c) 2016-2017, Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Peter Lunk (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.ui.handlers

import java.io.File
import org.eclipse.core.commands.AbstractHandler
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException
import org.eclipse.emf.diffmerge.patch.api.ModelPatchException
import org.eclipse.emf.diffmerge.patch.runtime.ModelPatchRecorder
import org.eclipse.emf.diffmerge.patch.ui.preferences.ModelPatchPreferenceProvider
import org.eclipse.emf.diffmerge.patch.ui.utils.DialogFactory
import org.eclipse.emf.diffmerge.patch.ui.utils.SerializerProvider
import org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection
import org.eclipse.emf.diffmerge.ui.setup.EMFDiffMergeEditorInput
import org.eclipse.jface.viewers.IStructuredSelection
import org.eclipse.swt.widgets.Shell
import org.eclipse.ui.IWorkbench
import org.eclipse.ui.handlers.HandlerUtil

class ModelpatchGenerationHandler extends AbstractHandler {
  public static val String MODELPATCH_GENERATION_ERROR_TITLE = "Model Patch Generation Error"
  extension ModelPatchPreferenceProvider = ModelPatchPreferenceProvider.INSTANCE
  extension ComparisonSelectionUtil util = new ComparisonSelectionUtil

  override Object execute(ExecutionEvent event) throws ExecutionException {
    val shell = HandlerUtil.getActiveShell(event)
    val workbench = HandlerUtil.getActiveWorkbenchWindow(event).workbench
    var IStructuredSelection selection = null

    try {
      selection = HandlerUtil.getCurrentSelection(event) as IStructuredSelection
    } catch (Exception ex) {
      ex.printStackTrace
    }

    val part = HandlerUtil.getActiveEditorInput(event)
    if (part instanceof EMFDiffMergeEditorInput) {
      val diffNode = part.compareResult
      val comparison = diffNode.actualComparison
      if (selection instanceof ComparisonSelection) {
        val diffs = selection.getDiffsToMerge(false, diffNode)
        if (!diffs.empty) {
          diffs.generatePatch(shell, workbench, selection)
        }
      } else {
        comparison.generatePatch(shell, workbench, selection)
      }
    }
    return null
  }

  public def Object generatePatch(Object diff, Shell shell, IWorkbench workbench, IStructuredSelection selection) {
    extension val DialogFactory factory = new DialogFactory(shell)
    val SerializerProvider serializerProvider = new SerializerProvider
    try {
      val serializer = serializerProvider.getSelectedSerializer(serializationType)
      val path = openSaveFileDialog(workbench, selection, serializer.preferredFileExtension)
      if (path !== null) {
        val modelPatchRecorder = new ModelPatchRecorder
        val generatedPatch = modelPatchRecorder.generateModelPatch(diff)
        val patchFile = path.location.toFile
        serializer.serialize(generatedPatch, patchFile)

        if (isFileGenerated(patchFile) == true) {
          openModelPatchEditor(workbench.activeWorkbenchWindow, path);
        } else {
          openErrorDialog(MODELPATCH_GENERATION_ERROR_TITLE, '''File cannot be created at the following path: «path»''')
        }
      } else {
        openInformationDialog("Model Patch Generation Information", "No file has been selected!")
      }
    } catch (ModelPatchException ex) {
      openErrorDialog(MODELPATCH_GENERATION_ERROR_TITLE, "Modelpatch generation finished with errors!", ex,
        "org.eclipse.emf.diffmerge.patch")
    } catch (ClassCastException ex) {
      openErrorDialog(MODELPATCH_GENERATION_ERROR_TITLE, "Patch generation only works from EMF Diff/Merge!")
    } catch (Exception ex) {
      ex.printStackTrace
      openErrorDialog(MODELPATCH_GENERATION_ERROR_TITLE, "Unknown error!", ex, "org.eclipse.emf.diffmerge.patch")
    }
    return null
  }

  def boolean isFileGenerated(File file) {
    return file.exists
  }

}