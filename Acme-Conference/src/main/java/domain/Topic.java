
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Topic extends DomainEntity {

	//Atributos de clase
	private String	name;
	private String	nameEs;


	//Getters and setters

	@NotBlank
	@Column(unique = true)
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@Column(unique = true)
	public String getNameEs() {
		return this.nameEs;
	}

	public void setNameEs(final String nameEs) {
		this.nameEs = nameEs;
	}

}
