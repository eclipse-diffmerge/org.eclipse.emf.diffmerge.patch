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
package org.eclipse.emf.diffmerge.patch.ui.wizards

import org.eclipse.emf.diffmerge.patch.api.ModelPatchException
import org.eclipse.emf.diffmerge.patch.ui.utils.ModelpatchApplicationDTO
import org.eclipse.emf.diffmerge.patch.ui.wizards.pages.ModelpatchEDMWizardPage
import org.eclipse.emf.diffmerge.patch.ui.wizards.pages.ModelpatchFilteringWizardPage
import org.eclipse.emf.diffmerge.patch.ui.wizards.pages.ModelpatchLoadingPatchWizardPage
import org.eclipse.emf.diffmerge.patch.ui.wizards.pages.ModelpatchResultWizardPage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.jface.wizard.IWizardPage
import org.eclipse.jface.wizard.Wizard

class ModelpatchApplicationWizard extends Wizard {
  ModelpatchApplicationDTO dto

  ModelpatchLoadingPatchWizardPage patchLoadingPage
  ModelpatchFilteringWizardPage patchModificationPage
  ModelpatchResultWizardPage resultPage
  ModelpatchEDMWizardPage edmPage

  new(Resource originalModel) {
    setWindowTitle("Modelpatch Application")
    dto = new ModelpatchApplicationDTO(originalModel)
    patchLoadingPage = new ModelpatchLoadingPatchWizardPage(dto)
    patchModificationPage = new ModelpatchFilteringWizardPage(dto)
    resultPage = new ModelpatchResultWizardPage(dto)
    edmPage = new ModelpatchEDMWizardPage(dto)
  }

  override void addPages() {
    addPage(patchLoadingPage)
    addPage(patchModificationPage)
    addPage(resultPage)
    addPage(edmPage)
  }

  override canFinish() {
    return container.currentPage == resultPage || (container.currentPage == edmPage && edmPage.pageComplete)
  }

  override boolean performFinish() {
    if (container.currentPage == resultPage) {
      dto.saveModel
      return true
    } else if (container.currentPage == edmPage) {
      return true
    }
    return false
  }

  override performCancel() {
    if (container.currentPage == edmPage) {
      dto.undoEDMModifications
    }
    super.performCancel()
  }

  override IWizardPage getNextPage(IWizardPage currentPage) {
    if (currentPage == patchLoadingPage) {
      try {
        dto.loadPatch(patchLoadingPage.getSelectedFilePath())
      } catch (ModelPatchException e) {
        e.printStackTrace()
      }
    }

    return super.getNextPage(currentPage)
  }

}
