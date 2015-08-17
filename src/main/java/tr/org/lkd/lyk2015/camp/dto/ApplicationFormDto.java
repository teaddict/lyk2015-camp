package tr.org.lkd.lyk2015.camp.dto;

import tr.org.lkd.lyk2015.camp.model.Application;
import tr.org.lkd.lyk2015.camp.model.Student;

public class ApplicationFormDto {

	Application application = new Application();
	Student student = new Student();
	
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	

	
}
