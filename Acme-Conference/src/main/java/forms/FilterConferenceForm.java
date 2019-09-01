
package forms;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import domain.Category;

public class FilterConferenceForm {

	private String		keyWord;
	private String		typeDate;
	private Category	category;


	//	@Pattern(regexp = "^FORTHCOMING|PAST|RUNNING$")
	@NotBlank
	public String getTypeDate() {
		return this.typeDate;
	}

	public void setTypeDate(final String typeDate) {
		this.typeDate = typeDate;
	}

	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(final String keyWord) {
		this.keyWord = keyWord;
	}

	@Valid
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

}
