
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Conference;

@Component
@Transactional
public class ConferenceToStringConverter implements Converter<Conference, String> {

	@Override
	public String convert(final Conference conference) {

		String res;

		if (conference == null)
			res = null;
		else
			res = String.valueOf(conference.getId());
		return res;
	}

}
