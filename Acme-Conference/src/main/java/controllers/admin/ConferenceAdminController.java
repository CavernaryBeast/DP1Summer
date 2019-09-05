
package controllers.admin;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdministratorService;
import services.CategoryService;
import services.ConferenceService;
import services.ReviewerService;
import services.SubmissionService;
import controllers.AbstractController;
import domain.Category;
import domain.Conference;
import domain.Reviewer;
import domain.Submission;
import forms.AdministratorFilterConferenceForm;

@Controller
@RequestMapping("/conference/administrator")
public class ConferenceAdminController extends AbstractController {

	@Autowired
	private ConferenceService		conferenceService;

	@Autowired
	private SubmissionService		submissionService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ReviewerService			reviewerService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private CategoryService			categoryService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Conference conference;

		conference = this.conferenceService.create();
		result = this.createEditModelAndView(conference);
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Conference> conferences;
		Collection<Conference> conferencesWithUnderReviewSubmissions;
		final AdministratorFilterConferenceForm administratorfilterConferenceForm = new AdministratorFilterConferenceForm();
		final String language = LocaleContextHolder.getLocale().getLanguage();
		conferences = this.conferenceService.findAll();
		conferencesWithUnderReviewSubmissions = this.conferenceService.getConferencesWithUnderReviewSubmissions();
		final Date now = new Date(System.currentTimeMillis() - 1);
		result = new ModelAndView("conference/list2");
		result.addObject("conferences", conferences);
		result.addObject("conferencesWithUnderReviewSubmissions", conferencesWithUnderReviewSubmissions);
		result.addObject("language", language);
		result.addObject("now", now);
		result.addObject("requestURI", "conference/administrator/list.do");
		result.addObject("actionFilter", "conference/administrator/adminFilter.do");
		result.addObject("administratorfilterConferenceForm", administratorfilterConferenceForm);
		return result;

	}

	@RequestMapping(value = "/adminFilter", method = RequestMethod.POST, params = "filter")
	public ModelAndView list(@Valid final AdministratorFilterConferenceForm administratorfilterConferenceForm, final BindingResult binding) {
		final ModelAndView result = new ModelAndView("conference/list2");
		final String language = LocaleContextHolder.getLocale().getLanguage();
		final String typeFilter = administratorfilterConferenceForm.getTypeFilter();
		final Date now = new Date(System.currentTimeMillis() - 1);
		Collection<Conference> conferences = new HashSet<>();
		final Collection<Conference> conferencesWithUnderReviewSubmissions = this.conferenceService.getConferencesWithUnderReviewSubmissions();

		if (typeFilter.equals("SUBMISSION"))
			conferences = this.conferenceService.getConferencesSubmissionDeadlineLastFiveDays();
		else if (typeFilter.equals("NOTIFICATION"))
			conferences = this.conferenceService.getConferencesNotificationDeadlineInLessFiveDays();
		else if (typeFilter.equals("CAMERAREADY"))
			conferences = this.conferenceService.getConferencesCameraReadyDeadlineInLessFiveDays();
		else if (typeFilter.equals("ORGANISED"))
			conferences = this.conferenceService.getConferencesStartDateInLessFiveDays();
		else
			conferences = this.conferenceService.findAll();

		result.addObject("conferences", conferences);
		result.addObject("language", language);
		result.addObject("now", now);
		result.addObject("requestURI", "conference/administrator/list.do");
		result.addObject("actionFilter", "conference/administrator/adminFilter.do");
		result.addObject("administratorfilterConferenceForm", administratorfilterConferenceForm);
		result.addObject("conferencesWithUnderReviewSubmissions", conferencesWithUnderReviewSubmissions);
		return result;
	}

	// Edit
	// ----------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int conferenceId) {

		ModelAndView result;

		try {
			Conference conference;
			conference = this.conferenceService.findOneEditable(conferenceId);
			result = this.createEditModelAndView(conference);

		} catch (final Throwable oops) {
			oops.printStackTrace();
			if (oops.getMessage().equals("The conference in database before saving must not be in final mode in order to modify it"))
				result = this.ListModelAndView("conference.form.error.alreadyFinalMode");
			else
				result = this.ListModelAndView();
		}

		return result;

	}
	protected ModelAndView ListModelAndView() {
		ModelAndView result;

		result = this.ListModelAndView(null);

		return result;
	}

	protected ModelAndView ListModelAndView(final String messageCode) {
		ModelAndView result;
		result = this.list();
		result.addObject("message", messageCode);
		return result;
	}

	// Save
	// ----------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Conference conference, final BindingResult binding, final int categoryId) {

		ModelAndView result;

		conference = this.conferenceService.reconstruct(conference, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(conference);
		else
			try {
				final Conference saved = this.conferenceService.save(conference);

				final Category category = this.categoryService.findOne(categoryId);
				category.getConferences().add(saved);
				this.categoryService.save(category);

				result = new ModelAndView("redirect:/conference/administrator/list.do");

			} catch (final Throwable oops) {
				oops.printStackTrace();
				if (oops.getMessage().equals("SubmissionDeadline"))
					result = this.createEditModelAndView(conference, "conference.form.error.submissionDeadline");
				else if (oops.getMessage().equals("NotificationDeadline"))
					result = this.createEditModelAndView(conference, "conference.form.error.notificationDeadline");
				else if (oops.getMessage().equals("CameraReadyDeadline"))
					result = this.createEditModelAndView(conference, "conference.form.error.cameraReadyDeadline");
				else if (oops.getMessage().equals("StartDate"))
					result = this.createEditModelAndView(conference, "conference.form.error.startDate");
				else if (oops.getMessage().equals("EndDate"))
					result = this.createEditModelAndView(conference, "conference.form.error.endDate");
				else if (oops.getMessage().equals("The conference in database before saving must not be in final mode in order to modify it"))
					result = this.createEditModelAndView(conference, "conference.form.error.alreadyFinalMode");
				else
					result = this.createEditModelAndView(conference, "conference.commit.error");
			}

		return result;

	}

	// Update ----------------------------------------------------------------------------------

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam final int conferenceId) {
		ModelAndView result;

		try {
			this.conferenceService.decideStatus(conferenceId);
			result = new ModelAndView("redirect:/conference/administrator/list.do");

		} catch (final Throwable oops) {
			oops.printStackTrace();
			if (oops.getMessage().equals("Submission Deadline not Elapsed"))
				result = this.ListModelAndView("conference.cantUpdate.submissionDeadlineNotElased");
			else if (oops.getMessage().equals("Camera-Ready Deadline already Elapsed"))
				result = this.ListModelAndView("conference.cantUpdate.cameraReadyDeadlineElapsed");
			else if (oops.getMessage().equals("The conference must have Under-Review submissions"))
				result = this.ListModelAndView("conference.cantUpdate.NoUnderReviewSubmissions");
			else
				result = this.ListModelAndView();
		}
		return result;
	}
	protected ModelAndView createEditModelAndView(final Conference conference) {
		ModelAndView result;

		result = this.createEditModelAndView(conference, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Conference conference, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("conference/edit");

		final Collection<Category> categories = this.categoryService.findAll();
		final String lang = LocaleContextHolder.getLocale().getLanguage();

		result.addObject("conference", conference);
		result.addObject("categories", categories);
		result.addObject("lang", lang);
		result.addObject("message", messageCode);
		return result;
	}

	@RequestMapping(value = "/listSubmissions", method = RequestMethod.GET)
	public ModelAndView listSubmissions(@RequestParam final int conferenceId) {
		ModelAndView result;
		Conference conference;
		conference = this.conferenceService.findOne(conferenceId);
		Collection<Submission> accepted;
		Collection<Submission> underReview;
		Collection<Submission> rejected;

		accepted = this.submissionService.findAcceptedSubmissionsFromConference(conferenceId);
		underReview = this.submissionService.findUnderReviewSubmissionsFromConference(conferenceId);
		rejected = this.submissionService.findRejectedSubmissionsFromConference(conferenceId);
		final Date now = new Date(System.currentTimeMillis() - 1);
		result = new ModelAndView("submission/listGeneral");
		result.addObject("accepted", accepted);
		result.addObject("underReview", underReview);
		result.addObject("rejected", rejected);
		result.addObject("now", now);
		result.addObject("conference", conference);
		result.addObject("requestURI", "conference/administrator/listSubmissions.do?conferenceId=" + conferenceId);
		return result;
	}

	protected ModelAndView ListSubmissionModelAndView(final int conferenceId) {
		ModelAndView result;

		result = this.ListSubmissionModelAndView(conferenceId, null);

		return result;
	}

	protected ModelAndView ListSubmissionModelAndView(final int conferenceId, final String messageCode) {
		ModelAndView result;
		result = this.listSubmissions(conferenceId);
		result.addObject("message", messageCode);
		return result;
	}

	@RequestMapping(value = "/assignSubmissionAutomatic", method = RequestMethod.GET)
	public ModelAndView assignSubmission(@RequestParam final int submissionId) {
		ModelAndView result;
		Conference conference;
		try {

			final Submission submission;
			this.submissionService.assignSubmission(submissionId);
			conference = this.conferenceService.getConferenceFromSubmissionId(submissionId);
			result = new ModelAndView("redirect:/conference/administrator/listSubmissions.do?conferenceId=" + conference.getId());

		} catch (final Throwable oops) {
			oops.printStackTrace();
			conference = this.conferenceService.getConferenceFromSubmissionId(submissionId);
			if (oops.getMessage().equals("Maximo 3 reviewers"))
				result = this.ListSubmissionModelAndView(conference.getId(), "submission.cantAddMoreReviewers");
			else if (oops.getMessage().equals("NotificationDeadline Elapsed"))
				result = this.ListSubmissionModelAndView(conference.getId(), "submission.cantAssingNDElapsed");
			else
				result = this.ListModelAndView();
		}

		return result;
	}

	@RequestMapping(value = "/addReviewer", method = RequestMethod.GET)
	public ModelAndView addReviewer(@RequestParam final int submissionId) {
		ModelAndView result;
		Submission submission;
		Conference conference;
		try {

			submission = this.submissionService.findOne3(submissionId);
			result = this.createEditModelAndViewAddReviewer(submission);

		} catch (final Throwable oops) {
			oops.printStackTrace();
			conference = this.conferenceService.getConferenceFromSubmissionId(submissionId);
			if (oops.getMessage().equals("Maximo 3 reviewers"))
				result = this.ListSubmissionModelAndView(conference.getId(), "submission.cantAddMoreReviewers");
			else if (oops.getMessage().equals("NotificationDeadline Elapsed"))
				result = this.ListSubmissionModelAndView(conference.getId(), "submission.cantAssingNDElapsed");
			else
				result = this.ListModelAndView();
		}

		return result;

	}

	@RequestMapping(value = "saveReviewer", method = RequestMethod.POST, params = "save")
	public ModelAndView savePaper(final Submission submission, final BindingResult binding, final Integer reviewerId) {

		ModelAndView res;
		Submission saved;
		Conference conference;
		//submission = this.submissionService.reconstructAddReviewer(submission, reviewerId, binding);
		final Reviewer reviewer = this.reviewerService.findOne(reviewerId);
		final Submission original = this.submissionService.findOne2(submission.getId());
		submission.setId(original.getId());
		submission.setVersion(original.getVersion());
		submission.setAuthor(original.getAuthor());
		submission.setMoment(original.getMoment());
		submission.setPaper(original.getPaper());
		submission.setTicker(original.getTicker());
		submission.setStatus(original.getStatus());
		submission.setReviewers(original.getReviewers());
		submission.getReviewers().add(reviewer);
		if (binding.hasErrors()) {
			System.out.println("Field: " + binding.getFieldError().getField());
			System.out.println(binding.getGlobalErrorCount());
			System.out.println(binding.getFieldErrorCount());
			res = this.createEditModelAndViewAddReviewer(submission);
		} else
			try {
				saved = this.submissionService.save2(submission);
				conference = this.conferenceService.getConferenceFromSubmissionId(submission.getId());
				res = new ModelAndView("redirect:/conference/administrator/listSubmissions.do?conferenceId=" + conference.getId());
			} catch (final Throwable oops) {
				res = this.createEditModelAndViewAddReviewer(submission, "submission.commit.error");
				System.out.println(oops.getStackTrace());
				System.out.println(oops.getCause().getMessage());
			}
		return res;
	}

	//	@RequestMapping(value = "decideStatus", method = RequestMethod.GET)
	//	public ModelAndView decideStatus(@RequestParam final int conferenceId) {
	//
	//		final ModelAndView res;
	//		this.conferenceService.decideStatus(conferenceId);
	//
	//		res = new ModelAndView("redirect:listSubmissions.do?conferenceId=" + conferenceId);
	//
	//		return res;
	//	}

	protected ModelAndView createEditModelAndViewAddReviewer(final Submission submission, final String messageCode) {

		ModelAndView res;
		this.administratorService.findByPrincipal();
		final Collection<Reviewer> posibleReviewers = this.reviewerService.selectAvailableAuthorsToAssingToSubmission(submission.getId());
		res = new ModelAndView("submission/add");
		res.addObject("message", messageCode);
		res.addObject("submission", submission);
		res.addObject("reviewers", posibleReviewers);

		return res;
	}

	protected ModelAndView createEditModelAndViewAddReviewer(final Submission submission) {

		ModelAndView res;

		res = this.createEditModelAndViewAddReviewer(submission, null);

		return res;
	}

}
