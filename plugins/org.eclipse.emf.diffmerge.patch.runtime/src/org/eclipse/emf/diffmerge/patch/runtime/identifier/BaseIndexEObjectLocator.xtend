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
import org.eclipse.emf.ecore.EObject
import org.eclipse.viatra.query.runtime.base.api.NavigationHelper

class BaseIndexEObjectLocator implements EObjectLocator {
  val NavigationHelper navigationHelper;

  new(NavigationHelper navigationHelper) {
    this.navigationHelper = navigationHelper;
  }

  override Optional<EObject> locate(String identifier) {
    var result = Optional.absent
    val owners = navigationHelper.findByAttributeValue(identifier);
    val idSettings = owners.filter[
      getEObject.eClass.getEIDAttribute == getEStructuralFeature
    ]
    if(idSettings.size() == 1){
      result = Optional.fromNullable(owners.iterator().next().getEObject);
    }
    return result;
  }

  def void dispose() {
    navigationHelper.dispose();
  }
}
