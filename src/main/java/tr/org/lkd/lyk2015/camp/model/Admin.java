package tr.org.lkd.lyk2015.camp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ADMIN");
		List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>(1);
		list.add(simpleGrantedAuthority);
		return list;
	}

}
