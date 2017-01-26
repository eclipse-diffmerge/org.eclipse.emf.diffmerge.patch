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
package org.eclipse.emf.diffmerge.patch;

import java.util.Objects;

import org.eclipse.emf.diffmerge.patch.api.Identifiable;

import com.google.common.base.Optional;

public abstract class StructuralFeatureEntry extends AbstractModelPatchEntry {

  private Identifiable feature;

  private Integer index = null;

  public Identifiable getFeature() {
    return feature;
  }

  public void setFeature(Identifiable feature) {
    this.feature = feature;
  }

  public Optional<Integer> getIndex() {
    return Optional.fromNullable(index);
  }

  public void setIndex(Optional<Integer> index) {
    this.index = index.orNull();
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof StructuralFeatureEntry) {
      StructuralFeatureEntry casted = (StructuralFeatureEntry)obj;
      return Objects.equals(this.feature, casted.feature) && this.getIndex().equals(casted.getIndex()) && super.equals(obj);
    }
    return false;
  }

  protected String getToStringEnding() {
      return getFeature().getIdentifier()+"' of "+getContext().getIdentifier()+(getIndex().isPresent()?("(index: "+getIndex().get()+")"):(""));
  }
}

