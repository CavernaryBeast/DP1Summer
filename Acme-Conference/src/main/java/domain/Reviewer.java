
package domain;

import java.util.Collection;

import org.hibernate.validator.constraints.NotEmpty;

public class Reviewer extends Actor {

	//Atributos de clase
	private Collection<String> expertise;

	//Atributos de asociación


	//Getters and setters
	@NotEmpty
	public Collection<String> getExpertise() {
		return this.expertise;
	}

	public void setExpertise(final Collection<String> expertise) {
		this.expertise = expertise;
	}

}
