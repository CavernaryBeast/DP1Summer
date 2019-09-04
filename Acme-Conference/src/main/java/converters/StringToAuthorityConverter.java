
package converters;

import java.net.URLDecoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import security.Authority;

@Component
@Transactional
public class StringToAuthorityConverter implements Converter<String, Authority> {

	@Override
	public Authority convert(final String text) {
		Authority res;
		String parts[];

		if (text == null)
			res = null;
		else
			try {
				parts = text.split("\\|");
				res = new Authority();
				if (URLDecoder.decode(parts[0], "UTF-8").equals("ADMINISTRATOR"))
					res.setAuthority(Authority.ADMINISTRATOR);
				else if (URLDecoder.decode(parts[0], "UTF-8").equals("AUTHOR"))
					res.setAuthority(Authority.AUTHOR);
				else if (URLDecoder.decode(parts[0], "UTF-8").equals("REVIEWER"))
					res.setAuthority(Authority.REVIEWER);
				else if (URLDecoder.decode(parts[0], "UTF-8").equals("SPONSOR"))
					res.setAuthority(Authority.SPONSOR);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			}

		return res;
	}
}
