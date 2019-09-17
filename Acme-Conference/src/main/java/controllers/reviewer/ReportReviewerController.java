
package controllers.reviewer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationParametersService;
import services.ReportService;
import services.ReviewerService;
import services.SubmissionService;
import controllers.AbstractController;
import domain.Report;
import domain.Reviewer;
import domain.Submission;

@Controller
@RequestMapping("report/reviewer")
public class ReportReviewerController extends AbstractController {

	@Autowired
	private ReportService					reportService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;

	@Autowired
	private ReviewerService					reviewerService;

	@Autowired
	private SubmissionService				submissionService;


	//Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		final ModelAndView res;

		final Collection<Report> reports = this.reportService.findOwnReports();

		res = new ModelAndView("report/list");

		res.addObject("reports", reports);
		res.addObject("requestURI", "report/reviewer/list.do");

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

	//Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int submissionId) {

		ModelAndView res;
		final Report report = this.reportService.create();

		final Submission submission = this.submissionService.findOne(submissionId);
		report.setSubmission(submission);

		final Reviewer principal = this.reviewerService.findByPrincipal();
		report.setReviewer(principal);

		res = this.createEditModelAndView(report);
		res.addObject("submissionId", submissionId);
		return res;
	}

	//	//Edition --------------------------------------------------------
	//
	//	@RequestMapping(value = "edit", method = RequestMethod.GET)
	//	public ModelAndView edit(@RequestParam final int creditcardId) {
	//
	//		final ModelAndView res;
	//		final Report report = this.reportService.findOne(creditcardId);
	//		final Author principal = this.authorService.findByPrincipal();
	//
	//		Assert.isTrue(report.getOwner().equals(principal));
	//
	//		res = this.createEditModelAndView(report);
	//
	//		return res;
	//	}

	//Save --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Report report, final BindingResult binding, @RequestParam final int submissionId) {

		ModelAndView res;
		Report saved;
		//	this.reportService.reconstruct(report, binding, submissionId);
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(report);
			res.addObject("submissionId", submissionId);
		} else
			try {
				final Submission submission = this.submissionService.findOne2(submissionId);
				this.submissionService.checkSubmisisonCanBeSavedToReview(submissionId);
				report.setSubmission(submission);
				saved = this.reportService.save(report);
				res = new ModelAndView("redirect:display.do?reportId=" + saved.getId());
				final String banner = this.configurationParametersService.getBanner();
				res.addObject("banner", banner);
			} catch (final Throwable oops) {
				if (oops.getMessage().equals("Is not a Submission To Review"))
					res = this.ListModelAndView("report.submissionNotValid");
				else
					res = this.createEditModelAndView(report, "report.commit.error");

				res.addObject("submissionId", submissionId);
			}
		return res;
	}

	//	//Delete --------------------------------------------------------
	//
	//	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
	//	public ModelAndView delete(final Report report, final BindingResult binding) {
	//
	//		ModelAndView res;
	//
	//		try {
	//			this.reportService.delete(report);
	//			res = new ModelAndView("redirect:list.do");
	//			final String banner = this.configurationParametersService.getBanner();
	//			res.addObject("banner", banner);
	//		} catch (final Throwable oops) {
	//			res = this.createEditModelAndView(report, "report.commit.error");
	//		}
	//		return res;
	//	}

	//Display --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int reportId) {

		ModelAndView res;
		final Report report = this.reportService.findOne(reportId);
		final Reviewer principal = this.reviewerService.findByPrincipal();
		Assert.isTrue(principal.equals(report.getReviewer()));

		final String role = "REVIEWER";

		res = new ModelAndView("report/display");
		res.addObject("report", report);
		res.addObject("role", role);
		res.addObject("requestURI", "report/reviewer/display.do?reportId=" + reportId);

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

	//Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Report report) {

		ModelAndView res;

		res = this.createEditModelAndView(report, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Report report, final String messageCode) {

		ModelAndView res;

		res = new ModelAndView("report/edit");

		res.addObject("report", report);
		res.addObject("message", messageCode);
		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

	protected ModelAndView ListModelAndView() {
		ModelAndView result;

		result = this.ListModelAndView(null);

		return result;
	}

	protected ModelAndView ListModelAndView(final String messageCode) {
		ModelAndView result;
		result = this.list();
		result.addObject("message", messageCode);
		return result;
	}

}
