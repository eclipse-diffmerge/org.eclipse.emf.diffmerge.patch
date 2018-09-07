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
package org.eclipse.emf.diffmerge.patch.runtime

import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.emf.diffmerge.patch.api.BaseModelPatchMetadata

class EMFModelPatchMetadata extends BaseModelPatchMetadata {

  @Accessors
  private List<String> modelUriList = newArrayList

  @Accessors
  private List<String> usedNamespaceUris = newArrayList

}
