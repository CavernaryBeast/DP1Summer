
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Conference extends DomainEntity {

	//Atributos de clase
	private String						title;
	private String						acronym;
	private String						venue;
	private Date						submissionDeadline;
	private Date						notificationDeadline;
	private Date						cameraReadyDeadline;
	private Date						startDate;
	private Date						endDate;
	private String						summary;
	private double						fee;
	private Boolean						isFinal;

	//Atributos de asociación
	private Administrator				administrator;
	private Collection<Registration>	registrations;
	private Collection<Submission>		submissions;
	private Collection<Activity>		activities;
	private Collection<Comment>			comments;


	//Getters and setters

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getAcronym() {
		return this.acronym;
	}

	public void setAcronym(final String acronym) {
		this.acronym = acronym;
	}

	@NotBlank
	public String getVenue() {
		return this.venue;
	}

	public void setVenue(final String venue) {
		this.venue = venue;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getSubmissionDeadline() {
		return this.submissionDeadline;
	}

	public void setSubmissionDeadline(final Date submissionDeadline) {
		this.submissionDeadline = submissionDeadline;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getNotificationDeadline() {
		return this.notificationDeadline;
	}

	public void setNotificationDeadline(final Date notificationDeadline) {
		this.notificationDeadline = notificationDeadline;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getCameraReadyDeadline() {
		return this.cameraReadyDeadline;
	}

	public void setCameraReadyDeadline(final Date cameraReadyDeadline) {
		this.cameraReadyDeadline = cameraReadyDeadline;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	@NotBlank
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	@Min(0)
	public double getFee() {
		return this.fee;
	}

	public void setFee(final double fee) {
		this.fee = fee;
	}

	@NotNull
	public Boolean getIsFinal() {
		return this.isFinal;
	}

	public void setIsFinal(final Boolean isFinal) {
		this.isFinal = isFinal;
	}

	@Valid
	@ManyToOne(optional = false)
	public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(final Administrator administrator) {
		this.administrator = administrator;
	}

	@Valid
	@OneToMany
	@ElementCollection
	public Collection<Registration> getRegistrations() {
		return this.registrations;
	}

	public void setRegistrations(final Collection<Registration> registrations) {
		this.registrations = registrations;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	@ElementCollection
	public Collection<Submission> getSubmissions() {
		return this.submissions;
	}

	public void setSubmissions(final Collection<Submission> submissions) {
		this.submissions = submissions;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	@ElementCollection
	public Collection<Activity> getActivities() {
		return this.activities;
	}

	public void setActivities(final Collection<Activity> activities) {
		this.activities = activities;
	}

	//	@NotNull
	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	@ElementCollection
	public Collection<Comment> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<Comment> comments) {
		this.comments = comments;
	}

}
