/*******************************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *      Abel Hegedus, Tamas Borbas, Balazs Grill, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.ui.dialogs;

import org.eclipse.emf.diffmerge.patch.api.ModelPatch;
import org.eclipse.emf.diffmerge.patch.ui.utils.DescriptiveEntryLabelProvider;
import org.eclipse.emf.diffmerge.patch.ui.utils.ModelPatchContentProvider;
import org.eclipse.emf.diffmerge.patch.ui.utils.ModelPatchLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.wb.swt.SWTResourceManager;

public class ModelpatchViewerDialog extends Dialog {
  private ModelPatch patch;
  private String path;
  private TreeViewer tvParsedPatch;

  /**
   * Create the dialog.
   * 
   * @param parentShell
   */
  public ModelpatchViewerDialog(Shell parentShell, ModelPatch patch, String path) {
    super(parentShell);
    setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.APPLICATION_MODAL);
    this.patch = patch;
    this.path = path;
  }

  private String title;
  private Text txtPath;

  public void setTitle(String title) {
    this.title = title;
    if (getShell() == null) {
      configureShell(createShell());
    } else {
      getShell().setText(title);
    }
  }

  @Override
  protected void configureShell(Shell shell) {
    super.configureShell(shell);
    shell.setText(title);
  }

  /**
   * Create contents of the dialog.
   * 
   * @param parent
   */
  @Override
  protected Control createDialogArea(Composite parent) {
    getShell().setMinimumSize(getInitialSize());
    Composite container = (Composite) super.createDialogArea(parent);

    Composite composite = new Composite(container, SWT.NONE);
    composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    GridLayout gl_composite = new GridLayout(2, false);
    gl_composite.marginHeight = 0;
    gl_composite.marginWidth = 0;
    composite.setLayout(gl_composite);

    Label lblPath = new Label(composite, SWT.WRAP);
    lblPath.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
    lblPath.setSize(92, 15);
    lblPath.setText("Path: ");

    txtPath = new Text(composite, SWT.BORDER);
    txtPath.setEditable(false);
    txtPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    txtPath.setText(path);

    Label lblEntries = new Label(container, SWT.NONE);
    lblEntries.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
    lblEntries.setText("Entries:");

    tvParsedPatch = new TreeViewer(container, SWT.BORDER);
    Tree treeParsedPatch = tvParsedPatch.getTree();
    treeParsedPatch.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
    treeParsedPatch.setVisible(true);
    tvParsedPatch.setContentProvider(new ModelPatchContentProvider());
    tvParsedPatch.setLabelProvider(new ModelPatchLabelProvider(new DescriptiveEntryLabelProvider()));
    tvParsedPatch.setInput(patch);

    return container;
  }

  /**
   * Create contents of the button bar.
   * 
   * @param parent
   */
  @Override
  protected void createButtonsForButtonBar(Composite parent) {
    createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
  }

  /**
   * Return the initial size of the dialog.
   */
  @Override
  protected Point getInitialSize() {
    return new Point(450, 300);
  }

  @Override
  public boolean close() {
    SWTResourceManager.dispose();
    return super.close();
  }

}
