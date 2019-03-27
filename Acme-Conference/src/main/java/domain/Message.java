
package domain;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

public class Message extends DomainEntity {

	//Atributos de clase
	private Date	moment;
	private String	subject;
	private String	body;
	private String	topic;

	//Atributos de asociación


	//Getters and setters
	@Past
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@NotBlank
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	@NotBlank
	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	//Messages have topic. Topics will be manageable to the Administrator,
	//so when a message is sent, the service will check that the topic selected exists.
	//There will be Asserts in the send method of MessageService
	//So, in the MessageService, we will retrieve the list of Topics from the ConfigurationParameters
	//and we will check that the Topic selected is contained in the list of existing Topics

	@NotBlank
	public String getTopic() {
		return this.topic;
	}

	public void setTopic(final String topic) {
		this.topic = topic;
	}

}
