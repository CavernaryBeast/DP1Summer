
package domain;

import org.hibernate.validator.constraints.NotBlank;

public class Comment extends DomainEntity {

	//Atributos de clase
	private String comment;

	//Atributos de asociación


	//Getters and setters
	@NotBlank
	public String getComment() {
		return this.comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

}
