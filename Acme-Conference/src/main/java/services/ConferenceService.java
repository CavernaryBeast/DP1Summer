
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ConferenceRepository;
import domain.Actor;
import domain.Conference;

@Transactional
@Service
public class ConferenceService {

	@Autowired
	private ConferenceRepository	conferenceRepository;

	@Autowired
	private ActorService			actorService;


	public Conference create() {
		final Actor actorLogged = this.actorService.findActorLogged();
		Assert.notNull(actorLogged);
		this.actorService.checkUserLoginAdministrator(actorLogged);
		final Conference res = new Conference();

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

	//We check that who wants to save the comment is the owner of the comment's Report
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

}
