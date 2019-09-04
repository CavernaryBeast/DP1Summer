
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ActivityRepository;
import repositories.SectionRepository;
import domain.Activity;
import domain.Conference;
import domain.Section;

@Transactional
@Service
public class ActivityService {

	@Autowired
	private ActivityRepository		activityRepository;

	@Autowired
	private SectionRepository		sectionRepository;

	@Autowired
	private PaperService			paperService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ConferenceService		conferenceService;

	@Autowired
	private ReportService			reportService;

	@Autowired
	private Validator				validator;


	public Activity createTutorial(final int conferenceId) {
		this.administratorService.findByPrincipal();
		final Conference conference = this.conferenceService.findOne2(conferenceId);
		final Date now = new Date(System.currentTimeMillis() - 1);
		Assert.isTrue(conference.getStartDate().after(now), "StartDate elapsed");
		final Activity result = new Activity();
		result.setType("TUTORIAL");
		result.setSections(new HashSet<Section>());
		return result;
	}

	public Activity createPresentation(final int conferenceId) {
		this.administratorService.findByPrincipal();
		final Collection<Conference> conferencesWithPosiblePapers = this.conferenceService.getConferencesavailablePapersForPresentations();
		final Conference conference = this.conferenceService.findOne2(conferenceId);
		final Date now = new Date(System.currentTimeMillis() - 1);
		Assert.isTrue(conference.getStartDate().after(now), "StartDate elapsed");
		Assert.isTrue(conferencesWithPosiblePapers.contains(conference), "Conference doesn't have available papers");
		final Activity result = new Activity();
		result.setType("PRESENTATION");
		return result;
	}

	public Activity createPanel(final int conferenceId) {
		this.administratorService.findByPrincipal();
		final Conference conference = this.conferenceService.findOne2(conferenceId);
		final Date now = new Date(System.currentTimeMillis() - 1);
		Assert.isTrue(conference.getStartDate().after(now), "StartDate elapsed");
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
		this.administratorService.findByPrincipal();
		Assert.isTrue(activityId != 0);
		Assert.notNull(activityId);
		Assert.isTrue(this.activityRepository.exists(activityId));
		final Activity res = this.activityRepository.findOne(activityId);
		Assert.notNull(res);
		return res;
	}

	public Activity findOneEditable(final int activityId) {
		this.administratorService.findByPrincipal();
		Assert.isTrue(activityId != 0);
		Assert.notNull(activityId);
		Assert.isTrue(this.activityRepository.exists(activityId));
		final Activity res = this.activityRepository.findOne(activityId);
		Assert.notNull(res);
		final Conference conference = this.conferenceService.getConferenceFromActivityId(activityId);
		final Date now = new Date(System.currentTimeMillis() - 1);
		Assert.isTrue(conference.getStartDate().after(now), "StartDate elapsed");
		return res;
	}

	public Activity save(final Activity activity, final int conferenceId) {
		Assert.notNull(activity);
		Activity saved;
		final Conference conference = this.conferenceService.findOne(conferenceId);
		this.administratorService.findByPrincipal();
		if (activity.getType().equals("PRESENTATION"))
			Assert.notNull(activity.getPaper(), "La presentación debe llevar un paper asociado");
		Assert.notNull(activity.getSections());
		Assert.isTrue(activity.getStartMoment().after(conference.getStartDate()) && activity.getStartMoment().before(conference.getEndDate()), "StartMoment between the startDate and the endDate of the conference");
		Assert.isTrue((conference.getEndDate().getTime() - activity.getStartMoment().getTime()) > activity.getDuration() * 3600000, "The duration shorter than the conference total one");
		saved = this.activityRepository.saveAndFlush(activity);
		final Collection<Activity> activities = conference.getActivities();
		if (!activities.contains(saved)) {
			activities.add(saved);
			conference.setActivities(activities);
			this.conferenceService.save2(conference);
		}

		return saved;
	}

	public Activity reconstructPresentation(final Activity activity, final BindingResult binding) {
		this.administratorService.findByPrincipal();
		if (activity.getId() == 0) {
			activity.setType("PRESENTATION");
			activity.setSections(new HashSet<Section>());
		} else {
			final Activity original = this.findOne(activity.getId());
			Assert.isTrue(original.getType().equals("PRESENTATION"));
			activity.setId(original.getId());
			activity.setVersion(original.getVersion());
			activity.setType(original.getType());
			activity.setPaper(original.getPaper());
			activity.setSections(new HashSet<Section>());

		}
		this.validator.validate(activity, binding);
		return activity;
	}

