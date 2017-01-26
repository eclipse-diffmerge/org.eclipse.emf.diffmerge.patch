/*******************************************************************************
 * Copyright (c) 2016-2017, Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.ui.utils

import org.eclipse.emf.diffmerge.patch.serializer.gson.GsonModelPatchSerializer

class SerializerProvider {
  public def org.eclipse.emf.diffmerge.patch.serializer.IModelPatchSerializer getSelectedSerializer(String preference){
    val accessType = SerializationTypes.valueOf(preference)

    var org.eclipse.emf.diffmerge.patch.serializer.IModelPatchSerializer serializer
    switch (accessType) {
      case GSON: {
        serializer = GsonModelPatchSerializer.create(this.class.classLoader)

      }

      default: {
        serializer = GsonModelPatchSerializer.create(this.class.classLoader)
      }
    }
  }
}
