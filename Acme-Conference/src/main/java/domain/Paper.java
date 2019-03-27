
package domain;

import java.util.Collection;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class Paper extends DomainEntity {

	//Atributos de clase
	private String				title;
	private Collection<String>	authors;
	private String				summary;
	private String				document;

	//Atributos de asociación


	//Getters and setters

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotEmpty
	public Collection<String> getAuthors() {
		return this.authors;
	}

	public void setAuthors(final Collection<String> authors) {
		this.authors = authors;
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
	@Pattern(regexp = "\\\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
	public String getDocument() {
		return this.document;
	}

	public void setDocument(final String document) {
		this.document = document;
	}

}
