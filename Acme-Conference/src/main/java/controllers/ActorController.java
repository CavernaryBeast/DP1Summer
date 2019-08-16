
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Administrator;
import domain.Author;
import domain.Reviewer;
import services.AdministratorService;
import services.AuthorService;
import services.ConfigurationParametersService;
import services.ReviewerService;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	@Autowired
	private AuthorService					authorService;

	@Autowired
	private ReviewerService					reviewerService;

	@Autowired
	private AdministratorService			administratorService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	// Sign up --------------------------------------------------------

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public ModelAndView signUp() {
		ModelAndView result;

		result = new ModelAndView("actor/signUp");
		result.addObject("requestURI", "actor/signUp.do");
		final String banner = this.configurationParametersService.getBanner();
		result.addObject("banner", banner);

		return result;
	}

	@RequestMapping(value = "/createAuthor", method = RequestMethod.GET)
	public ModelAndView createAuthor() {
		ModelAndView result;
		final Author author = this.authorService.create();

		final String role = "author";

		result = this.createEditModelAndView(author);
		result.addObject("role", role);

		return result;
	}

	@RequestMapping(value = "/createReviewer", method = RequestMethod.GET)
	public ModelAndView createReviewer() {
		ModelAndView result;
		final Reviewer reviewer = this.reviewerService.create();

		final String role = "reviewer";

		result = this.createEditModelAndView(reviewer);
		result.addObject("role", role);

		return result;
	}

	@RequestMapping(value = "/administrator/createAdministrator", method = RequestMethod.GET)
	public ModelAndView createAdmin() {
		ModelAndView result;

		this.administratorService.findByPrincipal();

		final Administrator admin = this.administratorService.create();

		final String role = "administrator";

		result = this.createEditModelAndView(admin);
		result.addObject("role", role);

		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Actor actor) {
		ModelAndView result;

		result = this.createEditModelAndView(actor, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Actor actor, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/edit");
		result.addObject("actor", actor);
		result.addObject("message", messageCode);
		// the message code references an error message or null
		final String banner = this.configurationParametersService.getBanner();
		result.addObject("banner", banner);

		return result;
	}

}
