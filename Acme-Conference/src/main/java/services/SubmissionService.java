
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import domain.Reviewer;
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
	private AdministratorService	administratorService;

	@Autowired
	private ReviewerService			reviewerService;

	@Autowired
	private ConferenceService		conferenceService;

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
		paper.setAuthors(new HashSet<Author>());
		res.setPaper(paper);

		res.setReviewers(null);

		final Collection<Conference> conferences = this.conferenceService.getConferencesSubmissionDeadlineNotElapsed();
		Assert.notEmpty(conferences, "It must be conferences in final mode to create a submission");

		return res;
	}

	public Collection<Submission> findAll() {
		final Collection<Submission> res = this.submissionRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public Submission findOne(final int id) {
		//		final Author author = this.authorService.findByPrincipal();
		Assert.isTrue(id != 0);
		Assert.notNull(id);
		Assert.isTrue(this.submissionRepository.exists(id));
		final Submission res = this.submissionRepository.findOne(id);
		Assert.notNull(res);
		//		Assert.isTrue(res.getAuthor().equals(author));
		return res;
	}

	public Submission findOneEditPaper(final int id) {
		//		final Author author = this.authorService.findByPrincipal();
		Assert.isTrue(id != 0);
		Assert.notNull(id);
		Assert.isTrue(this.submissionRepository.exists(id));
		final Submission res = this.submissionRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public Submission findOne2(final int id) {
		Assert.isTrue(id != 0);
		Assert.notNull(id);
		Assert.isTrue(this.submissionRepository.exists(id));
		final Submission res = this.submissionRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Submission findOne3(final int id) {
		Assert.isTrue(id != 0);
		Assert.notNull(id);
		Assert.isTrue(this.submissionRepository.exists(id));
		final Submission res = this.submissionRepository.findOne(id);
		Assert.notNull(res);
		Assert.isTrue(res.getReviewers().size() < 3, "Maximo 3 reviewers");
		final Conference conference = this.conferenceService.getConferenceFromSubmissionId(id);
		final Date now = new Date(System.currentTimeMillis() - 1);
		Assert.isTrue(conference.getNotificationDeadline().after(now), "NotificationDeadline Elapsed");
		return res;
	}

	public Submission save(final Submission submission) {
		Assert.notNull(submission);
		Submission saved;
		Paper savedP;
		Assert.isTrue(submission.getId() == 0);//Only create available,not edit
		final Author principal = this.authorService.findByPrincipal();
		Assert.isTrue(!submission.getPaper().isCameraReady(), "The submission must include a paper to review only, not a camera-ready version");
		final Paper paper = submission.getPaper();
		paper.setCameraReady(false);
		final Set<Author> autoresSecundarios = new HashSet<>();
		for (final Author a : paper.getAuthors())
			if (a != null)
				autoresSecundarios.add(a);

		//	autoresSecundarios.add(principal);
		paper.setAuthors(autoresSecundarios);
		savedP = this.paperService.save(paper);
		submission.setAuthor(principal);
		submission.setPaper(savedP);
		submission.setNotified(false);

		saved = this.submissionRepository.saveAndFlush(submission);
		return saved;
	}

	public Submission save2(final Submission submission) {
		Assert.notNull(submission, "Submission Null");
		System.out.println("reviewers en save2: " + submission.getReviewers());
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

	public Collection<Submission> findSubmissionsFromAuthor(final int authorId) {
		Assert.notNull(authorId);
		Assert.isTrue(authorId != 0);
		final Collection<Submission> res;
		res = this.submissionRepository.findOwn(authorId);
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
		submission.setReviewers(null);
		this.validator.validate(submission, binding);
		return submission;

	}
	public Submission reconstructAddReviewer(final Submission submission, final int reviewerId, final BindingResult binding) {

		Assert.isTrue(submission.getId() != 0);

		this.administratorService.findByPrincipal();

		final Submission original = this.findOne2(submission.getId());
		submission.setId(original.getId());
		submission.setVersion(original.getVersion());
		submission.setAuthor(original.getAuthor());
		submission.setMoment(original.getMoment());
		submission.setPaper(original.getPaper());
		submission.setTicker(original.getTicker());
		submission.setStatus(original.getStatus());
		final Reviewer reviewer = this.reviewerService.findOne(reviewerId);
		submission.setReviewers(original.getReviewers());
		submission.getReviewers().add(reviewer);

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

	public Collection<Submission> findSubmissionsToReview() {

		final Reviewer principal = this.reviewerService.findByPrincipal();

		final int reviewerId = principal.getId();

		final Collection<Submission> res = this.submissionRepository.findSubmissionsToReview(reviewerId);

		Assert.notNull(res);

		return res;
	}

	public Collection<Submission> findSubmissionsWithEditablePapers() {
		this.authorService.findByPrincipal();
		final Collection<Submission> res = this.submissionRepository.findSubmissionsWithEditablePapers();
		Assert.notNull(res);
		return res;
	}

	public Collection<Submission> findAcceptedSubmissionsFromConference(final int conferenceId) {
		Assert.isTrue(conferenceId != 0, "ConferenceId es 0");
		Assert.notNull(conferenceId, "ConferenceId es nulo");
		Assert.isTrue(this.conferenceService.exist(conferenceId));
		Collection<Submission> result;
		result = this.submissionRepository.findAcceptedSubmissionsFromConference(conferenceId);
		Assert.notNull(result, "No hay submissions en Accepted");
		return result;
	}

	public Collection<Submission> findRejectedSubmissionsFromConference(final int conferenceId) {
		Assert.isTrue(conferenceId != 0, "ConferenceId es 0");
		Assert.notNull(conferenceId, "ConferenceId es nulo");
		Assert.isTrue(this.conferenceService.exist(conferenceId));
		Collection<Submission> result;
		result = this.submissionRepository.findRejectedSubmissionsFromConference(conferenceId);
		Assert.notNull(result, "No hay submissions en Rejected");
		return result;
	}

	public Collection<Submission> findSubmissionsAcceptedWithPaperCameraReadyFromConference(final int conferenceId) {
		Assert.isTrue(conferenceId != 0, "ConferenceId es 0");
		Assert.notNull(conferenceId, "ConferenceId es nulo");
		Assert.isTrue(this.conferenceService.exist(conferenceId));
		Collection<Submission> result;
		result = this.submissionRepository.findSubmissionsAcceptedWithPaperCameraReadyFromConference(conferenceId);
		Assert.notNull(result, "No hay submissions aceptadas con el paper listo");
		return result;
	}

	public Submission assignSubmission(final int submissionId) {
		Submission submission;
		submission = this.findOne2(submissionId);
		final Collection<Reviewer> posibleReviewers;
		final Collection<Reviewer> reviewersAsignados = new HashSet<Reviewer>();
		Assert.isTrue(submission.getReviewers().size() <= 3, "Maximo 3 reviewers");
		posibleReviewers = this.reviewerService.selectAvailableAuthorsToAssingToSubmission(submissionId);
		final Map<Reviewer, Integer> matchesPerActor = new HashMap<>();
		Matcher matchesTitle, matchesSummary;
		final Conference conference = this.conferenceService.getConferenceFromSubmissionId(submissionId);
		final Date now = new Date(System.currentTimeMillis() - 1);
		Assert.isTrue(conference.getNotificationDeadline().after(now), "NotificationDeadline Elapsed");
		for (final Reviewer r : posibleReviewers) {
			Integer countMatches = 0;
			matchesTitle = this.patternKeywords(r).matcher(conference.getTitle().toLowerCase());
			matchesSummary = this.patternKeywords(r).matcher(conference.getSummary().toLowerCase());

			while (matchesTitle.find())
				countMatches++;
			while (matchesSummary.find())
				countMatches++;
			if (countMatches > 0)
				matchesPerActor.put(r, countMatches);
		}

		final List<Entry<Reviewer, Integer>> matchesSorted = new ArrayList<>(SubmissionService.sortByValue(matchesPerActor).entrySet());
		System.out.println(matchesSorted);
		final int cantidadPorAñadir = 3 - submission.getReviewers().size();

		if (cantidadPorAñadir > 0)
			if (matchesSorted.size() > cantidadPorAñadir)
				for (int i = 0; i < cantidadPorAñadir; i++) {
					final Reviewer r = matchesSorted.get(i).getKey();
					submission.getReviewers().add(r);
				}
			else if (!matchesSorted.isEmpty())
				for (int i = 0; i < matchesSorted.size(); i++) {
					final Reviewer r = matchesSorted.get(i).getKey();
					submission.getReviewers().add(r);
				}
		Assert.isTrue(submission.getReviewers().size() <= 3, "Máximo 3 tras el proceso");
		this.save2(submission);
		return submission;
	}

	private Pattern patternKeywords(final Reviewer reviewer) {
		final Collection<String> keywords = reviewer.getExpertise();
		String pattern = "";
		for (final String kw : keywords)
			pattern = pattern + (kw.toLowerCase() + "|");
		pattern = pattern.substring(0, pattern.length() - 1);
		final Pattern result = Pattern.compile(pattern);
		return result;
	}

	private static <K, V> Map<K, V> sortByValue(final Map<K, V> map) {
		final List<Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Object>() {

			@Override
			@SuppressWarnings("unchecked")
			public int compare(final Object o1, final Object o2) {
				return ((Comparable<V>) ((Map.Entry<K, V>) (o1)).getValue()).compareTo(((Map.Entry<K, V>) (o2)).getValue());
			}
		});

		final Map<K, V> result = new LinkedHashMap<>();
		for (final Iterator<Entry<K, V>> it = list.iterator(); it.hasNext();) {
			final Map.Entry<K, V> entry = it.next();
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}

	public boolean exist(final int id) {
		Assert.notNull(id);
		Assert.isTrue(id != 0);
		final boolean res = this.submissionRepository.exists(id);
		Assert.notNull(res);

		return res;
	}

	public String getSubmissionsPerConferenceStats() {
		String result;
		this.administratorService.findByPrincipal();
		result = this.submissionRepository.getSubmissionsPerConferenceStats();
		Assert.notNull(result);
		return result;
	}

	public Submission findSubmissionFromPaperId(final int paperId) {
		Assert.isTrue(paperId != 0);
		Submission result;
		result = this.submissionRepository.findSubmissionFromPaperId(paperId);
		Assert.notNull(result);
		return result;
	}

}
