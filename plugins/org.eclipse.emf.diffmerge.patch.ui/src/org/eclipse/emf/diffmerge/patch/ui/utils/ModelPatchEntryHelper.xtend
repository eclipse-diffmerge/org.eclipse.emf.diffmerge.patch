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

import org.eclipse.emf.diffmerge.patch.ElementEntry
import org.eclipse.emf.diffmerge.patch.AttributeEntry
import org.eclipse.emf.diffmerge.patch.ReferenceEntry
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntry

class ModelPatchEntryHelper {
  dispatch def String stringRepresentation(ElementEntry entry) '''
    ElementEntry
     * Direction: «entry.direction.name»
     * ID: «entry.context.identifier»
     * Type: «entry.type.identifier»
  '''

  dispatch def String stringRepresentation(AttributeEntry entry) '''
    AttributeEntry
     * Direction: «entry.direction.name»
     * Attribute owner: «entry.context.identifier»
     * Attribute: «entry.feature.identifier»
     * Value: «entry.value»
  '''

  dispatch def String stringRepresentation(ReferenceEntry entry) '''
    ReferenceEntry
     * Direction: «entry.direction.name»
     * Reference owner: «entry.context.identifier»
     * Reference: «entry.feature.identifier»
     * Target: «entry.target.identifier»
  '''

  dispatch def String stringRepresentation(ModelPatchEntry entry) {
    return entry.toString
  }
}
