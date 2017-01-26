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
package org.eclipse.emf.diffmerge.patch.serializer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.emf.diffmerge.patch.api.ModelPatch;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchException;

public interface IModelPatchSerializer {
  /**
   * Interface for saving the model patch to a new file.
   * In the case of an already existing file the content is overwritten.
   *
   *
   * @param modelPatch
   *                an initialized ModelPatch object with properly
   *                used JsonProperty annotations
   * @param file
   *                the file to write to
   *
   * @throws IOException
   *                 if some error occur during the JSON serialization
   * @throws ModelPatchException
   */

  public void serialize(ModelPatch modelPatch, File file)
      throws ModelPatchException;

  public String serialize(ModelPatch modelPatch) throws ModelPatchException;

  public InputStream serializeStream(ModelPatch modelPatch) throws ModelPatchException;

  public ModelPatch load(File file) throws ModelPatchException;

  public ModelPatch load(String jsonSource) throws ModelPatchException;

  public ModelPatch load(InputStream jsonStream) throws ModelPatchException;

}


