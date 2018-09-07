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
import org.eclipse.emf.diffmerge.patch.api.ModelPatch
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntry

class ModelPatchLabelProvider implements ILabelProvider {
  IEntryLabelProvider entryLabelProvider

  new() {
    entryLabelProvider = new DefaultEntryLabelProvider
  }

  new(IEntryLabelProvider entryLabelProvider) {
    this.entryLabelProvider = entryLabelProvider
  }

  override getImage(Object element) {
    return null
  }

  override getText(Object element) {
    return element.label
  }

  override addListener(ILabelProviderListener listener) {}

  override dispose() {}

  override isLabelProperty(Object element, String property) {
    return true
  }

  override removeListener(ILabelProviderListener listener) {}

  private dispatch def String getLabel(ModelPatch patch) {
    return '''ModelPatch [«patch.entries.size»]'''
  }

  private dispatch def String getLabel(ModelPatchEntry entry) {
    return entryLabelProvider.getLabel(entry)
  }

  private dispatch def String getLabel(IdentifiableWrapper wrapper) {
    return '''«wrapper.name»: «wrapper.ident»'''
  }

  private dispatch def String getLabel(Object object) {
    return object.toString
  }

  def correctCase(String string) {
    return string.toLowerCase.toFirstUpper
  }
}
