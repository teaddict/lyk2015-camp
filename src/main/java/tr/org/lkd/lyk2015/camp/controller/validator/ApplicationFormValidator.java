package tr.org.lkd.lyk2015.camp.controller.validator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import tr.org.lkd.lyk2015.camp.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.model.Application.WorkStatus;
import tr.org.lkd.lyk2015.camp.model.Student;
import tr.org.lkd.lyk2015.camp.service.BlackListService;
import tr.org.lkd.lyk2015.camp.service.ExamValidationService;
import tr.org.lkd.lyk2015.camp.service.TcknValidationService;

@Component
public class ApplicationFormValidator implements Validator {

	@Autowired
	private TcknValidationService tcknValidationService;

	// noktalı virgül sonrasında ctrl + space yaparsak otomatik isim geliyor
	@Autowired
	private BlackListService blackListService;

	@Autowired
	private ExamValidationService examValidationService;

	@Override
	public boolean supports(Class<?> claz) {
		return claz.equals(ApplicationFormDto.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

		ApplicationFormDto application = (ApplicationFormDto) target;

		// prevent inconsistent working status
		if (application.getApplication().getWorkStatus() == WorkStatus.NOT_WORKING
				&& application.getApplication().getOfficer().equals(true)) {
			errors.rejectValue("application.workStatus", "error.notWorkingOfficer",
					"Hem çalışmayıp hem nasıl memursun!");
		}
		// check course size
		application.getPreferredCourseIds().removeAll(Collections.singleton(null));
		if (application.getPreferredCourseIds().size() == 0) {
			errors.rejectValue("preferredCourseIds", "error.preferredCourseNotSelected",
					"En az bir kurs seçmelisiniz.");
		}

		// prevent same course
		int listSize = application.getPreferredCourseIds().size();
		Set<Long> set = new HashSet<>(application.getPreferredCourseIds());
		int setSize = set.size();

		if (listSize != setSize) {
			errors.rejectValue("preferredCourseIds", "error.preferredCourseSame", "Aynı kursu seçemezsiniz!");
		}

		// bizim bi hata dosyamız var , o dosyadan "error.preferredCourseSame"
		// arıyor, eğer varsa bu hata onu yazdırıyor
		// yoksa ""Aynı kursu seçemezsiniz!" mesajını gösteriyor.

		// validate tckn from web service
		Student student = application.getStudent();
		boolean tcknValid = this.tcknValidationService.validate(student.getName(), student.getSurname(),
				student.getBirthDate(), student.getTckn());
		if (!tcknValid) {
			errors.rejectValue("student.tckn", "error.tcknValidate", "Tc Kimlik no hatalı.");
		}

		boolean checkBlack = this.blackListService.validate(student.getTckn(), student.getEmail(), student.getName(),
				student.getSurname());
		if (!checkBlack) {
			errors.rejectValue("student.email", "error.checkBlackList", "Kara listedesiniz!");
		}

		// BURASI DEĞİŞCEK
		boolean checkExam = this.examValidationService.validate(student.getTckn(), student.getEmail());
		if (!checkBlack) {
			errors.rejectValue("student.exam", "error.checkExam", "Ön sınavı geçmemişsiniz!");
		}
	}

}
