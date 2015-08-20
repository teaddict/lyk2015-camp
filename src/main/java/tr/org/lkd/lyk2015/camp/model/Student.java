package tr.org.lkd.lyk2015.camp.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

@Entity
public class Student extends AbstractUser {

	public enum Sex {
		MALE, FEMALE
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Sex sex;

	@OneToMany(mappedBy = "owner")
	private Set<Application> applicationForms;

	public Sex getSex() {
		return this.sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

}
