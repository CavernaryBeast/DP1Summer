
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.FoletRepository;
import domain.Folet;

@Component
@Transactional
public class StringToFoletConverter implements Converter<String, Folet> {

	@Autowired
	private FoletRepository	foletRepository;


	@Override
	public Folet convert(final String text) {

		Folet res;
		final int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.foletRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
