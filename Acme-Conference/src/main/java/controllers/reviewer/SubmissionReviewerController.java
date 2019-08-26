
package controllers.reviewer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationParametersService;
import services.SubmissionService;
import controllers.AbstractController;
import domain.Submission;

@Controller
@RequestMapping("submission/reviewer")
public class SubmissionReviewerController extends AbstractController {

	@Autowired
	private SubmissionService				submissionService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	//Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		final ModelAndView res;

		final Collection<Submission> ownSubmissions = this.submissionService.findSubmissionsToReview();

		res = new ModelAndView("submission/list");

		res.addObject("submissions", ownSubmissions);
		res.addObject("requestURI", "submission/reviewer/list.do");

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

	// Show ----------------------------------------------------------------------------------

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int submissionId) {
		ModelAndView result;
		Submission submission;

		submission = this.submissionService.findOne(submissionId);
		result = new ModelAndView("submission/show");
		result.addObject("submission", submission);

		return result;
	}

}
