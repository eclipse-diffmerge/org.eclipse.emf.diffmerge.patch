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
package org.eclipse.emf.diffmerge.patch;

import java.util.Objects;

import org.eclipse.emf.diffmerge.patch.api.EntryType;
import org.eclipse.emf.diffmerge.patch.api.Identifiable;

public class ElementEntry extends AbstractModelPatchEntry {

  private Identifiable type;

  public Identifiable getType() {
    return type;
  }

  public void setType(Identifiable type) {
    this.type = type;
  }

  @Override
  public EntryType getEntryType() {
    return EntryType.ELEMENT;
  }

  @Override
  public String toString() {
    return getDirection()+" '"+type.getIdentifier()+"' (id: '"+getContext().getIdentifier()+"')";
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof ElementEntry) {
      ElementEntry casted = (ElementEntry)obj;
      return Objects.equals(this.type, casted.type) && super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getEntryType(), this.getDirection(), this.getContext(), this.getType());
  }
}
