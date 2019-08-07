
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.SubmissionRepository;
import domain.Submission;

@Transactional
@Service
public class SubmissionService {

	@Autowired
	private SubmissionRepository	submissionRepository;

	@Autowired
	private AuthorService			authorService;

	@Autowired
	private ConferenceService		conferenceService;

	@Autowired
	private Validator				validator;


	public Submission create() {

		this.authorService.findByPrincipal();
		final Submission res = new Submission();
		return res;
	}

	public Collection<Submission> findAll() {
		final Collection<Submission> res = this.submissionRepository.findAll();
		Assert.notNull(res);

		return res;
	}
	public Submission findOne(final int id) {
		this.authorService.findByPrincipal();
		Assert.isTrue(id != 0);
		Assert.notNull(id);
		final Submission res = this.submissionRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public Submission save(final Submission submission) {
		Assert.notNull(submission);
		Submission saved;
		if (submission.getId() == 0) {

		}
		saved = this.submissionRepository.save(submission);
		return saved;
	}

	public void delete(final Submission submission) {
		Assert.notNull(submission);
		this.submissionRepository.delete(submission);
	}

}
