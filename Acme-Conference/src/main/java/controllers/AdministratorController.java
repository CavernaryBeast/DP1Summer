
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Administrator;
import services.AdministratorService;
import services.ConfigurationParametersService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private AdministratorService			administratorService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	// Edition --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView res;
		final Administrator principal = this.administratorService.findByPrincipal();

		res = this.createEditModelAndView(principal);

		return res;
	}

	// Save -----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("administrator") @Valid final Administrator administrator, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(administrator);
		else
			try {
				this.administratorService.save(administrator);

				result = new ModelAndView("redirect:/");
				final String banner = this.configurationParametersService.getBanner();
				result.addObject("banner", banner);
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(administrator, "actor.commit.error");
			}
		return result;

	}

	// Display --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		final Administrator principal = this.administratorService.findByPrincipal();

		result = new ModelAndView("administrator/display");
		result.addObject("administrator", principal);
		final String banner = this.configurationParametersService.getBanner();
		result.addObject("banner", banner);
		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Administrator administrator) {
		ModelAndView result;

		result = this.createEditModelAndView(administrator, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Administrator administrator, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/edit");
		result.addObject("actor", administrator);
		result.addObject("role", "administrator");
		result.addObject("requestURI", "administrator/edit.do");
		result.addObject("message", messageCode);
		// the message code references an error message or null
		final String banner = this.configurationParametersService.getBanner();
		result.addObject("banner", banner);

		return result;
	}

}
