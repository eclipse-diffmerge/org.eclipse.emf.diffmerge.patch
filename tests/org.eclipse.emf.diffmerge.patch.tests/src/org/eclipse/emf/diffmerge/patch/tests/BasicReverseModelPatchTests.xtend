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

import java.util.Collection
import org.eclipse.emf.diffmerge.patch.AttributeEntry
import org.eclipse.emf.diffmerge.patch.ElementEntry
import org.eclipse.emf.diffmerge.patch.ReferenceEntry
import org.eclipse.emf.diffmerge.patch.api.ChangeDirection
import org.eclipse.emf.diffmerge.patch.api.EntryType
import org.eclipse.emf.diffmerge.patch.api.Identifiable
import org.eclipse.emf.diffmerge.patch.api.ModelPatchBuilder
import org.eclipse.emf.diffmerge.patch.api.ModelPatchReverser
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

import static org.junit.Assert.*

@RunWith(Parameterized)
class BasicReverseModelPatchTests {
  private extension val ModelPatchReverser modelPatchReverser = ModelPatchReverser.INSTANCE
  private val REVERSE_PATCH_ENTRIES_SIZE = 1
  private val CONTEXT_ID = "context"
  private val FEATURE_ID = "feature"
  private val TARGET_ID = "target"
  private val TYPE_ID = "type"
  private val VALUE = "value"

  @Parameterized.Parameters(name = "{1} {0}")
  def static Collection<Object[]> testData() {
    return newArrayList(
      #[EntryType.ATTRIBUTE, ChangeDirection.ADD],
      #[EntryType.ATTRIBUTE, ChangeDirection.REMOVE],
      #[EntryType.ELEMENT, ChangeDirection.ADD],
      #[EntryType.ELEMENT, ChangeDirection.REMOVE],
      #[EntryType.REFERENCE, ChangeDirection.ADD],
      #[EntryType.REFERENCE, ChangeDirection.REMOVE]
    )
  }

  @Parameterized.Parameter(0)
  public EntryType type
  @Parameterized.Parameter(1)
  public ChangeDirection direction

  @Test
  public def test() {
    // Prepare original patch
    val originalPatch = ModelPatchBuilder.create.addEntry(
      (ModelPatchBuilder.entryBuilder => [
        it.context = new Identifiable(CONTEXT_ID)
        it.direction = direction
        switch type {
          case ATTRIBUTE: {
            it.feature = new Identifiable(FEATURE_ID)
            it.value = VALUE
          }
          case ELEMENT: {
            it.type = new Identifiable(TYPE_ID)
          }
          case REFERENCE: {
            it.feature = new Identifiable(FEATURE_ID)
            it.target = new Identifiable(TARGET_ID)
          }
        }
      ]).build(type)
    ).build

    // Create reverse patch
    val reversePatch = originalPatch.reverse

    // Check reverse patch
    assertEquals(REVERSE_PATCH_ENTRIES_SIZE, reversePatch.entries.size)
    val reverseEntry = reversePatch.entries.get(0)
    assertEquals(CONTEXT_ID, reverseEntry.context.identifier)
    assertEquals(direction.reverse, reverseEntry.direction)
    assertEquals(type, reverseEntry.entryType)
    switch type {
      case ATTRIBUTE: {
        assertEquals(FEATURE_ID, (reverseEntry as AttributeEntry).feature.identifier)
        assertEquals(VALUE, (reverseEntry as AttributeEntry).value)
      }
      case ELEMENT: {
        assertEquals(TYPE_ID, (reverseEntry as ElementEntry).type.identifier)
      }
      case REFERENCE: {
        assertEquals(FEATURE_ID, (reverseEntry as ReferenceEntry).feature.identifier)
        assertEquals(TARGET_ID, (reverseEntry as ReferenceEntry).target.identifier)
      }
    }
  }

  private def getReverse(ChangeDirection direction) {
    if(direction==ChangeDirection.ADD) {
      return ChangeDirection.REMOVE
    } else {
      return ChangeDirection.ADD
    }
  }
}
