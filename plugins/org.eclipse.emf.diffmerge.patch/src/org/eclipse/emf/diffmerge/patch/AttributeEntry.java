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

import org.eclipse.emf.diffmerge.patch.api.EntryType;

public class AttributeEntry extends StructuralFeatureEntry {

  private String value;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public EntryType getEntryType() {
    return EntryType.ATTRIBUTE;
  }

  @Override
  public String toString() {
    return getDirection()+" '"+value+"' value from '"+getToStringEnding();
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof AttributeEntry) {
      AttributeEntry casted = (AttributeEntry)obj;
      return Objects.equals(this.value, casted.value) && super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getEntryType(), this.getDirection(), this.getContext(), this.getFeature(), this.getValue(), this.getIndex());
  }
}
