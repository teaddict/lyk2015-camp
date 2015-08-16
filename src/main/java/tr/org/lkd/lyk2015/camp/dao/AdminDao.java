package tr.org.lkd.lyk2015.camp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tr.org.lkd.lyk2015.camp.model.Admin;

@Repository
public class AdminDao {

    @Autowired
    protected SessionFactory sessionFactory;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public Long create(final Admin admin){

        final Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(admin);
    }

}