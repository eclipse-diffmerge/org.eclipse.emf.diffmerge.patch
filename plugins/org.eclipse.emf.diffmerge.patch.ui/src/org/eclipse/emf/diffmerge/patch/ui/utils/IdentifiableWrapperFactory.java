/*******************************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *      Abel Hegedus, Tamas Borbas, Balazs Grill, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.ui.utils;

import org.eclipse.emf.diffmerge.patch.api.Identifiable;

public enum IdentifiableWrapperFactory {
  INSTANCE;

  public IdentifiableWrapper create(Identifiable ident, String name) {
    return create(ident.getIdentifier(), name);
  }

  public IdentifiableWrapper create(String ident, String name) {
    IdentifiableWrapper wrapper = new IdentifiableWrapper();
    wrapper.name = name;
    wrapper.ident = ident;
    return wrapper;
  }
}
