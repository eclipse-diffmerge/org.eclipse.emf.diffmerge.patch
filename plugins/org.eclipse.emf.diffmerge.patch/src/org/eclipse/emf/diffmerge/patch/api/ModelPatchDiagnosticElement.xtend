/*******************************************************************************
 * Copyright (c) 2016-2017, Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.api

import org.eclipse.xtend.lib.annotations.Accessors

class ModelPatchDiagnosticElement {
  @Accessors(PUBLIC_GETTER, PUBLIC_SETTER)
  private Exception caughtException
  @Accessors(PUBLIC_GETTER, PUBLIC_SETTER)
  private String message
  @Accessors(PUBLIC_GETTER, PUBLIC_SETTER)
  private ModelPatchEntry problematicEntry
}
