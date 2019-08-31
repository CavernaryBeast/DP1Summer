
package controllers.admin;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Activity;
import domain.Conference;
import domain.Paper;
import domain.Section;
import services.ActivityService;
import services.AuthorService;
import services.ConferenceService;
import services.PaperService;
import services.SubmissionService;

@Controller
@RequestMapping("/activity/administrator")
public class ActivityAdminController extends AbstractController {

	@Autowired
	private ActivityService		activityService;

	@Autowired
	private AuthorService		authorService;

	@Autowired
	private ConferenceService	conferenceService;

	@Autowired
	private SubmissionService	submissionService;

	@Autowired
	private PaperService		paperService;


	//Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int conferenceId) {

		final ModelAndView res;

		final Collection<Activity> presentations = this.activityService.findPresentationsFromConference(conferenceId);
		final Collection<Activity> tutorials = this.activityService.findTutorialsFromConference(conferenceId);
		final Collection<Activity> panels = this.activityService.findPanelsFromConference(conferenceId);
		final Collection<Conference> conferencesWithPosiblePapers = this.conferenceService.getConferencesavailablePapersForPresentations();
		final Conference conference = this.conferenceService.findOne(conferenceId);
		final Date now = new Date(System.currentTimeMillis() - 1);
		res = new ModelAndView("activity/list");

		res.addObject("presentations", presentations);
		res.addObject("tutorials", tutorials);
		res.addObject("panels", panels);
		res.addObject("conferenceId", conferenceId);
		res.addObject("conferencesWithPosiblePapers", conferencesWithPosiblePapers);
		res.addObject("conference", conference);
		res.addObject("now", now);
		res.addObject("requestURI", "activity/administrator/list.do?conferenceId=" + conferenceId);

