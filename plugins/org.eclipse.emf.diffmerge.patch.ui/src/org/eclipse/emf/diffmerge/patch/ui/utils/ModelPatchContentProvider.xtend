/*******************************************************************************
 * Copyright (c) 2016-2017, Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Balazs Grill, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.ui.utils

import org.eclipse.emf.diffmerge.patch.api.ModelPatch
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntry
import java.util.List
import org.eclipse.jface.viewers.ITreeContentProvider
import org.eclipse.jface.viewers.Viewer

class ModelPatchContentProvider implements ITreeContentProvider {

  override void dispose() {}

  override void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}

  override Object[] getElements(Object inputElement) {
    if(inputElement==null) {
      return newArrayList
    }
    return inputElement.allElements
  }

  override Object[] getChildren(Object parentElement) {
    return parentElement.allElements
  }

  override Object getParent(Object element) {
    return null
  }

  override boolean hasChildren(Object element) {
    return element.allElements.size>0
  }

  private dispatch def List<? extends Object> getAllElements(Object patch) {
    return newArrayList
  }
  private dispatch def List<? extends Object> getAllElements(ModelPatch patch) {
    return patch.entries
  }
  private dispatch def List<? extends Object> getAllElements(ModelPatchEntry ee) {
    return newArrayList
  }
}
