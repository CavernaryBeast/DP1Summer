
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrator;
import domain.Message;
import domain.Submission;
import domain.Topic;
import repositories.MessageRepository;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository				messageRepository;

	@Autowired
	private ActorService					actorService;

	@Autowired
	private AdministratorService			administratorService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;

	@Autowired
	private TopicService					topicService;


	//El sender se asignara mas adelante
	public Message create() {

		Message mes;
		Date moment;

		mes = new Message();
		moment = new Date(System.currentTimeMillis() - 1);

		final Collection<Actor> recipients = new ArrayList<>();
		mes.setRecipients(recipients);

		mes.setMoment(moment);

		return mes;
	}

	public Collection<Message> findAll() {

		final Collection<Message> res = this.messageRepository.findAll();
		Assert.notEmpty(res);

		return res;
	}

	public Message findOne(final int id) {

		Assert.isTrue(id != 0);
		final Message res = this.messageRepository.findOne(id);

		return res;
	}

	public Message save(final Message mes) {

		Assert.notNull(mes);

		Message saved;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		mes.setMoment(moment);

		saved = this.messageRepository.save(mes);

		return saved;
	}

	public void delete(final Message mes) {

		Message saved;

		Assert.notNull(mes);

		final Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(mes.getSender().equals(principal) || mes.getRecipients().contains(principal) || mes.getSender().equals(null));

		if (!mes.getSender().equals(null) || !mes.getRecipients().isEmpty())
			if (mes.getSender().equals(principal))
				mes.setSender(null);
			else if (mes.getRecipients().contains(principal)) {

				final Collection<Actor> recipients = mes.getRecipients();
				recipients.remove(principal);
				mes.setRecipients(recipients);

			}
		if (mes.getSender().equals(null) && mes.getRecipients().isEmpty())
			this.messageRepository.delete(mes);

		saved = this.messageRepository.save(mes);
	}

	public Collection<Message> listByTopic(final int topicId) {

		Assert.isTrue(topicId != 0);
		final Collection<Topic> topics = this.configurationParametersService.getConfigurationParameters().getTopics();
		final Topic topic = this.topicService.findOne(topicId);
		Assert.isTrue(topics.contains(topic));

		final int principalId = this.actorService.findByPrincipal().getId();
		final Collection<Message> messages = this.messageRepository.findByTopicId(topicId, principalId);
		Assert.notNull(messages);

		return messages;
	}

	public Collection<Message> listBySender(final int senderId) {

		Assert.isTrue(senderId != 0);

		final int principalId = this.actorService.findByPrincipal().getId();
		final Collection<Message> messages = this.messageRepository.findBySender(senderId, principalId);
		Assert.notNull(messages);

		return messages;
	}

	public Collection<Message> listSystemMessages() {

		final int principalId = this.actorService.findByPrincipal().getId();
		final Collection<Message> messages = this.messageRepository.findSystemMessages(principalId);
		Assert.notNull(messages);

		return messages;
	}

	public Collection<Message> listByRecipient(final int recipientId) {

		Assert.isTrue(recipientId != 0);

		final int principalId = this.actorService.findByPrincipal().getId();
		final Collection<Message> messages = this.messageRepository.findByRecipientId(recipientId, principalId);
		Assert.notNull(messages);

		return messages;
	}

	public Collection<Message> listOwn() {

		final Actor principal = this.actorService.findByPrincipal();

		final Collection<Message> messages = this.messageRepository.findOwn(principal.getId());
		Assert.notNull(messages);

		return messages;
	}

	//Notification and Broadcast functionality
	public void notifyAuthor(final Submission submission) {

		Assert.isTrue(submission.getId() != 0);
		final Administrator admin = this.administratorService.findByPrincipal();

		final Topic topic = this.topicService.findByName("DECISION");

		final Message notification = this.create();

		//Setting of the corresponding recipient->The author of the submission
		final Collection<Actor> recipients = notification.getRecipients();
		recipients.add(submission.getAuthor());
		notification.setRecipients(recipients);

		//Setting of the subject regarding the status of the submission
		notification.setSubject("Decision made regarding your submission");

		//Setting of the corresponding body of the message->The decision made regarding the submission
		final String body = "Your submission has been" + submission.getStatus();
		notification.setBody(body);

		//Setting of the topic regarding the status of the submission
		notification.setTopic(topic);

		this.messageRepository.save(notification);
	}

}
