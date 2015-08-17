package tr.org.lkd.lyk2015.camp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.model.Admin;
import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.model.Instructor;
import tr.org.lkd.lyk2015.camp.dao.AdminDao;
import tr.org.lkd.lyk2015.camp.dao.CourseDao;
import tr.org.lkd.lyk2015.camp.dao.InstructorDao;

@Service
@Transactional
public class InstructorService extends GenericService<Instructor> {
	
	@Autowired
	private InstructorDao instructorDao;
										
	@Autowired
	private CourseDao courseDao;

	public void create(Instructor instructor, List<Long> ids) {
		
		List<Course> courses = courseDao.getByIds(ids);
		
		Set<Course> setCourse = new HashSet();
		setCourse.addAll(courses);
		
		instructor.setCourses(setCourse);

		instructorDao.create(instructor);
	}


}