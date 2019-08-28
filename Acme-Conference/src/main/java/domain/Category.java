
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	private String					name;
	private String					nameEs;
	private Category				father;

	private Collection<Conference>	conferences;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getNameEs() {
		return this.nameEs;
	}

	public void setNameEs(final String nameEs) {
		this.nameEs = nameEs;
	}

	@Valid
	@ManyToOne(optional = true)
	public Category getFather() {
		return this.father;
	}

	public void setFather(final Category father) {
		this.father = father;
	}

	@Valid
	@OneToMany()
	public Collection<Conference> getConferences() {
		return this.conferences;
	}

	public void setConferences(final Collection<Conference> conferences) {
		this.conferences = conferences;
	}

}
