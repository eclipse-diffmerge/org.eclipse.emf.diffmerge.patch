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
package org.eclipse.emf.diffmerge.patch.ui.editors;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.diffmerge.patch.api.IModelPatchEntryFilter;
import org.eclipse.emf.diffmerge.patch.api.ModelPatch;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchException;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchFilterApplier;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchReverser;
import org.eclipse.emf.diffmerge.patch.serializer.IModelPatchSerializer;
import org.eclipse.emf.diffmerge.patch.ui.dialogs.ModelpatchEntryFilterCreationDialog;
import org.eclipse.emf.diffmerge.patch.ui.preferences.ModelPatchPreferenceProvider;
import org.eclipse.emf.diffmerge.patch.ui.utils.DescriptiveEntryLabelProvider;
import org.eclipse.emf.diffmerge.patch.ui.utils.DialogFactory;
import org.eclipse.emf.diffmerge.patch.ui.utils.ModelPatchContentProvider;
import org.eclipse.emf.diffmerge.patch.ui.utils.ModelPatchEntryFilterContentProvider;
import org.eclipse.emf.diffmerge.patch.ui.utils.ModelPatchEntryFilterLabelProvider;
import org.eclipse.emf.diffmerge.patch.ui.utils.ModelPatchLabelProvider;
import org.eclipse.emf.diffmerge.patch.ui.utils.SerializerProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;

public class ModelPatchEditorPart extends EditorPart {
  private SerializerProvider serializerProvider = new SerializerProvider();
  private ModelPatchFilterApplier filterApplier = new ModelPatchFilterApplier();
  private IFile file;
  public ModelPatch originalModelPatch;
  public ModelPatch modifiedModelPatch;
  public java.util.List<IModelPatchEntryFilter> filters = new ArrayList<>();
  public DialogFactory dialFactory;

  public static final String ID = "org.eclipse.emf.diffmerge.patch.gui.editors.ModelPatchEditorPart"; //$NON-NLS-1$
  private Button btnReversePatch;
  private List lFilters;
  private Button btnAdd;
  private Button btnRemove;
  private Label lblSequenceOfPatch;
  private TreeViewer tvParsedPatch;
  private Label lblFilters;

  public ModelPatchEditorPart() {
  }

