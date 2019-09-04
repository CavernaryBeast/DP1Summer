
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import services.ConfigurationParametersService;
import domain.Author;

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
	public ModelAndView save(@ModelAttribute("actor") @Valid final Author author, final BindingResult binding) {

		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(author);
		else
			try {
				this.authorService.save(author);

				res = new ModelAndView("redirect:/");
				final String banner = this.configurationParametersService.getBanner();
				res.addObject("banner", banner);
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(author, "actor.commit.error");
			}
		return res;

	}

	// Display --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int authorId) {

		ModelAndView res;
		Author author;

		author = this.authorService.findOne(authorId);
		res = new ModelAndView("author/display");
		res.addObject("author", author);
		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);
		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView display() {

		ModelAndView res;
		Author author;

		author = this.authorService.findByPrincipal();
		res = new ModelAndView("author/show");
		res.addObject("author", author);
		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);
		return res;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Author author) {

		ModelAndView res;

		res = this.createEditModelAndView(author, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Author author, final String messageCode) {

		ModelAndView res;

		res = new ModelAndView("actor/edit");
		res.addObject("actor", author);
		res.addObject("role", "author");
		res.addObject("requestURI", "author/edit.do");
		res.addObject("message", messageCode);
		// the message code references an error message or null
		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

}
