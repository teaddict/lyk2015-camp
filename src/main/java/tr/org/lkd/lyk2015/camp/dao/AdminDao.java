package tr.org.lkd.lyk2015.camp.dao;

import java.util.Calendar;
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

@Repository
public class AdminDao {

    @Autowired
    protected SessionFactory sessionFactory;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public Long create(final Admin admin){
    	
    	Calendar now = Calendar.getInstance();
    	admin.setCreateDate(now);
    	admin.setUpdateDate(now);
    	
        final Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(admin);
    }
    
    @SuppressWarnings("unchecked")
	public List<Admin> getAll() {

		final Session session = sessionFactory.getCurrentSession();
		//filtreleme yapcağımızı sınıfı giriyoruz
		final Criteria criteria = session.createCriteria(Admin.class);
		//tek bi tane çıkartır sonuçları. aynı olanları eler
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFetchMode("*", FetchMode.JOIN); // lazy olanları bile alır "*" sayesinde
		criteria.add(Restrictions.eq("deleted", false));
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