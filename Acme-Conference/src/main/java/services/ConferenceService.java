
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ConferenceRepository;
import domain.Activity;
import domain.Administrator;
import domain.Conference;
import domain.Registration;
import domain.Submission;

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
	private Validator				validator;


	public Conference create() {

		final Administrator principal = this.administratorService.findByPrincipal();
		final Conference res = new Conference();
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
		if (conference.getId() == 0) {
			final Date submissionDeadline = conference.getSubmissionDeadline();
			final Date notificationDeadline = conference.getNotificationDeadline();
			final Date cameraReadyDeadline = conference.getCameraReadyDeadline();
			final Date startDate = conference.getStartDate();
			final Date endDate = conference.getEndDate();

			Assert.isTrue(submissionDeadline.before(notificationDeadline) && submissionDeadline.before(cameraReadyDeadline) && submissionDeadline.before(startDate) && submissionDeadline.before(endDate), "SubmissionDeadline");
			Assert.isTrue(notificationDeadline.before(cameraReadyDeadline) && notificationDeadline.before(startDate) && notificationDeadline.before(endDate), "NotificationDeadline");
			Assert.isTrue(cameraReadyDeadline.before(startDate) && cameraReadyDeadline.before(endDate), "CameraReadyDeadline");
			Assert.isTrue(startDate.before(endDate), "StartDate");

		}
		saved = this.conferenceRepository.save(conference);
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
			Assert.isTrue(!(original.getIsFinal()), "The conference in database before saving must not be in final mode in order to modify it");
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

}
