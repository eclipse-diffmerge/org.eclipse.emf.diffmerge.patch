/*******************************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
