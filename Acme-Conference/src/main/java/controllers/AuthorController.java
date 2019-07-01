
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Author;
import services.AuthorService;
import services.ConfigurationParametersService;

@Controller
@RequestMapping("/author")
public class AuthorController extends AbstractController {

	@Autowired
	private AuthorService					authorService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	// Edition --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView res;
		final Author principal = this.authorService.findByPrincipal();

		res = this.createEditModelAndView(principal);

		return res;
	}

	// Save -----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Author author, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(author);
		else
			try {
				this.authorService.save(author);

				result = new ModelAndView("redirect:/");
				final String banner = this.configurationParametersService.getBanner();
				result.addObject("banner", banner);
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(author, "actor.commit.error");
			}
		return result;

	}

	// Display --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int authorId) {
		ModelAndView result;
		Author author;

		author = this.authorService.findOne(authorId);
		result = new ModelAndView("author/display");
		result.addObject("author", author);
		final String banner = this.configurationParametersService.getBanner();
		result.addObject("banner", banner);
		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Author author) {
		ModelAndView result;

		result = this.createEditModelAndView(author, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Author author, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/edit");
		result.addObject("actor", author);
		result.addObject("role", "author");
		result.addObject("requestURI", "author/edit.do");
		result.addObject("message", messageCode);
		// the message code references an error message or null
		final String banner = this.configurationParametersService.getBanner();
		result.addObject("banner", banner);

		return result;
	}

}
