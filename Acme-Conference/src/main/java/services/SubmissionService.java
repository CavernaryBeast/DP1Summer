
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SubmissionRepository;
import domain.Author;
import domain.Conference;
import domain.Paper;
import domain.Submission;

@Transactional
@Service
public class SubmissionService {

	@Autowired
	private SubmissionRepository	submissionRepository;

	@Autowired
	private PaperService			paperService;

	@Autowired
	private AuthorService			authorService;

	@Autowired
	private ConferenceService		conferenceService;

	@Autowired
	private ReportService			reportService;

	@Autowired
	private Validator				validator;


	public Submission create() {

		this.authorService.findByPrincipal();
		final Submission res = new Submission();
		res.setStatus("UNDER-REVIEW");
		final Author principal = this.authorService.findByPrincipal();

		String ticker = principal.getName().substring(0, 1);
		if (principal.getMiddleName().isEmpty() || principal.getMiddleName() == null)
			ticker = ticker + "X";
		else
			ticker = ticker + principal.getMiddleName().substring(0, 1);
		ticker = ticker + principal.getSurname().substring(0, 1);

		final String alphanumeric = RandomStringUtils.randomAlphanumeric(4).toUpperCase();

		ticker = ticker + "-" + alphanumeric;

		res.setTicker(ticker);
		final Paper paper = new Paper();
		res.setPaper(paper);
		return res;
	}

	public Collection<Submission> findAll() {
		final Collection<Submission> res = this.submissionRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public Submission findOne(final int id) {
		final Author author = this.authorService.findByPrincipal();
		Assert.isTrue(id != 0);
		Assert.notNull(id);
		Assert.isTrue(this.submissionRepository.exists(id));
		final Submission res = this.submissionRepository.findOne(id);
		Assert.notNull(res);
		Assert.isTrue(res.getAuthor().equals(author));
		return res;
	}

	//	public Submission findOne2(final int id) {
	//		this.authorService.findByPrincipal();
	//		Assert.isTrue(id != 0);
	//		Assert.notNull(id);
	//		Assert.isTrue(this.submissionRepository.exists(id));
	//		final Submission res = this.submissionRepository.findOne(id);
	//		Assert.notNull(res);
	//
	//		return res;
	//	}

	public Submission save(final Submission submission) {
		Assert.notNull(submission);
		Submission saved;
		Assert.isTrue(submission.getId() == 0);//Only create available,not edit
		final Author principal = this.authorService.findByPrincipal();
		Assert.isTrue(!submission.getPaper().isCameraReady(), "The submission must include a paper to review only, not a camera-ready version");
		final Paper paper = submission.getPaper();
		paper.setCameraReady(false);
		final Collection<Author> autoresSecundarios = paper.getAuthors();
		autoresSecundarios.add(principal);
		paper.setAuthors(autoresSecundarios);
		this.paperService.save(paper);
		submission.setPaper(paper);

		saved = this.submissionRepository.saveAndFlush(submission);
		return saved;
	}

	public Submission save2(final Submission submission) {
		Assert.notNull(submission, "Submission Null");
		Submission saved;
		saved = this.submissionRepository.saveAndFlush(submission);
		return saved;
	}

	public void delete(final Submission submission) {
		Assert.notNull(submission);
		this.submissionRepository.delete(submission);
	}

	public Collection<Submission> findOwn() {
		final Author author = this.authorService.findByPrincipal();
		Assert.notNull(author);
		Assert.isTrue(author.getId() != 0);
		final Collection<Submission> res;
		System.out.println(author.getId());
		res = this.submissionRepository.findOwn(author.getId());
		Assert.notNull(res);
		return res;
	}

	public Submission reconstruct(final Submission submission, final BindingResult binding) {

		final Author principal = this.authorService.findByPrincipal();

		String ticker = principal.getName().substring(0, 1);
		if (principal.getMiddleName().isEmpty() || principal.getMiddleName() == null)
			ticker = ticker + "X";
		else
			ticker = ticker + principal.getMiddleName().substring(0, 1);
		ticker = ticker + principal.getSurname().substring(0, 1);

		final String alphanumeric = RandomStringUtils.randomAlphanumeric(4).toUpperCase();

		ticker = ticker + "-" + alphanumeric;

		final Date now = new Date(System.currentTimeMillis() - 1);
		submission.setTicker(ticker);
		submission.setMoment(now);
		submission.setStatus("UNDER-REVIEW");
		submission.setAuthor(principal);
		this.validator.validate(submission, binding);
		return submission;

	}

	public void setStatusToAccepted(final int submissionId) {
		final Author logged = this.authorService.findByPrincipal();
		Assert.isTrue(submissionId != 0);
		Assert.notNull(submissionId);
		Assert.isTrue(this.submissionRepository.exists(submissionId));
		final Submission submission = this.findOne(submissionId);
		Assert.isTrue(submission.getAuthor().equals(logged), "El usuario logueado debe ser el author de la submission para poder aceptarla");
		final Conference conference = this.conferenceService.getConferenceFromSubmissionId(submissionId);
		final Date now = new Date(System.currentTimeMillis() - 1);
		Assert.isTrue(conference.getCameraReadyDeadline().after(now));
		submission.setStatus("ACCEPTED");
		this.save2(submission);
	}

	public Collection<Submission> findUnderReviewSubmissionsFromConference(final int conferenceId) {
		Assert.isTrue(conferenceId != 0, "ConferenceId es 0");
		Assert.notNull(conferenceId, "ConferenceId es nulo");
		Assert.isTrue(this.conferenceService.exist(conferenceId));
		Collection<Submission> result;
		result = this.submissionRepository.findUnderReviewSubmissionsFromConference(conferenceId);
		Assert.notNull(result, "No hay submissions en UnderReview");
		return result;
	}

}
