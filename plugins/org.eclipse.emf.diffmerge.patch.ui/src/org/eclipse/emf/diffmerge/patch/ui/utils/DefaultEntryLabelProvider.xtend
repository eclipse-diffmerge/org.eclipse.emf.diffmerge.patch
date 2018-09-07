/*******************************************************************************
 * Copyright (c) 2016-2017, Thales Global Services S.A.S.
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

import org.eclipse.emf.diffmerge.patch.AttributeEntry
import org.eclipse.emf.diffmerge.patch.ElementEntry
import org.eclipse.emf.diffmerge.patch.ReferenceEntry
import org.eclipse.emf.diffmerge.patch.StructuralFeatureEntry
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntry

class DefaultEntryLabelProvider implements IEntryLabelProvider {

  override getLabel(ModelPatchEntry entry) {
    return entry.toString
  }

  override shortDescription(
    ModelPatchEntry entry) '''«entry.direction.toString.toLowerCase.toFirstUpper» «entry.entryType.toString.toLowerCase.toFirstUpper»'''

  override getPropertyList(ModelPatchEntry entry) {
    val props = <EntryPropertyWrapper>newArrayList

    props.add(new EntryPropertyWrapper("context", entry.context))

    if (entry instanceof ElementEntry) {
      props.add(new EntryPropertyWrapper("type", entry.type))
    }

    if (entry instanceof StructuralFeatureEntry) {
      props.add(new EntryPropertyWrapper("feature", entry.feature))
      if (entry.index.isPresent) {
        props.add(new EntryPropertyWrapper("index", entry.index))
      }

      if (entry instanceof AttributeEntry) {
        props.add(new EntryPropertyWrapper("value", entry.value))
      } else if (entry instanceof ReferenceEntry) {
        props.add(new EntryPropertyWrapper("target", entry.target))
      }
    }

    return props
  }

}
