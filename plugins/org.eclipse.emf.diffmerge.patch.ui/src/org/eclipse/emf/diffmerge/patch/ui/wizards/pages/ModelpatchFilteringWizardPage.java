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
package org.eclipse.emf.diffmerge.patch.ui.wizards.pages;

import java.util.ArrayList;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

import org.eclipse.emf.diffmerge.patch.api.ComplexMPEFilterType;
import org.eclipse.emf.diffmerge.patch.api.IModelPatchEntryFilter;
import org.eclipse.emf.diffmerge.patch.api.filters.ComplexEntryFilter;
import org.eclipse.emf.diffmerge.patch.ui.dialogs.ModelpatchEntryFilterCreationDialog;
import org.eclipse.emf.diffmerge.patch.ui.utils.DescriptiveEntryLabelProvider;
import org.eclipse.emf.diffmerge.patch.ui.utils.ModelPatchContentProvider;
import org.eclipse.emf.diffmerge.patch.ui.utils.ModelPatchEntryFilterContentProvider;
import org.eclipse.emf.diffmerge.patch.ui.utils.ModelPatchEntryFilterLabelProvider;
import org.eclipse.emf.diffmerge.patch.ui.utils.ModelPatchLabelProvider;
import org.eclipse.emf.diffmerge.patch.ui.utils.ModelpatchApplicationDTO;

public class ModelpatchFilteringWizardPage extends WizardPage {
  public static final String[] SUPPORTED_PATCH_FILE_EXTENSIONS = { "*.modelpatch" };
  public static final String ID = "org.eclipse.emf.diffmerge.patch";

  // Data fields
  private DataBindingContext m_bindingContext;
  private ModelpatchApplicationDTO dto;
  private java.util.List<IModelPatchEntryFilter> filters = new ArrayList<>();
  private boolean isFiltered = false;

  // UI fields
  private Text txtPatchFilePath;

  private TreeViewer tvParsedPatch;
  private Button btnReversePatch;
  private Button btnFiltering;
  private Button btnAdd;
  private Button btnRemove;
  private List lFilters;

  /**
   * Create the dialog.
   * 
   * @param patch
   *
   * @param parentShell
   */
  public ModelpatchFilteringWizardPage(ModelpatchApplicationDTO dto) {
    super("Modify patch");
    this.setTitle("Modify Patch");
    this.dto = dto;
  }

  /**
   * Create contents of the dialog.
   *
   * @param parent
   */
  public void createControl(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);
    setControl(container);
    container.setLayout(new GridLayout(1, false));

    Composite compFile = new Composite(container, SWT.NONE);
    compFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    GridLayout gl_compFile = new GridLayout(2, false);
    gl_compFile.marginWidth = 0;
    gl_compFile.marginHeight = 0;
    compFile.setLayout(gl_compFile);

    CLabel lblPatchFile = new CLabel(compFile, SWT.NONE);
    lblPatchFile.setBounds(0, 0, 61, 21);
    lblPatchFile.setText("Patch file:");

    txtPatchFilePath = new Text(compFile, SWT.BORDER);
    txtPatchFilePath.setEditable(false);
    txtPatchFilePath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    txtPatchFilePath.setBounds(0, 0, 76, 21);

