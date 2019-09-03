
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import domain.Activity;
import domain.Administrator;
import domain.Conference;
import domain.Registration;
import domain.Report;
import domain.Submission;
import repositories.ConferenceRepository;

@Transactional
@Service
public class ConferenceService {

	@Autowired
	private ConferenceRepository	conferenceRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private SubmissionService		submissionService;

	@Autowired
	private ReportService			reportService;

	@Autowired
	private ActivityService			activityService;

	@Autowired
	private Validator				validator;

	@Autowired
	private MessageService			messageService;


	public Conference create() {

		final Administrator principal = this.administratorService.findByPrincipal();
		final Conference res = new Conference();
		res.setActivities(new HashSet<Activity>());
		return res;
	}

	public Collection<Conference> findAll() {
		//TODO: Para el list público(incluyendo sin loguear en el sistema) los list de conference solo deben llevar las que estén a final mode
		final Collection<Conference> res = this.conferenceRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public Conference findOne(final int id) {
		//TODO: Probablemente haya que diferenciar los casos en funcion del actor, en función de la fecha que está por cumplirse.
		this.administratorService.findByPrincipal();
		Assert.isTrue(id != 0);
		Assert.notNull(id);
		Assert.isTrue(this.exist(id));
		final Conference res = this.conferenceRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public Conference findOneEditable(final int id) {
		//TODO: Probablemente haya que diferenciar los casos en funcion del actor, en función de la fecha que está por cumplirse.
		this.administratorService.findByPrincipal();
		Assert.isTrue(id != 0);
		Assert.notNull(id);
		Assert.isTrue(this.exist(id));
		final Conference res = this.conferenceRepository.findOne(id);
		Assert.notNull(res);
		Assert.isTrue(!(res.getIsFinal()), "The conference in database before saving must not be in final mode in order to modify it");

		return res;
	}

	public Conference findOne2(final int id) {
		//usado para el submissionService, requiere que el que esté logueado no sea administrator
		Assert.isTrue(id != 0);
		Assert.notNull(id);
		Assert.isTrue(this.exist(id));
		final Conference res = this.conferenceRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public Conference save(final Conference conference) {
		Assert.notNull(conference);
		Conference saved;

		final Date submissionDeadline = conference.getSubmissionDeadline();
		final Date notificationDeadline = conference.getNotificationDeadline();
		final Date cameraReadyDeadline = conference.getCameraReadyDeadline();
		final Date startDate = conference.getStartDate();
		final Date endDate = conference.getEndDate();
		final Date now = new Date(System.currentTimeMillis() - 1);
		Assert.isTrue(submissionDeadline.after(now) && submissionDeadline.before(notificationDeadline) && submissionDeadline.before(cameraReadyDeadline) && submissionDeadline.before(startDate) && submissionDeadline.before(endDate), "SubmissionDeadline");
		Assert.isTrue(notificationDeadline.after(now) && notificationDeadline.before(cameraReadyDeadline) && notificationDeadline.before(startDate) && notificationDeadline.before(endDate), "NotificationDeadline");
		Assert.isTrue(cameraReadyDeadline.after(now) && cameraReadyDeadline.before(startDate) && cameraReadyDeadline.before(endDate), "CameraReadyDeadline");
		Assert.isTrue(startDate.after(now) && startDate.before(endDate), "StartDate");
		Assert.isTrue(endDate.after(now), "EndDate");

		if (conference.getId() != 0) {
			final Conference original = this.findOne(conference.getId());
			Assert.isTrue(!(original.getIsFinal()), "The conference in database before saving must not be in final mode in order to modify it");
		}
		saved = this.conferenceRepository.save(conference);

		return saved;
	}

	public Conference save2(final Conference conference) {
		Assert.notNull(conference);
		Conference saved;
		saved = this.conferenceRepository.save(conference);
		Assert.notNull(saved);
		return saved;
	}

	public void delete(final Conference conference) {
		Assert.notNull(conference);
		this.conferenceRepository.delete(conference);
	}

	public Collection<Conference> getAllConferencesByKeyword(final String keyword) {
		Collection<Conference> result;
		result = this.conferenceRepository.getAllConferencesByKeyword(keyword);
		Assert.notNull(result);
		return result;
	}

	public Collection<Conference> getForthcomingConferencesByKeyword(final String keyword) {
		Collection<Conference> result;
		result = this.conferenceRepository.getForthcomingConferencesByKeyword(keyword);
		Assert.notNull(result);
		return result;
	}

	public Collection<Conference> getRunningConferencesByKeyword(final String keyword) {
		Collection<Conference> result;
		result = this.conferenceRepository.getRunningConferencesByKeyword(keyword);
		Assert.notNull(result);
		return result;
	}

	public Collection<Conference> getPastConferencesByKeyword(final String keyword) {
		Collection<Conference> result;
		result = this.conferenceRepository.getPastConferencesByKeyword(keyword);
		Assert.notNull(result);
		return result;
	}

	public Collection<Conference> getPConferencesByKeyword(final String keyword) {
		Collection<Conference> result;
		result = this.conferenceRepository.getPastConferencesByKeyword(keyword);
		Assert.notNull(result);
		return result;
	}

	public Collection<Conference> getConferencesSubmissionDeadlineLastFiveDays() {
		Collection<Conference> result;
		result = this.conferenceRepository.getConferencesSubmissionDeadlineLastFiveDays();
		Assert.notNull(result);
		return result;
	}

	public Collection<Conference> getConferencesNotificationDeadlineInLessFiveDays() {
		Collection<Conference> result;
		result = this.conferenceRepository.getConferencesNotificationDeadlineInLessFiveDays();
		Assert.notNull(result);
		return result;
	}

	public Collection<Conference> getConferencesCameraReadyDeadlineInLessFiveDays() {
		Collection<Conference> result;
		result = this.conferenceRepository.getConferencesCameraReadyDeadlineInLessFiveDays();
		Assert.notNull(result);
		return result;
	}

	public Collection<Conference> getConferencesStartDateInLessFiveDays() {
		Collection<Conference> result;
		result = this.conferenceRepository.getConferencesStartDateInLessFiveDays();
		Assert.notNull(result);
		return result;
	}

	public Collection<Conference> getAllConferencesFinalMode() {
		Collection<Conference> result;
		result = this.conferenceRepository.getAllConferencesFinalMode();
		Assert.notNull(result);
		return result;
	}

	public Collection<Conference> getConferencesSubmissionDeadlineNotElapsed() {
		Collection<Conference> result;
		result = this.conferenceRepository.getConferencesSubmissionDeadlineNotElapsed();
		Assert.notNull(result);
		return result;
	}

	public Collection<Conference> getConferencesStartDateNotElapsed() {
		Collection<Conference> result;
		result = this.conferenceRepository.getConferencesStartDateNotElapsed();
		Assert.notNull(result);
		return result;
	}

	public Collection<Conference> getConferencesStartDateNotElapsed2() {
		Collection<Conference> result;
		result = this.conferenceRepository.getConferencesStartDateNotElapsed();
		Assert.notNull(result);
		return result;
	}

	public Collection<Conference> getConferencesWithUnderReviewSubmissions() {
		Collection<Conference> result;
		result = this.conferenceRepository.getConferencesWithUnderReviewSubmissions();
		Assert.notNull(result);
		return result;
	}

	public Collection<Conference> getConferencesavailablePapersForPresentations() {
		Collection<Conference> result;
		result = this.conferenceRepository.getConferencesAvailablePapersForPresentations();
		Assert.notNull(result);
		return result;
	}

	public Conference getConferenceFromSubmissionId(final int submissionId) {
		Conference result;
		Submission submission;
		result = this.conferenceRepository.getConferenceFromSubmissionId(submissionId);
		submission = this.submissionService.findOne2(submissionId);
		Assert.notNull(result);
		Assert.isTrue(result.getSubmissions().contains(submission), "La conference debe poseer la submission");
		return result;
	}

	public Conference getConferenceFromPaperId(final int paperId) {
		Assert.isTrue(paperId != 0);
		Conference result;
		result = this.conferenceRepository.getConferenceFromPaperId(paperId);
		Assert.notNull(result);
		return result;
	}

	public Conference reconstruct(final Conference conference, final BindingResult binding) {
		Conference original;
		final Administrator principal = this.administratorService.findByPrincipal();

		if (conference.getId() == 0) {
			conference.setActivities(new ArrayList<Activity>());
			conference.setSubmissions(new ArrayList<Submission>());
			conference.setRegistrations(new ArrayList<Registration>());
			conference.setAdministrator(principal);
		} else {
			original = this.findOne(conference.getId());
			conference.setId(original.getId());
			conference.setVersion(original.getVersion());
			conference.setActivities(original.getActivities());
			conference.setSubmissions(original.getSubmissions());
			conference.setRegistrations(original.getRegistrations());
			conference.setAdministrator(original.getAdministrator());

		}
		this.validator.validate(conference, binding);
		return conference;

	}

	public boolean exist(final int id) {
		Assert.notNull(id);
		Assert.isTrue(id != 0);
		final boolean res = this.conferenceRepository.exists(id);
		Assert.notNull(res);

		return res;
	}

	public void decideStatus(final int conferenceId) {
		this.administratorService.findByPrincipal();
		Assert.isTrue(conferenceId != 0, "Id distinto de 0");
		Assert.notNull(conferenceId, "Id no nulo");
		Assert.isTrue(this.exist(conferenceId), "Existe Id");
		final Conference conference = this.findOne(conferenceId);
		final Collection<Conference> conferencesWithUnderReviewSubmissions = this.getConferencesWithUnderReviewSubmissions();
		final Date now = new Date(System.currentTimeMillis() - 1);
		Assert.isTrue(now.after(conference.getSubmissionDeadline()), "Submission Deadline not Elapsed");
		Assert.isTrue(conference.getCameraReadyDeadline().after(now), "Camera-Ready Deadline already Elapsed");
		Assert.isTrue(conferencesWithUnderReviewSubmissions.contains(conference), "The conference must have Under-Review submissions");
		final Collection<Submission> submissionsUnderReview = this.submissionService.findUnderReviewSubmissionsFromConference(conferenceId);
		for (final Submission submission : submissionsUnderReview) {
			System.out.println("reviewers: " + submission.getReviewers());
			final Collection<Report> reports = this.reportService.findBySubmissionId(submission.getId());
			if (!reports.isEmpty()) {
				int contadorAccept = 0;
				int contadorReject = 0;
				//			final int contadorBorderline = 0; -> No hace falta ni tenerlas en cuenta,si empatan automaticamente ganan las accept
				for (final Report r : reports)
					if (r.getDecision().equals("ACCEPT"))
						contadorAccept++;
					else if (r.getDecision().equals("REJECT"))
						contadorReject++;

				if (contadorAccept >= contadorReject)
					submission.setStatus("ACCEPTED");
				else
					submission.setStatus("REJECTED");
			} else
				submission.setStatus("ACCEPTED");
			//			this.submissionService.save2(submission);
			this.messageService.notifyAuthor(submission);
		}
	}

	public Conference getConferenceFromActivityId(final int activityId) {
		Conference result;
		Activity activity;
		result = this.conferenceRepository.getConferenceFromActivityId(activityId);
		activity = this.activityService.findOne(activityId);
		Assert.notNull(result);
		Assert.isTrue(result.getActivities().contains(activity), "La conference debe poseer la activity");
		return result;
	}

	public String getFeesPerConferenceStats() {
		String result;
		result = this.conferenceRepository.getFeesPerConferenceStats();
		Assert.notNull(result);
		return result;
	}

	public String getDaysPerConferenceStats() {
		String result;
		result = this.conferenceRepository.getDaysPerConferenceStats();
		Assert.notNull(result);
		return result;
	}

}
