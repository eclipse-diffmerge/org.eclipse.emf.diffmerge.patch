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

import org.eclipse.emf.diffmerge.patch.AttributeEntry;
import org.eclipse.emf.diffmerge.patch.ElementEntry;
import org.eclipse.emf.diffmerge.patch.ReferenceEntry;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

public class ModelPatchEntryBuilder {

  private ChangeDirection direction;
  private Identifiable context;
  private Identifiable feature;
  private Identifiable target;
  private Identifiable type;
  private String value;
  private Optional<Integer> index = Optional.absent();

  public static ModelPatchEntryBuilder create() {
      ModelPatchEntryBuilder builder = new ModelPatchEntryBuilder();
    return builder;
  }

  public static ModelPatchEntryBuilder create(Identifiable context, ChangeDirection direction) {
    ModelPatchEntryBuilder builder = create();
    builder.context = context;
    builder.direction = direction;
    return builder;
  }

  public ModelPatchEntry build(EntryType entryType) {
    ModelPatchEntry entry = null;
    switch (entryType) {
    case ELEMENT:
      entry = buildElementEntry();
      break;
    case ATTRIBUTE:
      entry = buildAttributeEntry();
      break;
    case REFERENCE:
      entry = buildReferenceEntry();
      break;
    default:
      break;
    }
    return entry;
  }

  public ElementEntry buildElementEntry() {
    Preconditions.checkNotNull(direction, "Direction cannot be null");
    Preconditions.checkNotNull(context, "Context cannot be null");
    Preconditions.checkNotNull(type, "Type cannot be null");
    ElementEntry elementEntry = new ElementEntry();
    elementEntry.setDirection(direction);
    elementEntry.setContext(context);
    elementEntry.setType(type);
    return elementEntry;
  }

  public ReferenceEntry buildReferenceEntry() {
    Preconditions.checkNotNull(direction, "Direction cannot be null");
    Preconditions.checkNotNull(context, "Context cannot be null");
    Preconditions.checkNotNull(feature, "Feature cannot be null");
    Preconditions.checkNotNull(target, "Target cannot be null");
    ReferenceEntry referenceEntry = new ReferenceEntry();
    referenceEntry.setDirection(direction);
    referenceEntry.setContext(context);
    referenceEntry.setFeature(feature);
    referenceEntry.setTarget(target);
    referenceEntry.setIndex(index);
    return referenceEntry;
  }

  public AttributeEntry buildAttributeEntry() {
    Preconditions.checkNotNull(direction, "Direction cannot be null");
    Preconditions.checkNotNull(context, "Context cannot be null");
    Preconditions.checkNotNull(feature, "Feature cannot be null");
    Preconditions.checkNotNull(value, "Value cannot be null");
    AttributeEntry attributeEntry = new AttributeEntry();
    attributeEntry.setDirection(direction);
    attributeEntry.setContext(context);
    attributeEntry.setFeature(feature);
    attributeEntry.setValue(value);
    attributeEntry.setIndex(index);
    return attributeEntry;
  }

  public ModelPatchEntryBuilder setDirection(ChangeDirection direction) {
    this.direction = direction;
    return this;
  }

  public ModelPatchEntryBuilder setContext(Identifiable context) {
    this.context = context;
    return this;
  }

  public ModelPatchEntryBuilder setFeature(Identifiable feature) {
    this.feature = feature;
    return this;
  }

  public ModelPatchEntryBuilder setTarget(Identifiable target) {
    this.target = target;
    return this;
  }

  public ModelPatchEntryBuilder setType(Identifiable type) {
    this.type = type;
    return this;
  }

  public ModelPatchEntryBuilder setValue(String value) {
    this.value = value;
    return this;
  }

  public ModelPatchEntryBuilder setIndex(Optional<Integer> index) {
    this.index = index;
    return this;
  }

}
