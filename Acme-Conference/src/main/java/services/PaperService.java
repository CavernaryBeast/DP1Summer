
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PaperRepository;
import domain.Author;
import domain.Conference;
import domain.Paper;
import domain.Submission;

@Transactional
@Service
public class PaperService {

	@Autowired
	private PaperRepository		paperRepository;

	@Autowired
	private AuthorService		authorService;

	@Autowired
	private ConferenceService	conferenceService;

	@Autowired
	private SubmissionService	submissionService;

	@Autowired
	private Validator			validator;


	public Paper save(final Paper paper) {
		Assert.notNull(paper, "Paper Null");
		Paper saved;
		final Author principal = this.authorService.findByPrincipal();
		final Collection<Author> autoresSecundarios = paper.getAuthors();
		autoresSecundarios.add(principal);
		paper.setAuthors(autoresSecundarios);
		saved = this.paperRepository.saveAndFlush(paper);
		return saved;
	}

	public Paper findOne(final int id) {
		final Author author = this.authorService.findByPrincipal();
		Assert.isTrue(id != 0);
		Assert.notNull(id);
		Assert.isTrue(this.paperRepository.exists(id));
		final Paper res = this.paperRepository.findOne(id);
		Assert.notNull(res);
		Assert.isTrue(res.getAuthors().contains(author));
		return res;
	}

	public Paper findOneToEdit(final int id) {
		final Author author = this.authorService.findByPrincipal();
		Assert.isTrue(id != 0);
		Assert.notNull(id);
		Assert.isTrue(this.paperRepository.exists(id));
		final Paper res = this.paperRepository.findOne(id);
		Assert.notNull(res);
		final Date now = new Date(System.currentTimeMillis() - 1);
		final Conference conference = this.conferenceService.getConferenceFromPaperId(res.getId());
		final Submission submission = this.submissionService.findSubmissionFromPaperId(res.getId());

		Assert.isTrue(submission.getAuthor().equals(author), "Not the author from submission");
		Assert.isTrue(res.getAuthors().contains(author), "Not one of the authors");
		Assert.isTrue(!res.isCameraReady(), "Paper already Cámera-Ready");
		Assert.isTrue(conference.getCameraReadyDeadline().after(now), "Camera-Ready Deadline Elapsed");
		Assert.isTrue(conference.getSubmissionDeadline().before(now), "Submission Deadline No Elapsed");
		Assert.isTrue(submission.getStatus().equals("ACCEPTED"), "Corresponding Submission Not Accepted");

		return res;
	}

	public Paper reconstruct(final Paper paper, final BindingResult binding) {
		this.authorService.findByPrincipal();
		final Paper original = this.findOne(paper.getId());
		paper.setId(original.getId());
		paper.setVersion(original.getVersion());
		this.validator.validate(paper, binding);
		return paper;

	}

	public Collection<Paper> findPapersSubmissionsAcceptedWithPaperCameraReadyFromConference(final int conferenceId) {
		Assert.isTrue(conferenceId != 0, "ConferenceId es 0");
		Assert.notNull(conferenceId, "ConferenceId es nulo");
		Assert.isTrue(this.conferenceService.exist(conferenceId));
		Collection<Paper> result;
		result = this.paperRepository.findPapersSubmissionsAcceptedWithPaperCameraReadyFromConference(conferenceId);
		Assert.notNull(result, "No hay submissions aceptadas con el paper listo");
		return result;
	}

}
