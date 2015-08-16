package tr.org.lkd.lyk2015.camp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.Admin;
import tr.org.lkd.lyk2015.camp.model.Instructor;

@Repository
public class InstructorDao {

    @Autowired
    protected SessionFactory sessionFactory;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public Long create(final Instructor instructor){

        final Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(instructor);
    }
    
    @SuppressWarnings("unchecked")
	public List<Instructor> getAll() {

		final Session session = sessionFactory.getCurrentSession();
		//filtreleme yapcağımızı sınıfı giriyoruz
		final Criteria criteria = session.createCriteria(Instructor.class);
		//tek bi tane çıkartır sonuçları. aynı olanları eler
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFetchMode("*", FetchMode.JOIN); // lazy olanları bile alır "*" sayesinde

		/*
		criteria.add(Restrictions.add("name","ali"));
		adı ali olanları getir
		*/
		
		/*
		 * bu id deki userın dosyalarını getir
		criteria.add(Restrictions.idEq(13L));
		*/
		
		/*
		 * List<User> users= (List<User>) criteria.list();
		 */
		
		return criteria.list();
	}

}