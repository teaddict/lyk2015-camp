package tr.org.lkd.lyk2015.camp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.dao.AdminDao;
import tr.org.lkd.lyk2015.camp.model.Admin;

import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
@Service
public class AdminService implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected AdminDao adminDao;

    public Long create(final Admin admin) {

        if(admin == null) {
            throw new RuntimeException("Model cannot be null");
        }

        return adminDao.create(admin);
    }
}