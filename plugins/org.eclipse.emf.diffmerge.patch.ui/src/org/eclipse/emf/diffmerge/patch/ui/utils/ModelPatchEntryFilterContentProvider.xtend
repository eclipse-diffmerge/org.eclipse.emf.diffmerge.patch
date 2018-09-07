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
package org.eclipse.emf.diffmerge.patch.ui.utils

import java.util.List
import org.eclipse.jface.viewers.IStructuredContentProvider
import org.eclipse.jface.viewers.Viewer

class ModelPatchEntryFilterContentProvider implements IStructuredContentProvider {

  override void dispose() {}

  override void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}

  override Object[] getElements(Object inputElement) {
    if (inputElement == null) {
      return newArrayList
    }
    return inputElement.allElements
  }

  private dispatch def List<? extends Object> getAllElements(Object object) {
    return newArrayList
  }

  private dispatch def List<? extends Object> getAllElements(List list) {
    return list
  }

}
