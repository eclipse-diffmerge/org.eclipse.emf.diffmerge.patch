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
package org.eclipse.emf.diffmerge.patch.tests.persistence.emf;

import org.eclipse.emf.diffmerge.patch.AttributeEntry;
import org.eclipse.emf.diffmerge.patch.ElementEntry;
import org.eclipse.emf.diffmerge.patch.ReferenceEntry;
import org.eclipse.emf.diffmerge.patch.api.ChangeDirection;
import org.eclipse.emf.diffmerge.patch.api.Identifiable;
import org.eclipse.emf.diffmerge.patch.api.ModelPatch;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchBuilder;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntryBuilder;

public class BaseModelPatchPersistenceEMFTest {

  public BaseModelPatchPersistenceEMFTest() {
    super();
  }

  protected ModelPatch buildPatch() {
    ModelPatchBuilder builder = ModelPatchBuilder.create();

    Identifiable elementId = new Identifiable("element1");
    ModelPatchEntryBuilder entryBuilder = ModelPatchBuilder.entryBuilder(elementId, ChangeDirection.ADD);
    ElementEntry elementEntry = entryBuilder
        .setType(new Identifiable("type1"))
        .buildElementEntry();
    builder.addEntry(elementEntry);

    AttributeEntry attributeEntry = entryBuilder
      .setFeature(new Identifiable("attr1"))
      .setValue("value1")
      .buildAttributeEntry();
    builder.addEntry(attributeEntry);

    ReferenceEntry referenceEntry = entryBuilder
        .setFeature(new Identifiable("ref1"))
        .setTarget(elementId)
        .buildReferenceEntry();
    builder.addEntry(referenceEntry);

    ReferenceEntry refEntry2 = entryBuilder
        .setFeature(new Identifiable("ref2"))
        .setTarget(new Identifiable("target"))
        .setDirection(ChangeDirection.REMOVE)
        .buildReferenceEntry();
    builder.addEntry(refEntry2);

    return builder.build();
  }

}
