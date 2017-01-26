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
package org.eclipse.emf.diffmerge.patch.tests

import org.eclipse.emf.diffmerge.patch.AttributeEntry
import org.eclipse.emf.diffmerge.patch.ElementEntry
import org.eclipse.emf.diffmerge.patch.ReferenceEntry
import org.eclipse.emf.diffmerge.patch.api.ChangeDirection
import org.eclipse.emf.diffmerge.patch.api.EntryType
import org.eclipse.emf.diffmerge.patch.api.Identifiable
import org.eclipse.emf.diffmerge.patch.api.ModelPatchBuilder
import org.eclipse.emf.diffmerge.patch.api.ModelPatchReverser
import org.junit.Test

import static org.junit.Assert.*

class ComplexReverseModelPatchTest {
  private extension val ModelPatchReverser modelPatchReverser = ModelPatchReverser.INSTANCE

  @Test
  public def referencedElementAdditionReverse() {
    // Prepare original patch
    val patchBuilder = ModelPatchBuilder.create
    val entryBuilder = ModelPatchBuilder.entryBuilder

    val element = new Identifiable("newElement")
    val type = new Identifiable("type")
    val elementEntry =	(entryBuilder => [
                it.context = element
                it.direction = ChangeDirection.ADD
                it.type = type
              ]).buildElementEntry
    patchBuilder.addEntry(elementEntry)

    val container = new Identifiable("container")
    val feature = new Identifiable("elements")
    val referenceEntry = (entryBuilder => [
                it.context = container
                it.direction = ChangeDirection.ADD
                it.feature = feature
                it.target = element
              ]).buildReferenceEntry
    patchBuilder.addEntry(referenceEntry)

    val originalPatch = patchBuilder.build


    // Create reverse patch
    val reversePatch = originalPatch.reverse


    // Check reverse patch
    assertEquals(2, reversePatch.entries.size)

    val reverseReferenceEntry = reversePatch.entries.get(0)
    assertEquals(container.identifier, reverseReferenceEntry.context.identifier)
    assertEquals(ChangeDirection.REMOVE, reverseReferenceEntry.direction)
    assertEquals(EntryType.REFERENCE, reverseReferenceEntry.entryType)
    assertEquals(feature.identifier, (reverseReferenceEntry as ReferenceEntry).feature.identifier)
    assertEquals(element.identifier, (reverseReferenceEntry as ReferenceEntry).target.identifier)

    val reverseElementEntry = reversePatch.entries.get(1)
    assertEquals(element.identifier, reverseElementEntry.context.identifier)
    assertEquals(ChangeDirection.REMOVE, reverseElementEntry.direction)
    assertEquals(EntryType.ELEMENT, reverseElementEntry.entryType)
    assertEquals(type.identifier, (reverseElementEntry as ElementEntry).type.identifier)
  }

  @Test
  public def referencedElementDeletionReverse() {
    // Prepare original patch
    val patchBuilder = ModelPatchBuilder.create
    val entryBuilder = ModelPatchBuilder.entryBuilder

    val element = new Identifiable("newElement")
    val container = new Identifiable("container")
    val feature = new Identifiable("elements")
    val referenceEntry = (entryBuilder => [
                  it.context = container
                  it.direction = ChangeDirection.REMOVE
                  it.feature = feature
                  it.target = element
                ]).buildReferenceEntry
    patchBuilder.addEntry(referenceEntry)

    val type = new Identifiable("type")
    val elementEntry = (entryBuilder => [
                  it.context = element
                  it.direction = ChangeDirection.REMOVE
                  it.type = type
                ]).buildElementEntry
    patchBuilder.addEntry(elementEntry)

    val originalPatch = patchBuilder.build


    // Create reverse patch
    val reversePatch = originalPatch.reverse


    // Check reverse patch
    assertEquals(2, reversePatch.entries.size)

    val reverseElementEntry = reversePatch.entries.get(0)
    assertEquals(element.identifier, reverseElementEntry.context.identifier)
    assertEquals(ChangeDirection.ADD, reverseElementEntry.direction)
    assertEquals(EntryType.ELEMENT, reverseElementEntry.entryType)
    assertEquals(type.identifier, (reverseElementEntry as ElementEntry).type.identifier)

    val reverseReferenceEntry = reversePatch.entries.get(1)
    assertEquals(container.identifier, reverseReferenceEntry.context.identifier)
    assertEquals(ChangeDirection.ADD, reverseReferenceEntry.direction)
    assertEquals(EntryType.REFERENCE, reverseReferenceEntry.entryType)
    assertEquals(feature.identifier, (reverseReferenceEntry as ReferenceEntry).feature.identifier)
    assertEquals(element.identifier, (reverseReferenceEntry as ReferenceEntry).target.identifier)
  }

  @Test
  public def attributeModificationReverse() {
    // Prepare original patch
    val patchBuilder = ModelPatchBuilder.create
    val entryBuilder = ModelPatchBuilder.entryBuilder

    val container = new Identifiable("namedElement")
    val feature = new Identifiable("name")
    val oldValue = "old"
    val removedEntry = (entryBuilder => [
                it.context = container
                it.direction = ChangeDirection.REMOVE
                it.feature = feature
                it.value = oldValue
              ]).buildAttributeEntry
    patchBuilder.addEntry(removedEntry)

    val newValue = "new"
    val addedEntry = (entryBuilder => [
                it.context = container
                it.direction = ChangeDirection.ADD
                it.feature = feature
                it.value = newValue
              ]).buildAttributeEntry
    patchBuilder.addEntry(addedEntry)

    val originalPatch = patchBuilder.build


    // Create reverse patch
    val reversePatch = originalPatch.reverse


    // Check reverse patch
    assertEquals(2, reversePatch.entries.size)

    val reverseAddedEntry = reversePatch.entries.get(0)
    assertEquals(container.identifier, reverseAddedEntry.context.identifier)
    assertEquals(ChangeDirection.REMOVE, reverseAddedEntry.direction)
    assertEquals(EntryType.ATTRIBUTE, reverseAddedEntry.entryType)
    assertEquals(feature.identifier, (reverseAddedEntry as AttributeEntry).feature.identifier)
    assertEquals(newValue, (reverseAddedEntry as AttributeEntry).value)

    val reverseRemovedEntry = reversePatch.entries.get(1)
    assertEquals(container.identifier, reverseRemovedEntry.context.identifier)
    assertEquals(ChangeDirection.ADD, reverseRemovedEntry.direction)
    assertEquals(EntryType.ATTRIBUTE, reverseRemovedEntry.entryType)
    assertEquals(feature.identifier, (reverseRemovedEntry as AttributeEntry).feature.identifier)
    assertEquals(oldValue, (reverseRemovedEntry as AttributeEntry).value)
  }

}
