/*******************************************************************************
 * Copyright (c) 2016-2017, Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Balazs Grill, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.runtime.identifier

import com.google.common.base.Optional
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.util.EcoreUtil

class TreeIteratorEObjectLocator implements EObjectLocator {

  ResourceSet resourceSet

  new(ResourceSet resourceSet) {
    this.resourceSet = resourceSet
  }

  override locate(String identifier) {
    val allResourceContents = resourceSet.resources.map [
      allContents.toIterable
    ].flatten

    val eObject = allResourceContents.findFirst [ element |
      identifier == EcoreUtil.getID(element)
    ]

    return Optional.fromNullable(eObject)
  }

}
