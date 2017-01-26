/*******************************************************************************
 * Copyright (c) 2016-2017, Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Abel Hegedus, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.tests.all

import org.eclipse.emf.diffmerge.patch.tests.emf.ModelPatchEmfTestSuite
import org.eclipse.emf.diffmerge.patch.tests.persistence.json.ModelPatchPersistenceJsonTestSuite
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses
import org.eclipse.emf.diffmerge.patch.tests.ModelPatchTestSuite

@SuiteClasses(#[
  ModelPatchTestSuite,
  ModelPatchEmfTestSuite,
  ModelPatchPersistenceJsonTestSuite
])

@RunWith(Suite)
class RunAllTestSuite {}
