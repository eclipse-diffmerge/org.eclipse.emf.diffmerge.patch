/**
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Abel Hegedus (IncQuery Labs Ltd.) - initial API and implementation
 */
package org.eclipse.emf.diffmerge.patch.tests

import org.eclipse.emf.diffmerge.patch.api.ChangeDirection
import org.eclipse.emf.diffmerge.patch.api.ComplexMPEFilterType
import org.eclipse.emf.diffmerge.patch.api.EntryType
import org.eclipse.emf.diffmerge.patch.api.Identifiable
import org.eclipse.emf.diffmerge.patch.api.ModelPatch
import org.eclipse.emf.diffmerge.patch.api.ModelPatchBuilder
import org.eclipse.emf.diffmerge.patch.api.ModelPatchFilterApplier
import org.eclipse.emf.diffmerge.patch.api.filters.ComplexEntryFilter
import org.eclipse.emf.diffmerge.patch.api.filters.EntryDirectionFilter
import org.eclipse.emf.diffmerge.patch.api.filters.EntryTypeFilter
import org.junit.Test

import static org.junit.Assert.*
import org.eclipse.emf.diffmerge.patch.api.filters.EntryPropertyFilter
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntryProperty

class ModelPatchFilterTest {

  @Test
  public def filterElementEntryType() {
    // Prepare original patch
    val originalPatch = ModelPatchBuilder.create.addEntry(
      (ModelPatchBuilder.entryBuilder => [
        it.context = new Identifiable("context")
        it.direction = ChangeDirection.ADD
        it.type = new Identifiable("type")
      ]).build(EntryType.ELEMENT)
    ).build

    // Create filtered patch
    val filtering = new ModelPatchFilterApplier
    val filter = new EntryTypeFilter(EntryType.ELEMENT)
    val filteredPatch = filtering.applyFilter(originalPatch, filter)

    assertTrue(filter.filteredTypes.contains(EntryType.ELEMENT))
    assertTrue(filteredPatch.entries.empty)
  }

  @Test
  public def filterEntryType_notEmpty() {
    // Prepare original patch
    val originalPatch = preparePatch()

    // Create filtered patch
    val filtering = new ModelPatchFilterApplier
    val filter = new EntryTypeFilter(EntryType.ELEMENT)
    val filteredPatch = filtering.applyFilter(originalPatch, filter)

    assertTrue(filteredPatch.entries.size == 1)
    assertTrue(filteredPatch.entries.head.entryType == EntryType.ATTRIBUTE)
  }

  @Test
  public def unfilterEntryType_notEmpty() {
    // Prepare original patch
    val originalPatch = preparePatch()

    // Create filtered patch
    val filtering = new ModelPatchFilterApplier
    val filter = new EntryTypeFilter(EntryType.ELEMENT)
    val filteredPatch = filtering.applyUnfilter(originalPatch, filter)

    assertTrue(filteredPatch.entries.size == 1)
    assertTrue(filteredPatch.entries.head.entryType == EntryType.ELEMENT)
  }

  @Test
  public def directionFilter() {
    // Prepare original patch
    val originalPatch = preparePatch()

    // Create filtered patch
    val filtering = new ModelPatchFilterApplier
    val filter = new EntryDirectionFilter(ChangeDirection.ADD)
    val filteredPatch = filtering.applyFilter(originalPatch, filter)

    assertTrue(filter.filteredDirection == ChangeDirection.ADD)
    assertTrue(filteredPatch.entries.size == 1)
    assertTrue(filteredPatch.entries.head.direction == ChangeDirection.REMOVE)
  }

  @Test
  public def entryPropertyFilter_context() {
    // Prepare original patch
    val originalPatch = preparePatch()

    // Create filtered patch
    val filtering = new ModelPatchFilterApplier
    val filter = new EntryPropertyFilter(ModelPatchEntryProperty.CONTEXT, "context")
    val filteredPatch = filtering.applyFilter(originalPatch, filter)

    assertTrue(filter.filteredProperty == ModelPatchEntryProperty.CONTEXT)
    assertTrue(filter.filteredValue == "context")
    assertTrue(filteredPatch.entries.size == 1)
    assertTrue(filteredPatch.entries.head.context.identifier != "context")
  }

  @Test
  public def entryPropertyFilter_type() {
    // Prepare original patch
    val originalPatch = preparePatch()

    // Create filtered patch
    val filtering = new ModelPatchFilterApplier
    val filter = new EntryPropertyFilter(ModelPatchEntryProperty.TYPE, "type")
    val filteredPatch = filtering.applyFilter(originalPatch, filter)

    assertTrue(filter.filteredProperty == ModelPatchEntryProperty.TYPE)
    assertTrue(filter.filteredValue == "type")
    assertTrue(filteredPatch.entries.size == 1)
    assertTrue(filteredPatch.entries.head.entryType != EntryType.ELEMENT)
  }

