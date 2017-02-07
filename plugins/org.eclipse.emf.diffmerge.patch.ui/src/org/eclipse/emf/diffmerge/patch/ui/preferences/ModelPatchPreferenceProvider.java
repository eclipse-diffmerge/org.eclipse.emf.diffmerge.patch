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

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.diffmerge.patch.runtime.modelaccess.ModelAccessTypes;
import org.eclipse.emf.diffmerge.patch.ui.ModelPatchUIPlugin;
import org.eclipse.emf.diffmerge.patch.ui.utils.PersistenceTypes;

public enum ModelPatchPreferenceProvider {
  INSTANCE;

  public static final String SERIALIZER_PREFERENCE_ID = "ModelPatch.Serializer";
  public static final String MODELACCESS_PREFERENCE_ID = "ModelPatch.ModelAccess";

  public String getSerializationType() {
    return Platform.getPreferencesService().getString(ModelPatchUIPlugin.PLUGIN_ID, SERIALIZER_PREFERENCE_ID,
        PersistenceTypes.GSON.toString(), null);
  }

  public String getModelAccessType() {
    return Platform.getPreferencesService().getString(ModelPatchUIPlugin.PLUGIN_ID, MODELACCESS_PREFERENCE_ID,
        ModelAccessTypes.EMF_REFLECTIVE.toString(), null);
  }
}
