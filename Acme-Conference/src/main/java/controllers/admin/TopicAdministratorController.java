
package controllers.admin;

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
import domain.Topic;
import services.AdministratorService;
import services.ConfigurationParametersService;
import services.TopicService;

@Controller
@RequestMapping("/topic/administrator")

public class TopicAdministratorController extends AbstractController {

	@Autowired
	private TopicService					topicService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;

	@Autowired
	private AdministratorService			administratorService;


	//Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView res;
		final Collection<Topic> topics = this.topicService.findAll();

		res = new ModelAndView("topic/list");

		res.addObject("topics", topics);
		res.addObject("requestURI", "topic/list.do");

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

	//Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		final ModelAndView res;
		final Topic topic = this.topicService.create();

		this.administratorService.findByPrincipal();

		res = this.createEditModelAndView(topic);

		return res;
	}

	// Edit
	// ----------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int topicId) {

		final ModelAndView res;

		final Topic topic = this.topicService.findOne(topicId);

		res = this.createEditModelAndView(topic);

		return res;

	}

	//Save --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("topic") @Valid final Topic topic, final BindingResult binding) {

		ModelAndView res;
		Topic saved;

		if (binding.hasErrors())
			res = this.createEditModelAndView(topic);
		else
			try {
				saved = this.topicService.save(topic);

				res = new ModelAndView("redirect:list.do");
				final String banner = this.configurationParametersService.getBanner();
				res.addObject("banner", banner);
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(topic, "topic.commit.error");
			}
		return res;
	}

	//Delete --------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int topicId) {

		final ModelAndView res;

		final Topic topic = this.topicService.findOne(topicId);
		this.topicService.delete(topic);

		res = new ModelAndView("redirect:list.do");
		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

	//Display --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int topicId) {

		ModelAndView res;
		final Topic topic = this.topicService.findOne(topicId);

		res = new ModelAndView("topic/display");
		res.addObject("topic", topic);

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

	//Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Topic topic) {

		ModelAndView res;

		res = this.createEditModelAndView(topic);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Topic topic, final String messageCode) {

		ModelAndView res;

		res = new ModelAndView("topic/edit");

		res.addObject("topic", topic);
		res.addObject("message", messageCode);
		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

}
