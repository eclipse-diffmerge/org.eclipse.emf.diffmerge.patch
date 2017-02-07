/*******************************************************************************
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *      Abel Hegedus, Tamas Borbas, Balazs Grill, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.ui.utils;

import org.eclipse.emf.diffmerge.patch.api.Identifiable;

import com.google.common.base.Optional;

public class EntryPropertyWrapper {
  public String prop = "";
  public String value = "";

  public EntryPropertyWrapper(String prop, String value) {
    this.prop = prop;
    this.value = value;
  }

  public EntryPropertyWrapper(String prop, Identifiable value) {
    this.prop = prop;
    this.value = value.getIdentifier();
  }

  public EntryPropertyWrapper(String prop, Optional<? extends Object> value) {
    this.prop = prop;
    this.value = value.isPresent() ? value.get().toString() : "No value";
  }
}
