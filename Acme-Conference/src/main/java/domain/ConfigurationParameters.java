
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class ConfigurationParameters extends DomainEntity {

	//Atributos de clase
	private String				sysName;
	private String				banner;
	private String				message;
	private String				messageEs;
	private String				countryCode;
	private String				defaultCountry;
	private Collection<String>	creditCardMakes;
	private Collection<String>	topics;
	private Collection<String>	topicsEs;

	//Atributos de asociación


	//Getters and setters

	@NotBlank
	public String getSysName() {
		return this.sysName;
	}

	public void setSysName(final String sysName) {
		this.sysName = sysName;
	}

	@NotBlank
	@Pattern(regexp = "\\\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	@NotBlank
	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	@NotBlank
	public String getMessageEs() {
		return this.messageEs;
	}

	public void setMessageEs(final String messageEs) {
		this.messageEs = messageEs;
	}

	@NotBlank
	@Pattern(regexp = "^\\\\+\\\\d{1,3}$")
	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	@NotBlank
	public String getDefaultCountry() {
		return this.defaultCountry;
	}

	public void setDefaultCountry(final String defaultCountry) {
		this.defaultCountry = defaultCountry;
	}

	@NotEmpty
	public Collection<String> getCreditCardMakes() {
		return this.creditCardMakes;
	}

	public void setCreditCardMakes(final Collection<String> creditCardMakes) {
		this.creditCardMakes = creditCardMakes;
	}

	@NotEmpty
	public Collection<String> getTopics() {
		return this.topics;
	}

	public void setTopics(final Collection<String> topics) {
		this.topics = topics;
	}

	@NotEmpty
	public Collection<String> getTopicsEs() {
		return this.topicsEs;
	}

	public void setTopicsEs(final Collection<String> topicsEs) {
		this.topicsEs = topicsEs;
	}

}