		return res;
	}
	//Creation --------------------------------------------------------

	@RequestMapping(value = "/createPresentation", method = RequestMethod.GET)
	public ModelAndView createPresentation(@RequestParam final int conferenceId) {

		ModelAndView res;
		final Activity presentation = this.activityService.createPresentation();
		res = this.createEditModelAndView(presentation, conferenceId);
		return res;
	}

	@RequestMapping(value = "/createTutorial", method = RequestMethod.GET)
	public ModelAndView createTutorial(@RequestParam final int conferenceId) {

		ModelAndView res;
		final Activity tutotial = this.activityService.createTutorial();
		res = this.createEditModelAndView(tutotial, conferenceId);
		return res;
	}

	@RequestMapping(value = "/createPanel", method = RequestMethod.GET)
	public ModelAndView createPanel(@RequestParam final int conferenceId) {

		ModelAndView res;
		final Activity panel = this.activityService.createPanel();
		res = this.createEditModelAndView(panel, conferenceId);
		return res;
	}

	//Edition --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int activityId, final int conferenceId) {

		final ModelAndView res;
		final Activity activity = this.activityService.findOne(activityId);

		res = this.createEditModelAndView(activity, conferenceId);

		return res;
	}

	//Save --------------------------------------------------------

	@RequestMapping(value = "/editPresentation", method = RequestMethod.POST, params = "save")
	public ModelAndView savePresentation(@Valid Activity activity, final BindingResult binding, final Integer conferenceId) {

		ModelAndView res;
		Activity saved;
		activity = this.activityService.reconstructPresentation(activity, binding);
		if (binding.hasErrors()) {
			System.out.println("Field: " + binding.getFieldError().getField());
			System.out.println(binding.getGlobalErrorCount());
			System.out.println(binding.getFieldErrorCount());
			res = this.createEditModelAndView(activity, conferenceId);
		} else
			try {
				final Conference conference = this.conferenceService.findOne2(conferenceId);
				final Collection<Activity> activities = conference.getActivities();
				activities.add(activity);
				conference.setActivities(activities);
				saved = this.activityService.save(activity);
				this.conferenceService.save2(conference);
				res = new ModelAndView("redirect:/activity/administrator/list.do?conferenceId=" + conferenceId);

			} catch (final Throwable oops) {
				res = this.createEditModelAndView(activity, conferenceId, "activity.commit.error");
				System.out.println(oops.getStackTrace());
				System.out.println(oops.getCause().getMessage());
			}
		return res;
	}

	@RequestMapping(value = "/editTutorial", method = RequestMethod.POST, params = "save")
	public ModelAndView saveTutorial(Activity activity, final BindingResult binding, final Integer conferenceId) {

		ModelAndView res;
		Activity saved;
		activity = this.activityService.reconstructTutorial(activity, binding);
		if (binding.hasErrors()) {
			System.out.println("Field: " + binding.getFieldError().getField());
			System.out.println(binding.getGlobalErrorCount());
			System.out.println(binding.getFieldErrorCount());
			res = this.createEditModelAndView(activity, conferenceId);
		} else
			try {
				final Conference conference = this.conferenceService.findOne2(conferenceId);
				final Collection<Activity> activities = new HashSet<>(conference.getActivities());
				saved = this.activityService.save(activity);
				activities.add(saved);
				conference.setActivities(activities);
				this.conferenceService.save2(conference);
				res = new ModelAndView("redirect:/activity/administrator/list.do?conferenceId=" + conferenceId);

			} catch (final Throwable oops) {
				System.out.println(oops.getLocalizedMessage());
				res = this.createEditModelAndView(activity, conferenceId, "activity.commit.error");

				System.out.println(oops.getStackTrace());
				System.out.println(oops.getCause().getMessage());
			}
		return res;
	}

	@RequestMapping(value = "/editPanel", method = RequestMethod.POST, params = "save")
	public ModelAndView savePanel(@Valid Activity activity, final BindingResult binding, final Integer conferenceId) {

		ModelAndView res;
		Activity saved;
		activity = this.activityService.reconstructPanel(activity, binding);
		if (binding.hasErrors()) {
			System.out.println("Field: " + binding.getFieldError().getField());
			System.out.println(binding.getGlobalErrorCount());
			System.out.println(binding.getFieldErrorCount());
			res = this.createEditModelAndView(activity, conferenceId);
		} else
			try {
				final Conference conference = this.conferenceService.findOne2(conferenceId);
				final Collection<Activity> activities = conference.getActivities();
				activities.add(activity);
				conference.setActivities(activities);
				saved = this.activityService.save(activity);
				this.conferenceService.save2(conference);
				res = new ModelAndView("redirect:/activity/administrator/list.do?conferenceId=" + conferenceId);

			} catch (final Throwable oops) {
				res = this.createEditModelAndView(activity, conferenceId, "activity.commit.error");
				System.out.println(oops.getStackTrace());
				System.out.println(oops.getCause().getMessage());
			}
		return res;
	}

	// Show ----------------------------------------------------------------------------------

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int activityId) {
		ModelAndView result;
		Activity activity;

		activity = this.activityService.findOne(activityId);
		//To convert the duration(in hours) to miliseconds(the unit of getTime() method),we must know that 1 hour = 3600000 miliseconds
		final long scheduleDuration = (long) (activity.getStartMoment().getTime() + (activity.getDuration() * 3600000));
		final Date schedule = new Date(scheduleDuration);
		result = new ModelAndView("activity/show");
		result.addObject("activity", activity);
		result.addObject("schedule", schedule);
		return result;
	}

	// Update ----------------------------------------------------------------------------------

	@RequestMapping(value = "/addSection", method = RequestMethod.GET)
	public ModelAndView addSection(@RequestParam final int activityId) {

		ModelAndView res;
		//		Activity activity;
		//		activity = this.activityService.findOne(activityId);
		final Conference conference = this.conferenceService.getConferenceFromActivityId(activityId);
		try {
			final Section section = this.activityService.createSection(activityId);
			res = new ModelAndView("activity/addSection");
			res.addObject("section", section);
			res.addObject("activityId", activityId);
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/activity/administrator/list.do?conferenceId=" + conference.getId());
			System.out.println(oops.getStackTrace());
			System.out.println(oops.getCause().getMessage());
		}

		return res;
	}

	@RequestMapping(value = "/saveSection", method = RequestMethod.POST, params = "save")
	public ModelAndView saveSection(@Valid final Section section, final BindingResult binding, final Integer activityId) {

		ModelAndView res;
		final Section saved;
		//		activity = this.activityService.reconstructPanel(activity, binding);
		if (binding.hasErrors()) {
			System.out.println("Field: " + binding.getFieldError().getField());
			System.out.println(binding.getGlobalErrorCount());
			System.out.println(binding.getFieldErrorCount());
			res = this.createEditModelAndViewSection(section, activityId);
		} else
			try {
				saved = this.activityService.saveSection(section, activityId);
				res = new ModelAndView("redirect:/activity/administrator/show.do?acitivityId=" + activityId);
				res.addObject("activityId", activityId);
			} catch (final Throwable oops) {
				res = this.createEditModelAndViewSection(section, activityId, "activity.commit.error");
				System.out.println(oops.getStackTrace());
				System.out.println(oops.getCause().getMessage());
			}
		return res;
	}

	//Delete --------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int activityId, @RequestParam final int conferenceId) {

		ModelAndView res;

		try {
			this.activityService.delete(activityId, conferenceId);
			res = new ModelAndView("redirect:/activity/administrator/list.do?conferenceId=" + conferenceId);

		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/activity/administrator/list.do?conferenceId=" + conferenceId);
		}
		return res;
	}

	//Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Activity activity, final int conferenceId) {

		ModelAndView res;

		res = this.createEditModelAndView(activity, conferenceId, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Activity activity, final int conferenceId, final String messageCode) {
		ModelAndView res;
		if (activity.getType().equals("PRESENTATION")) {
			final Collection<Paper> papers = this.paperService.findPapersSubmissionsAcceptedWithPaperCameraReadyFromConference(conferenceId);
			res = new ModelAndView("activity/editPresentation");
			res.addObject("papers", papers);
			res.addObject("presentation", activity);
		} else if (activity.getType().equals("TUTORIAL")) {
			res = new ModelAndView("activity/editTutorial");
			res.addObject("tutorial", activity);
		} else {
			res = new ModelAndView("activity/editPanel");
			res.addObject("panel", activity);
		}
		res.addObject("message", messageCode);
		res.addObject("conferenceId", conferenceId);
		return res;
	}

	protected ModelAndView createEditModelAndViewSection(final Section section, final int activityId) {

		ModelAndView res;

		res = this.createEditModelAndViewSection(section, activityId, null);

		return res;
	}

	protected ModelAndView createEditModelAndViewSection(final Section section, final int activityId, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("activity/editTutorial");
		res.addObject("section", section);
		res.addObject("message", messageCode);
		res.addObject("activityId", activityId);
		return res;
	}

}
