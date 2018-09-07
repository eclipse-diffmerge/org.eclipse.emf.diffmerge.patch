/*******************************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *      Abel Hegedus, Tamas Borbas, Balazs Grill, Peter Lunk, Daniel Segesdi (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.ui;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class ModelPatchUIPlugin extends AbstractUIPlugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.eclipse.emf.diffmerge.patch.ui"; //$NON-NLS-1$

  public static final String ICON_GENERATE_PATCH = "generate_patch";
  public static final String ICON_APPLY_PATCH = "apply_patch";

  // The shared instance
  private static ModelPatchUIPlugin plugin;

  /**
   * The constructor
   */
  public ModelPatchUIPlugin() {
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
   * BundleContext)
   */
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
   * BundleContext)
   */
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   *
   * @return the shared instance
   */
  public static ModelPatchUIPlugin getDefault() {
    return plugin;
  }

  @Override
  protected void initializeImageRegistry(ImageRegistry reg) {
      super.initializeImageRegistry(reg);
      reg.put(ICON_GENERATE_PATCH, imageDescriptorFromPlugin(PLUGIN_ID, "icons/generate_16.png"));
      reg.put(ICON_APPLY_PATCH, imageDescriptorFromPlugin(PLUGIN_ID, "icons/apply_16.png"));
  }

}
