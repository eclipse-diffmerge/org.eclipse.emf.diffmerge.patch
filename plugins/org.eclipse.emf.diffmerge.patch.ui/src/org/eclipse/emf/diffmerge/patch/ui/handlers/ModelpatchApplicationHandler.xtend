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

import org.eclipse.emf.diffmerge.patch.ui.utils.DialogFactory
import org.eclipse.emf.diffmerge.patch.ui.wizards.ModelpatchApplicationWizard
import org.eclipse.core.commands.AbstractHandler
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.jface.viewers.IStructuredSelection
import org.eclipse.jface.wizard.WizardDialog
import org.eclipse.swt.widgets.Shell
import org.eclipse.ui.handlers.HandlerUtil

class ModelpatchApplicationHandler extends AbstractHandler {
  public static val ID = "org.eclipse.emf.diffmerge.patch"

  Shell shell

  override Object execute(ExecutionEvent event) throws ExecutionException {
    try {
      shell = HandlerUtil.getActiveShell(event)
      (HandlerUtil.getCurrentSelection(event) as IStructuredSelection).executePatch
    } catch(Exception ex) {
      val factory = new DialogFactory(shell)
      factory.openErrorDialog("Modelpatch Application Problem", "Patch cannot be applied!", ex, ID)
      ex.printStackTrace
    }
    return null
  }


  private dispatch def void executePatch(IStructuredSelection strucSelection) {
    strucSelection.firstElement.executePatch
  }
  private dispatch def void executePatch(EObject eobj) {
    eobj.eResource.executePatch
  }
  private dispatch def void executePatch(Resource resource) {
    // Show ModelpatchApplicationWizard for patch file and filters
    val wizard = new WizardDialog(shell, new ModelpatchApplicationWizard(resource))
    wizard.open
  }
}
