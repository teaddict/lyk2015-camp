package tr.org.lkd.lyk2015.camp.dto;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import tr.org.lkd.lyk2015.camp.model.Application;
import tr.org.lkd.lyk2015.camp.model.Student;

public class ApplicationFormDto {
	@Valid
	Application application = new Application();
	@Valid
	Student student = new Student();
	@Size(min = 1, max = 3)
	public List<Long> preferredCourseIds = Arrays.asList(null, null, null);

	public List<Long> getPreferredCourseIds() {
		return this.preferredCourseIds;
	}

	public void setPreferredCourseIds(List<Long> preferredCourseIds) {
		this.preferredCourseIds = preferredCourseIds;
	}

	public Application getApplication() {
		return this.application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