  /**
   * Create contents of the editor part.
   *
   * @param parent
   */
  @Override
  public void createPartControl(Composite parent) {
    Composite container = new Composite(parent, SWT.NONE);
    container.setLayout(new GridLayout(1, false));
    dialFactory = new DialogFactory(getSite().getShell());

    Composite compFile = new Composite(container, SWT.NONE);
    compFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    GridLayout gl_compFile = new GridLayout(2, false);
    gl_compFile.marginWidth = 0;
    gl_compFile.marginHeight = 0;
    compFile.setLayout(gl_compFile);

    lblSequenceOfPatch = new Label(container, SWT.NONE);
    lblSequenceOfPatch.setText("Sequence of patch entries:");

    tvParsedPatch = new TreeViewer(container, SWT.BORDER);
    tvParsedPatch.setContentProvider(new ModelPatchContentProvider());
    tvParsedPatch.setLabelProvider(new ModelPatchLabelProvider(new DescriptiveEntryLabelProvider()));
    Tree treeParsedPatch = tvParsedPatch.getTree();
    treeParsedPatch.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
    treeParsedPatch.setVisible(true);

    btnReversePatch = new Button(container, SWT.CHECK);
    btnReversePatch.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        refreshPatchViewer();
      }
    });
    btnReversePatch.setText("Reverse");

    lblFilters = new Label(container, SWT.NONE);
    lblFilters.setText("Filters:");

    Composite compFiltering = new Composite(container, SWT.NONE);
    compFiltering.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
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
        ModelpatchEntryFilterCreationDialog dialog = new ModelpatchEntryFilterCreationDialog(getSite().getShell());
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

    refreshPatchViewer();
  }

  private ModelPatch modifyPatch(boolean isReverse) {
    if (isReverse) {
      try {
        modifiedModelPatch = ModelPatchReverser.INSTANCE.reverse(originalModelPatch);
      } catch (ModelPatchException e) {
        e.printStackTrace();
      }
    } else {
      modifiedModelPatch = originalModelPatch;
    }
    for (IModelPatchEntryFilter filter : filters) {
      if (filter != null)
        modifiedModelPatch = filterApplier.applyFilter(modifiedModelPatch, filter);
    }
    return modifiedModelPatch;
  }

  private void refreshPatchViewer() {
    modifyPatch(btnReversePatch.getSelection());
    tvParsedPatch.setInput(modifiedModelPatch);
    tvParsedPatch.refresh();
    firePropertyChange(PROP_DIRTY);
  }

  @Override
  public void setFocus() {
    // Set the focus
  }

  @Override
  public void doSave(IProgressMonitor monitor) {
    monitor.beginTask("Save model patch: " + file.getName(), 1);
    try {
      IModelPatchSerializer serializer = getSerializer();
      serializer.serialize(modifiedModelPatch, new File(file.getLocation().toOSString()));
      refreshAfterSave();
    } catch (ModelPatchException e) {
      new RuntimeException("We are unable to save the modifications!", new Throwable(e));
    }
    monitor.done();
  }

  private IModelPatchSerializer getSerializer() {
    IModelPatchSerializer serializer = serializerProvider.getSerializerByFileExtension(file.getFileExtension());
    if(serializer == null) {
      String pref = ModelPatchPreferenceProvider.INSTANCE.getSerializationType();
      serializer = serializerProvider.getSelectedSerializer(pref);
    }
    return serializer;
  }

  @Override
  public void doSaveAs() {
    try {
      IWorkbench workbench = getSite().getWorkbenchWindow().getWorkbench();
      String pref = ModelPatchPreferenceProvider.INSTANCE.getSerializationType();
      IModelPatchSerializer serializer = serializerProvider.getSelectedSerializer(pref);
      IFile newFile = dialFactory.openSaveFileDialog(workbench,
          new StructuredSelection(ResourcesPlugin.getWorkspace().getRoot().getFile(file.getFullPath())),
          serializer.getPreferredFileExtension());
      if (newFile != null) {
        serializer.serialize(modifiedModelPatch, newFile.getLocation().toFile());
        file = newFile;
        setPartName("ModelPatch: " + file.getName());
        refreshAfterSave();
      }
    } catch (ModelPatchException e) {
      new RuntimeException("We are unable to save the new file!", new Throwable(e));
    }
  }

  private void refreshAfterSave() {
    originalModelPatch = modifiedModelPatch;
    filters.clear();
    btnReversePatch.setSelection(false);
    refreshPatchViewer();
  }

  @Override
  public void init(IEditorSite site, IEditorInput input) throws PartInitException {
    if (!(input instanceof FileEditorInput)) {
      throw new PartInitException("Wrong input!");
    }
    file = ((FileEditorInput) input).getFile();
    try {
      IModelPatchSerializer serializer = getSerializer();
      originalModelPatch = serializer.load(new File(file.getLocation().toOSString()));
      modifiedModelPatch = originalModelPatch;
    } catch (ModelPatchException e) {
      throw new PartInitException(e.getMessage(), new Throwable(e));
    }
    this.setInput(input);
    this.setSite(site);
    setPartName("ModelPatch: " + file.getName());
  }

  @Override
  public boolean isDirty() {
    if (originalModelPatch == null) {
      return false;
    }
    return !originalModelPatch.equals(modifiedModelPatch);
  }

  @Override
  public boolean isSaveAsAllowed() {
    return true;
  }

  @Override
  public void dispose() {
    super.dispose();
    filterApplier = new ModelPatchFilterApplier();
    file = null;
    originalModelPatch = null;
    modifiedModelPatch = null;
    filters = null;
  }

}
