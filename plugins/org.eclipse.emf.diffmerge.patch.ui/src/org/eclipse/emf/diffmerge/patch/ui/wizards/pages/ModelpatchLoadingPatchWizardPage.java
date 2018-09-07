/*******************************************************************************
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Abel Hegedus, Tamas Borbas, Peter Lunk (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.ui.wizards.pages;

import org.eclipse.emf.diffmerge.patch.ui.utils.DialogFactory;
import org.eclipse.emf.diffmerge.patch.ui.utils.ModelpatchApplicationDTO;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ModelpatchLoadingPatchWizardPage extends WizardPage {
  private ModelpatchApplicationDTO dto;
  private Text txtPatchFilePath;
  private String filePath;

  /**
   * Create the wizard.
   */
  public ModelpatchLoadingPatchWizardPage(ModelpatchApplicationDTO dto) {
    super("Load Patch File");
    setTitle("Load Patch File");
    setDescription("Select the applicable patch file.");
    this.dto = dto;
  }

  /**
   * Create contents of the wizard.
   * 
   * @param parent
   */
  public void createControl(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);
    final DialogFactory dialogFactory = new DialogFactory(getShell());

    setControl(container);
    container.setLayout(new GridLayout(4, false));

    Label lblPatchFile = new Label(container, SWT.NONE);
    lblPatchFile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
    lblPatchFile.setText("Patch file:");

    txtPatchFilePath = new Text(container, SWT.BORDER);
    txtPatchFilePath.setEditable(false);
    txtPatchFilePath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

    Button btnBrowseWorkspace = new Button(container, SWT.NONE);
    btnBrowseWorkspace.setText("Browse Workspace...");
    btnBrowseWorkspace.addSelectionListener(new SelectionListener() {

      @Override
      public void widgetSelected(SelectionEvent e) {
        String path = dialogFactory.openWorkspaceFileDialog("Patch File Selection");
        if (path != null) {
          txtPatchFilePath.setText(path);
        } else {
          txtPatchFilePath.setText("");
        }
        filePath = path;
        setPageComplete(checkPatchFile(path));
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
      }
    });

    Button btnBrowseFileSystem = new Button(container, SWT.NONE);
    btnBrowseFileSystem.setText("Browse File System...");

    btnBrowseFileSystem.addSelectionListener(new SelectionListener() {

      @Override
      public void widgetSelected(SelectionEvent e) {
        String path = dialogFactory.openFileDialog("Patch File Selection", new String[] { "modelpatch" });
        if (path != null) {
          txtPatchFilePath.setText(path);
        } else {
          txtPatchFilePath.setText("");
        }
        filePath = path;
        setPageComplete(checkPatchFile(path));
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
      }
    });
    setPageComplete(false);
  }

  public String getSelectedFilePath() {
    return filePath;
  }

  private boolean checkPatchFile(String path) {
    boolean loadedSuccessfully = false;
    try {
      loadedSuccessfully = dto.loadPatch(path);
      setErrorMessage(null);
    } catch (Exception ex) {
      setErrorMessage(ex.getMessage());
    }
    return loadedSuccessfully;
  }

}
