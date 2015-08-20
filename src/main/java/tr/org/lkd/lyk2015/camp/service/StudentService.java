package tr.org.lkd.lyk2015.camp.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.model.Student;

@Service
@Transactional
public class StudentService extends GenericService<Student> {

}
