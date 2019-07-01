
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import domain.Registration;
import repositories.RegistrationRepository;

@Component
@Transactional
public class StringToRegistrationConverter implements Converter<String, Registration> {

	@Autowired
	private RegistrationRepository registrationRepository;


	@Override
	public Registration convert(final String text) {

		Registration res;
		final int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.registrationRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
