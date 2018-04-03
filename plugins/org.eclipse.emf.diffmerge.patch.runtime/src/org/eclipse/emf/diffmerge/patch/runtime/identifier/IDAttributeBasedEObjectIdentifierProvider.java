/*******************************************************************************
 * Copyright (c) 2017 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
