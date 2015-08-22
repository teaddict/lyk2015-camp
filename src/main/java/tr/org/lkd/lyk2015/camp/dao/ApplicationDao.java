package tr.org.lkd.lyk2015.camp.dao;

import java.util.Calendar;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.Application;

@Repository
public class ApplicationDao extends GenericDao<Application> {

	public Application getByValidationId(String validateId) {

		Criteria c = this.createCriteria();

		c.add(Restrictions.eq("validateId", validateId));

		return (Application) c.uniqueResult();
	}

	public Application getStudentsApplication(Long studentId) {
		Criteria c = this.createCriteria();
		// create alias ile uzun uzun variable name yazmamak için
		// daha kısa bi şey tanımlıyoruz
		c.createAlias("owner", "o");
		c.add(Restrictions.eq("o.id", studentId));
		c.add(Restrictions.eqOrIsNull("year", Calendar.getInstance().get(Calendar.YEAR)));
		c.setFetchMode("preferredCourses", FetchMode.JOIN); // prevent lazy
															// loading
		return (Application) c.uniqueResult();
	}

}
