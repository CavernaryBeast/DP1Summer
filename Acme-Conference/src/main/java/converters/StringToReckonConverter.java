
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.ReckonRepository;
import domain.Reckon;

@Component
@Transactional
public class StringToReckonConverter implements Converter<String, Reckon> {

	@Autowired
	private ReckonRepository	reckonRepository;


	@Override
	public Reckon convert(final String text) {

		Reckon res;
		final int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.reckonRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
