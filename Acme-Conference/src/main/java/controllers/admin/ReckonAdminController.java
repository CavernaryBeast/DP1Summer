
package controllers.admin;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdministratorService;
import services.ReckonService;
import controllers.AbstractController;
import domain.Conference;
import domain.Reckon;

@Controller
@RequestMapping("/reckon/administrator")
public class ReckonAdminController extends AbstractController {

	@Autowired
	ReckonService			reckonService;
	@Autowired
	ActorService			actorService;
	@Autowired
	AdministratorService	administratorService;


	// Create --------------------------------------------------------------------------------

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int conferenceId) {
		ModelAndView result;
		Reckon reckon;
		//		Audit audit;
		//		audit = this.auditService.findOne2(auditId);
		this.administratorService.findByPrincipal();
		reckon = this.reckonService.create();
		result = this.createEditModelAndView(reckon, conferenceId);
		return result;
	}

	// List ----------------------------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int conferenceId) {
		ModelAndView result;
		Collection<Reckon> reckons;

		this.administratorService.findByPrincipal();
		final int year = Calendar.getInstance().get(Calendar.YEAR);
		final int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		reckons = this.reckonService.getReckonsByConferencetId(conferenceId);
		result = new ModelAndView("reckon/list");
		result.addObject("reckons", reckons);
		result.addObject("requestURI", "conference/administrator/list.do");
		result.addObject("conferenceId", conferenceId);
		result.addObject("nowYear", year);
		result.addObject("nowMonth", month);
		result.addObject("nowDay", day);

		return result;
	}

	@RequestMapping(value = "/listReckons", method = RequestMethod.GET)
	public ModelAndView listAudit(@RequestParam final int conferenceId) {
		ModelAndView result;
		Collection<Reckon> reckons;
		this.administratorService.findByPrincipal();
		final int year = Calendar.getInstance().get(Calendar.YEAR);
		final int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		reckons = this.reckonService.getReckonsByConferencetId(conferenceId);
		result = new ModelAndView("reckon/list");
		result.addObject("reckons", reckons);
		result.addObject("nowYear", year);
		result.addObject("nowMonth", month);
		result.addObject("nowDay", day);
		result.addObject("requestURI", "reckon/administrator/listReckons.do?conferenceId=" + conferenceId);
		return result;
	}

	// Show ----------------------------------------------------------------------------------

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int reckonId) {
		ModelAndView result;
		Reckon reckon;
		reckon = this.reckonService.findOne(reckonId);
		result = new ModelAndView("reckon/show");
		result.addObject("reckon", reckon);
		return result;
	}

	// Edit ----------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int reckonId) {
		ModelAndView result;
		Reckon reckon;
		final Reckon found = this.reckonService.findOne(reckonId);
		final Conference conference = found.getConference();
		this.administratorService.findByPrincipal();

		try {
			reckon = this.reckonService.findOneEditable(reckonId);
			result = this.createEditModelAndView(reckon, conference.getId());
			result.addObject("conferenceId", conference.getId());
		} catch (final Throwable oops) {
			if (oops.getMessage().equals("The reckon was already in final mode"))
				result = this.ListSubmissionModelAndView(conference.getId(), "reckon.cantEditFinalMode");
			else
				result = this.ListSubmissionModelAndView(conference.getId());
		}

		return result;
	}

	// Save ----------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Reckon reckon, final BindingResult binding, @RequestParam final int conferenceId) {
		ModelAndView result;
		reckon = this.reckonService.reconstruct(reckon, conferenceId, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(reckon, conferenceId);
		else
			try {

				this.reckonService.save(reckon);
				final String url = "redirect:/reckon/administrator/list.do?conferenceId=" + conferenceId;
				result = new ModelAndView(url);
			} catch (final Throwable oops) {
				if (oops.getMessage().equals("The reckon was already in final mode"))
					result = this.ListSubmissionModelAndView(conferenceId, "You can't edit the reckon because is in final mode ");
				else
					result = this.createEditModelAndView(reckon, conferenceId, "problem.commit.error");
			}

		return result;
	}

	// Delete --------------------------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int reckonId) {
		ModelAndView result;
		final Reckon reckon = this.reckonService.findOne(reckonId);
		final Conference conference = reckon.getConference();
		this.reckonService.findOneEditable(reckonId);
		try {
			this.reckonService.delete(reckonId);
			final String url = "redirect:/reckon/administrator/list.do?conferenceId=" + conference.getId();
			result = new ModelAndView(url);
			//		result.addObject("conferenceId", conference.getId());
		} catch (final Throwable oops) {
			if (oops.getMessage().equals("The reckon was already in final mode"))
				result = this.ListSubmissionModelAndView(conference.getId(), "reckon.cantDeleteFinalMode");
			else
				result = this.ListSubmissionModelAndView(conference.getId());
		}

		return result;
	}
	// Ancillary methods ---------------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Reckon reckon, final int conferenceId) {
		ModelAndView result;

		result = this.createEditModelAndView(reckon, conferenceId, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Reckon reckon, final int conferenceId, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("reckon/edit");
		result.addObject("reckon", reckon);
		result.addObject("conferenceId", conferenceId);
		result.addObject("message", messageCode);

		return result;
	}

	protected ModelAndView ListSubmissionModelAndView(final int conferenceId) {
		ModelAndView result;

		result = this.ListSubmissionModelAndView(conferenceId, null);

		return result;
	}

	protected ModelAndView ListSubmissionModelAndView(final int conferenceId, final String messageCode) {
		ModelAndView result;
		result = this.list(conferenceId);
		result.addObject("message", messageCode);
		return result;
	}

}
