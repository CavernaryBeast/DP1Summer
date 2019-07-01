
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import domain.Reviewer;
import repositories.ReviewerRepository;

@Component
@Transactional
public class StringToReviewerConverter implements Converter<String, Reviewer> {

	@Autowired
	private ReviewerRepository reviewerRepository;


	@Override
	public Reviewer convert(final String text) {

		Reviewer res;
		final int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.reviewerRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
