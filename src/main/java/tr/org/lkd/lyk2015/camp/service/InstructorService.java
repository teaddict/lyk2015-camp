package tr.org.lkd.lyk2015.camp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.model.Admin;
import tr.org.lkd.lyk2015.camp.model.Instructor;
import tr.org.lkd.lyk2015.camp.dao.AdminDao;
import tr.org.lkd.lyk2015.camp.dao.InstructorDao;

@Service
@Transactional
public class InstructorService extends GenericService<Instructor> {
	
	@Autowired
	private InstructorDao instructorDao;


}