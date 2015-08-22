package tr.org.lkd.lyk2015.camp.service;

import java.security.AccessControlException;
import java.util.ArrayList;
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

	public enum ValidationResult {
		NO_SUCH_APPLICATION, ALREADY_VALIDATED, SUCCESS
	}

	public void create(ApplicationFormDto applicationFormDto) {

		// NESNELERİMİZ
		Application application = applicationFormDto.getApplication();
		Student student = applicationFormDto.getStudent();

		// UUID ÜRET Ve link hazırla
		String url = URL_BASE + this.generateUUID(application);

		// COURSELARI ALIP SET EDELİM
		this.getCoursesByIds(application, applicationFormDto.getPreferredCourseIds());

		String password = UUID.randomUUID().toString();
		password = password.substring(0, 8);

		// APPLICATION OBJESİNE STUDENTİ BAĞLAYALIM
		Student studentFromDao = this.studentDao.getByTckn(applicationFormDto.getStudent().getTckn());
		if (studentFromDao == null) {
			// BOYLE YAPMAMIZIN NEDENI STUDENTI DATABASEDEN ÇEKMEMİZ GEREKİYOR
			// YOKSA OLMAZ; CASCADE EKLEMEMİZ GEREKİYOR
			// STUDENTI OLUŞTURUP SONRA APPLICATION ILE İLİŞKİLENDİREBİLİRZ
			student.setPassword(this.passwordEncoder.encode(password));
			this.studentDao.create(applicationFormDto.getStudent());
			studentFromDao = student;
			application.setOwner(studentFromDao);

		} else {
			application.setOwner(studentFromDao);
			// kullanıcı varsa dokunma
			// studentFromDao.setPassword(this.passwordEncoder.encode(password));
		}

		application.setYear(Calendar.getInstance().get(Calendar.YEAR));
		url = url + "\ngiriş bilgileriniz\n" + studentFromDao.getEmail() + "\nŞifreniz: " + password;
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
		if (application == null) {
			return false;
		}
		Student student = application.getOwner();
		application.setValidated(true);
		// update çağırmasam da transactional old için
		// yaptığımd eğişiklikleri direk database yazar
		this.applicationDao.update(application);
		if (this.emailService.sendConfirmation(student.getEmail(), "Başvuru Formu Doğrulaması", this.onayMesajı)) {
			System.out.println("email gönderildi.");
		} else {
			System.out.println("email gönderilemedi");
		}
		return true;

	}

	public ApplicationFormDto createApplicationDto(Long id) {
		Application application = this.applicationDao.getStudentsApplication(id);
		Student student = this.studentDao.getById(id);
		List<Long> courseIds = new ArrayList<>();
		for (Course course : application.getPreferredCourses()) {
			courseIds.add(course.getId());
		}
		// null göndermemesi için
		int emptySize = 3 - courseIds.size();
		for (int i = 0; i < emptySize; i++) {
			courseIds.add(null);
		}

		ApplicationFormDto applicationFormDto = new ApplicationFormDto();
		applicationFormDto.setApplication(application);
		applicationFormDto.setPreferredCourseIds(courseIds);
		applicationFormDto.setStudent(student);

		return applicationFormDto;
	}

	public void update(ApplicationFormDto applicationFormDto) {
		// NESNELERİMİZ
		Application application = applicationFormDto.getApplication();

		// COURSELARI ALIP SET EDELİM
		//
		this.getCoursesByIds(application, applicationFormDto.getPreferredCourseIds());

		Application applicationFromDao = this.applicationDao.getById(application.getId());
		applicationFromDao.setCorporation(application.getCorporation());
		applicationFromDao.setGithubLink(application.getGithubLink());
		applicationFromDao.setEnglishLevel(application.getEnglishLevel());
		applicationFromDao.setNeedAccomodation(application.isNeedAccomodation());
		applicationFromDao.setWorkDetails(application.getWorkDetails());
		applicationFromDao.setOfficer(application.isOfficer());
		applicationFromDao.setWorkStatus(application.getWorkStatus());

	}

	// APPLICATION - STUDENT EŞLEŞMESİ
	// O APPLICATION O STUDENTE Mİ AİT ?
	public void isUserAuthorizedForForm(Student student, Application application) {
		Application applicationFromDb = this.applicationDao.getStudentsApplication(student.getId());

		if (applicationFromDb == null || !applicationFromDb.getId().equals(application.getId())) {
			throw new AccessControlException("This form is not owned by current user");
		}

	}

}
