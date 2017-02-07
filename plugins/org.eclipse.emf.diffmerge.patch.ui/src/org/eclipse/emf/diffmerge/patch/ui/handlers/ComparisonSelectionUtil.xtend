/*******************************************************************************
 * Copyright (c) 2016-2017, Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Abel Hegedus, Tamas Borbas, Peter Lunk (IncQuery Labs Ltd.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patch.ui.handlers

import java.lang.reflect.InvocationTargetException
import java.util.ArrayList
import java.util.Collection
import java.util.Collections
import java.util.HashSet
import java.util.List
import java.util.Map.Entry
import java.util.Set
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.emf.common.util.EMap
import org.eclipse.emf.diffmerge.api.IMatch
import org.eclipse.emf.diffmerge.api.Role
import org.eclipse.emf.diffmerge.api.diff.IDifference
import org.eclipse.emf.diffmerge.api.diff.IPresenceDifference
import org.eclipse.emf.diffmerge.diffdata.EMatch
import org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode
import org.eclipse.emf.diffmerge.ui.viewers.MergeImpactViewer.ImpactInput
import org.eclipse.jface.operation.IRunnableWithProgress
import org.eclipse.ui.PlatformUI
import org.eclipse.ui.progress.IProgressService

class ComparisonSelectionUtil {

  protected def Collection<IDifference> getDiffsToMerge(ComparisonSelection selection, boolean toLeft_p,
    EMFDiffNode input) {
    val showImpact = input.isShowMergeImpact();
    input.setDefaultShowImpact(false);
    val Role destination = Role.REFERENCE;
    val isCoverChildren = true;
    val isIncrementalMode = false;
    val List<EMatch> selectedMatches = getSelectedMatchesForInteractions(selection)
    val Collection<IDifference> toMerge = if (!selectedMatches.isEmpty()) {
        getDifferencesToMerge(selectedMatches, destination, isCoverChildren, isIncrementalMode, input)
      } else {
        input.categoryManager.getPendingDifferencesFiltered(selection.asDifferencesToMerge())
      }
    val ImpactInput mergeInput = new ImpactInput(toMerge, toLeft_p, input);
    val IProgressService progress = PlatformUI.getWorkbench().getProgressService();
    try {
      progress.busyCursorWhile(new IRunnableWithProgress() {
        /**
         * @see IRunnableWithProgress#run(IProgressMonitor)
         */
        override void run(IProgressMonitor monitor_p) throws InvocationTargetException, InterruptedException {
          mergeInput.compute(monitor_p);
        }
      });
    } catch (Exception ex) {
      return null;
    }
    input.setDefaultShowImpact(showImpact);

    val EMap<IMatch, Collection<IDifference>> explicitImpacts = mergeInput.getImpact(true);
    val EMap<IMatch, Collection<IDifference>> implicitImpacts = mergeInput.getImpact(false);
    val Set<IDifference> diffs = new HashSet<IDifference>();
    for (Entry<IMatch, Collection<IDifference>> impact : implicitImpacts) {
      diffs.addAll(impact.getValue());
    }
    for (Entry<IMatch, Collection<IDifference>> impact : explicitImpacts) {
      diffs.addAll(impact.getValue());
    }

    return diffs;
  }

  protected def List<EMatch> getSelectedMatchesForInteractions(ComparisonSelection selection_p) {
    var List<EMatch> selectedMatches = selection_p.getSelectedMatches();
    if (selectedMatches.isEmpty()) {
      val List<EMatch> treePath = selection_p.getSelectedTreePath();
      if (!treePath.isEmpty()) {
        selectedMatches = Collections.singletonList(treePath.get(treePath.size() - 1));
      }
    }
    return selectedMatches;
  }

  /**
   * Copied from {@link org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer}
   *
   * Return the differences to merge from a given list of selected matches and the given
   * criteria
   * @param selectedMatches_p a non-null list
   * @param coverChildren_p whether children of the matches must be covered
   * @param incrementalMode_p whether optional deletions must be skipped
   * @return a non-null, potentially empty, unmodifiable list
   */
  protected def List<IDifference> getDifferencesToMerge(List<EMatch> selectedMatches_p, Role destination_p,
    boolean coverChildren_p, boolean incrementalMode_p, EMFDiffNode input) {
    val List<IDifference> result = new ArrayList<IDifference>();
    val IProgressService progress = PlatformUI.getWorkbench().getProgressService();
    try {
      progress.busyCursorWhile(new IRunnableWithProgress() {
        /**
         * @see IRunnableWithProgress#run(IProgressMonitor)
         */
        override void run(IProgressMonitor monitor_p) throws InvocationTargetException, InterruptedException {
          for (EMatch selectedMatch : selectedMatches_p) {
            if (coverChildren_p) {
              addDifferencesToMergeRec(result, selectedMatch, destination_p, incrementalMode_p, input);
            } else {
              addDifferencesToMerge(result, selectedMatch, destination_p, incrementalMode_p, input);
            }
          }
        }
      });
    } catch (Exception e) {
      // Proceed
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * Copied from {@link org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer}
   *
   * Add differences to merge on the given match to the given list according
   * to the given criteria
   * @param toMerge_p a non-null, modifiable list
   * @param match_p a non-null match
   * @param destination_p a non-null role which is TARGET or REFEREBCE
   * @param incrementalMode_p whether optional deletions must be skipped
   */
  protected def void addDifferencesToMerge(List<IDifference> toMerge_p, IMatch match_p, Role destination_p,
    boolean incrementalMode_p, EMFDiffNode input) {
    for (IDifference difference : match_p.getAllDifferences()) {
      if (!input.getCategoryManager().isFiltered(difference)) {
        if (!incrementalMode_p || difference instanceof IPresenceDifference &&
          (difference as IPresenceDifference).getPresenceRole() != destination_p)
          toMerge_p.add(difference);
      }
    }
  }

  /**
   * Copied from {@link org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer}
   *
   * Add differences to merge on the given match and its children to the given list according
   * to the given criteria
   * @param toMerge_p a non-null, modifiable list
   * @param match_p a non-null match
   * @param destination_p a non-null role which is TARGET or REFEREBCE
   * @param incrementalMode_p whether optional deletions must be skipped
   */
  protected def void addDifferencesToMergeRec(List<IDifference> toMerge_p, IMatch match_p, Role destination_p,
    boolean incrementalMode_p, EMFDiffNode input) {
    addDifferencesToMerge(toMerge_p, match_p, destination_p, incrementalMode_p, input);
    for (IMatch child : input.getCategoryManager().getChildrenForMerge(match_p)) {
      addDifferencesToMergeRec(toMerge_p, child, destination_p, incrementalMode_p, input);
    }
  }
}
