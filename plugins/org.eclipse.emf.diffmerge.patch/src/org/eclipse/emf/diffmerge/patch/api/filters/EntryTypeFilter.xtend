/*******************************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.api.filters

import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.emf.diffmerge.patch.api.EntryType
import java.util.List
import org.eclipse.emf.diffmerge.patch.api.IModelPatchEntryFilter
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntry

import static com.google.common.base.Preconditions.*

class EntryTypeFilter implements IModelPatchEntryFilter {
  @Accessors(PUBLIC_GETTER)
  private List<EntryType> filteredTypes

  new(EntryType... filteredTypes) {
    checkNotNull(filteredTypes)
    this.filteredTypes = filteredTypes
  }

  override isEntryFiltered(ModelPatchEntry entry) {
    return filteredTypes.exists[it==entry.entryType]
  }

}
