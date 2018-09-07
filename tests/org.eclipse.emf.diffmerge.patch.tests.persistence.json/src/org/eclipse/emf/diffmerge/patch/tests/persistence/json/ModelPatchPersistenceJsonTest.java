/*******************************************************************************
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Abel Hegedus, Tamas Borbas, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.tests.persistence.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.diffmerge.patch.AttributeEntry;
import org.eclipse.emf.diffmerge.patch.ElementEntry;
import org.eclipse.emf.diffmerge.patch.ReferenceEntry;
import org.eclipse.emf.diffmerge.patch.api.ChangeDirection;
import org.eclipse.emf.diffmerge.patch.api.Identifiable;
import org.eclipse.emf.diffmerge.patch.api.ModelPatch;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchBuilder;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntryBuilder;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchException;
import org.eclipse.emf.diffmerge.patch.serializer.gson.GsonModelPatchSerializer;
import org.junit.Test;

public class ModelPatchPersistenceJsonTest {


  @Test
  public void emptyModelPatchGsonSerialization() throws ModelPatchException {
    ModelPatch modelPatch = new ModelPatch();
    String result = GsonModelPatchSerializer.create(this.getClass().getClassLoader()).persist(modelPatch);
    assertTrue(result.contains("entries"));
  }

  @Test
  public void entriesGsonSerialization() throws ModelPatchException {
    ModelPatch modelPatch = buildPatch();

    String result = GsonModelPatchSerializer.create(this.getClass().getClassLoader()).persist(modelPatch);
    System.out.println(result);
    assertTrue(result.contains("entries"));
  }

  @Test
  public void modelPatchBuilderGson() throws ModelPatchException {
    ModelPatch modelPatch = buildPatch();

    String result = GsonModelPatchSerializer.create(this.getClass().getClassLoader()).persist(modelPatch);
    System.out.println(result);
    assertTrue(result.contains("entries"));

    ModelPatch patch = GsonModelPatchSerializer.create(this.getClass().getClassLoader()).load(result);
    assertEquals(patch.getEntries().size(), modelPatch.getEntries().size());
  }

  private ModelPatch buildPatch(){
    ModelPatchBuilder builder = ModelPatchBuilder.create();

    Identifiable elementId = new Identifiable("element1");
    ModelPatchEntryBuilder entryBuilder = ModelPatchBuilder.entryBuilder(elementId, ChangeDirection.ADD);
    ElementEntry elementEntry = entryBuilder
        .setType(new Identifiable("type1"))
        .buildElementEntry();
    builder.addEntry(elementEntry);

    AttributeEntry attributeEntry = entryBuilder
      .setFeature(new Identifiable("attr1"))
      .setValue("value1")
      .buildAttributeEntry();
    builder.addEntry(attributeEntry);

    ReferenceEntry referenceEntry = entryBuilder
        .setFeature(new Identifiable("ref1"))
        .setTarget(elementId)
        .buildReferenceEntry();
    builder.addEntry(referenceEntry);

    return builder.build();
  }

}
