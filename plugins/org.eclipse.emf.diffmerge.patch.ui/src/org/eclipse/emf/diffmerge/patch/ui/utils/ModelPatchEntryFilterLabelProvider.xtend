/*******************************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Balazs Grill, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.ui.utils

import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.jface.viewers.ILabelProviderListener
import org.eclipse.emf.diffmerge.patch.api.filters.ComplexEntryFilter
import org.eclipse.emf.diffmerge.patch.api.filters.EntryDirectionFilter
import org.eclipse.emf.diffmerge.patch.api.filters.EntryTypeFilter

class ModelPatchEntryFilterLabelProvider implements ILabelProvider {

  override getImage(Object element) {}

  override getText(Object element) {
    return element.label
  }

  private dispatch def String getLabel(Object object) {
    return object.toString
  }

  private dispatch def String getLabel(ComplexEntryFilter filter) '''«filter.type.name»'''

  private dispatch def String getLabel(
    EntryDirectionFilter filter) '''Filtered direction: «filter.filteredDirection.name»'''

  private dispatch def String getLabel(
    EntryTypeFilter filter) '''Filtered entry types: «FOR type : filter.filteredTypes SEPARATOR ", "»«type.name»«ENDFOR»'''

  override addListener(ILabelProviderListener listener) {}

  override dispose() {}

  override isLabelProperty(Object element, String property) {
    return true
  }

  override removeListener(ILabelProviderListener listener) {}

}
