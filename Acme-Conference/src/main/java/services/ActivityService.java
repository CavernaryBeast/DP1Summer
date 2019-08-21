
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ActivityRepository;
import domain.Activity;
import domain.Author;
import domain.Section;

@Transactional
@Service
public class ActivityService {

	@Autowired
	private ActivityRepository	activityRepository;

	@Autowired
	private PaperService		paperService;

	@Autowired
	private AuthorService		authorService;

	@Autowired
	private ConferenceService	conferenceService;

	@Autowired
	private ReportService		reportService;

	@Autowired
	private Validator			validator;


	public Activity createTutorial() {
		this.authorService.findByPrincipal();
		final Activity result = new Activity();
		result.setType("TUTORIAL");
		result.setSections(new HashSet<Section>());
		return result;
	}

	public Activity createPresentation() {
		this.authorService.findByPrincipal();
		final Activity result = new Activity();
		result.setType("PRESENTATION");
		return result;
	}

	public Activity createPanel() {
		this.authorService.findByPrincipal();
		final Activity result = new Activity();
		result.setType("PANEL");
		return result;
	}

	public Collection<Activity> findAll() {
		final Collection<Activity> res = this.activityRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Activity findOne(final int activityId) {
		final Author author = this.authorService.findByPrincipal();
		Assert.isTrue(activityId != 0);
		Assert.notNull(activityId);
		Assert.isTrue(this.activityRepository.exists(activityId));
		final Activity res = this.activityRepository.findOne(activityId);
		Assert.notNull(res);
		Assert.isTrue(res.getSpeakers().contains(author));
		return res;
	}

	public Activity save(final Activity activity) {
		Assert.notNull(activity);
		Activity saved;
		final Author principal = this.authorService.findByPrincipal();
		//		Assert.isTrue(!submission.getPaper().isCameraReady(), "The submission must include a paper to review only, not a camera-ready version");
		//		final Paper paper = submission.getPaper();
		//paper.setCameraReady(false);
		//		final Collection<Author> autoresSecundarios = paper.getAuthors();
		//		autoresSecundarios.add(principal);
		//		paper.setAuthors(autoresSecundarios);
		//		this.paperService.save(paper);
		//		submission.setPaper(paper);

		saved = this.activityRepository.save(activity);
		return saved;
	}

	//	public void delete(final Submission submission) {
	//		Assert.notNull(submission);
	//		this.submissionRepository.delete(submission);
	//	}

	//	public Collection<Submission> findOwn() {
	//		final Author author = this.authorService.findByPrincipal();
	//		Assert.notNull(author);
	//		Assert.isTrue(author.getId() != 0);
	//		final Collection<Submission> res;
	//		System.out.println(author.getId());
	//		res = this.submissionRepository.findOwn(author.getId());
	//		Assert.notNull(res);
	//		return res;
	//	}

	public Activity reconstruct(final Activity activity, final BindingResult binding) {
		this.authorService.findByPrincipal();
		final Activity original = this.findOne(activity.getId());
		activity.setId(original.getId());
		activity.setVersion(original.getVersion());

		return activity;
	}

	public Collection<Activity> findPresentationsFromConference(final int conferenceId) {
		Assert.isTrue(conferenceId != 0, "ConferenceId es 0");
		Assert.notNull(conferenceId, "ConferenceId es nulo");
		Assert.isTrue(this.conferenceService.exist(conferenceId));
		Collection<Activity> result;
		result = this.activityRepository.findPresentationsFromConference(conferenceId);
		Assert.notNull(result, "La lista de presentaciones no puede ser nula");
		return result;
	}

	public Collection<Activity> findTutorialsFromConference(final int conferenceId) {
		Assert.isTrue(conferenceId != 0, "ConferenceId es 0");
		Assert.notNull(conferenceId, "ConferenceId es nulo");
		Assert.isTrue(this.conferenceService.exist(conferenceId));
		Collection<Activity> result;
		result = this.activityRepository.findTutorialsFromConference(conferenceId);
		Assert.notNull(result, "La lista de tutoriales no puede ser nula");
		return result;
	}

	public Collection<Activity> findPanelsFromConference(final int conferenceId) {
		Assert.isTrue(conferenceId != 0, "ConferenceId es 0");
		Assert.notNull(conferenceId, "ConferenceId es nulo");
		Assert.isTrue(this.conferenceService.exist(conferenceId));
		Collection<Activity> result;
		result = this.activityRepository.findPanelsFromConference(conferenceId);
		Assert.notNull(result, "La lista de presentaciones no puede ser nula");
		return result;
	}

}