  @Test
  public def entryPropertyFilter_feature() {
    // Prepare original patch
    val originalPatch = preparePatch()

    // Create filtered patch
    val filtering = new ModelPatchFilterApplier
    val filter = new EntryPropertyFilter(ModelPatchEntryProperty.FEATURE, "feature")
    val filteredPatch = filtering.applyFilter(originalPatch, filter)

    assertTrue(filter.filteredProperty == ModelPatchEntryProperty.FEATURE)
    assertTrue(filter.filteredValue == "feature")
    assertTrue(filteredPatch.entries.size == 1)
    assertTrue(filteredPatch.entries.head.entryType != EntryType.ATTRIBUTE)
  }

  @Test
  public def entryPropertyFilter_value() {
    // Prepare original patch
    val originalPatch = preparePatch()

    // Create filtered patch
    val filtering = new ModelPatchFilterApplier
    val filter = new EntryPropertyFilter(ModelPatchEntryProperty.VALUE, "value")
    val filteredPatch = filtering.applyFilter(originalPatch, filter)

    assertTrue(filter.filteredProperty == ModelPatchEntryProperty.VALUE)
    assertTrue(filter.filteredValue == "value")
    assertTrue(filteredPatch.entries.size == 1)
    assertTrue(filteredPatch.entries.head.entryType != EntryType.ATTRIBUTE)
  }

  @Test
  public def entryPropertyFilter_target() {
    // Prepare original patch
    val originalPatch = preparePatch()
    originalPatch.entries.add((ModelPatchBuilder.entryBuilder => [
        it.context = new Identifiable("context3")
        it.direction = ChangeDirection.ADD
        it.feature = new Identifiable("feature2")
        it.target = new Identifiable("target")
      ]).build(EntryType.REFERENCE))

    // Create filtered patch
    val filtering = new ModelPatchFilterApplier
    val filter = new EntryPropertyFilter(ModelPatchEntryProperty.TARGET, "target")
    val filteredPatch = filtering.applyFilter(originalPatch, filter)

    assertTrue(filter.filteredProperty == ModelPatchEntryProperty.TARGET)
    assertTrue(filter.filteredValue == "target")
    assertTrue(filteredPatch.entries.size == 2)
    assertTrue(filteredPatch.entries.head.entryType != EntryType.REFERENCE)
  }

  @Test
  public def complexFilterAnd() {
    // Prepare original patch
    val originalPatch = preparePatch()

    // Create filtered patch
    val filtering = new ModelPatchFilterApplier
    val directionFilter = new EntryDirectionFilter(ChangeDirection.ADD)
    val entryFilter = new EntryTypeFilter(EntryType.ELEMENT)
    val filter = new ComplexEntryFilter(ComplexMPEFilterType.AND, entryFilter, directionFilter)
    val filteredPatch = filtering.applyFilter(originalPatch, filter)

    assertTrue(filter.subFilters.size == 2)
    assertTrue(filter.type == ComplexMPEFilterType.AND)
    assertTrue(filteredPatch.entries.size == 1)
    assertTrue(filteredPatch.entries.head.direction == ChangeDirection.REMOVE)
  }

  @Test
  public def complexFilterOr() {
    // Prepare original patch
    val originalPatch = preparePatch()

    // Create filtered patch
    val filtering = new ModelPatchFilterApplier
    val directionFilter = new EntryDirectionFilter(ChangeDirection.ADD)
    val entryFilter = new EntryTypeFilter(EntryType.ELEMENT)
    val filter = new ComplexEntryFilter(ComplexMPEFilterType.OR, entryFilter, directionFilter)
    val filteredPatch = filtering.applyFilter(originalPatch, filter)

    assertTrue(filter.subFilters.size == 2)
    assertTrue(filter.type == ComplexMPEFilterType.OR)
    assertTrue(filteredPatch.entries.size == 1)
    assertTrue(filteredPatch.entries.head.direction == ChangeDirection.REMOVE)
  }

  protected def ModelPatch preparePatch() {
    ModelPatchBuilder.create.addEntry(
      (ModelPatchBuilder.entryBuilder => [
        it.context = new Identifiable("context")
        it.direction = ChangeDirection.ADD
        it.type = new Identifiable("type")
      ]).build(EntryType.ELEMENT)
    )
    .addEntry(
      (ModelPatchBuilder.entryBuilder => [
        it.context = new Identifiable("context2")
        it.direction = ChangeDirection.REMOVE
        it.feature = new Identifiable("feature")
        it.value = "value"
      ]).build(EntryType.ATTRIBUTE)
    )
    .build
  }
}
