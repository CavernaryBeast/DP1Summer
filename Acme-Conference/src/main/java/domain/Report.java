
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity {

	//Atributos de clase
	private double				originalityScore;
	private double				qualityScore;
	private double				readabilityScore;
	private String				decision;

	//Atributos de asociación
	private Reviewer			reviewer;
	private Submission			submission;
	private Collection<Comment>	comments;


	//Getters and setters
	@Range(min = 0, max = 10)
	public double getOriginalityScore() {
		return this.originalityScore;
	}

	public void setOriginalityScore(final double originalityScore) {
		this.originalityScore = originalityScore;
	}

	@Range(min = 0, max = 10)
	public double getQualityScore() {
		return this.qualityScore;
	}

	public void setQualityScore(final double qualityScore) {
		this.qualityScore = qualityScore;
	}

	@Range(min = 0, max = 10)
	public double getReadabilityScore() {
		return this.readabilityScore;
	}

	public void setReadabilityScore(final double readabilityScore) {
		this.readabilityScore = readabilityScore;
	}

	@NotBlank
	@Pattern(regexp = "^(REJECT|BORDER-LINE|ACCEPT)$")
	public String getDecision() {
		return this.decision;
	}

	public void setDecision(final String decision) {
		this.decision = decision;
	}

	@Valid
	@ManyToOne(optional = false)
	public Reviewer getReviewer() {
		return this.reviewer;
	}

	public void setReviewer(final Reviewer reviewer) {
		this.reviewer = reviewer;
	}

	@Valid
	@ManyToOne(optional = false)
	public Submission getSubmission() {
		return this.submission;
	}

	public void setSubmission(final Submission submission) {
		this.submission = submission;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Comment> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<Comment> comments) {
		this.comments = comments;
	}

}
