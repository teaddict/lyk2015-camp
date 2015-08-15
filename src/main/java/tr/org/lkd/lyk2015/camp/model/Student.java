package tr.org.lkd.lyk2015.camp.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Student extends AbstractUser {
	
	public enum Sex
	{
		MALE,
		FEMALE
	}
	
	@Enumerated(EnumType.STRING)
	private Sex sex;

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}  
	
	
}
