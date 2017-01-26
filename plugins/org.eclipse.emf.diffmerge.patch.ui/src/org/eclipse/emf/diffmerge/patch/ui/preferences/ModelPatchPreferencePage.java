/*******************************************************************************
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *      Abel Hegedus, Tamas Borbas, Balazs Grill, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.ui.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.emf.diffmerge.patch.runtime.modelaccess.ModelAccessTypes;
import org.eclipse.emf.diffmerge.patch.ui.Activator;
import org.eclipse.emf.diffmerge.patch.ui.utils.SerializationTypes;

public class ModelPatchPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

  public ModelPatchPreferencePage() {
    super(GRID);

  }

  public void createFieldEditors() {

    addField(new RadioGroupFieldEditor(ModelPatchPreferenceProvider.MODELACCESS_PREFERENCE_ID, "Model access:", 1,
        new String[][] { { "EMF Reflective Model Access", ModelAccessTypes.EMF_REFLECTIVE.toString() },
            { "Editing Domain Aware Model Access", ModelAccessTypes.VIATRA.toString() } },
        getFieldEditorParent()));

    addField(new RadioGroupFieldEditor(ModelPatchPreferenceProvider.SERIALIZER_PREFERENCE_ID, "Serialization:", 1,
        new String[][] { { "Jackson - JSON", SerializationTypes.JACKSON.toString() },
            { "GSON - JSON", SerializationTypes.GSON.toString() },
            { "Jackson - BSON", SerializationTypes.BSON.toString() } },
        getFieldEditorParent()));
  }

  @Override
  public void init(IWorkbench workbench) {
    setPreferenceStore(Activator.getDefault().getPreferenceStore());
  }

}
