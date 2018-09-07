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
package org.eclipse.emf.diffmerge.patch.serializer.gson.typeadapters;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class AbstractSubclassAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {

  private static final String CLASSNAME = "CLASSNAME";
  private static final String INSTANCE = "INSTANCE";
  private ClassLoader classLoader;

  public AbstractSubclassAdapter(ClassLoader classLoader) {
    this.classLoader = classLoader;
  }

  @Override
  public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {

    JsonObject retValue = new JsonObject();
    String className = src.getClass().getName();
    retValue.addProperty(CLASSNAME, className);
    JsonElement elem = context.serialize(src);
    retValue.add(INSTANCE, elem);
    return retValue;
  }

  @Override
  public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    JsonObject jsonObject = json.getAsJsonObject();
    JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
    String className = prim.getAsString();

    Class<?> klass = null;
    try {
      klass = classLoader.loadClass(className);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      throw new JsonParseException(e.getMessage());
    }
    return context.deserialize(jsonObject.get(INSTANCE), klass);
  }


}
