
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationParametersService;
import services.SponsorService;
import domain.Sponsor;

@Controller
@RequestMapping("/sponsor")
public class SponsorController extends AbstractController {

	@Autowired
	private SponsorService					sponsorService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	// Edition --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView res;
		final Sponsor principal = this.sponsorService.findByPrincipal();

		res = this.createEditModelAndView(principal);

		return res;
	}

	// Save -----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("actor") @Valid final Sponsor sponsor, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(sponsor);
		else
			try {
				this.sponsorService.save(sponsor);

				result = new ModelAndView("redirect:/");
				final String banner = this.configurationParametersService.getBanner();
				result.addObject("banner", banner);
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(sponsor, "actor.commit.error");
			}
		return result;

	}

	// Display --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		final Sponsor principal = this.sponsorService.findByPrincipal();
		result = new ModelAndView("sponsor/display");
		result.addObject("sponsor", principal);
		final String banner = this.configurationParametersService.getBanner();
		result.addObject("banner", banner);
		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Sponsor sponsor) {
		ModelAndView result;
		result = this.createEditModelAndView(sponsor, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Sponsor sponsor, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/edit");
		result.addObject("actor", sponsor);
		result.addObject("role", "sponsor");
		result.addObject("requestURI", "sponsor/edit.do");
		result.addObject("message", messageCode);
		// the message code references an error message or null
		final String banner = this.configurationParametersService.getBanner();
		result.addObject("banner", banner);

		return result;
	}

}
