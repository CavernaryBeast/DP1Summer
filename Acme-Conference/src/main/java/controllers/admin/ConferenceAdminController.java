
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
import services.ConferenceService;
import controllers.AbstractController;
import domain.Conference;
import forms.AdministratorFilterConferenceForm;

@Controller
@RequestMapping("/conference/administrator")
public class ConferenceAdminController extends AbstractController {

	@Autowired
	ConferenceService		conferenceService;

	@Autowired
	private ActorService	actorService;


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
		Collection<Conference> conferences = new HashSet<>();

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
		result.addObject("requestURI", "conference/administrator/list.do");
		result.addObject("actionFilter", "conference/administrator/adminFilter.do");
		result.addObject("administratorfilterConferenceForm", administratorfilterConferenceForm);
		return result;
	}

	// Edit
	// ----------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int conferenceId) {

		ModelAndView result;

		Conference conference;

		conference = this.conferenceService.findOne(conferenceId);
		result = this.createEditModelAndView(conference);

		return result;

	}

	// Save
	// ----------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Conference conference, final BindingResult binding) {

		ModelAndView result;

		conference = this.conferenceService.reconstruct(conference, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(conference);
		else
			try {
				this.conferenceService.save(conference);
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
				else
					result = this.createEditModelAndView(conference, "conference.commit.error");
			}

		return result;

	}

	// Update ----------------------------------------------------------------------------------

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam final int conferenceId) {
		ModelAndView result;
		this.conferenceService.decideStatus(conferenceId);
		result = new ModelAndView("redirect:/conference/administrator/list.do");
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

		result.addObject("conference", conference);
		result.addObject("message", messageCode);
		return result;
	}

}
