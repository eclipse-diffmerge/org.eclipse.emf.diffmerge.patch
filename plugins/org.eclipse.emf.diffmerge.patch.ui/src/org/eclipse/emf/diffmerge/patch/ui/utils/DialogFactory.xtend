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
package org.eclipse.emf.diffmerge.patch.ui.utils

import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IResource
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IStatus
import org.eclipse.core.runtime.MultiStatus
import org.eclipse.core.runtime.Status
import org.eclipse.jface.dialogs.ErrorDialog
import org.eclipse.jface.dialogs.MessageDialog
import org.eclipse.jface.viewers.IStructuredSelection
import org.eclipse.jface.window.Window
import org.eclipse.jface.wizard.WizardDialog
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.FileDialog
import org.eclipse.swt.widgets.Shell
import org.eclipse.ui.IWorkbench
import org.eclipse.ui.IWorkbenchWindow
import org.eclipse.ui.PartInitException
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog
import org.eclipse.ui.dialogs.WizardNewFileCreationPage
import org.eclipse.ui.ide.IDE
import org.eclipse.ui.model.WorkbenchContentProvider
import org.eclipse.ui.model.WorkbenchLabelProvider
import org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard

class DialogFactory {
  private val Shell shell

  new(Shell shell) {
    this.shell = shell
  }

  public def openInformationDialog(String title, String message) {
    MessageDialog.openInformation(shell, title, message);
  }

  public def openFileDialog(String[] supportedFileExtensions) {
    return openFileDialog("File Selection", supportedFileExtensions)
  }

  public def openFileDialog(String title, String[] supportedFileExtensions) {
    (new FileDialog(shell, SWT.OPEN) => [
      it.text = title
      it.filterExtensions = supportedFileExtensions
    ]).open
  }

  public def openSaveFileDialog(IWorkbench workbench, IStructuredSelection selection, String fextension) {
    val wizard = new BasicNewFileResourceWizard() {
      public String path = null
      public IFile file = null
      private WizardNewFileCreationPage mainPage

      override addPages() {
        super.addPages()
        mainPage = (pages.get(0) as WizardNewFileCreationPage)
        mainPage.fileExtension = fextension
      }

      override performFinish() {
        file = mainPage.createNewFile();
        if (file == null) {
          return false
        }

        selectAndReveal(file)
        if (file.location != null) {
          path = file.location.toString
        }

        return true;
      }

    } => [
      it.windowTitle = "Modelpatch file"
    ]
    wizard.init(workbench, selection)
    val dialog = new WizardDialog(shell, wizard) => [
      it.blockOnOpen = true
    ]
    dialog.open()
    return wizard.file
  }

  public def openModelPatchEditor(IWorkbenchWindow window, IFile file) {
    try {
      if (window != null) {
        val page = window.getActivePage()
        if (page != null) {
          IDE.openEditor(page, file, true)
        }
      }
    } catch (PartInitException e) {
      openError(window.getShell(), "Modelpatch editor cannot be opened", e.getMessage(), e)
    }
  }

  public def openWorkspaceFileDialog() {
    return openWorkspaceFileDialog("File Selection")
  }

  public def openWorkspaceFileDialog(String title) {
    val dialog = new ElementTreeSelectionDialog(shell, new WorkbenchLabelProvider(), new WorkbenchContentProvider())

    dialog.title = title
    dialog.setInput(ResourcesPlugin.getWorkspace().getRoot())
    dialog.setAllowMultiple(false)

    if (dialog.open() == Window.OK) {
      return (dialog.firstResult as IResource).location.toOSString
    }
    return null
  }

  public def openErrorDialog(String dialogTitle, String message, String errorMessage, Throwable ex, String ID) {
    ErrorDialog.openError(shell, dialogTitle, message, createMultiStatus(errorMessage, ex, ID))
  }

  public def openErrorDialog(String dialogTitle, String message, Throwable ex, String ID) {
    ErrorDialog.openError(shell, dialogTitle, message, createMultiStatus(ex.getMessage(), ex, ID))
  }

  public def openErrorDialog(String dialogTitle, String message) {
    MessageDialog.openError(shell, dialogTitle, message)
  }

  /**
   * Copied from {@link org.eclipse.ui.internal.ide.DialogUtil#openError(Shell, String, String, PartInitException)}
   */
  private def void openError(Shell parent, String title, String message, PartInitException exception) {
    // Check for a nested CoreException
    var CoreException nestedException = null
    var status = exception.getStatus()
    if (status !== null && status.getException() instanceof CoreException) {
      nestedException = status.getException() as CoreException
    }
    if (nestedException !== null) {
      // Open an error dialog and include the extra
      // status information from the nested CoreException
      ErrorDialog.openError(parent, title, message, nestedException.getStatus())
    } else {
      // Open a regular error dialog since there is no
      // extra information to display. Don't use SWT.SHEET because
      // we don't know if the title contains important information.
      MessageDialog.openError(parent, title, message)
    }
  }

  private def MultiStatus createMultiStatus(String msg, Throwable t, String ID) {
    val childStatuses = <Status>newArrayList
    val stackTraces = t.getStackTrace()

    for (StackTraceElement stackTrace : stackTraces) {
      val status = new Status(IStatus.ERROR, ID, stackTrace.toString)
      childStatuses.add(status)
    }

    return new MultiStatus(ID, IStatus.ERROR, childStatuses.toArray(#[]), t.toString(), t)
  }
}
