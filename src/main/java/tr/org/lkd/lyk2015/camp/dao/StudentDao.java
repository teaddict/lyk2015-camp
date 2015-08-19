package tr.org.lkd.lyk2015.camp.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.Student;

@Repository
public class StudentDao extends GenericDao<Student> {

	@Override
	public Student getById(Long tckn) {
		Criteria c = this.createCriteria();

		c.add(Restrictions.eq("tckn", tckn));

		return (Student) c.uniqueResult();
	}

}
