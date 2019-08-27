
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


	public Activity createTutorial() {
		this.administratorService.findByPrincipal();
		final Activity result = new Activity();
		result.setType("TUTORIAL");
		result.setSections(new HashSet<Section>());
		return result;
	}

	public Activity createPresentation() {
		this.administratorService.findByPrincipal();
		final Activity result = new Activity();
		result.setType("PRESENTATION");
		return result;
	}

	public Activity createPanel() {
		this.administratorService.findByPrincipal();
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

	public Activity save(final Activity activity) {
		Assert.notNull(activity);
		Activity saved;
		this.administratorService.findByPrincipal();
		if (activity.getType().equals("PRESENTATION"))
			Assert.notNull(activity.getPaper());
		Assert.notNull(activity.getSections());
		saved = this.activityRepository.saveAndFlush(activity);
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

	//	public Activity reconstruct(final Activity activity, final BindingResult binding) {
	//		this.administratorService.findByPrincipal();
	//
	//		if (activity.getId() == 0) {
	//			if (activity.getType().equals("PRESENTATION"))
	//				Assert.notNull(activity.getPaper(), "La presentación debe llevar un paper asociado");
	//			else
	//				activity.setPaper(null);
	//			activity.setSections(new HashSet<Section>());
	//		} else {
	//			final Activity original = this.findOne(activity.getId());
	//			activity.setId(original.getId());
	//			activity.setVersion(original.getVersion());
	//			activity.setType(original.getType());
	//			if (original.getType().equals("PRESENTATION"))
	//				activity.setPaper(original.getPaper());
	//			else
	//				activity.setPaper(null);
	//			if (original.getType().equals("TUTORIAL"))
	//				activity.setSections(original.getSections());
	//			else
	//				activity.setSections(new HashSet<Section>());
	//			this.validator.validate(activity, binding);
	//		}
	//
	//		return activity;
	//	}

	public Activity reconstructPresentation(final Activity activity, final BindingResult binding) {
		this.administratorService.findByPrincipal();
		if (activity.getId() == 0) {
			Assert.notNull(activity.getPaper(), "La presentación debe llevar un paper asociado");
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

	public void delete(final int activityId, final int conferenceId) {
		Assert.isTrue(conferenceId != 0, "ConferenceId es 0");
		Assert.notNull(conferenceId, "ConferenceId es nulo");
		Assert.isTrue(activityId != 0, "ActivityId es 0");
		Assert.notNull(activityId, "ActivityId es nulo");
		final Conference conference = this.conferenceService.findOne(conferenceId);
		final Activity activity = this.findOne(activityId);
		Assert.isTrue(conference.getActivities().contains(activity), "Solo puedes borrar una activity si pertenece a la conferencia");
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
		Assert.isTrue(activity.getType().equals("TUTORIAL"), "Solo se puede añadir sections a los tutorials");
		final Section section = new Section();
		return section;
	}

	public Section saveSection(final Section section, final int activityId) {
		Assert.isTrue(activityId != 0, "ActivityId es 0");
		Assert.notNull(activityId, "ActivityId es nulo");
		Assert.isTrue(this.exist(activityId), "Existe activityId");
		this.administratorService.findByPrincipal();
		final Activity activity = this.findOne(activityId);
		Assert.isTrue(activity.getType().equals("TUTORIAL"), "Solo se puede añadir sections a los tutorials");
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

}
