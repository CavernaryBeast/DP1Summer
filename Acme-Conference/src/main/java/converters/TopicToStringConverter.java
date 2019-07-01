
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Topic;

@Component
@Transactional
public class TopicToStringConverter implements Converter<Topic, String> {

	@Override
	public String convert(final Topic topic) {

		String res;

		if (topic == null)
			res = null;
		else
			res = String.valueOf(topic.getId());
		return res;
	}

}
