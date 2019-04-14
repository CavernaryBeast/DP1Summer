
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Reviewer extends Actor {

	//Atributos de clase
	private Collection<String>	expertise;


	//Atributos de asociación

	//Getters and setters
	@NotEmpty
	@ElementCollection
	public Collection<String> getExpertise() {
		return this.expertise;
	}

	public void setExpertise(final Collection<String> expertise) {
		this.expertise = expertise;
	}

}
