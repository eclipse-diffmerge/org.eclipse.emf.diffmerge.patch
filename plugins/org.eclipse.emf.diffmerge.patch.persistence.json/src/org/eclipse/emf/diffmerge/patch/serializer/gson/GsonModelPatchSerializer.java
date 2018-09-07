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
package org.eclipse.emf.diffmerge.patch.serializer.gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.emf.diffmerge.patch.api.ModelPatch;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchEntry;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchException;
import org.eclipse.emf.diffmerge.patch.api.ModelPatchMetadata;
import org.eclipse.emf.diffmerge.patch.persistence.ModelPatchPersister;
import org.eclipse.emf.diffmerge.patch.serializer.IModelPatchSerializer;
import org.eclipse.emf.diffmerge.patch.serializer.gson.typeadapters.AbstractSubclassAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonModelPatchSerializer implements IModelPatchSerializer, ModelPatchPersister<String>{

  public static GsonModelPatchSerializer create(ClassLoader classLoader) {
    GsonModelPatchSerializer gsonModelPatchSerializer = new GsonModelPatchSerializer();
    gsonModelPatchSerializer.classLoader = classLoader;
    return gsonModelPatchSerializer;
  }

  private ClassLoader classLoader = GsonModelPatchSerializer.class.getClassLoader();

  @Override
  public void serialize(ModelPatch modelPatch, File file) throws ModelPatchException {
    file.getParentFile().mkdirs();
    try {
      FileWriter writer = new FileWriter(file);
      createGson().toJson(modelPatch, writer);
      writer.flush();
      writer.close();
    } catch (IOException e) {
      throw new ModelPatchException("IO exception caught during GSON serialization", e);
    }

  }

  @Override
  public String serialize(ModelPatch modelPatch) throws ModelPatchException {
    return createGson().toJson(modelPatch);
  }

  @Override
  public ModelPatch load(File file) throws ModelPatchException {
    try {
      FileReader reader = new FileReader(file);
      ModelPatch patch = createGson().fromJson(reader, ModelPatch.class);
      if(patch.getEntries().isEmpty()){
        throw new ModelPatchException("Loaded GSON patch is empty, it probably has incorrect JSON syntax");
      }
      reader.close();
      return patch;
    } catch (IOException e) {
      throw new ModelPatchException("GSON deserialization failed, check if source file has the correct JSON syntax", e);
    }
  }

  @Override
  public ModelPatch load(String jsonSource) throws ModelPatchException {
    return createGson().fromJson(jsonSource, ModelPatch.class);
  }

  private Gson createGson(){
    return new GsonBuilder()
        .registerTypeAdapter(ModelPatchEntry.class, new AbstractSubclassAdapter<ModelPatchEntry>(classLoader))
        .registerTypeAdapter(ModelPatchMetadata.class, new AbstractSubclassAdapter<ModelPatchMetadata>(classLoader))
        .setPrettyPrinting()
        .create();
  }

  @Override
  public String persist(ModelPatch modelPatch) throws ModelPatchException {
    return serialize(modelPatch);
  }

  @Override
  public String getPreferredFileExtension() {
    return "modelpatch";
  }

}
