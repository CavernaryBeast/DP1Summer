
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
import services.SubmissionService;
import controllers.AbstractController;
import domain.Author;
import domain.Conference;
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


	//Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		final ModelAndView res;

		final Collection<Submission> submissions = this.submissionService.findOwn();
		final Collection<Conference> conferences = this.conferenceService.getConferencesSubmissionDeadlineNotElapsed();
		res = new ModelAndView("submission/list");

		res.addObject("submissions", submissions);
		res.addObject("conferences", conferences);
		res.addObject("requestURI", "submission/author/list.do");

		return res;
	}

	//Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView res;
		final Submission submission = this.submissionService.create();

		res = this.createEditModelAndView(submission);

		final String lang = LocaleContextHolder.getLocale().getLanguage();
		res.addObject("lang", lang);

		return res;
	}

	//Edition --------------------------------------------------------

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int submissionId) {

		final ModelAndView res;
		final Submission submission = this.submissionService.findOne(submissionId);
		final Author principal = this.authorService.findByPrincipal();

		//	Assert.isTrue(creditCard.getOwner().equals(principal));

		res = this.createEditModelAndView(submission);

		return res;
	}

	//Save --------------------------------------------------------

	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("submission") @Valid Submission submission, final BindingResult binding, final Integer conferenceId) {

		ModelAndView res;
		Submission saved;
		submission = this.submissionService.reconstruct(submission, binding);
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
				this.conferenceService.save(conference);
				res = new ModelAndView("redirect:/submission/author/list.do");

			} catch (final Throwable oops) {
				res = this.createEditModelAndView(submission, "submission.commit.error");
				System.out.println(oops.getStackTrace());
				System.out.println(oops.getCause().getMessage());
			}
		return res;
	}

	//Delete --------------------------------------------------------

	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Submission submission, final BindingResult binding) {

		ModelAndView res;

		try {
			this.submissionService.delete(submission);
			res = new ModelAndView("redirect:list.do");

		} catch (final Throwable oops) {
			res = this.createEditModelAndView(submission, "creditcard.commit.error");
		}
		return res;
	}

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
		final Collection<Conference> conferences = this.conferenceService.getConferencesSubmissionDeadlineNotElapsed();
		res = new ModelAndView("submission/edit");
		res.addObject("message", messageCode);
		res.addObject("submission", submission);
		res.addObject("authors", authors);
		res.addObject("conferences", conferences);
		return res;
	}

}
