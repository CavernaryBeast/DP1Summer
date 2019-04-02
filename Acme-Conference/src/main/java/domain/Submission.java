
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Submission extends DomainEntity {

	//Atributos de clase
	private String				ticker;
	private Date				moment;
	private String				status;

	//Atributos de asociación
	private Author				author;
	private Paper				paper;
	private CameraReadyPaper	cameraReadyPaper;


	//Getters and setters
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}-[A-Z0-9]{4}$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@Past
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@NotBlank
	@Pattern(regexp = "^(UNDER-REVIEW|REJECTED|ACCEPTED)$")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@Valid
	@ManyToOne(optional = false)
	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(final Author author) {
		this.author = author;
	}

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	public Paper getPaper() {
		return this.paper;
	}

	public void setPaper(final Paper paper) {
		this.paper = paper;
	}

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	public CameraReadyPaper getCameraReadyPaper() {
		return this.cameraReadyPaper;
	}

	public void setCameraReadyPaper(final CameraReadyPaper cameraReadyPaper) {
		this.cameraReadyPaper = cameraReadyPaper;
	}

}
