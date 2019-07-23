
package forms;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

public class FilterConferenceForm {

	private String	keyWord;
	private String	typeDate;


	public String getTypeDate() {
		return this.typeDate;
	}

	public void setTypeDate(final String typeDate) {
		this.typeDate = typeDate;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	@Pattern(regexp = "^FORTHCOMING|PAST|RUNNING$")
	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(final String keyWord) {
		this.keyWord = keyWord;
	}

}
