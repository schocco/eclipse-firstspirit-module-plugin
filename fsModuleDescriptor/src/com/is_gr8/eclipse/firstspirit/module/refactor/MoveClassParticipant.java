package com.is_gr8.eclipse.firstspirit.module.refactor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.internal.core.PackageFragment;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.MoveParticipant;

/**
 * A move participant that creates a change if a class is moved to another package.
 *
 */
public class MoveClassParticipant extends MoveParticipant {
	
	private IType element;

	@Override
	public RefactoringStatus checkConditions(IProgressMonitor arg0, CheckConditionsContext arg1)
			throws OperationCanceledException {
		return new RefactoringStatus();
	}

	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		String oldName = element.getFullyQualifiedName();
		IPackageFragment destination = (IPackageFragment) getArguments().getDestination();
		IFile moduleDescriptor = getModuleDescriptor();
		if(moduleDescriptor.exists()) {
			String newName = getNewName(destination);
			return new ModuleDescriptorChange(moduleDescriptor, oldName, newName);
		}
		return null;
	}

	private String getNewName(IPackageFragment destination) {
		String newName = element.getElementName();
		if(destination != null && !destination.getElementName().isEmpty()) {
			newName = destination.getElementName() + "." + newName;
		}
		return newName;
	}
	

	private IFile getModuleDescriptor() {
		IProject project = element.getJavaProject().getProject();
		return project.getFile("src/main/module/module.xml");
	}

	@Override
	public String getName() {
		return "module.xml participant";
	}

	@Override
	protected boolean initialize(Object element) {
		this.element = (IType) element;
		return true;
	}
	

}
