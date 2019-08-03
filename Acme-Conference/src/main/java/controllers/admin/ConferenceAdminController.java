
package controllers.admin;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;
import controllers.AbstractController;
import domain.Conference;
import forms.AdministratorFilterConferenceForm;

@Controller
@RequestMapping("/conference/administrator")
public class ConferenceAdminController extends AbstractController {

	@Autowired
	ConferenceService	conferenceService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Conference conference;
		conference = this.conferenceService.create();
		result = this.createEditModelAndView(conference);
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Conference> conferences;
		final AdministratorFilterConferenceForm administratorfilterConferenceForm = new AdministratorFilterConferenceForm();
		final String language = LocaleContextHolder.getLocale().getLanguage();
		conferences = this.conferenceService.findAll();
		result = new ModelAndView("conference/list2");
		result.addObject("conferences", conferences);
		result.addObject("language", language);
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
