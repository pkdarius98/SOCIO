package es.uam.app.actions.metamodels;

import es.uam.app.actions.DeleteMetamodel;
import es.uam.app.projects.MetaModelProject;
import es.uam.app.projects.ecore.ClassControl;
import es.uam.app.projects.ecore.Controlador;

public class RemoveClass extends DeleteMetamodel{

	private ClassControl class_;
	
	private MetaModelProject project;
	private boolean isUndo=false;
	private boolean isExecute=false;
	public RemoveClass(MetaModelProject proj, ClassControl class_) {
		setProject(proj);
		this.class_ = class_;
	}

	/*public RemoveClass(ClassControl classControl) {
		super(null);
		this.class_ = classControl;
	}*/

	@Override
	public void doIt() {
		if (isExecute()){
			return;
		}
		getProject().removeClass(class_);
		setExecute(true);
	}

	@Override
	public Controlador getObject() {
		doIt();
		return class_;
	}

	@Override
	public void undoIt() {
		if (!isExecute() || isUndo()){
			return;
		}
		
		project.unRemoveClass(class_);
		setUndo(true);
	}

	@Override
	public void redoIt() {
		if (!isExecute() || !isUndo()){
			return;
		}
		project.removeClass(class_);
		setUndo(false);
	}

	public MetaModelProject getProject() {
		return project;
	}

	public void setProject(MetaModelProject project) {
		this.project = project;
	}

	public boolean isUndo() {
		return isUndo;
	}

	public void setUndo(boolean isUndo) {
		this.isUndo = isUndo;
	}

	public boolean isExecute() {
		return isExecute;
	}

	public void setExecute(boolean isExecute) {
		this.isExecute = isExecute;
	}

}
