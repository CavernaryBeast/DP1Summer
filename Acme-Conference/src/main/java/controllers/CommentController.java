
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.ActorService;
import services.CommentService;
import services.ConferenceService;
import services.ConfigurationParametersService;
import domain.Activity;
import domain.Actor;
import domain.Comment;
import domain.Conference;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	@Autowired
	private CommentService					commentService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;

	@Autowired
	private ConferenceService				conferenceService;

	@Autowired
	private ActivityService					activityService;

	@Autowired
	private ActorService					actorService;


	//Listing comments of a Conference--------------------------------------------------------

	@RequestMapping(value = "/listConferenceComments", method = RequestMethod.GET)
	public ModelAndView listForConference(@RequestParam final int conferenceId) {

		final ModelAndView res;

		final Conference conference = this.conferenceService.findOne(conferenceId);

		final Collection<Comment> comments = conference.getComments();

		res = new ModelAndView("comment/list");

		res.addObject("comments", comments);

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		final String object = "conference";
		res.addObject("object", object);
		res.addObject("conferenceId", conferenceId);

		return res;
	}

	//Listing comments of an Activity--------------------------------------------------------

	@RequestMapping(value = "/listActivityComments", method = RequestMethod.GET)
	public ModelAndView listForActivity(@RequestParam final int activityId) {

		final ModelAndView res;

		final Activity activity = this.activityService.findOne(activityId);

		final Collection<Comment> comments = activity.getComments();

		res = new ModelAndView("comment/list");

		res.addObject("comments", comments);

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		final String object = "activity";
		res.addObject("object", object);
		res.addObject("activityId", activityId);

		return res;
	}

	//Creation to Conference--------------------------------------------------------

	@RequestMapping(value = "/createToConference", method = RequestMethod.GET)
	public ModelAndView createToConference(@RequestParam final int conferenceId) {

		ModelAndView res;
		final Comment comment = this.commentService.create();

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			final Actor author = this.actorService.findByPrincipal();
			comment.setAuthor(author);
		}

		this.conferenceService.findOne(conferenceId);

		res = this.createEditModelAndView(comment);
		res.addObject("actionURI", "comment/editToConference.do?conferenceId=" + conferenceId);

		return res;
	}

	//Creation to Activity--------------------------------------------------------

	@RequestMapping(value = "/createToActivity", method = RequestMethod.GET)
	public ModelAndView createToActivity(@RequestParam final int activityId) {

		ModelAndView res;
		final Comment comment = this.commentService.create();

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			final Actor author = this.actorService.findByPrincipal();
			comment.setAuthor(author);
		}

		this.activityService.findOne(activityId);

		res = this.createEditModelAndView(comment);
		res.addObject("actionURI", "comment/editToActivity.do?activityId=" + activityId);

		return res;
	}

	//Save to Conference --------------------------------------------------------

	@RequestMapping(value = "editToConference", method = RequestMethod.POST, params = "save")
	public ModelAndView saveToConference(@ModelAttribute("comment") @Valid final Comment comment, final BindingResult binding, @RequestParam final int conferenceId) {

		ModelAndView res;

		if (binding.hasErrors()) {
			res = this.createEditModelAndView(comment);
			res.addObject("actionURI", "comment/editToConference.do?conferenceId=" + conferenceId);
		} else
			try {
				this.commentService.saveToConference(comment, conferenceId);

				res = new ModelAndView("redirect:listConferenceComments.do?conferenceId=" + conferenceId);
				final String banner = this.configurationParametersService.getBanner();
				res.addObject("banner", banner);
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(comment, "report.commit.error");
				res.addObject("actionURI", "comment/editToConference.do?conferenceId=" + conferenceId);
			}
		return res;
	}

	//Save to Conference --------------------------------------------------------

	@RequestMapping(value = "editToActivity", method = RequestMethod.POST, params = "save")
	public ModelAndView saveToActivity(@ModelAttribute("comment") @Valid final Comment comment, final BindingResult binding, @RequestParam final int activityId) {

		ModelAndView res;

		if (binding.hasErrors()) {
			res = this.createEditModelAndView(comment);
			res.addObject("actionURI", "comment/editToActivity.do?activityId=" + activityId);
		} else
			try {
				this.commentService.saveToActivity(comment, activityId);

				res = new ModelAndView("redirect:listActivityComments.do?activityId=" + activityId);
				final String banner = this.configurationParametersService.getBanner();
				res.addObject("banner", banner);
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(comment, "report.commit.error");
				res.addObject("actionURI", "comment/editToActivity.do?activityId=" + activityId);
			}
		return res;
	}

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
