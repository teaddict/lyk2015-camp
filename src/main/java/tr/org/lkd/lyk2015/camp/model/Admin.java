package tr.org.lkd.lyk2015.camp.model;

import javax.persistence.Entity;

@Entity
public class Admin extends AbstractUser{
	
	private String lkdNo;

	public String getLkdNo() {
		return lkdNo;
	}

	public void setLkdNo(String lkdNo) {
		this.lkdNo = lkdNo;
	}

}
