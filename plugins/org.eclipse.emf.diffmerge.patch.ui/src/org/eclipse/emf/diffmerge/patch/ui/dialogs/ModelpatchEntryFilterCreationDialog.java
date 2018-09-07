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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.diffmerge.patch.api.ChangeDirection;
import org.eclipse.emf.diffmerge.patch.api.EntryType;
import org.eclipse.emf.diffmerge.patch.api.IModelPatchEntryFilter;
import org.eclipse.emf.diffmerge.patch.api.filters.EntryDirectionFilter;
import org.eclipse.emf.diffmerge.patch.api.filters.EntryTypeFilter;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ModelpatchEntryFilterCreationDialog extends Dialog {
  @SuppressWarnings("unused")
  private DataBindingContext m_bindingContext;
  IModelPatchEntryFilter filter = null;
  private Label lblDirection;
  private Label lblFilterType;
  private Label lblEntryType;
  private Combo cFilterType;
  private ModelpatchEntryFilterType filterType = ModelpatchEntryFilterType.Direction;
  private boolean add = true;
  private boolean remove = false;
  private boolean attribute = true;
  private boolean element = false;
  private boolean reference = false;

  /**
   * Create the dialog.
   * 
   * @param parentShell
   */
  public ModelpatchEntryFilterCreationDialog(Shell parentShell) {
    super(parentShell);
    setShellStyle(SWT.TITLE | SWT.APPLICATION_MODAL | SWT.RESIZE);
  }

  private String title;

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
  protected Control createDialogArea(Composite parent) {Composite container = (Composite) super.createDialogArea(parent);

    Composite composite_1 = new Composite(container, SWT.NONE);
    composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
    composite_1.setLayout(new GridLayout(2, false));
    lblFilterType = new Label(composite_1, SWT.NONE);
    lblFilterType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    lblFilterType.setText("Filter type:");
  
    ComboViewer cvFilterType = new ComboViewer(composite_1, SWT.READ_ONLY);
    cvFilterType.setContentProvider(new ArrayContentProvider());
    cvFilterType.setInput(ModelpatchEntryFilterType.values());
    cFilterType = cvFilterType.getCombo();
    cFilterType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    cFilterType.select(0);
  
    final Composite composite = new Composite(composite_1, SWT.NONE);
    composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
    final StackLayout stack = new StackLayout();
    composite.setLayout(stack);
  
    final Composite cmpNothing = new Composite(composite, SWT.NONE);
    cmpNothing.setLayout(new FillLayout(SWT.HORIZONTAL));
  
    final Composite grpDirectionFilter = new Composite(composite, SWT.NONE);
    GridLayout gl_grpDirectionFilter = new GridLayout(3, false);
    gl_grpDirectionFilter.marginWidth = 0;
    gl_grpDirectionFilter.marginHeight = 0;
    grpDirectionFilter.setLayout(gl_grpDirectionFilter);
  
    lblDirection = new Label(grpDirectionFilter, SWT.NONE);
    lblDirection.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    lblDirection.setText("Direction:");
  
    Button btnAdd = new Button(grpDirectionFilter, SWT.RADIO);
    btnAdd.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        add = ((Button) e.widget).getSelection();
      }
    });
    btnAdd.setSelection(true);
    btnAdd.setText("ADD");
  
    Button btnRemove = new Button(grpDirectionFilter, SWT.RADIO);
    btnRemove.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        remove = ((Button) e.widget).getSelection();
      }
    });
    btnRemove.setText("REMOVE");
  
    final Composite grpEntryTypeFilter = new Composite(composite, SWT.NONE);
    GridLayout gl_grpEntryTypeFilter = new GridLayout(4, false);
    gl_grpEntryTypeFilter.marginHeight = 0;
    gl_grpEntryTypeFilter.marginWidth = 0;
    grpEntryTypeFilter.setLayout(gl_grpEntryTypeFilter);
  
    lblEntryType = new Label(grpEntryTypeFilter, SWT.NONE);
    lblEntryType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    lblEntryType.setText("Entry type:");
  
    Button btnAttribute = new Button(grpEntryTypeFilter, SWT.RADIO);
    btnAttribute.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        attribute = ((Button) e.widget).getSelection();
      }
    });
    btnAttribute.setSelection(true);
    btnAttribute.setText("Attribute");
  
    Button btnElement = new Button(grpEntryTypeFilter, SWT.RADIO);
    btnElement.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        element = ((Button) e.widget).getSelection();
      }
    });
    btnElement.setText("Element");
  
    Button btnReference = new Button(grpEntryTypeFilter, SWT.RADIO);
    btnReference.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        reference = ((Button) e.widget).getSelection();
      }
    });
    btnReference.setText("Reference");
  
    stack.topControl = grpDirectionFilter;
    new Label(composite_1, SWT.NONE);
    new Label(composite_1, SWT.NONE);
  
    cFilterType.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        filterType = getSelectedFilterType();
        switch (filterType) {
        case Direction:
          stack.topControl = grpDirectionFilter;
          break;
        case EntryType:
          stack.topControl = grpEntryTypeFilter;
          break;
        default:
          stack.topControl = cmpNothing;
          break;
        }
        composite.layout();
      }
  
    });
  
    return container;
  }

  /**
   * Create contents of the button bar.
   * 
   * @param parent
   */
  @Override
  protected void createButtonsForButtonBar(Composite parent) {
    Button btnOK = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    btnOK.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        switch (filterType) {
        case Direction:
          if (add) {
            filter = new EntryDirectionFilter(ChangeDirection.ADD);
          } else if (remove) {
            filter = new EntryDirectionFilter(ChangeDirection.REMOVE);
          }
          break;
        case EntryType:
          if (attribute) {
            filter = new EntryTypeFilter(EntryType.ATTRIBUTE);
          } else if (element) {
            filter = new EntryTypeFilter(EntryType.ELEMENT);
          } else if (reference) {
            filter = new EntryTypeFilter(EntryType.REFERENCE);
          }
          break;
        default:
          break;
        }
      }
    });
    createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    m_bindingContext = initDataBindings();
  }

  public IModelPatchEntryFilter getCreatedFilter() {
    return filter;
  }

  enum ModelpatchEntryFilterType {
    Direction, EntryType
  }

  private ModelpatchEntryFilterType getSelectedFilterType() {
    return ModelpatchEntryFilterType.valueOf(cFilterType.getItem(cFilterType.getSelectionIndex()));
  }

  protected DataBindingContext initDataBindings() {
    DataBindingContext bindingContext = new DataBindingContext();
    //
    IObservableValue observeSizeLblDirectionObserveWidget = WidgetProperties.size().observe(lblDirection);
    IObservableValue observeSizeLblFilterTypeObserveWidget = WidgetProperties.size().observe(lblFilterType);
    bindingContext.bindValue(observeSizeLblDirectionObserveWidget, observeSizeLblFilterTypeObserveWidget, null, null);
    //
    IObservableValue observeSizeLblEntryTypeObserveWidget = WidgetProperties.size().observe(lblEntryType);
    IObservableValue observeSizeLblFilterTypeObserveWidget_1 = WidgetProperties.size().observe(lblFilterType);
    bindingContext.bindValue(observeSizeLblEntryTypeObserveWidget, observeSizeLblFilterTypeObserveWidget_1, null, null);
    //
    return bindingContext;
  }
}
