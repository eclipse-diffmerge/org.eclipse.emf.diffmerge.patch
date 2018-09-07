/*******************************************************************************
 * Copyright (c) 2017-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Abel Hegedus (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.tests.persistence.emf

import java.io.File
import org.eclipse.emf.diffmerge.patch.api.ModelPatch
import org.eclipse.emf.diffmerge.patch.api.ModelPatchException
import org.eclipse.emf.diffmerge.patch.persistence.emf.EMFModelPatchSerializer
import org.junit.Test

import static org.junit.Assert.*

class ModelPatchSerializerEMFTest extends BaseModelPatchPersistenceEMFTest {

  public static val PATCH_FILE_PATH = "../org.eclipse.emf.diffmerge.patch.tests.emf/model/demo-patch-generated."
  public static val RESULT_FILE_PATH = "../org.eclipse.emf.diffmerge.patch.tests.emf/results/demo-patch-generated."

  @Test
  def void persist_emptyModelPatch() throws ModelPatchException {
    val modelPatch = new ModelPatch()
    val emfSerializer = new EMFModelPatchSerializer()
    val eModelPatch = emfSerializer.serialize(modelPatch)
    println(eModelPatch);
    assertTrue(eModelPatch.contains("org.eclipse.emf.diffmerge.patch:EModelPatch"));
  }

  @Test
  def void persist_entries() throws ModelPatchException {
    val modelPatch = buildPatch()
    val emfSerializer = new EMFModelPatchSerializer()
    val eModelPatch = emfSerializer.serialize(modelPatch)
    println(eModelPatch)
    assertTrue(eModelPatch.contains("entries"))
  }

  @Test
  def void load_entries() throws ModelPatchException {
    val modelPatch = buildPatch()
    val emfSerializer = new EMFModelPatchSerializer()
    val eModelPatch = emfSerializer.serialize(modelPatch)

    val modelPatch2 = emfSerializer.load(eModelPatch)
    assertEquals(modelPatch.entries.size, modelPatch2.entries.size)
  }

  @Test
  def void persist_file() throws ModelPatchException {
    val patchFile = new File(PATCH_FILE_PATH + "emodelpatch");
    val epatchFile = new File(RESULT_FILE_PATH + "emodelpatch");
    epatchFile.delete
    val emfSerializer = new EMFModelPatchSerializer()
    val modelPatch = emfSerializer.load(patchFile)
    emfSerializer.serialize(modelPatch,epatchFile)
    assertTrue(epatchFile.exists)
  }

  @Test
  def void load_file() throws ModelPatchException {
    val epatchFile = new File(PATCH_FILE_PATH + "emodelpatch");
    val emfSerializer = new EMFModelPatchSerializer()

    val modelPatch = emfSerializer.load(epatchFile)
    assertTrue(modelPatch.entries.size > 0)
  }
}
