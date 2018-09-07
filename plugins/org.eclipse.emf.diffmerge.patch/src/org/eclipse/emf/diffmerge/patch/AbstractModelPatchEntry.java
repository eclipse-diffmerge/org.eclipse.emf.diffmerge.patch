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
package org.eclipse.emf.diffmerge.patch;

import java.util.Objects;

import org.eclipse.emf.diffmerge.patch.api.ChangeDirection;
import org.eclipse.emf.diffmerge.patch.api.Identifiable;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntry;

public abstract class AbstractModelPatchEntry implements ModelPatchEntry {

  private ChangeDirection direction;

  private Identifiable context;

  @Override
  public ChangeDirection getDirection() {
    return direction;
  }

  public void setDirection(ChangeDirection direction) {
    this.direction = direction;
  }

  @Override
  public Identifiable getContext() {
    return context;
  }

  public void setContext(Identifiable context) {
    this.context = context;
  }

  @Override
  public abstract String toString();

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof AbstractModelPatchEntry) {
      AbstractModelPatchEntry casted = (AbstractModelPatchEntry)obj;
      return Objects.equals(this.context, casted.context) && this.direction==casted.direction;
    }
    return false;
  }
}
