
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Vaste;

@Component
@Transactional
public class VasteToStringConverter implements Converter<Vaste, String> {

	@Override
	public String convert(final Vaste vaste) {

		String res;

		if (vaste == null)
			res = null;
		else
			res = String.valueOf(vaste.getId());
		return res;
	}

}
