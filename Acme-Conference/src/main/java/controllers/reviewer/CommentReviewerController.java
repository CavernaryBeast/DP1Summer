
package controllers.reviewer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Comment;
import domain.Report;
import domain.Reviewer;
import services.CommentService;
import services.ConfigurationParametersService;
import services.ReportService;
import services.ReviewerService;

@Controller
@RequestMapping("/comment/reviewer")
public class CommentReviewerController extends AbstractController {

	@Autowired
	private CommentService					commentService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;

	@Autowired
	private ReportService					reportService;

	@Autowired
	private ReviewerService					reviewerService;


	//Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int reportId) {

		final ModelAndView res;

		final Report report = this.reportService.findOne(reportId);

		final Collection<Comment> comments = report.getComments();

		res = new ModelAndView("comment/list");

		res.addObject("comments", comments);

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		final String object = "report";
		res.addObject("object", object);
		res.addObject("reportId", reportId);

		return res;
	}

	//Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int reportId) {

		ModelAndView res;
		final Comment comment = this.commentService.create();
		final Reviewer author = this.reviewerService.findByPrincipal();

		comment.setAuthor(author);

		this.reportService.findOne(reportId);

		res = this.createEditModelAndView(comment);

		res.addObject("actionURI", "comment/reviewer/edit.do?reportId=" + reportId);

		return res;
	}

	//Save --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("comment") @Valid final Comment comment, final BindingResult binding, @RequestParam final int reportId) {

		ModelAndView res;

		if (binding.hasErrors()) {
			res = this.createEditModelAndView(comment);
			res.addObject("actionURI", "comment/reviewer/edit.do?reportId=" + reportId);
		} else
			try {
				this.commentService.saveToReport(comment, reportId);

				res = new ModelAndView("redirect:display.do?reportId=" + reportId);
				final String banner = this.configurationParametersService.getBanner();
				res.addObject("banner", banner);
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(comment, "report.commit.error");
				res.addObject("actionURI", "comment/reviewer/edit.do?reportId=" + reportId);
			}

		return res;
	}

	//	//Display --------------------------------------------------------
	//
	//	@RequestMapping(value = "display", method = RequestMethod.GET)
	//	public ModelAndView display(@RequestParam final int reportId) {
	//
	//		ModelAndView res;
	//		final Report report = this.reportService.findOne(reportId);
	//
	//		res = new ModelAndView("report/display");
	//		res.addObject("report", report);
	//
	//		final String banner = this.configurationParametersService.getBanner();
	//		res.addObject("banner", banner);
	//
	//		return res;
	//	}

	//Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Comment comment) {

		ModelAndView res;

		res = this.createEditModelAndView(comment, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Comment comment, final String messageCode) {

		ModelAndView res;

		res = new ModelAndView("comment/edit");

		res.addObject("comment", comment);
		res.addObject("message", messageCode);
		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

}
