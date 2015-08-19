package tr.org.lkd.lyk2015.camp.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.dao.ApplicationDao;
import tr.org.lkd.lyk2015.camp.dao.CourseDao;
import tr.org.lkd.lyk2015.camp.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.model.Application;
import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.model.Student;

@Service
@Transactional
public class ApplicationService extends GenericService<Application> {

	// @Autowired
	// ApplicationDao applicationDao;

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private StudentService studentService;

	@Autowired
	private ApplicationDao applicationDao;

	private static final String URL_BASE = "http://localhost:8080/camp/application/validate/";

	public void create(ApplicationFormDto applicationFormDto) {

		// NESNELERİMİZ
		Application application = applicationFormDto.getApplication();
		Student student = applicationFormDto.getStudent();

		// UUID ÜRET Ve link hazırla
		String url = URL_BASE + this.generateUUID(application);

		// COURSELARI ALIP SET EDELİM
		this.getCoursesByIds(application, applicationFormDto.getPreferredCourseIds());

		// APPLICATION OBJESİNE STUDENTİ BAĞLAYALIM
		application.setOwner(this.studentService.isExist(applicationFormDto));

		this.applicationDao.create(application);

	}

	public String generateUUID(Application application) {
		String uuid = UUID.randomUUID().toString();
		application.setValidateId(uuid);
		return uuid;
	}

	public void getCoursesByIds(Application application, List<Long> ids) {

		List<Course> courses = this.courseDao.getByIds(ids);
		application.getPreferredCourses().addAll(courses);
	}

}
