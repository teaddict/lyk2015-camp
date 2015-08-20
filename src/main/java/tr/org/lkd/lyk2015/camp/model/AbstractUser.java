package tr.org.lkd.lyk2015.camp.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@MappedSuperclass
public abstract class AbstractUser extends AbstractBaseModel implements UserDetails {

	@NotEmpty
	private String name;
	@NotEmpty
	private String surname;
	@Column(unique = true)
	@NotNull
	private Long tckn;
	@Min(1935)
	@Max(2003)
	@Column(nullable = false)
	// @Pattern(regexp="^[0-9]{4}$", message="")
	private Integer birthDate;
	@Email
	@Column(unique = true)
	@NotEmpty
	private String email;
	// eklenecek
	@Column(nullable = false)
	private String password = "";
	@Column(unique = true, nullable = false)
	private String phone = "";

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Long getTckn() {
		return this.tckn;
	}

	public void setTckn(Long tckn) {
		this.tckn = tckn;
	}

	public Integer getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Integer birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
