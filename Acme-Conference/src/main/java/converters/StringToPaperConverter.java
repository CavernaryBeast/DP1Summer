
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import domain.Paper;
import repositories.PaperRepository;

@Component
@Transactional
public class StringToPaperConverter implements Converter<String, Paper> {

	@Autowired
	private PaperRepository paperRepository;


	@Override
	public Paper convert(final String text) {

		Paper res;
		final int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.paperRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
