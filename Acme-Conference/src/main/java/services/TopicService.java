
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.ConfigurationParameters;
import domain.Message;
import domain.Topic;
import repositories.TopicRepository;

@Service
@Transactional
public class TopicService {

	@Autowired
	private TopicRepository					topicRepository;

	@Autowired
	private AdministratorService			administratorService;

	@Autowired
	private MessageService					messageService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	public Topic create() {

		final Topic topic = new Topic();

		return topic;
	}

	public Collection<Topic> findAll() {

		final Collection<Topic> topics = this.topicRepository.findAll();
		Assert.notEmpty(topics);

		this.administratorService.findByPrincipal();

		return topics;
	}

	public Collection<Topic> findAll2() {

		final Collection<Topic> topics = this.topicRepository.findAll();
		Assert.notEmpty(topics);

		return topics;
	}

	public Topic findOne(final int id) {

		Assert.isTrue(id != 0);

		this.administratorService.findByPrincipal();

		final Topic res = this.topicRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public Topic findByName(final String name) {

		Assert.isTrue(name.length() > 0);

		final Topic res = this.topicRepository.findByName(name);
		Assert.notNull(res);

		return res;
	}

	public Topic save(final Topic topic) {

		Assert.notNull(topic);
		this.administratorService.findByPrincipal();

		Assert.isTrue(!topic.getName().equals("BASIC"));
		Assert.isTrue(!topic.getName().equals("DECISION"));

		return this.topicRepository.save(topic);
	}

	public void delete(final Topic topic) {

		Assert.notNull(topic);
		Assert.isTrue(!topic.getName().equals("BASIC"));
		Assert.isTrue(!topic.getName().equals("DECISION"));

		this.administratorService.findByPrincipal();

		final Collection<Message> messages = this.messageService.findByTopicId(topic.getId());
		final List<Topic> all = (List<Topic>) this.findAll();
		all.remove(topic);
		final Topic toSet = all.get(0);
		for (final Message mes : messages) {
			mes.setTopic(toSet);
			this.messageService.save2(mes);
		}
		final ConfigurationParameters confParams = this.configurationParametersService.getConfigurationParameters();
		Assert.isTrue(confParams.getTopics().size() >= 1);
		confParams.getTopics().remove(topic);
		this.configurationParametersService.save(confParams);

		this.topicRepository.delete(topic);
	}
}
