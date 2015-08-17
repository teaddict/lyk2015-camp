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

import tr.org.lkd.lyk2015.camp.model.AbstractBaseModel;
import tr.org.lkd.lyk2015.camp.model.Admin;
import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.service.GenericService;

@Repository
public class CourseDao extends GenericDao<Course>{

	@SuppressWarnings("unchecked")
	public List<Course> getByIds(List<Long> ids) {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.in("id",ids));
		
		return criteria.list();
	}

}