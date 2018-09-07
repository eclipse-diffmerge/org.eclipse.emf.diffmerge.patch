/*******************************************************************************
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *      Abel Hegedus, Tamas Borbas, Balazs Grill, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.tests.emf;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.diffmerge.patch.AttributeEntry;
import org.eclipse.emf.diffmerge.patch.ElementEntry;
import org.eclipse.emf.diffmerge.patch.ReferenceEntry;
import org.eclipse.emf.diffmerge.patch.StructuralFeatureEntry;
import org.eclipse.emf.diffmerge.patch.api.Identifiable;
import org.eclipse.emf.diffmerge.patch.api.ModelPatch;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchBuilder;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntry;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntryBuilder;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchException;
import org.eclipse.emf.diffmerge.patch.serializer.gson.GsonModelPatchSerializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@RunWith(Parameterized.class)
public class ModelPatchScalerTest extends CPSModelPatchTest {
  protected int scale;

  @Parameters
    public static Collection<Object[]> data() {
      ImmutableList<Integer> sizes = ImmutableList.<Integer>builder()
        .add(1)
        .add(2)
        .add(4)
        .add(8)
        .add(16)
        .add(32)
        .add(64)
        .add(128)
        .add(256)
        .build();
      Iterable<Object[]> transformed = Iterables.transform(sizes, new Function<Integer, Object[]>() {
      @Override
      public Object[] apply(Integer input) {
        return new Object[]{ input };
      }
    });
        return Lists.newArrayList(transformed);
    }

  public ModelPatchScalerTest(int scale) {
    // we need to multiply one less then
    this.scale = scale;
  }

  @Test
  public void multiplyPatch() throws ModelPatchException, IOException {
    File patchFile = new File(MODEL_FOLDER + "/clockRadio-physicalArch-transition.modelpatch");
    ModelPatch modelPatch = GsonModelPatchSerializer.create(this.getClass().getClassLoader()).load(patchFile);
    ModelPatch scaledPatch = scaleModelPatch(modelPatch, scale);
    File scaledFile = new File(MODEL_FOLDER + "/clockRadio-physicalArch-transition_" + scale + ".modelpatch");
    GsonModelPatchSerializer.create(this.getClass().getClassLoader()).serialize(scaledPatch, scaledFile);
  }

  private ModelPatch scaleModelPatch(ModelPatch modelPatch, int scale) {
    List<ModelPatchEntry> entries = modelPatch.getEntries();

    int iteration = 0;
    ModelPatchBuilder modelPatchBuilder = ModelPatchBuilder.create();
    ModelPatchEntryBuilder entryBuilder = ModelPatchBuilder.entryBuilder();

    while (iteration < scale) {
      for (ModelPatchEntry modelPatchEntry : entries) {
        if (iteration == 0) {
          modelPatchBuilder.addEntry(modelPatchEntry);
        } else {
          Identifiable context = createIdentifiable(iteration, modelPatchEntry.getContext());
          entryBuilder.setContext(context);
          entryBuilder.setDirection(modelPatchEntry.getDirection());
          if (modelPatchEntry instanceof ElementEntry) {
            entryBuilder.setType(((ElementEntry) modelPatchEntry).getType());
          } else {
            entryBuilder.setFeature(((StructuralFeatureEntry) modelPatchEntry).getFeature());
            if (modelPatchEntry instanceof ReferenceEntry) {
              Identifiable target = createIdentifiable(iteration, ((ReferenceEntry) modelPatchEntry).getTarget());
              entryBuilder.setTarget(target);
            } else {
              entryBuilder.setValue(((AttributeEntry) modelPatchEntry).getValue());
            }
          }
          ModelPatchEntry entry = entryBuilder.build(modelPatchEntry.getEntryType());
          modelPatchBuilder.addEntry(entry);
        }
      }
      iteration++;
    }

    ModelPatch scaledPatch = modelPatchBuilder.build();
    return scaledPatch;
  }

  private Identifiable createIdentifiable(int iteration, Identifiable identifiable) {
    String identifier = identifiable.getIdentifier();
    identifier = identifier + "_" + iteration;
    Identifiable scaledIdentifiable = new Identifiable(identifier);
    return scaledIdentifiable;
  }

}
