/*******************************************************************************
 * Copyright (c) 2016-2017, Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Balazs Grill, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.tests.emf

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses
import org.eclipse.emf.diffmerge.patch.tests.emf.identifier.IdentifierTestSuite

@SuiteClasses(#[
  EMFModelPatchMetadataTest,
//	ModelPatchScalerTest,				// no asserts
  CPSModelPatchApplierTest,
//	EMFDiffMergeTest,					// no asserts
  EMFModelAccessTest,
  ModelPatchRecorderTest,
  ModelPatchApplierSmokeTests,
  ModelPatchExporterTest,
  IdentifierTestSuite
])
@RunWith(Suite)
class ModelPatchEmfTestSuite {

}