    btnReversePatch = new Button(container, SWT.CHECK);
    btnReversePatch.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        reverse = btnReversePatch.getSelection();
        refreshPatchViewer();
      }
    });
    btnReversePatch.setText("Reverse");

    btnFiltering = new Button(container, SWT.CHECK);
    btnFiltering.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        isFiltered = ((Button) btnFiltering).getSelection();
        refreshPatchViewer();
      }
    });
    btnFiltering.setText("Filtering");

    Composite compFiltering = new Composite(container, SWT.NONE);
    compFiltering.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    GridLayout gl_compFiltering = new GridLayout(2, false);
    gl_compFiltering.marginWidth = 0;
    gl_compFiltering.marginHeight = 0;
    compFiltering.setLayout(gl_compFiltering);

    final ListViewer lvFilters = new ListViewer(compFiltering, SWT.BORDER | SWT.V_SCROLL);
    lFilters = lvFilters.getList();
    lFilters.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
    lvFilters.setContentProvider(new ModelPatchEntryFilterContentProvider());
    lvFilters.setLabelProvider(new ModelPatchEntryFilterLabelProvider());
    lvFilters.setInput(filters);

    btnAdd = new Button(compFiltering, SWT.NONE);
    btnAdd.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        ModelpatchEntryFilterCreationDialog dialog = new ModelpatchEntryFilterCreationDialog(getShell());
        dialog.setBlockOnOpen(true);
        dialog.setTitle("Add filter");
        dialog.open();
        IModelPatchEntryFilter filter = dialog.getCreatedFilter();
        if (filter != null) {
          filters.add(filter);
          lvFilters.refresh();
          refreshPatchViewer();
        }
      }
    });
    btnAdd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    btnAdd.setText("Add");

    btnRemove = new Button(compFiltering, SWT.NONE);
    btnRemove.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        for (Object selectedElement : lvFilters.getStructuredSelection().toList()) {
          filters.remove(selectedElement);
        }
        lvFilters.refresh();
        refreshPatchViewer();
      }
    });
    btnRemove.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
    btnRemove.setText("Remove");

    lblSequenceOfPatch = new Label(container, SWT.NONE);
    lblSequenceOfPatch.setText("Sequence of patch entries:");

    tvParsedPatch = new TreeViewer(container, SWT.BORDER);
    tvParsedPatch.setContentProvider(new ModelPatchContentProvider());
    tvParsedPatch.setLabelProvider(new ModelPatchLabelProvider(new DescriptiveEntryLabelProvider()));
    Tree treeParsedPatch = tvParsedPatch.getTree();
    treeParsedPatch.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
    treeParsedPatch.setVisible(true);

    m_bindingContext = initDataBindings();
  }

  private void refreshPatchViewer() {
    dto.modifyPatch(btnReversePatch.getSelection(), getFilter());
    tvParsedPatch.setInput(dto.modifiedModelPatch);
    tvParsedPatch.refresh();
  }

  private boolean direct;

  public boolean isDirect() {
    return direct;
  }

  private boolean reverse;
  private Label lblSequenceOfPatch;

  public boolean isReverse() {
    return reverse;
  }

  public IModelPatchEntryFilter getFilter() {
    if (isFiltered) {
      if (filters.size() > 1) {
        return new ComplexEntryFilter(ComplexMPEFilterType.OR, filters.toArray(new IModelPatchEntryFilter[] {}));
      } else if (filters.size() == 1) {
        return filters.get(0);
      }
    }
    return null;
  }

  public void setPatchPath(String patchPath) {
    txtPatchFilePath.setText(patchPath);
  }

  @Override
  public void setVisible(boolean visible) {
    if (visible) {
      refreshPatchViewer();
      setPatchPath(org.eclipse.core.runtime.Path.fromOSString(dto.patchPath).lastSegment());
    }
    super.setVisible(visible);
  }

  protected DataBindingContext initDataBindings() {
    DataBindingContext bindingContext = new DataBindingContext();
    IObservableValue observeSelectionBtnFilteringObserveWidget = WidgetProperties.selection().observe(btnFiltering);
    //
    IObservableValue observeEnabledBtnAddObserveWidget = WidgetProperties.enabled().observe(btnAdd);
    bindingContext.bindValue(observeEnabledBtnAddObserveWidget, observeSelectionBtnFilteringObserveWidget, null, null);
    //
    IObservableValue observeEnabledBtnRemoveObserveWidget = WidgetProperties.enabled().observe(btnRemove);
    bindingContext.bindValue(observeEnabledBtnRemoveObserveWidget, observeSelectionBtnFilteringObserveWidget, null,
        null);
    //
    IObservableValue observeEnabledLFiltersObserveWidget = WidgetProperties.enabled().observe(lFilters);
    bindingContext.bindValue(observeEnabledLFiltersObserveWidget, observeSelectionBtnFilteringObserveWidget, null,
        null);
    //
    return bindingContext;
  }

  @Override
  public void dispose() {
    m_bindingContext.dispose();
    super.dispose();
  }
}
