
package controllers;

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

import services.CategoryService;
import services.ConferenceService;
import services.SponsorshipService;
import domain.Category;
import domain.Conference;
import domain.Sponsorship;
import forms.FilterConferenceForm;

@Controller
@RequestMapping("/conference")
public class ConferenceController extends AbstractController {

	@Autowired
	ConferenceService			conferenceService;

	@Autowired
	private CategoryService		categoryService;

	@Autowired
	private SponsorshipService	sponsorshipService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Conference> conferences;
		final FilterConferenceForm filterConferenceForm = new FilterConferenceForm();
		filterConferenceForm.setCategory(null);
		final String language = LocaleContextHolder.getLocale().getLanguage();
		conferences = this.conferenceService.getAllConferencesFinalMode();
		final Date now = new Date(System.currentTimeMillis() - 1);
		result = new ModelAndView("conference/list");

		final Collection<Category> categories = this.categoryService.findAll();

		result.addObject("conferences", conferences);
		result.addObject("language", language);
		result.addObject("requestURI", "conference/list.do");
		result.addObject("actionFilter", "conference/listFilter.do");
		result.addObject("filterConferenceForm", filterConferenceForm);
		result.addObject("now", now);
		result.addObject("categories", categories);
		return result;
	}

	@RequestMapping(value = "/listFilter", method = RequestMethod.POST, params = "filter")
	public ModelAndView list(@Valid final FilterConferenceForm filterConferenceForm, final BindingResult binding) {
		final ModelAndView result = new ModelAndView("conference/list");
		final String language = LocaleContextHolder.getLocale().getLanguage();
		final String typeDate = filterConferenceForm.getTypeDate();
		Collection<Conference> conferences = new HashSet<>();
		final Date now = new Date(System.currentTimeMillis() - 1);

		if (typeDate.equals("FORTHCOMING"))
			conferences = this.conferenceService.getForthcomingConferencesByKeyword(filterConferenceForm.getKeyWord());
		else if (typeDate.equals("RUNNING"))
			conferences = this.conferenceService.getRunningConferencesByKeyword(filterConferenceForm.getKeyWord());
		else if (typeDate.equals("PAST"))
			conferences = this.conferenceService.getPastConferencesByKeyword(filterConferenceForm.getKeyWord());
		else
			conferences = this.conferenceService.getAllConferencesByKeyword(filterConferenceForm.getKeyWord());

		if (filterConferenceForm.getCategory() != null) {
			final Collection<Conference> aux = filterConferenceForm.getCategory().getConferences();
			conferences.retainAll(aux);
		}

		final Collection<Category> categories = this.categoryService.findAll();

		result.addObject("conferences", conferences);
		result.addObject("language", language);
		result.addObject("requestURI", "conference/list.do");
		result.addObject("actionFilter", "conference/listFilter.do");
		result.addObject("filterConferenceForm", filterConferenceForm);
		result.addObject("now", now);
		result.addObject("categories", categories);
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int conferenceId) {
		ModelAndView result;
		Conference conference;

		conference = this.conferenceService.findOne2(conferenceId);
		result = new ModelAndView("conference/show");
		result.addObject("conference", conference);
		Collection<Sponsorship> sponsorships = new HashSet<Sponsorship>();
		sponsorships = this.sponsorshipService.findSponsorshipsFromConferenceId(conference.getId());
		if (!sponsorships.isEmpty()) {
			final Sponsorship randomSponsorship = this.sponsorshipService.randomSponsorShip(sponsorships);
			result.addObject("randomSponsorship", randomSponsorship);
		}
		result.addObject("sponsorships", sponsorships);

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
