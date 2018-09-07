/*******************************************************************************
 * Copyright (c) 2017-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Abel Hegedus (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.persistence.emf.transform

import org.apache.log4j.Logger
import org.eclipse.emf.diffmerge.patch.AbstractModelPatchEntry
import org.eclipse.emf.diffmerge.patch.AttributeEntry
import org.eclipse.emf.diffmerge.patch.ElementEntry
import org.eclipse.emf.diffmerge.patch.ReferenceEntry
import org.eclipse.emf.diffmerge.patch.StructuralFeatureEntry
import org.eclipse.emf.diffmerge.patch.api.BaseModelPatchMetadata
import org.eclipse.emf.diffmerge.patch.api.ChangeDirection
import org.eclipse.emf.diffmerge.patch.api.Identifiable
import org.eclipse.emf.diffmerge.patch.api.ModelPatch
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntry
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EAttributeEntry
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EChangeDirection
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EElementEntry
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EIdentifiable
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEMFMetadata
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchEntry
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatchMetadata
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EReferenceEntry
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EStructuralFeatureEntry
import org.eclipse.emf.diffmerge.patch.runtime.EMFModelPatchMetadata

class EModelPatchToPOJO {

  val EModelPatch source
  val ModelPatch target
  val Logger logger

  new(EModelPatch source, ModelPatch target, Logger logger) {
    this.source = source
    this.target = target
    this.logger = logger

  }

  def transform(){
    source.metadata.transformElement

    source.entries.filter(EModelPatchEntry).forEach[
      it.transformElement
    ]
  }

  protected def void transformElement(EModelPatchMetadata emetadata) {
    val metadata = if(emetadata instanceof EModelPatchEMFMetadata
    ){
      new EMFModelPatchMetadata => [
        modelUriList += emetadata.modelUris
        usedNamespaceUris += emetadata.usedNamespaceUris
      ]
    } else {
      new BaseModelPatchMetadata
    }
    metadata => [
      author = emetadata.author
      createdAt = emetadata.createdAt
      description = emetadata.description
      input = emetadata.input
    ]
    target.metadata = metadata
    return
  }

  protected def void transformElement(EModelPatchEntry entry) {
    val eentry = entry.transformEntry
    target.entries.add(eentry)
    return
  }

  protected def dispatch ModelPatchEntry transformEntry(EElementEntry entry) {
    new ElementEntry => [
      entry.transformAbstractEntryFeatures(it)
      type = entry.type.toIdentifiable
    ]
  }

  protected def dispatch ModelPatchEntry transformEntry(EAttributeEntry entry) {
    new AttributeEntry => [
      entry.transformStructuralFeatureEntryFeatures(it)
      value = entry.value
    ]
  }

  protected def dispatch ModelPatchEntry transformEntry(EReferenceEntry entry) {
    new ReferenceEntry => [
      entry.transformStructuralFeatureEntryFeatures(it)
      target = entry.target.toIdentifiable
    ]
  }

  protected def transformAbstractEntryFeatures(EModelPatchEntry eentry, AbstractModelPatchEntry entry) {
      entry.direction = eentry.direction.transformDirection
      entry.context = eentry.context.toIdentifiable
  }

  protected def transformStructuralFeatureEntryFeatures(EStructuralFeatureEntry eentry, StructuralFeatureEntry entry) {
      eentry.transformAbstractEntryFeatures(entry)
      entry.feature = eentry.feature.toIdentifiable
  }

  protected def toIdentifiable(EIdentifiable eidentifiable) {
    new Identifiable(eidentifiable.targetIdentifier)
  }

  protected def transformDirection(EChangeDirection direction) {
    return switch direction {
      case ADD: ChangeDirection.ADD
      case REMOVE: ChangeDirection.REMOVE
    }
  }

}
