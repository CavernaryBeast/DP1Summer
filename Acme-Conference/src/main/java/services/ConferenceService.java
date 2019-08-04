
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ConferenceRepository;
import domain.Activity;
import domain.Actor;
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


	public Conference create() {
		final Actor actorLogged = this.actorService.findByPrincipal();
		Assert.notNull(actorLogged);
		this.actorService.checkUserLoginAdministrator(actorLogged);
		final Administrator adminLogged = (Administrator) actorLogged;
		final Conference res = new Conference();
		res.setActivities(new ArrayList<Activity>());
		res.setSubmissions(new ArrayList<Submission>());
		res.setRegistrations(new ArrayList<Registration>());
		res.setAdministrator(adminLogged);
		return res;
	}

	public Collection<Conference> findAll() {

		final Collection<Conference> res = this.conferenceRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public Conference findOne(final int id) {
		//TODO: Probablemente haya que diferenciar los casos en funcion del actor, en función de la fecha que está por cumplirse.
		Assert.isTrue(id != 0);
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

			Assert.isTrue(submissionDeadline.before(notificationDeadline) && submissionDeadline.before(cameraReadyDeadline) && submissionDeadline.before(startDate) && submissionDeadline.before(endDate),
				"The Submission Deathline must be before the notification deathline, before the camera-ready Deadline , before the start date and before de end date. ");
			Assert.isTrue(notificationDeadline.before(cameraReadyDeadline) && notificationDeadline.before(startDate) && notificationDeadline.before(endDate),
				"The notification Deathline must be before the camera-ready deathline, before the startDate and before the endDate");
			Assert.isTrue(cameraReadyDeadline.before(startDate) && cameraReadyDeadline.before(endDate), "The Camera-Ready Deathline must be before the startDate and before the endDate");
			Assert.isTrue(startDate.before(endDate), " The startDate must be before the endDate");

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

}
