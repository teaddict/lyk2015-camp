package tr.org.lkd.lyk2015.camp.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Admin extends AbstractUser {

	@Column(nullable = false, unique = true)
	@NotEmpty
	private String lkdNo;

	public String getLkdNo() {
		return this.lkdNo;
	}

	public void setLkdNo(String lkdNo) {
		this.lkdNo = lkdNo;
	}

}
