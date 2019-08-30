
package controllers.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.ConfigurationParameters;
import services.ConfigurationParametersService;

@Controller
@RequestMapping("/configurationparameters/administrator")
public class ConfigurationParametersAdmincontroller extends AbstractController {

	@Autowired
	private ConfigurationParametersService configurationParametersService;


	// Edit
	// ----------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {

		final ModelAndView res;

		final ConfigurationParameters confParams = this.configurationParametersService.getConfigurationParameters();

		res = this.createEditModelAndView(confParams);

		return res;

	}

	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("confParams") @Valid final ConfigurationParameters confParams, final BindingResult binding) {

		ModelAndView res;
		ConfigurationParameters saved;

		if (binding.hasErrors())
			res = this.createEditModelAndView(confParams);
		else
			try {
				saved = this.configurationParametersService.save(confParams);

				res = new ModelAndView("redirect:display.do");

				final String banner = this.configurationParametersService.getBanner();
				res.addObject("banner", banner);

			} catch (final Throwable oops) {
				res = this.createEditModelAndView(confParams, "confParams.commit.error");
			}
		return res;
	}

	//Display --------------------------------------------------------

	@RequestMapping(value = "display", method = RequestMethod.GET)
	public ModelAndView display() {

		ModelAndView res;
		final ConfigurationParameters confParams = this.configurationParametersService.getConfigurationParameters();

		res = new ModelAndView("configurationParameters/display");
		res.addObject("confParams", confParams);

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

	//Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final ConfigurationParameters confParams) {

		ModelAndView res;

		res = this.createEditModelAndView(confParams, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final ConfigurationParameters confParams, final String messageCode) {

		ModelAndView res;

		res = new ModelAndView("configurationParameters/edit");

		res.addObject("confParams", confParams);
		res.addObject("message", messageCode);
		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

}
