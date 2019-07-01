
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import domain.Conference;
import repositories.ConferenceRepository;

@Component
@Transactional
public class StringToConferenceConverter implements Converter<String, Conference> {

	@Autowired
	private ConferenceRepository conferenceRepository;


	@Override
	public Conference convert(final String text) {

		Conference res;
		final int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.conferenceRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
