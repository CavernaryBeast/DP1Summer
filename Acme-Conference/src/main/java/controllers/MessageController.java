
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Administrator;
import domain.Message;
import domain.Topic;
import services.ActorService;
import services.AdministratorService;
import services.ConfigurationParametersService;
import services.MessageService;
import services.TopicService;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

	@Autowired
	private MessageService					messageService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;

	@Autowired
	private ActorService					actorService;

	@Autowired
	private TopicService					topicService;

	@Autowired
	private AdministratorService			administratorService;


	//Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView res;
		final Collection<Message> messages = this.messageService.listOwn();

		res = new ModelAndView("message/list");

		res.addObject("messages", messages);
		res.addObject("requestURI", "message/list.do");

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		String lang = LocaleContextHolder.getLocale().getLanguage();
		if (lang == "en")
			lang = "topic.name";
		else if (lang == "es")
			lang = "topic.nameEs";
		res.addObject("lang", lang);

		return res;
	}

	//Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		final ModelAndView res;
		final Message m = this.messageService.create();

		final Actor principal = this.actorService.findByPrincipal();
		m.setSender(principal);

		final String lang = LocaleContextHolder.getLocale().getLanguage();

		res = this.createEditModelAndView(m, false, null);
		res.addObject("lang", lang);

		return res;
	}

	//Broadcast to every actor in the system --------------------------------------------------------

	@RequestMapping(value = "/administrator/broadcast", method = RequestMethod.GET)
	public ModelAndView broadcast() {

		final Administrator principal = this.administratorService.findByPrincipal();

		final ModelAndView res;
		final Message m = this.messageService.create();

		m.setSender(principal);

		res = this.createEditModelAndView(m, true, null);

		final String lang = LocaleContextHolder.getLocale().getLanguage();
		res.addObject("lang", lang);

		return res;
	}

	//Broadcast to all author in the system --------------------------------------------------------

	@RequestMapping(value = "/administrator/broadcastToAuthors", method = RequestMethod.GET)
	public ModelAndView broadcastToAuthors() {

		final Administrator principal = this.administratorService.findByPrincipal();

		final ModelAndView res;
		final Message m = this.messageService.create();

		m.setSender(principal);

		res = this.createEditModelAndViewToAuthors(m, null);

		final String lang = LocaleContextHolder.getLocale().getLanguage();
		res.addObject("lang", lang);

		return res;
	}

	//Message to the authors submitted to a Conference
	@RequestMapping(value = "/administrator/actorsSubmitted", method = RequestMethod.GET)
	public ModelAndView actorsSubmitted(@RequestParam final int conferenceId) {

		final Administrator principal = this.administratorService.findByPrincipal();

		final ModelAndView res;
		final Message m = this.messageService.create();

		m.setSender(principal);

		final Collection<Actor> actorsSubmitted = this.actorService.findSubmittedByConferenceId(conferenceId);

		m.setRecipients(actorsSubmitted);

		res = this.createEditModelAndViewAux(m, "submitted");

		final String lang = LocaleContextHolder.getLocale().getLanguage();
		res.addObject("lang", lang);

		return res;
	}

	//Message to the authors registered to a Conference
	@RequestMapping(value = "/administrator/actorsRegistered", method = RequestMethod.GET)
	public ModelAndView actorsRegistered(@RequestParam final int conferenceId) {

		final Administrator principal = this.administratorService.findByPrincipal();

		final ModelAndView res;
		final Message m = this.messageService.create();

		m.setSender(principal);

		final Collection<Actor> actorsRegistered = this.actorService.findRegisteredByConferenceId(conferenceId);

		m.setRecipients(actorsRegistered);

		res = this.createEditModelAndViewAux(m, "registered");

		final String lang = LocaleContextHolder.getLocale().getLanguage();
		res.addObject("lang", lang);

		return res;
	}

	//Reply --------------------------------------------------------

	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public ModelAndView reply(@RequestParam final int messageId) {

		ModelAndView res;

		//We get the message that we want to reply to, and the sender of the message
		final Message originMessage = this.messageService.findOne(messageId);
		final Actor actorToReply = originMessage.getSender();
		final Collection<Actor> recipients = new ArrayList<Actor>();
		recipients.add(actorToReply);

		final Message m = this.messageService.create();

		final Actor principal = this.actorService.findByPrincipal();
		m.setSender(principal);

		res = this.createEditModelAndView(m, false, recipients);

		final String lang = LocaleContextHolder.getLocale().getLanguage();
		res.addObject("lang", lang);

		return res;
	}

	//Save --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("m") @Valid final Message m, final BindingResult binding, @RequestParam final String type) {

		ModelAndView res = null;

		if (binding.hasErrors()) {
			if (type == "edit")
				res = this.createEditModelAndView(m, false, null);
			else if (type == "broadcast")
				res = this.createEditModelAndView(m, true, null);
			else if (type == "broadcastToAllAuthors")
				res = this.createEditModelAndViewToAuthors(m, null);
			else if (type == "reply")
				res = this.createEditModelAndView(m, false, m.getRecipients());
			else if (type == "submitted" || type == "registered")
				res = this.createEditModelAndViewAux(m, type);
		} else
			try {
				this.messageService.save(m);
				res = new ModelAndView("redirect:list.do");
				final String banner = this.configurationParametersService.getBanner();
				res.addObject("banner", banner);
			} catch (final Throwable oops) {
				if (type == "edit")
					res = this.createEditModelAndView(m, false, null, "message.commit.error");
				else if (type == "broadcast")
					res = this.createEditModelAndView(m, true, null, "message.commit.error");
				else if (type == "broadcastToAllAuthors")
					res = this.createEditModelAndViewToAuthors(m, "message.commit.error");
				else if (type == "reply")
					res = this.createEditModelAndView(m, false, m.getRecipients(), "message.commit.error");
				else if (type == "submitted" || type == "registered")
					res = this.createEditModelAndViewAux(m, type, "message.commit.error");
			}
		final String lang = LocaleContextHolder.getLocale().getLanguage();
		res.addObject("lang", lang);

		return res;
	}
	//Delete --------------------------------------------------------

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int messageId) {

		ModelAndView res;
		final Message toDelete = this.messageService.findOne(messageId);

		try {
			this.messageService.delete(toDelete);
			res = new ModelAndView("redirect:list.do");
			final String banner = this.configurationParametersService.getBanner();
			res.addObject("banner", banner);
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:display.do?messageId=" + messageId);
			final String error = "Cannot delete this message";
			res.addObject("error", error);
		}
		return res;
	}

	//Display --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int messageId) {

		ModelAndView res;
		final Message m = this.messageService.findOne(messageId);
		boolean ownMessage = false;
		final Actor principal = this.actorService.findByPrincipal();

		if (m.getSender() == principal)
			ownMessage = true;

		res = new ModelAndView("message/display");
		res.addObject("m", m);
		res.addObject("ownMessage", ownMessage);

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		final String lang = LocaleContextHolder.getLocale().getLanguage();
		//		if (lang == "en")
		//			lang = "m.topic.name";
		//		else if (lang == "es")
		//			lang = "m.topic.nameEs";
		res.addObject("lang", lang);

		return res;
	}

	//Ancillary methods --------------------------------------------------------

	/**
	 *
	 * @param message
	 *            The message that is going to be created
	 * @param broadcast
	 *            If the message is broadcast, it is set to true
	 * @param sender
	 *            Used if the mesage created is a reply to another message.
	 *            The sender will be the recipient of the new message.
	 *            If null, the message is not a reply
	 * @return
	 */
	protected ModelAndView createEditModelAndView(final Message message, final boolean broadcast, final Collection<Actor> sender) {

		ModelAndView res;

		res = this.createEditModelAndView(message, broadcast, sender, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Message message, final boolean broadcast, final Collection<Actor> sender, final String messageCode) {

		//We check that the Message is a new message, or a Broadcast message or a Reply message
		Assert.isTrue((broadcast == false && sender == null) || (broadcast == true && sender == null) || (broadcast == false && sender != null));

		final ModelAndView res;
		final Collection<Actor> possibleRecipients = this.actorService.findAll();
		final Collection<Topic> topics = this.topicService.findAll();
		String type = "edit";

		res = new ModelAndView("message/edit");

		if (broadcast == true) {
			message.setRecipients(possibleRecipients);
			type = "broadcast";
		} else if (sender != null) {
			message.setRecipients(sender);
			type = "reply";
		}
		res.addObject("m", message);
		res.addObject("possibleRecipients", possibleRecipients);
		res.addObject("topics", topics);
		res.addObject("type", type);
		res.addObject("message", messageCode);
		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}
	//CreateEditModelAndView: Auxiliar method to broadcast to all authors
	protected ModelAndView createEditModelAndViewToAuthors(final Message message) {

		ModelAndView res;

		res = this.createEditModelAndViewToAuthors(message, null);

		return res;
	}

	protected ModelAndView createEditModelAndViewToAuthors(final Message message, final String messageCode) {

		final ModelAndView res;
		final Collection<Actor> allAuthors = this.actorService.findAllAuthors();
		final Collection<Topic> topics = this.topicService.findAll();
		final String type = "broadcastToAllAuthors";

		res = new ModelAndView("message/edit");

		message.setRecipients(allAuthors);

		res.addObject("m", message);
		res.addObject("topics", topics);
		res.addObject("type", type);
		res.addObject("message", messageCode);
		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

	//Aux method to broadcast to Submmited and registered actors
	protected ModelAndView createEditModelAndViewAux(final Message message, final String type) {

		ModelAndView res;

		res = this.createEditModelAndViewAux(message, type, null);

		return res;
	}

	protected ModelAndView createEditModelAndViewAux(final Message message, final String type, final String messageCode) {

		ModelAndView res;
		final Collection<Topic> topics = this.topicService.findAll();

		res = new ModelAndView("message/edit");

		res.addObject("m", message);
		res.addObject("topics", topics);
		res.addObject("message", messageCode);

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		res.addObject("type", type);

		return res;
	}
}
