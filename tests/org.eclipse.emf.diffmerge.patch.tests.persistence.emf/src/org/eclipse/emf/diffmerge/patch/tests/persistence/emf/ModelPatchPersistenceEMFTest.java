/*******************************************************************************
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Abel Hegedus (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.tests.persistence.emf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.diffmerge.patch.ElementEntry;
import org.eclipse.emf.diffmerge.patch.api.ChangeDirection;
import org.eclipse.emf.diffmerge.patch.api.Identifiable;
import org.eclipse.emf.diffmerge.patch.api.ModelPatch;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchBuilder;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchException;
import org.eclipse.emf.diffmerge.patch.persistence.emf.EMFModelPatchPersister;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch;
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEMFMetadata;
import org.eclipse.emf.diffmerge.patch.runtime.EMFModelPatchMetadata;
import org.junit.Test;

public class ModelPatchPersistenceEMFTest extends BaseModelPatchPersistenceEMFTest {


  @Test
  public void persist_emptyModelPatch() throws ModelPatchException {
    ModelPatch modelPatch = new ModelPatch();
    EMFModelPatchPersister emfPersister = new EMFModelPatchPersister();
    EModelPatch eModelPatch = emfPersister.persist(modelPatch);
    assertNotNull(eModelPatch.getMetadata());
  }

  @Test
  public void persist_entries() throws ModelPatchException {
    ModelPatch modelPatch = buildPatch();
    EMFModelPatchPersister emfPersister = new EMFModelPatchPersister();
    EModelPatch eModelPatch = emfPersister.persist(modelPatch);
    assertFalse(eModelPatch.getEntries().isEmpty());
  }

  @Test
  public void persist_emfMetadata() throws ModelPatchException {
    ModelPatch modelPatch = buildPatch();
    EMFModelPatchMetadata emfModelPatchMetadata = new EMFModelPatchMetadata();
    emfModelPatchMetadata.getModelUriList().add("modelUri");
    emfModelPatchMetadata.getUsedNamespaceUris().add("nsUri");
    modelPatch.setMetadata(emfModelPatchMetadata);

    EMFModelPatchPersister emfPersister = new EMFModelPatchPersister();
    EModelPatch eModelPatch = emfPersister.persist(modelPatch);

    assertTrue(eModelPatch.getMetadata() instanceof EModelPatchEMFMetadata);
  }

  @Test
  public void persist_duplicates() throws ModelPatchException {
    ModelPatch modelPatch = buildPatch();
    ElementEntry duplicateEntry = ModelPatchBuilder.entryBuilder(new Identifiable("context"), ChangeDirection.ADD)
      .setType(new Identifiable("target"))
      .buildElementEntry();
    modelPatch.getEntries().add(duplicateEntry);

    EMFModelPatchPersister emfPersister = new EMFModelPatchPersister();
    EModelPatch eModelPatch = emfPersister.persist(modelPatch);
    assertFalse(eModelPatch.getEntries().isEmpty());
  }

  @Test
  public void load() throws ModelPatchException {
    ModelPatch modelPatch = buildPatch();
    EMFModelPatchPersister emfPersister = new EMFModelPatchPersister();
    EModelPatch eModelPatch = emfPersister.persist(modelPatch);
    assertFalse(eModelPatch.getEntries().isEmpty());

    ModelPatch patch = emfPersister.load(eModelPatch);
    assertEquals(patch.getEntries().size(), modelPatch.getEntries().size());
  }

  @Test
  public void load_emfMetadata() throws ModelPatchException {
    ModelPatch modelPatch = buildPatch();
    EMFModelPatchMetadata emfModelPatchMetadata = new EMFModelPatchMetadata();
    emfModelPatchMetadata.getModelUriList().add("modelUri");
    emfModelPatchMetadata.getUsedNamespaceUris().add("nsUri");
    modelPatch.setMetadata(emfModelPatchMetadata);

    EMFModelPatchPersister emfPersister = new EMFModelPatchPersister();
    EModelPatch eModelPatch = emfPersister.persist(modelPatch);
    assertFalse(eModelPatch.getEntries().isEmpty());

    ModelPatch patch = emfPersister.load(eModelPatch);
    assertTrue(patch.getMetadata() instanceof EMFModelPatchMetadata);
  }

}
