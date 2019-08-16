
package controllers.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Author;
import domain.Report;
import services.AuthorService;
import services.ConfigurationParametersService;
import services.ReportService;

@Controller
@RequestMapping("/report/author")
public class ReportAuthorController {

	@Autowired
	private ReportService					reportService;

	@Autowired
	private AuthorService					authorService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	//Display --------------------------------------------------------

	@RequestMapping(value = "display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int reportId) {

		ModelAndView res;
		final Report report = this.reportService.findOne(reportId);

		final Author principal = this.authorService.findByPrincipal();
		Assert.isTrue(principal.equals(report.getSubmission().getAuthor()));

		final String role = "AUTHOR";

		res = new ModelAndView("report/display");
		res.addObject("report", report);
		res.addObject("role", role);

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

}
