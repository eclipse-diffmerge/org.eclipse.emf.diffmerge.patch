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

import java.util.Objects;

public class Identifiable {

  private String identifier;

  private String description;

  public Identifiable(String identifier) {
    this.identifier = identifier;
  }

  protected Identifiable() {
    this.identifier = null;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof Identifiable) {
      Identifiable casted = (Identifiable)obj;
      return Objects.equals(this.identifier, casted.identifier);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.identifier);
  }
}
