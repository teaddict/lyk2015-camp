package tr.org.lkd.lyk2015.camp.service;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.dao.ApplicationDao;
import tr.org.lkd.lyk2015.camp.dao.CourseDao;
import tr.org.lkd.lyk2015.camp.dao.StudentDao;
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

	@Autowired
	private EmailService emailService;

	@Autowired
	StudentDao studentDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final String URL_BASE = "http://localhost:8080/camp/application/validate/";

	private static final String onayMesajı = "Başvurunuz doğrulanmıştır. Seçilme sonuçları için mailinize bakmayı unutmayın";

	public void create(ApplicationFormDto applicationFormDto) {

		// NESNELERİMİZ
		Application application = applicationFormDto.getApplication();
		Student student = applicationFormDto.getStudent();

		// UUID ÜRET Ve link hazırla
		String url = URL_BASE + this.generateUUID(application);

		// COURSELARI ALIP SET EDELİM
		this.getCoursesByIds(application, applicationFormDto.getPreferredCourseIds());

		// APPLICATION OBJESİNE STUDENTİ BAĞLAYALIM
		if (this.studentDao.getByTckn(applicationFormDto.getStudent().getTckn()) == null) {
			// BOYLE YAPMAMIZIN NEDENI STUDENTI DATABASEDEN ÇEKMEMİZ GEREKİYOR
			// YOKSA OLMAZ; CASCADE EKLEMEMİZ GEREKİYOR
			// STUDENTI OLUŞTURUP SONRA APPLICATION ILE İLİŞKİLENDİREBİLİRZ
			Student studentFromDao = new Student();
			this.studentDao.create(applicationFormDto.getStudent());
			studentFromDao = applicationFormDto.getStudent();
			application.setOwner(studentFromDao);

		} else {
			application.setOwner(this.studentDao.getByTckn(applicationFormDto.getStudent().getTckn()));
		}

		String uuid = UUID.randomUUID().toString();
		application.getOwner().setPassword(this.passwordEncoder.encode("lyk2015"));
		application.setYear(Calendar.getInstance().get(Calendar.YEAR));

		if (this.emailService.sendConfirmation(student.getEmail(), "Başvuru Onayı", url)) {
			System.out.println("email gönderildi.");
		} else {
			System.out.println("email gönderilemedi");
		}
		this.applicationDao.create(application);

	}

	public String generateUUID(Application application) {
		String uuid = UUID.randomUUID().toString();
		application.setValidateId(uuid);
		return uuid;
	}

	public void getCoursesByIds(Application application, List<Long> ids) {

		List<Course> courses = this.courseDao.getByIds(ids);
		application.getPreferredCourses().clear();
		application.getPreferredCourses().addAll(courses);
	}

	public boolean validate(String validationId) {

		Application application = this.applicationDao.getByValidationId(validationId);
		Student student = application.getOwner();
		application.setValidated(true);
		// update çağırmasam da transactional old için
		// yaptığımd eğişiklikleri direk database yazar
		this.applicationDao.update(application);
		if (this.emailService.sendConfirmation(student.getEmail(), "Başvuru Formu Doğrulaması", onayMesajı)) {
			System.out.println("email gönderildi.");
		} else {
			System.out.println("email gönderilemedi");
		}
		return true;

	}

}
