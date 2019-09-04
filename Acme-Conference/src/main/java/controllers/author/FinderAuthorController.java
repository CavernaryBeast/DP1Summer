
package controllers.author;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import services.CategoryService;
import services.ConferenceService;
import services.ConfigurationParametersService;
import services.FinderService;
import controllers.AbstractController;
import domain.Author;
import domain.Category;
import domain.Conference;
import domain.Finder;

@Controller
@RequestMapping("/finder/author")
public class FinderAuthorController extends AbstractController {

	@Autowired
	private FinderService					finderService;

	@Autowired
	private AuthorService					authorService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;

	@Autowired
	private ConferenceService				conferenceService;

	@Autowired
	private CategoryService					categoryService;


	//Edition --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {

		final ModelAndView res;

		final Author principal = this.authorService.findByPrincipal();

		final Finder finder = principal.getFinder();

		final Collection<Category> categories = this.categoryService.findAll();

		final String lang = LocaleContextHolder.getLocale().getLanguage();

		res = this.createEditModelAndView(finder);
		res.addObject("categories", categories);
		res.addObject("lang", lang);

		return res;
	}

	//Save --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("finder") @Valid final Finder finder, final BindingResult binding) {

		ModelAndView res;
		final Finder saved;

		if (binding.hasErrors())
			res = this.createEditModelAndView(finder);
		else
			try {
				this.finderService.save(finder);

				res = new ModelAndView("redirect:display.do");

				final String banner = this.configurationParametersService.getBanner();
				res.addObject("banner", banner);
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(finder, "finder.commit.error");
			}
		return res;
	}

	//Display --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {

		ModelAndView res;
		final Author principal = this.authorService.findByPrincipal();
		final Finder finder = principal.getFinder();

		res = new ModelAndView("conference/listFound");

		final Collection<Conference> conferences = this.conferenceService.useFinder(finder);

		res.addObject("conferences", conferences);
		res.addObject("requestURI", "finder/author/display.do");

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

	//Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Finder finder) {

		ModelAndView res;

		res = this.createEditModelAndView(finder, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Finder finder, final String messageCode) {

		ModelAndView res;

		res = new ModelAndView("finder/edit");

		res.addObject("finder", finder);

		res.addObject("message", messageCode);
		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

}
