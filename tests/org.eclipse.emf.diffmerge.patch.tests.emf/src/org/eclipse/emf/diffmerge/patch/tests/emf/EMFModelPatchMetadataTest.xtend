/*******************************************************************************
 * Copyright (c) 2016-2017, Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Balazs Grill, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.tests.emf

import org.eclipse.emf.diffmerge.patch.api.ModelPatch
import org.eclipse.emf.diffmerge.patch.runtime.EMFModelPatchMetadata
import org.eclipse.emf.diffmerge.patch.serializer.gson.GsonModelPatchSerializer
import org.junit.Test

import static org.junit.Assert.*

class EMFModelPatchMetadataTest {

  @Test
  def emfModelPatchMetadata(){
    val modelPatch = new ModelPatch();
    val md = new EMFModelPatchMetadata
    md.modelUriList += "testUri"
    md.modelUriList += "testUri2"
    md.usedNamespaceUris += "testNS1"
    md.usedNamespaceUris += "testNS2"
    modelPatch.metadata = md
    val gson = GsonModelPatchSerializer.create(this.class.classLoader)
    val result = gson.serialize(modelPatch)
    val patch = gson.load(result)
    assertTrue(patch.metadata instanceof EMFModelPatchMetadata)
    val emd = patch.metadata as EMFModelPatchMetadata
    assertTrue(emd.modelUriList.size == md.modelUriList.size)
    assertTrue(emd.usedNamespaceUris.size == md.usedNamespaceUris.size)
  }

}
