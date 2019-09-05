
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Reckon;

@Component
@Transactional
public class ReckonToStringConverter implements Converter<Reckon, String> {

	@Override
	public String convert(final Reckon reckon) {

		String res;

		if (reckon == null)
			res = null;
		else
			res = String.valueOf(reckon.getId());
		return res;
	}

}
