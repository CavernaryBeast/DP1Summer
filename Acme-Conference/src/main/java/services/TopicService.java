
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TopicRepository;
import domain.Topic;

@Service
@Transactional
public class TopicService {

	@Autowired
	private TopicRepository			topicRepository;

	@Autowired
	private AdministratorService	administratorService;


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

		return this.topicRepository.save(topic);
	}

	public void delete(final Topic topic) {

		Assert.notNull(topic);

		this.administratorService.findByPrincipal();

		this.topicRepository.delete(topic);
	}
}
