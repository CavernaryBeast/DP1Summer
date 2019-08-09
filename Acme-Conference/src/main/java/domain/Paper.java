
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Paper extends DomainEntity {

	//Atributos de clase
	private String				title;
	private String				summary;
	private String				document;
	private boolean				cameraReady;

	//Atributos de asociación
	private Collection<Author>	authors;


	//Getters and setters

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	//Este es el pattern de un archivo en línea
	@NotBlank
	@Pattern(regexp = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
	public String getDocument() {
		return this.document;
	}

	public void setDocument(final String document) {
		this.document = document;
	}

	public boolean isCameraReady() {
		return this.cameraReady;
	}

	public void setCameraReady(final boolean cameraReady) {
		this.cameraReady = cameraReady;
	}

	@NotEmpty
	@ManyToMany
	public Collection<Author> getAuthors() {
		return this.authors;
	}

	public void setAuthors(final Collection<Author> authors) {
		this.authors = authors;
	}

}