	public Activity reconstructTutorial(final Activity activity, final BindingResult binding) {
		this.administratorService.findByPrincipal();
		if (activity.getId() == 0) {
			activity.setType("TUTORIAL");
			activity.setSections(new HashSet<Section>());
		} else {
			final Activity original = this.findOne(activity.getId());
			Assert.isTrue(original.getType().equals("TUTORIAL"));
			activity.setId(original.getId());
			activity.setVersion(original.getVersion());
			activity.setType(original.getType());
			activity.setPaper(null);
		}
		this.validator.validate(activity, binding);
		return activity;
	}

	public Activity reconstructPanel(final Activity activity, final BindingResult binding) {
		this.administratorService.findByPrincipal();
		if (activity.getId() == 0) {
			activity.setType("PANEL");
			activity.setSections(new HashSet<Section>());
		} else {
			final Activity original = this.findOne(activity.getId());
			Assert.isTrue(original.getType().equals("PANEL"));
			activity.setId(original.getId());
			activity.setVersion(original.getVersion());
			activity.setType(original.getType());
			activity.setPaper(null);
			activity.setSections(new HashSet<Section>());
		}
		this.validator.validate(activity, binding);
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

	public Collection<Activity> findAllActivitiesFromConference(final int conferenceId) {
		Assert.isTrue(conferenceId != 0, "ConferenceId es 0");
		Assert.notNull(conferenceId, "ConferenceId es nulo");
		Assert.isTrue(this.conferenceService.exist(conferenceId));
		Collection<Activity> result;
		result = this.activityRepository.findAllActivitiesFromConference(conferenceId);
		Assert.notNull(result, "La lista de actividades no puede ser nula");
		return result;
	}

	public void delete(final int activityId, final int conferenceId) {
		Assert.isTrue(conferenceId != 0, "ConferenceId es 0");
		Assert.notNull(conferenceId, "ConferenceId es nulo");
		Assert.isTrue(activityId != 0, "ActivityId es 0");
		Assert.notNull(activityId, "ActivityId es nulo");
		final Conference conference = this.conferenceService.findOne(conferenceId);
		final Activity activity = this.findOne(activityId);
		final Date now = new Date(System.currentTimeMillis() - 1);
		Assert.isTrue(conference.getActivities().contains(activity), "Activity must be from the conference");
		Assert.isTrue(conference.getStartDate().after(now), "StartDate elapsed");
		conference.getActivities().remove(activity);
		this.conferenceService.save2(conference);
		this.activityRepository.delete(activity.getId());
	}

	public Section createSection(final int activityId) {
		Assert.isTrue(activityId != 0, "ActivityId es 0");
		Assert.notNull(activityId, "ActivityId es nulo");
		Assert.isTrue(this.exist(activityId), "Existe activityId");
		this.administratorService.findByPrincipal();
		final Activity activity = this.findOne(activityId);
		final Conference conference = this.conferenceService.getConferenceFromActivityId(activityId);
		final Date now = new Date(System.currentTimeMillis() - 1);
		Assert.isTrue(activity.getType().equals("TUTORIAL"), "Only for tutorials");
		Assert.isTrue(conference.getStartDate().after(now), "StartDate elapsed");
		Assert.isTrue(activity.getStartMoment().after(now), "Activity already started");
		final Section section = new Section();
		return section;
	}
	public Section saveSection(final Section section, final int activityId) {
		Assert.isTrue(activityId != 0, "ActivityId es 0");
		Assert.notNull(activityId, "ActivityId es nulo");
		Assert.isTrue(this.exist(activityId), "Existe activityId");
		this.administratorService.findByPrincipal();
		final Activity activity = this.findOne(activityId);
		Assert.isTrue(activity.getType().equals("TUTORIAL"), "Only for tutorials");
		activity.getSections().add(section);
		final Section saved = this.sectionRepository.save(section);
		this.activityRepository.saveAndFlush(activity);
		return saved;
	}

	public boolean exist(final int id) {
		Assert.notNull(id);
		Assert.isTrue(id != 0);
		final boolean res = this.activityRepository.exists(id);
		Assert.notNull(res);

		return res;
	}

	public Activity save2(final Activity activity) {

		Assert.notNull(activity);
		Assert.isTrue(activity.getId() != 0);

		final Activity saved = this.activityRepository.save(activity);

		return saved;
	}

}
