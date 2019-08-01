
package forms;

import org.hibernate.validator.constraints.NotBlank;

public class FilterConferenceForm {

	private String	keyWord;
	private String	typeDate;


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

}
