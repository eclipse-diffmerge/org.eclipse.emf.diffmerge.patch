/*******************************************************************************
 * Copyright (c) 2016-2017, Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.api

import org.eclipse.emf.diffmerge.patch.AttributeEntry
import org.eclipse.emf.diffmerge.patch.ElementEntry
import org.eclipse.emf.diffmerge.patch.ReferenceEntry

class ModelPatchReverser {
  public static val INSTANCE = new ModelPatchReverser()

  public def ModelPatch reverse(ModelPatch originalPatch) throws ModelPatchException {
    val reverseBuilder = ModelPatchBuilder.create
    originalPatch.entries.reverseView.forEach[ originalEntry |
      reverseBuilder.addEntry(originalEntry.reverseEntry)
    ]
    return reverseBuilder.build
  }



  public dispatch def ModelPatchEntry reverseEntry(ModelPatchEntry originalEntry) throws ModelPatchException {
    throw new ModelPatchException("Unhandled entry type")
  }

  public dispatch def ModelPatchEntry reverseEntry(AttributeEntry originalEntry) {
    val reverseEntryBuilder = originalEntry.reverseEntryBuilder => [
      it.feature = originalEntry.feature.cloneIdentifiable
      it.value = originalEntry.value
      it.index = originalEntry.index
    ]
    return reverseEntryBuilder.buildAttributeEntry
  }

  public dispatch def ModelPatchEntry reverseEntry(ElementEntry originalEntry) {
    val reverseEntryBuilder = originalEntry.reverseEntryBuilder => [
      it.type = originalEntry.type.cloneIdentifiable
    ]
    return reverseEntryBuilder.buildElementEntry
  }

  public dispatch def ModelPatchEntry reverseEntry(ReferenceEntry originalEntry) {
    val reverseEntryBuilder = originalEntry.reverseEntryBuilder => [
      it.feature = originalEntry.feature.cloneIdentifiable
      it.target = originalEntry.target.cloneIdentifiable
      it.index = originalEntry.index
    ]
    return reverseEntryBuilder.buildReferenceEntry
  }



  public def ChangeDirection reverseDirection(ChangeDirection original) {
    if(ChangeDirection.ADD==original) {
      return ChangeDirection.REMOVE
    } else {
      return ChangeDirection.ADD
    }
  }



  private def reverseEntryBuilder(ModelPatchEntry originalEntry) {
    return ModelPatchBuilder.entryBuilder(
      originalEntry.context.cloneIdentifiable,
      originalEntry.direction.reverseDirection
    )
  }

  private def Identifiable cloneIdentifiable(Identifiable original) {
    return new Identifiable(original.identifier)
  }
}
