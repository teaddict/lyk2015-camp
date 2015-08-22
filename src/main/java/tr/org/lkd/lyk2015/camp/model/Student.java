
package tr.org.lkd.lyk2015.camp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("STUDENT");
		List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>(1);
		list.add(simpleGrantedAuthority);
		return list;
	}

}
