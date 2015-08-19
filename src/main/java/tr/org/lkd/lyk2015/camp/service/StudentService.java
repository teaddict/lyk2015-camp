package tr.org.lkd.lyk2015.camp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.dao.StudentDao;
import tr.org.lkd.lyk2015.camp.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.model.Student;

@Service
@Transactional
public class StudentService extends GenericService<Student> {

	@Autowired
	StudentDao studentDao;

	public Student isExist(ApplicationFormDto applicationFormDto) {
		if (this.studentDao.getById(applicationFormDto.getStudent().getTckn()) != null) {
			return this.createStudent(applicationFormDto);
		} else {
			return this.studentDao.getById(applicationFormDto.getStudent().getTckn());
		}
	}

	public Student createStudent(ApplicationFormDto applicationFormDto) {
		// BOYLE YAPMAMIZIN NEDENI STUDENTI DATABASEDEN ÇEKMEMİZ GEREKİYOR
		// YOKSA OLMAZ; CASCADE EKLEMEMİZ GEREKİYOR
		// STUDENTI OLUŞTURUP SONRA APPLICATION ILE İLİŞKİLENDİREBİLİRZ
		Student studentFromDao = new Student();
		this.studentDao.create(applicationFormDto.getStudent());
		studentFromDao = applicationFormDto.getStudent();
		return studentFromDao;
	}

}
