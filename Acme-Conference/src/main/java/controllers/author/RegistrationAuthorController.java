
package controllers.author;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import services.ConferenceService;
import services.ConfigurationParametersService;
import services.CreditCardService;
import services.PaperService;
import services.RegistrationService;
import controllers.AbstractController;
import domain.Author;
import domain.Conference;
import domain.CreditCard;
import domain.Registration;

@Controller
@RequestMapping("/registration/author")
public class RegistrationAuthorController extends AbstractController {

	@Autowired
	private RegistrationService				registrationService;

	@Autowired
	private AuthorService					authorService;

	@Autowired
	private ConferenceService				conferenceService;

	@Autowired
	private CreditCardService				creditCardService;

	@Autowired
	private PaperService					paperService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	//Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		final ModelAndView res;
		final Collection<Registration> registrations = this.registrationService.findOwn();
		final Collection<Conference> conferences = this.conferenceService.getConferencesStartDateNotElapsed();
		res = new ModelAndView("registration/list");
		res.addObject("registrations", registrations);
		res.addObject("conferences", conferences);
		res.addObject("requestURI", "submission/author/list.do");

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

	//Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		try {
			final Registration registration = this.registrationService.create();
			res = this.createEditModelAndView(registration);
			final String lang = LocaleContextHolder.getLocale().getLanguage();
			res.addObject("lang", lang);
		} catch (final Throwable oops) {
			if (oops.getMessage().equals("No conferences avaliable"))
				res = this.ListModelAndView("registration.conferencesNotAvailable");
			else
				res = this.ListModelAndView();
		}

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

	//Save --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Registration registration, final BindingResult binding, final Integer conferenceId) {

		ModelAndView res;

		//registration = this.registrationService.reconstruct(registration, binding);
		if (binding.hasErrors()) {
			System.out.println("Field: " + binding.getFieldError().getField());
			System.out.println(binding.getGlobalErrorCount());
			System.out.println(binding.getFieldErrorCount());
			res = this.createEditModelAndView(registration, "registration.commit.error");
		} else
			try {
				Registration saved;
				CreditCard saved2;
				final Author principal = this.authorService.findByPrincipal();
				final Conference conference = this.conferenceService.findOne2(conferenceId);
				final Collection<Registration> registrationsOfConference = conference.getRegistrations();
				registration.setAuthor(principal);
				final CreditCard creditCard = registration.getCreditCard();
				this.registrationService.tryluhnTest(creditCard.getNumber());
				creditCard.setOwner(principal);
				saved2 = this.creditCardService.save(creditCard);
				registration.setCreditCard(saved2);
				saved = this.registrationService.save(registration);
				registrationsOfConference.add(saved);
				conference.setRegistrations(registrationsOfConference);
				this.conferenceService.save2(conference);
				res = new ModelAndView("redirect:/registration/author/list.do");

			} catch (final Throwable oops) {
				if (oops.getMessage().equals("Not a valid Credit Card"))
					res = this.createEditModelAndView(registration, "registration.NotValidCreditCard");
				else
					res = this.createEditModelAndView(registration, "registration.commit.error");

			}

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}
	// Show ----------------------------------------------------------------------------------

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int registrationId) {
		ModelAndView result;
		final Registration registration;

		try {
			registration = this.registrationService.findOne(registrationId);
			result = new ModelAndView("registration/show");
			result.addObject("registration", registration);
		} catch (final Throwable oops) {
			if (oops.getMessage().equals("Not the author"))
				result = this.ListModelAndView("registration.NotTheAuthor");
			else
				result = this.ListModelAndView();
		}

		final String banner = this.configurationParametersService.getBanner();
		result.addObject("banner", banner);

		return result;
	}

	//Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Registration registration) {

		ModelAndView res;

		res = this.createEditModelAndView(registration, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Registration registration, final String messageCode) {

		ModelAndView res;
		final Collection<String> makes = this.configurationParametersService.getCreditCardMakes();
		final Collection<Conference> conferences = this.conferenceService.getConferencesStartDateNotElapsed3();
		res = new ModelAndView("registration/edit");
		res.addObject("message", messageCode);
		res.addObject("registration", registration);
		res.addObject("conferences", conferences);
		res.addObject("makes", makes);

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
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

		final String banner = this.configurationParametersService.getBanner();
		result.addObject("banner", banner);

		return result;
	}

	//	protected ModelAndView createEditModelAndViewPaper(final Paper paper) {
	//
	//		ModelAndView res;
	//
	//		res = this.createEditModelAndViewPaper(paper, null);
	//
	//		return res;
	//	}
	//
	//	protected ModelAndView createEditModelAndViewPaper(final Paper paper, final String messageCode) {
	//
	//		ModelAndView res;
	//		final Collection<Author> authors = this.authorService.findAll();
	//		final Author author = this.authorService.findByPrincipal();
	//		authors.remove(author);
	//		res = new ModelAndView("submission/editPaper");
	//		res.addObject("message", messageCode);
	//		res.addObject("paper", paper);
	//		res.addObject("authors", authors);
	//		return res;
	//	}

}
