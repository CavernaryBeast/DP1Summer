
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Author extends Actor {

	//Atributos de clase
	private String	alias;
	private int		puntuation;


	//Atributos de asociaci�n
	private Finder	finder;

	public int getPuntuation() {
		return this.puntuation;
	}

	public void setPuntuation(final int puntuation) {
		this.puntuation = puntuation;
	}

	//Getters and setters
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(final String alias) {
		this.alias = alias;
	}

	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public Finder getFinder() {
		return this.finder;
	}

	public void setFinder(final Finder finder) {
		this.finder = finder;
	}

}
