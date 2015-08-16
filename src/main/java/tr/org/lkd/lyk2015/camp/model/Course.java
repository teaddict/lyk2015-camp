package tr.org.lkd.lyk2015.camp.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Course extends AbstractBaseModel {
	
	private String name;
	private String description;
	private String prerequisities;
	private String detailPageLink;
	private Boolean active;
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@ManyToMany
	private Set<Instructor> instructors;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrerequisities() {
		return prerequisities;
	}

	public void setPrerequisities(String prerequisities) {
		this.prerequisities = prerequisities;
	}

	public String getDetailPageLink() {
		return detailPageLink;
	}

	public void setDetailPageLink(String detailPageLink) {
		this.detailPageLink = detailPageLink;
	}

	public Set<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(Set<Instructor> instructors) {
		this.instructors = instructors;
	}
	
	
}
