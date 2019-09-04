
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Author extends Actor {

	//Atributos de clase
	private String	alias;
	private int		puntuation;


	//Atributos de asociación

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

}
