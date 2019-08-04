
package controllers;

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
import domain.Conference;
import forms.FilterConferenceForm;

@Controller
@RequestMapping("/conference")
public class ConferenceController extends AbstractController {

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
		final FilterConferenceForm filterConferenceForm = new FilterConferenceForm();
		final String language = LocaleContextHolder.getLocale().getLanguage();
		conferences = this.conferenceService.findAll();
		result = new ModelAndView("conference/list");
		result.addObject("conferences", conferences);
		result.addObject("language", language);
		result.addObject("requestURI", "conference/list.do");
		result.addObject("actionFilter", "conference/listFilter.do");
		result.addObject("filterConferenceForm", filterConferenceForm);
		return result;
	}

	@RequestMapping(value = "/listFilter", method = RequestMethod.POST, params = "filter")
	public ModelAndView list(@Valid final FilterConferenceForm filterConferenceForm, final BindingResult binding) {
		final ModelAndView result = new ModelAndView("conference/list");
		final String language = LocaleContextHolder.getLocale().getLanguage();
		final String typeDate = filterConferenceForm.getTypeDate();
		Collection<Conference> conferences = new HashSet<>();

		if (typeDate.equals("FORTHCOMING"))
			conferences = this.conferenceService.getForthcomingConferencesByKeyword(filterConferenceForm.getKeyWord());
		else if (typeDate.equals("RUNNING"))
			conferences = this.conferenceService.getRunningConferencesByKeyword(filterConferenceForm.getKeyWord());
		else if (typeDate.equals("PAST"))
			conferences = this.conferenceService.getPastConferencesByKeyword(filterConferenceForm.getKeyWord());
		else
			conferences = this.conferenceService.getAllConferencesByKeyword(filterConferenceForm.getKeyWord());

		result.addObject("conferences", conferences);
		result.addObject("language", language);
		result.addObject("requestURI", "conference/list.do");
		result.addObject("actionFilter", "conference/listFilter.do");
		result.addObject("filterConferenceForm", filterConferenceForm);
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
