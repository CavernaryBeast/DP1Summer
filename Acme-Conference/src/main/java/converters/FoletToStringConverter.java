
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Folet;

@Component
@Transactional
public class FoletToStringConverter implements Converter<Folet, String> {

	@Override
	public String convert(final Folet folet) {

		String res;

		if (folet == null)
			res = null;
		else
			res = String.valueOf(folet.getId());
		return res;
	}

}
