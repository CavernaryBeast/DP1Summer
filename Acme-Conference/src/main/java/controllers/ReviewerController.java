
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Reviewer;
import services.ConfigurationParametersService;
import services.ReviewerService;

@Controller
@RequestMapping("/reviewer")
public class ReviewerController extends AbstractController {

	@Autowired
	private ReviewerService					reviewerService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	// Edition --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView res;
		final Reviewer principal = this.reviewerService.findByPrincipal();

		res = this.createEditModelAndView(principal);

		return res;
	}

	// Save -----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Reviewer reviewer, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(reviewer);
		else
			try {
				this.reviewerService.save(reviewer);

				result = new ModelAndView("redirect:/");
				final String banner = this.configurationParametersService.getBanner();
				result.addObject("banner", banner);
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(reviewer, "actor.commit.error");
			}
		return result;

	}

	// Display --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int reviewerId) {
		ModelAndView result;
		Reviewer reviewer;

		reviewer = this.reviewerService.findOne(reviewerId);
		result = new ModelAndView("reviewer/display");
		result.addObject("reviewer", reviewer);
		final String banner = this.configurationParametersService.getBanner();
		result.addObject("banner", banner);
		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Reviewer reviewer) {
		ModelAndView result;

		result = this.createEditModelAndView(reviewer, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Reviewer reviewer, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/edit");
		result.addObject("actor", reviewer);
		result.addObject("role", "reviewer");
		result.addObject("requestURI", "reviewer/edit.do");
		result.addObject("message", messageCode);
		// the message code references an error message or null
		final String banner = this.configurationParametersService.getBanner();
		result.addObject("banner", banner);

		return result;
	}

}
