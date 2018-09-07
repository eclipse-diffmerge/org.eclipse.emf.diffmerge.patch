/*******************************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
