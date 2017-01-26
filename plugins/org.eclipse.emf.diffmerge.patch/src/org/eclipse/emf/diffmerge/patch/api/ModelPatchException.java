/*******************************************************************************
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Abel Hegedus, Tamas Borbas, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.api;

public class ModelPatchException extends Exception {

  private static final long serialVersionUID = 1L;

  public ModelPatchException(Throwable rootCause)
    {
        super(rootCause);
    }

    public ModelPatchException(String msg)
    {
        super(msg);
    }

    public ModelPatchException(String msg, Throwable rootCause)
    {
        super(msg, rootCause);
    }
}
