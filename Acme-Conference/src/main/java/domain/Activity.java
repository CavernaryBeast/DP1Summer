
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Activity extends DomainEntity {

	//Atributos de clase
	private String				title;
	private Collection<String>	speakers;
	private Date				startMoment;
	private double				duration;
	private String				room;
	private String				summary;
	private Collection<String>	attachments;
	private String				type;

	//Atributos de asociación
	private CameraReadyPaper	cameraReadyPaper;
	private Collection<Section>	sections;


	//Getters and setters

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotEmpty
	public Collection<String> getSpeakers() {
		return this.speakers;
	}

	public void setSpeakers(final Collection<String> speakers) {
		this.speakers = speakers;
	}

	@Future
	public Date getStartMoment() {
		return this.startMoment;
	}

	public void setStartMoment(final Date startMoment) {
		this.startMoment = startMoment;
	}

	@Min(0)
	public double getDuration() {
		return this.duration;
	}

	public void setDuration(final double duration) {
		this.duration = duration;
	}

	@NotBlank
	public String getRoom() {
		return this.room;
	}

	public void setRoom(final String room) {
		this.room = room;
	}

	@NotBlank
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	@NotNull
	public Collection<String> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(final Collection<String> attachments) {
		this.attachments = attachments;
	}

	@NotBlank
	@Pattern(regexp = "^(TUTORIAL|PANEL|PRESENTATION)$")
	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	@Valid
	@OneToOne(optional = true)
	public CameraReadyPaper getCameraReadyPaper() {
		return this.cameraReadyPaper;
	}

	public void setCameraReadyPaper(final CameraReadyPaper cameraReadyPaper) {
		this.cameraReadyPaper = cameraReadyPaper;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Section> getSections() {
		return this.sections;
	}

	public void setSections(final Collection<Section> sections) {
		this.sections = sections;
	}

}
