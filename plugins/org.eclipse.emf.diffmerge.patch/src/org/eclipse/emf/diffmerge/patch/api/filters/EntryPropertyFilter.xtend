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

import org.eclipse.emf.diffmerge.patch.AttributeEntry
import org.eclipse.emf.diffmerge.patch.ElementEntry
import org.eclipse.emf.diffmerge.patch.ReferenceEntry
import org.eclipse.emf.diffmerge.patch.StructuralFeatureEntry
import org.eclipse.emf.diffmerge.patch.api.IModelPatchEntryFilter
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntry
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntryProperty
import org.eclipse.xtend.lib.annotations.Accessors

import static com.google.common.base.Preconditions.*

class EntryPropertyFilter implements IModelPatchEntryFilter {
  @Accessors(PUBLIC_GETTER)
  private ModelPatchEntryProperty filteredProperty
  @Accessors(PUBLIC_GETTER)
  private String filteredValue

  new(ModelPatchEntryProperty filteredProperty, String filteredValue) {
    checkNotNull(filteredProperty)
    checkNotNull(filteredValue)
    this.filteredProperty = filteredProperty
    this.filteredValue = filteredValue
  }

  override isEntryFiltered(ModelPatchEntry entry) {
    var boolean result = false
    switch(filteredProperty) {
      case CONTEXT: {
        result = filteredValue==entry.context.identifier
      }
      case FEATURE: {
        if(entry instanceof StructuralFeatureEntry) {
          result = filteredValue==entry.feature.identifier
        }
      }
      case TARGET: {
        if(entry instanceof ReferenceEntry) {
          result = filteredValue==entry.target.identifier
        }
      }
      case TYPE: {
        if(entry instanceof ElementEntry) {
          result = filteredValue==entry.type.identifier
        }
      }
      case VALUE: {
        if(entry instanceof AttributeEntry) {
          result = filteredValue==entry.value
        }
      }
    }
    return result
  }

}
