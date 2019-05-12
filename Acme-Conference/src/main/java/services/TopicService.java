
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Topic;
import repositories.TopicRepository;

@Service
@Transactional
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;


	public Topic create() {

		final Topic topic = new Topic();

		return topic;
	}

	public Collection<Topic> findAll() {

		final Collection<Topic> topics = this.topicRepository.findAll();
		Assert.notEmpty(topics);

		return topics;
	}

	public Topic findOne(final int id) {

		Assert.isTrue(id != 0);

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

}
