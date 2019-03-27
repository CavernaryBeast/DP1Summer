
package domain;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class Report extends DomainEntity {

	//Atributos de clase
	private double	originalityScore;
	private double	qualityScore;
	private double	readabilityScore;
	private String	decision;

	//Atributos de asociación


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

}
