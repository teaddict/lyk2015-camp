package tr.org.lkd.lyk2015.camp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * This entity represents an application form which is submitted by a Student.
 * 
 * Same student may submit different forms in different years.
 *
 */
@Entity
public class Application extends AbstractBaseModel {

	public enum WorkStatus {
		WORKING, STUDENT, NOT_WORKING
	}

	private Integer year;

	@Enumerated(EnumType.STRING)
	private WorkStatus workStatus;

	private Boolean officer = false;

	private String corporation;

	private String workDetails;

	private Integer englishLevel = 0;

	private String githubLink;

	@OneToMany
	private Set<Course> preferredCourses = new HashSet<>();

	@ManyToOne
	private Student owner;

	private boolean needAccomodation;

	public WorkStatus getWorkStatus() {
		return this.workStatus;
	}

	public void setWorkStatus(WorkStatus workStatus) {
		this.workStatus = workStatus;
	}

	public boolean isOfficer() {
		return this.officer;
	}

	public void setOfficer(boolean officer) {
		this.officer = officer;
	}

	public String getCorporation() {
		return this.corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getWorkDetails() {
		return this.workDetails;
	}

	public void setWorkDetails(String workDetails) {
		this.workDetails = workDetails;
	}

	public int getEnglishLevel() {
		return this.englishLevel;
	}

	public void setEnglishLevel(int englishLevel) {
		this.englishLevel = englishLevel;
	}

	public String getGithubLink() {
		return this.githubLink;
	}

	public void setGithubLink(String githubLink) {
		this.githubLink = githubLink;
	}

	public Set<Course> getPreferredCourses() {
		return this.preferredCourses;
	}

	public void setPreferredCourses(Set<Course> preferredCourses) {
		this.preferredCourses = preferredCourses;
	}

	public boolean isNeedAccomodation() {
		return this.needAccomodation;
	}

	public void setNeedAccomodation(boolean needAccomodation) {
		this.needAccomodation = needAccomodation;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Boolean getOfficer() {
		return this.officer;
	}

	public void setOfficer(Boolean officer) {
		this.officer = officer;
	}

	public Student getOwner() {
		return this.owner;
	}

	public void setOwner(Student owner) {
		this.owner = owner;
	}

	public void setEnglishLevel(Integer englishLevel) {
		this.englishLevel = englishLevel;
	}
}
