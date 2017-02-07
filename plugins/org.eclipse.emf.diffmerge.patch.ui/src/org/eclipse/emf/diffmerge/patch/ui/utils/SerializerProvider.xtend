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

import org.eclipse.emf.diffmerge.patch.persistence.emf.EMFModelPatchSerializer
import org.eclipse.emf.diffmerge.patch.serializer.IModelPatchSerializer
import org.eclipse.emf.diffmerge.patch.serializer.gson.GsonModelPatchSerializer

class SerializerProvider {
  def IModelPatchSerializer getSelectedSerializer(String typeName) {
    val accessType = PersistenceTypes.valueOf(typeName)

    return accessType.selectedSerializer
  }

  def IModelPatchSerializer getSelectedSerializer(PersistenceTypes type) {
    var IModelPatchSerializer serializer
    switch (type) {
      case GSON: {
        serializer = GsonModelPatchSerializer.create(this.class.classLoader)
      }
      case EMF: {
        serializer = new EMFModelPatchSerializer
      }
      default: {
        serializer = GsonModelPatchSerializer.create(this.class.classLoader)
      }
    }
    return serializer
  }

  def IModelPatchSerializer getSerializerByFileExtension(String fileExtension) {
    val serializer = PersistenceTypes.values.map [
      it.selectedSerializer
    ].findFirst [
      preferredFileExtension == fileExtension
    ]
    return serializer
  }
}
