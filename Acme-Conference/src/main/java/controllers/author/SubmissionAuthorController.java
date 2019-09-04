
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import services.ConferenceService;
import services.PaperService;
import services.SubmissionService;
import controllers.AbstractController;
import domain.Author;
import domain.Conference;
import domain.Paper;
import domain.Submission;

@Controller
@RequestMapping("/submission/author")
public class SubmissionAuthorController extends AbstractController {

	@Autowired
	private SubmissionService	submissionService;

	@Autowired
	private AuthorService		authorService;

	@Autowired
	private ConferenceService	conferenceService;

	@Autowired
	private PaperService		paperService;


	//Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		final ModelAndView res;

		final Collection<Submission> submissions = this.submissionService.findOwn();
		final Collection<Conference> conferences = this.conferenceService.getConferencesSubmissionDeadlineNotElapsed();
		final Collection<Submission> submissionsWithEditablePapers = this.submissionService.findSubmissionsWithEditablePapers();
		res = new ModelAndView("submission/list");

		res.addObject("submissions", submissions);
		res.addObject("conferences", conferences);
		res.addObject("submissionsWithEditablePapers", submissionsWithEditablePapers);
		res.addObject("requestURI", "submission/author/list.do");

		return res;
	}

	//Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		try {
			final Submission submission = this.submissionService.create();
			result = this.createEditModelAndView(submission);
			final String lang = LocaleContextHolder.getLocale().getLanguage();
			result.addObject("lang", lang);
		} catch (final Throwable oops) {
			oops.printStackTrace();
			if (oops.getMessage().equals("It must be conferences in final mode to create a submission"))
				result = this.ListModelAndView("submission.error.noConferences");
			else
				result = this.ListModelAndView();
		}

		return result;
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

	//Edition --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int submissionId) {

		final ModelAndView res;
		final Submission submission = this.submissionService.findOne(submissionId);
		final Author principal = this.authorService.findByPrincipal();

		//	Assert.isTrue(creditCard.getOwner().equals(principal));

		res = this.createEditModelAndView(submission);

		return res;
	}

	//Save --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("submission") Submission submission, final BindingResult binding, final Integer conferenceId) {

		ModelAndView res;
		Submission saved;
		System.out.println("reviewers en controller: " + submission.getReviewers());
		submission = this.submissionService.reconstruct(submission, binding);
		System.out.println("reviewers en tras reconstruct: " + submission.getReviewers());
		if (binding.hasErrors()) {
			System.out.println("Field: " + binding.getFieldError().getField());
			System.out.println(binding.getGlobalErrorCount());
			System.out.println(binding.getFieldErrorCount());
			res = this.createEditModelAndView(submission);
		} else
			try {

				final Conference conference = this.conferenceService.findOne2(conferenceId);
				final Collection<Submission> submissionsOfConference = conference.getSubmissions();
				submissionsOfConference.add(submission);
				conference.setSubmissions(submissionsOfConference);
				saved = this.submissionService.save(submission);
				this.conferenceService.save2(conference);
				res = new ModelAndView("redirect:/submission/author/list.do");

			} catch (final Throwable oops) {
				res = this.createEditModelAndView(submission, "submission.commit.error");
				System.out.println(oops.getStackTrace());
				System.out.println(oops.getCause().getMessage());
			}
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

	// Update ----------------------------------------------------------------------------------

	@RequestMapping(value = "/editPaper", method = RequestMethod.GET)
	public ModelAndView editPaper(@RequestParam final int submissionId) {
		ModelAndView res;
		try {

			final Submission submission = this.submissionService.findOne(submissionId);
			final Paper paper = this.paperService.findOneToEdit(submission.getPaper().getId());
			res = this.createEditModelAndViewPaper(paper);
		} catch (final Throwable oops) {
			oops.printStackTrace();
			if (oops.getMessage().equals("Camera-Ready Deadline Elapsed"))
				res = this.ListModelAndView("submission.editPaperError.cameraReadyDeadlineElapsed");
			else if (oops.getMessage().equals("Corresponding Submission Not Accepted"))
				res = this.ListModelAndView("submission.editPaperError.submissionNotAccepted");
			else if (oops.getMessage().equals("Not one of the authors"))
				res = this.ListModelAndView("submission.editPaperError.notPaperAuthor");
			else if (oops.getMessage().equals("Paper already C�mera-Ready"))
				res = this.ListModelAndView("submission.editPaperError.paperAlreadyCameraReady");
			else if (oops.getMessage().equals("Not the author from submission"))
				res = this.ListModelAndView("submission.editPaperError.NotSubmissionAuthor");
			else if (oops.getMessage().equals("Submission Deadline No Elapsed"))
				res = this.ListModelAndView("submission.editPaperError.submissionDeadlineNotElapsed");
			else
				res = this.ListModelAndView();
		}

		return res;
	}

	@RequestMapping(value = "/editPaper", method = RequestMethod.POST, params = "save")
	public ModelAndView savePaper(@ModelAttribute("paper") @Valid Paper paper, final BindingResult binding) {

		ModelAndView res;
		Paper saved;
		paper = this.paperService.reconstruct(paper, binding);
		if (binding.hasErrors()) {
			System.out.println("Field: " + binding.getFieldError().getField());
			System.out.println(binding.getGlobalErrorCount());
			System.out.println(binding.getFieldErrorCount());
			res = this.createEditModelAndViewPaper(paper);
		} else
			try {
				saved = this.paperService.save(paper);
				res = new ModelAndView("redirect:/submission/author/list.do");

			} catch (final Throwable oops) {
				res = this.createEditModelAndViewPaper(paper, "submission.commit.error");
				System.out.println(oops.getStackTrace());
				System.out.println(oops.getCause().getMessage());
			}
		return res;
	}

	//Delete --------------------------------------------------------
	//
	//	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
	//	public ModelAndView delete(final Submission submission, final BindingResult binding) {
	//
	//		ModelAndView res;
	//
	//		try {
	//			this.submissionService.delete(submission);
	//			res = new ModelAndView("redirect:list.do");
	//
	//		} catch (final Throwable oops) {
	//			res = this.createEditModelAndView(submission, "creditcard.commit.error");
	//		}
	//		return res;
	//	}

	//Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Submission submission) {

		ModelAndView res;

		res = this.createEditModelAndView(submission, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Submission submission, final String messageCode) {

		ModelAndView res;
		final Collection<Author> authors = this.authorService.findAll();
		final Author author = this.authorService.findByPrincipal();
		authors.remove(author);
		if (submission.getPaper().getAuthors() != null) {
			boolean check = false;
			for (final Author a : submission.getPaper().getAuthors())
				if (a == null) {
					check = true;
					break;
				}
			if (check)
				submission.getPaper().setAuthors(null);
		}

		final Collection<Conference> conferences = this.conferenceService.getConferencesSubmissionDeadlineNotElapsed();
		res = new ModelAndView("submission/edit");
		res.addObject("message", messageCode);
		res.addObject("submission", submission);
		res.addObject("authors", authors);
		res.addObject("conferences", conferences);
		return res;
	}

	protected ModelAndView createEditModelAndViewPaper(final Paper paper) {

		ModelAndView res;

		res = this.createEditModelAndViewPaper(paper, null);

		return res;
	}

	protected ModelAndView createEditModelAndViewPaper(final Paper paper, final String messageCode) {

		ModelAndView res;
		final Collection<Author> authors = this.authorService.findAll();
		final Author author = this.authorService.findByPrincipal();
		authors.remove(author);
		res = new ModelAndView("submission/editPaper");
		res.addObject("message", messageCode);
		res.addObject("paper", paper);
		res.addObject("authors", authors);
		return res;
	}

}
