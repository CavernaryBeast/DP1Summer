
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.VasteRepository;
import domain.Vaste;

@Component
@Transactional
public class StringToVasteConverter implements Converter<String, Vaste> {

	@Autowired
	private VasteRepository	vasteRepository;


	@Override
	public Vaste convert(final String text) {

		Vaste res;
		final int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.vasteRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
