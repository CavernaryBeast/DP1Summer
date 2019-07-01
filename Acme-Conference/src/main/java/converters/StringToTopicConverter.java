
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import domain.Topic;
import repositories.TopicRepository;

@Component
@Transactional
public class StringToTopicConverter implements Converter<String, Topic> {

	@Autowired
	private TopicRepository topicRepository;


	@Override
	public Topic convert(final String text) {

		Topic res;
		final int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.topicRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
