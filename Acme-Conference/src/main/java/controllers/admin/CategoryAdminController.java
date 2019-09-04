
package controllers.admin;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.CategoryService;
import services.ConfigurationParametersService;
import controllers.AbstractController;
import domain.Category;

@Controller
@RequestMapping("/category/administrator")
public class CategoryAdminController extends AbstractController {

	@Autowired
	private CategoryService					categoryService;

	@Autowired
	private AdministratorService			administratorService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	//Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView res;

		final Collection<Category> categories = this.categoryService.findAll();

		this.administratorService.findByPrincipal();

		res = new ModelAndView("category/list");

		res.addObject("categories", categories);
		res.addObject("requestURI", "category/administrator/list.do");

		final String lang = LocaleContextHolder.getLocale().getLanguage();
		res.addObject("lang", lang);

		return res;
	}

	//Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView res;
		final Category category = this.categoryService.create();

		this.administratorService.findByPrincipal();

		res = this.createEditModelAndView(category);

		return res;
	}

	//Creation --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int categoryId) {

		ModelAndView res;

		final Category category = this.categoryService.findOne(categoryId);
		this.administratorService.findByPrincipal();

		res = this.createEditModelAndView(category);

		return res;
	}

	//Save --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("category") @Valid final Category category, final BindingResult binding) {

		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(category);
		else
			try {
				this.categoryService.save(category);

				res = new ModelAndView("redirect:list.do");
				final String banner = this.configurationParametersService.getBanner();
				res.addObject("banner", banner);

			} catch (final Throwable oops) {
				res = this.createEditModelAndView(category, "category.commit.error");
			}
		return res;
	}

	//Delete --------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int categoryId) {

		ModelAndView res;
		final Category toDelete = this.categoryService.findOne(categoryId);

		try {
			this.categoryService.delete(toDelete);
			res = new ModelAndView("redirect:list.do");
			final String banner = this.configurationParametersService.getBanner();
			res.addObject("banner", banner);
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:list.do");
			final String error = "Cannot delete this category";
			res.addObject("error", error);
		}
		return res;
	}

	//Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Category category) {

		ModelAndView res;

		res = this.createEditModelAndView(category, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Category category, final String messageCode) {

		ModelAndView res;

		res = new ModelAndView("category/edit");

		final Collection<Category> categories = this.categoryService.findAll();
		if (category.getFather() != null)
			categories.remove(category);

		res.addObject("category", category);
		res.addObject("categories", categories);
		res.addObject("message", messageCode);
		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);
		final String lang = LocaleContextHolder.getLocale().getLanguage();
		res.addObject("lang", lang);

		return res;
	}

}
