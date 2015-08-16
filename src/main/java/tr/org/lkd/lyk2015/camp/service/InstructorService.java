package tr.org.lkd.lyk2015.camp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.dao.AdminDao;
import tr.org.lkd.lyk2015.camp.dao.InstructorDao;
import tr.org.lkd.lyk2015.camp.model.Admin;
import tr.org.lkd.lyk2015.camp.model.Instructor;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
@Service
public class InstructorService implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected InstructorDao instructorDao;

    public Long create(final Instructor instructor) {

        if(instructor == null) {
            throw new RuntimeException("Model cannot be null");
        }

        return instructorDao.create(instructor);
    }
    
    public List<Instructor> getAll() {

		return instructorDao.getAll();
	}
}