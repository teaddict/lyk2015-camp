package tr.org.lkd.lyk2015.camp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.dao.CourseDao;
import tr.org.lkd.lyk2015.camp.dao.InstructorDao;
import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.model.Instructor;

@Service
@Transactional
public class InstructorService extends GenericService<Instructor> {

	@Autowired
	private InstructorDao instructorDao;

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void create(Instructor instructor, List<Long> ids) {

		List<Course> courses = this.courseDao.getByIds(ids);
		instructor.getCourses().addAll(courses);
		/*
		 * Set<Course> setCourse = new HashSet<>(); setCourse.addAll(courses);
		 * 
		 * instructor.setCourses(setCourse);
		 */
		instructor.setPassword(this.passwordEncoder.encode(instructor.getPassword()));
		this.instructorDao.create(instructor);
	}

	public void update(Instructor instructor, List<Long> ids) {

		List<Course> courses = this.courseDao.getByIds(ids);
		Set<Course> setCourse = new HashSet<>(courses);
		instructor.setCourses(setCourse);

		this.instructorDao.update(instructor);
	}

	public Instructor getInstructorWithCourses(Long id) {
		Instructor instructor = this.instructorDao.getByIdWithCourses(id);

		return instructor;
	}

}