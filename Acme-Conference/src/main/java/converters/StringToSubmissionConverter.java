
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import domain.Submission;
import repositories.SubmissionRepository;

@Component
@Transactional
public class StringToSubmissionConverter implements Converter<String, Submission> {

	@Autowired
	private SubmissionRepository submissionRepository;


	@Override
	public Submission convert(final String text) {

		Submission res;
		final int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.submissionRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
