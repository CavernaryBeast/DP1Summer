
package controllers.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.AuthorService;
import services.ConferenceService;
import services.PaperService;
import services.SubmissionService;
import controllers.AbstractController;
import domain.Activity;
import domain.Author;
import domain.Paper;
import domain.Submission;

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
		res = new ModelAndView("activity/list");

		res.addObject("presentations", presentations);
		res.addObject("tutorials", tutorials);
		res.addObject("panels", panels);
		res.addObject("conferenceId", conferenceId);
		res.addObject("requestURI", "activity/administrator/list.do?conferenceId=" + conferenceId);

		return res;
	}

	//Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET, params = "presentation")
	public ModelAndView createPresentation(@RequestParam final int conferenceId) {

		ModelAndView res;
		final Activity presentation = this.activityService.createPresentation();
		res = this.createEditModelAndView(presentation, conferenceId);
		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET, params = "tutorial")
	public ModelAndView createTutorial(@RequestParam final int conferenceId) {

		ModelAndView res;
		final Activity tutotial = this.activityService.createTutorial();
		res = this.createEditModelAndView(tutotial, conferenceId);
		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET, params = "panel")
	public ModelAndView createPanel(@RequestParam final int conferenceId) {

		ModelAndView res;
		final Activity panel = this.activityService.createPanel();
		res = this.createEditModelAndView(panel, conferenceId);
		return res;
	}

	//Edition --------------------------------------------------------

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int activityId, final int conferenceId) {

		final ModelAndView res;
		final Activity activity = this.activityService.findOne(activityId);

		res = this.createEditModelAndView(activity, conferenceId);

		return res;
	}

	//Save --------------------------------------------------------

	//	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "save")
	//	public ModelAndView save(@Valid Activity activity, final BindingResult binding, final Integer conferenceId) {
	//
	//		ModelAndView res;
	//		Submission saved;
	//		activity = this.activityService.reconstruct(activity, binding);
	//		if (binding.hasErrors()) {
	//			System.out.println("Field: " + binding.getFieldError().getField());
	//			System.out.println(binding.getGlobalErrorCount());
	//			System.out.println(binding.getFieldErrorCount());
	//			res = this.createEditModelAndView(submission);
	//		} else
	//			try {
	//
	//				final Conference conference = this.conferenceService.findOne2(conferenceId);
	//				final Collection<Submission> submissionsOfConference = conference.getSubmissions();
	//				submissionsOfConference.add(submission);
	//				conference.setSubmissions(submissionsOfConference);
	//				saved = this.submissionService.save(submission);
	//				this.conferenceService.save(conference);
	//				res = new ModelAndView("redirect:/submission/author/list.do");
	//
	//			} catch (final Throwable oops) {
	//				res = this.createEditModelAndView(submission, "submission.commit.error");
	//				System.out.println(oops.getStackTrace());
	//				System.out.println(oops.getCause().getMessage());
	//			}
	//		return res;
	//	}

	// Show ----------------------------------------------------------------------------------

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int activityId) {
		ModelAndView result;
		Activity activity;

		activity = this.activityService.findOne(activityId);
		result = new ModelAndView("activity/show");
		result.addObject("activity", activity);

		return result;
	}

	// Update ----------------------------------------------------------------------------------

	//Delete --------------------------------------------------------

	//	@RequestMapping(value = "edit", method = RequestMethod.GET, params = "delete")
	//	public ModelAndView delete(final Submission submission, final BindingResult binding) {
	//
	//		ModelAndView res;
	//
	//		try {
	//			this.activityService.delete(submission);
	//			res = new ModelAndView("redirect:list.do");
	//
	//		} catch (final Throwable oops) {
	//			res = this.createEditModelAndView(submission, "creditcard.commit.error");
	//		}
	//		return res;
	//	}

	//Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Activity activity, final int conferenceId) {

		ModelAndView res;

		res = this.createEditModelAndView(activity, conferenceId, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Activity activity, final int conferenceId, final String messageCode) {

		ModelAndView res;
		//final Collection<Conference> conferences = this.conferenceService.getConferencesSubmissionDeadlineNotElapsed();
		res = new ModelAndView("activity/edit");
		res.addObject("message", messageCode);
		res.addObject("activity", activity);
		res.addObject("conferenceId", conferenceId);
		if (activity.getType().equals("PRESENTATION")) {
			final Collection<Submission> submissions = this.submissionService.findSubmissionsAcceptedWithPaperCameraReadyFromConference(conferenceId);
			res.addObject("submissions", submissions);
		}

		return res;
	}

	protected ModelAndView createEditModelAndViewPaper(final Paper paper) {

		ModelAndView res;

		res = this.createEditModelAndViewPaper(paper, null);

		return res;
	}

	protected ModelAndView createEditModelAndViewPaper(final Paper paper, final String messageCode) {

		ModelAndView res;
		final Collection<Author> authors = this.authorService.findAll();
		final Author author = this.authorService.findByPrincipal();
		authors.remove(author);
		res = new ModelAndView("submission/editPaper");
		res.addObject("message", messageCode);
		res.addObject("paper", paper);
		res.addObject("authors", authors);
		return res;
	}

}
