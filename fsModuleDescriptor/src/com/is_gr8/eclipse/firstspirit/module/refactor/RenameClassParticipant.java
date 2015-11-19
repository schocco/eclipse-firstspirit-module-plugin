package com.is_gr8.eclipse.firstspirit.module.refactor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jdt.core.IType;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.RenameParticipant;

/**
 * A RenameParticipant that performs changes in the module.xml if referenced classes
 * are renamed.
 *
 */
public class RenameClassParticipant extends RenameParticipant {
	
	private IType element;

	@Override
	public RefactoringStatus checkConditions(IProgressMonitor arg0, CheckConditionsContext arg1)
			throws OperationCanceledException {
		return new RefactoringStatus();
	}

	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		String oldName = element.getFullyQualifiedName();
		String newName = getNewFullyQualifiedName(oldName);
		IFile moduleDescriptor = getModuleDescriptor();
		if(moduleDescriptor.exists()) {
			return new ModuleDescriptorChange(moduleDescriptor, oldName, newName);
		}
		return null;
	}
	

	private String getNewFullyQualifiedName(String oldName) {
		if(oldName.contains(".")) {
			return oldName.substring(0, oldName.lastIndexOf(".")+1) + getArguments().getNewName();
		} else {
			return getArguments().getNewName();
		}
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
