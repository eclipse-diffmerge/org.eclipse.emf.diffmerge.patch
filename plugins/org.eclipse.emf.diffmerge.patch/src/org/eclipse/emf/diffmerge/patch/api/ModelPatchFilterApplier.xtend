/*******************************************************************************
 * Copyright (c) 2016-2017, Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.api

class ModelPatchFilterApplier {

  /**
   * Filter out matching entries from the originalPatch based on the given filter and return the filtered patch.
   * Matching elements will not be in the new patch. If you need the matching elements use the
   * {@link #applyUnfilter(ModelPatch, IModelPatchEntryFilter) applyUnfilter}.
   *
   * @param originalModelPatch The modifiable patch
   * @param filter The applicable filter
   * @return ModelPatch which does not contain filtered elements
   */
  def ModelPatch applyFilter(ModelPatch originalPatch, IModelPatchEntryFilter filter) {
    val builder = ModelPatchBuilder.create
    builder.append(originalPatch.entries.filter[!filter.isEntryFiltered(it)].toList)
    return builder.build
  }

  /**
   * Filter out non matching entries from the originalPatch based on the given filter and return the filtered patch.
   * Only matching elements will be in the new patch. If you need the non matching elements
   * {@link #applyFilter(ModelPatch, IModelPatchEntryFilter) applyFilter}.
   *
   * @param originalModelPatch The modifiable patch
   * @param filter The applicable filter
   * @return ModelPatch which only contains filtered elements
   */
  def ModelPatch applyUnfilter(ModelPatch originalPatch, IModelPatchEntryFilter filter) {
    val builder = ModelPatchBuilder.create
    builder.append(originalPatch.entries.filter[filter.isEntryFiltered(it)].toList)
    return builder.build
  }

}
