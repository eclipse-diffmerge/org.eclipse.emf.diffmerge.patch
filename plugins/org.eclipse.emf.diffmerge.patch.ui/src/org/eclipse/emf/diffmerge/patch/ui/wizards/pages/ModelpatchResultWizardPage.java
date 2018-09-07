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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.wb.swt.SWTResourceManager;

import org.eclipse.emf.diffmerge.patch.api.ModelPatchDiagnosticElement;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntry;
import org.eclipse.emf.diffmerge.patch.ui.utils.DefaultEntryLabelProvider;
import org.eclipse.emf.diffmerge.patch.ui.utils.EntryPropertyWrapper;
import org.eclipse.emf.diffmerge.patch.ui.utils.IEntryLabelProvider;
import org.eclipse.emf.diffmerge.patch.ui.utils.ModelpatchApplicationDTO;

public class ModelpatchResultWizardPage extends WizardPage {
  IEntryLabelProvider entryLabelProvider = new DefaultEntryLabelProvider();

  private Tree tblDiagnostics;
  private ModelpatchApplicationDTO dto;
  private StackLayout stack;
  private TreeViewer tvDiagnostics;
  private Text lblNoProblem;
  private Composite composite;
  private Text txtDescription;
  private Text txtRejects;
  private Composite cmpDiagnostics;

  /**
   * Create the dialog.
   *
   * @param dto
   */
  public ModelpatchResultWizardPage(ModelpatchApplicationDTO dto) {
    super("Patch application results");
    setTitle("Patch Application Resolution Result");
    this.dto = dto;
  }

  @Override
  public void setVisible(boolean visible) {
    if (visible) {
      dto.applyPatch();
      final List<ModelPatchDiagnosticElement> input = getTableInput();
      if (input.isEmpty()) {
        stack.topControl = lblNoProblem;
      } else {
        tvDiagnostics.setInput(input);
        stack.topControl = cmpDiagnostics;
      }
    }
    composite.layout();
    super.setVisible(visible);
  }

  private List<ModelPatchDiagnosticElement> getTableInput() {
    if (dto.patchApplication != null && dto.patchApplication.getDiagnostics() != null
        && dto.patchApplication.getDiagnostics().getDiagnosticElements() != null) {
      List<ModelPatchDiagnosticElement> elements = dto.patchApplication.getDiagnostics().getDiagnosticElements();
      return elements;
    }
    return new ArrayList<>();
  }

  /**
   * Create contents of the dialog.
   *
   * @param parent
   */
  @Override
  public void createControl(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);
    setControl(container);
    container.setLayout(new GridLayout(1, false));

    Label lblResult = new Label(container, SWT.NONE);
    lblResult.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
    lblResult.setText("Result");

    composite = new Composite(container, SWT.NONE);
    composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
    stack = new StackLayout();
    composite.setLayout(stack);

    lblNoProblem = new Text(composite, SWT.READ_ONLY | SWT.MULTI);
    lblNoProblem
        .setText("Patch application resolution finished successfully!\r\nPatch can be applied without any rejects.");

    cmpDiagnostics = new Composite(composite, SWT.NONE);
    GridLayout gl_cmpDiagnostics = new GridLayout(1, false);
    gl_cmpDiagnostics.marginWidth = 0;
    gl_cmpDiagnostics.marginHeight = 0;
    cmpDiagnostics.setLayout(gl_cmpDiagnostics);

    txtRejects = new Text(cmpDiagnostics, SWT.READ_ONLY | SWT.MULTI);
    txtRejects.setText(
        "Patch application resolution finished!\r\nThe following rejects have been found while resolving the patch:");
    txtRejects.setBounds(0, 0, 76, 21);

    tvDiagnostics = new TreeViewer(cmpDiagnostics,
        SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
    tblDiagnostics = tvDiagnostics.getTree();
    tblDiagnostics.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
    tblDiagnostics.setSize(564, 236);
    tblDiagnostics.setLinesVisible(true);
    tblDiagnostics.setHeaderVisible(true);

    TreeColumn tvcEntry = new TreeColumn(tblDiagnostics, SWT.NONE);
    tvcEntry.setWidth(150);
    tvcEntry.setText("Entry");

    TreeColumn tvcMessage = new TreeColumn(tblDiagnostics, SWT.NONE);
    tvcMessage.setWidth(400);
    tvcMessage.setText("Details");

    tvDiagnostics.setContentProvider(new ITreeContentProvider() {
      @Override
      public void dispose() {
      }

      @Override
      public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
      }

      @Override
      public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
      }

      @SuppressWarnings("rawtypes")
      @Override
      public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof ModelPatchDiagnosticElement) {
          ModelPatchDiagnosticElement diagElem = (ModelPatchDiagnosticElement) parentElement;
          ModelPatchEntry entry = diagElem.getProblematicEntry();
          return entryLabelProvider.getPropertyList(entry).toArray();
        }
        if (parentElement instanceof ArrayList) {
          return ((ArrayList) parentElement).toArray();
        }
        return new Object[0];
      }

      @Override
      public Object getParent(Object element) {
        return null;
      }

      @Override
      public boolean hasChildren(Object element) {
        Object[] childrens = getChildren(element);
        return childrens != null && childrens.length > 0;
      }

    });
    tvDiagnostics.setLabelProvider(new ITableLabelProvider() {
      @Override
      public void removeListener(ILabelProviderListener listener) {
      }

      @Override
      public boolean isLabelProperty(Object element, String property) {
        return false;
      }

      @Override
      public void dispose() {
      }

      @Override
      public void addListener(ILabelProviderListener listener) {
      }

      @Override
      public String getColumnText(Object element, int columnIndex) {
        if (element instanceof ModelPatchDiagnosticElement) {
          switch (columnIndex) {
          case 0:
            return entryLabelProvider.shortDescription(((ModelPatchDiagnosticElement) element).getProblematicEntry());
          case 1:
            return ((ModelPatchDiagnosticElement) element).getMessage();
          }
        } else if (element instanceof EntryPropertyWrapper) {
          switch (columnIndex) {
          case 0:
            return ((EntryPropertyWrapper) element).prop;
          case 1:
            return ((EntryPropertyWrapper) element).value;
          }
        }
        return "";
      }

      @Override
      public Image getColumnImage(Object element, int columnIndex) {
        return null;
      }
    });

    Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
    label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

    txtDescription = new Text(container, SWT.READ_ONLY | SWT.WRAP | SWT.MULTI);
    txtDescription.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    txtDescription.setText(
        "If you click Next, you can select changes from the resolved model which you want to merge into the target model.\r\nIf you choose Finish, all the possible changes will be merged directly into the target model.");
  }

  @Override
  public IWizardPage getPreviousPage() {
    return null;
  }
}
