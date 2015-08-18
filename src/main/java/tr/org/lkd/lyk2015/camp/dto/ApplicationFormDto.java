package tr.org.lkd.lyk2015.camp.dto;

import java.util.ArrayList;
import java.util.List;

import tr.org.lkd.lyk2015.camp.model.Application;
import tr.org.lkd.lyk2015.camp.model.Student;

public class ApplicationFormDto {

	Application application = new Application();
	Student student = new Student();
	
	public List<Long> preferredCourseIds = new ArrayList<>();
	
	
	public List<Long> getPreferredCourseIds() {
		return preferredCourseIds;
	}
	public void setPreferredCourseIds(List<Long> preferredCourseIds) {
		this.preferredCourseIds = preferredCourseIds;
	}
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
