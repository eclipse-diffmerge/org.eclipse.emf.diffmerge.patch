/*******************************************************************************
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Abel Hegedus, Tamas Borbas, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.api;

import java.util.Collection;

public class ModelPatchBuilder {
  ModelPatch modelPatch = new ModelPatch();

  public static ModelPatchBuilder create() {
      return new ModelPatchBuilder();
  }

  public ModelPatchBuilder addEntry(ModelPatchEntry entry) {
    modelPatch.getEntries().add(entry);
    return this;
  }

  public ModelPatchBuilder addEntry(ModelPatchEntryBuilder entryBuilder, EntryType entryType) {
    ModelPatchEntry entry = entryBuilder.build(entryType);
    addEntry(entry);
    return this;
  }

  public ModelPatchBuilder append(ModelPatchBuilder other) {
    return this.append(other.build());
  }
  public ModelPatchBuilder append(ModelPatch patch) {
    return this.append(patch.getEntries());
  }
  public ModelPatchBuilder append(Collection<? extends ModelPatchEntry> entries) {
    this.modelPatch.getEntries().addAll(entries);
    return this;
  }

  public ModelPatch build() {
    return modelPatch;
  }

  public static ModelPatchEntryBuilder entryBuilder(Identifiable context, ChangeDirection direction) {
    return ModelPatchEntryBuilder.create(context, direction);
  }

  public static ModelPatchEntryBuilder entryBuilder() {
    return ModelPatchEntryBuilder.create();
  }

}
