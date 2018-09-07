/*******************************************************************************
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Abel Hegedus, Tamas Borbas, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.serializer;

import java.io.File;

import org.eclipse.emf.diffmerge.patch.api.ModelPatch;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchException;

/**
 * Interface for saving the model patch to a new file. In the case of an already
 * existing file the content is overwritten.
 *
 * @param modelPatch
 *          an initialized ModelPatch object
 * @param file
 *          the file to write to
 *
 * @throws ModelPatchException
 */
public interface IModelPatchSerializer {

  public void serialize(ModelPatch modelPatch, File file) throws ModelPatchException;

  public String serialize(ModelPatch modelPatch) throws ModelPatchException;

  public ModelPatch load(File file) throws ModelPatchException;

  public ModelPatch load(String source) throws ModelPatchException;

  public String getPreferredFileExtension();

}


