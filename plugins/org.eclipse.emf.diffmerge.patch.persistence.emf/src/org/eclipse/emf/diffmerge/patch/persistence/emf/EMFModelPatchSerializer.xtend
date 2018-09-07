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
package org.eclipse.emf.diffmerge.patch.persistence.emf

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.nio.charset.StandardCharsets
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.diffmerge.patch.api.ModelPatch
import org.eclipse.emf.diffmerge.patch.api.ModelPatchException
import org.eclipse.emf.diffmerge.patch.persistence.emf.emodelpatch.EModelPatch
import org.eclipse.emf.diffmerge.patch.serializer.IModelPatchSerializer
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl

class EMFModelPatchSerializer implements IModelPatchSerializer {

  val persister = new EMFModelPatchPersister

  override load(File file) throws ModelPatchException {
    try{
      val rs = new ResourceSetImpl
      val r = rs.getResource(URI.createFileURI(file.path), true)
      val emodelpatch = r.contents.head
      if(emodelpatch instanceof EModelPatch){
        val modelpatch = persister.load(emodelpatch)
        return modelpatch
      }
    } catch(Exception e){
      throw new ModelPatchException("Exception occurred while loading model patch", e)
    }
  }

  override load(String source) throws ModelPatchException {
    var InputStream stream
    try{
      stream = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8))
      val reader = stream
      val rs = new ResourceSetImpl
      val r = rs.createResource(URI.createFileURI("file://dummy.emodelpatch")) => [
        load(reader, #{})
      ]
      val emodelpatch = r.contents.head
      if(emodelpatch instanceof EModelPatch){
        val modelpatch = persister.load(emodelpatch)
        return modelpatch
      }
    } catch(Exception e){
      throw new ModelPatchException("Exception occurred while loading model patch", e)
    } finally {
      stream.close
    }
  }

  override serialize(ModelPatch modelPatch, File file) throws ModelPatchException {
    try{
      val rs = new ResourceSetImpl
      val r = rs.createResource(URI.createFileURI(file.path))
      val emodelpatch = persister.persist(modelPatch)
      r.contents.add(emodelpatch)
      r.save(#{})
    } catch(Exception e){
      throw new ModelPatchException("Exception occurred while loading model patch", e)
    }
  }

  override serialize(ModelPatch modelPatch) throws ModelPatchException {
    var ByteArrayOutputStream stream
    var String result = null
    try{
      val rs = new ResourceSetImpl
      val r = rs.createResource(URI.createFileURI("file://dummy.emodelpatch"))
      val emodelpatch = persister.persist(modelPatch)
      r.contents.add(emodelpatch)
      stream = new ByteArrayOutputStream()
      r.save(stream, #{})
      result = stream.toString(StandardCharsets.UTF_8.name)
    } catch(Exception e){
      throw new ModelPatchException("Exception occurred while loading model patch", e)
    } finally {
      stream.close
    }
    return result
  }

  override getPreferredFileExtension() {
    return "emodelpatch"
  }

}
