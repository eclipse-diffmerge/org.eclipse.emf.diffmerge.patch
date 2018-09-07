/*******************************************************************************
 * Copyright (c) 2017 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Abel Hegedus (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.runtime.identifier;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * @author Abel Hegedus
 *
 */
public class IDAttributeBasedEObjectIdentifierProvider implements EObjectIdentifierProvider {

  @Override
  public String identify(EObject eObject) {
    EAttribute eidAttribute = eObject.eClass().getEIDAttribute();
    if(eidAttribute != null){
      Object idValue = eObject.eGet(eidAttribute);
      if (idValue != null) {
        return EcoreUtil.convertToString(eidAttribute.getEAttributeType(), idValue);
      }
    }
    return null;
  }

}
