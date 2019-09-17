
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
import services.VasteService;
import controllers.AbstractController;
import domain.Administrator;
import domain.Conference;
import domain.Vaste;

@Controller
@RequestMapping("/vaste/administrator")
public class VasteAdminController extends AbstractController {

	@Autowired
	VasteService			vasteService;
	@Autowired
	ActorService			actorService;
	@Autowired
	AdministratorService	administratorService;


	// Create --------------------------------------------------------------------------------

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int conferenceId) {
		ModelAndView result;
		final Vaste vaste;
		//		Audit audit;
		//		audit = this.auditService.findOne2(auditId);
		this.administratorService.findByPrincipal();
		vaste = this.vasteService.create();
		result = this.createEditModelAndView(vaste, conferenceId);
		return result;
	}

	// List ----------------------------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int conferenceId) {
		ModelAndView result;
		Collection<Vaste> vastes;

		this.administratorService.findByPrincipal();
		final int year = Calendar.getInstance().get(Calendar.YEAR);
		final int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		vastes = this.vasteService.getVastesByConferencetId(conferenceId);
		result = new ModelAndView("vaste/list");
		result.addObject("vastes", vastes);
		result.addObject("requestURI", "conference/administrator/list.do");
		result.addObject("conferenceId", conferenceId);
		result.addObject("nowYear", year);
		result.addObject("nowMonth", month);
		result.addObject("nowDay", day);

		return result;
	}

	@RequestMapping(value = "/listVastes", method = RequestMethod.GET)
	public ModelAndView listAudit(@RequestParam final int conferenceId) {
		ModelAndView result;
		final Collection<Vaste> vastes;
		final Administrator administrator = this.administratorService.findByPrincipal();
		final String username = administrator.getUserAccount().getUsername();
		final int year = Calendar.getInstance().get(Calendar.YEAR);
		final int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		vastes = this.vasteService.getVastesByConferencetId(conferenceId);
		result = new ModelAndView("vaste/list");
		result.addObject("vastes", vastes);
		result.addObject("nowYear", year);
		result.addObject("nowMonth", month);
		result.addObject("nowDay", day);
		result.addObject("administrator", administrator);
		result.addObject("conferenceId", conferenceId);
		return result;
	}

	// Show ----------------------------------------------------------------------------------

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int vasteId) {
		ModelAndView result;
		Vaste vaste;
		vaste = this.vasteService.findOne(vasteId);
		result = new ModelAndView("vaste/show");
		result.addObject("vaste", vaste);
		return result;
	}

	// Edit ----------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int vasteId) {
		ModelAndView result;
		Vaste vaste;
		final Vaste found = this.vasteService.findOne(vasteId);
		final Conference conference = found.getConference();
		this.administratorService.findByPrincipal();

		try {
			vaste = this.vasteService.findOneEditable(vasteId);
			result = this.createEditModelAndView(vaste, conference.getId());
			result.addObject("conferenceId", conference.getId());
		} catch (final Throwable oops) {
			if (oops.getMessage().equals("The vaste was already in final mode"))
				result = this.ListSubmissionModelAndView(conference.getId(), "vaste.cantEditFinalMode");
			else
				result = this.ListSubmissionModelAndView(conference.getId());
		}

		return result;
	}

	// Save ----------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Vaste vaste, final BindingResult binding, @RequestParam final int conferenceId) {
		ModelAndView result;
		vaste = this.vasteService.reconstruct(vaste, conferenceId, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(vaste, conferenceId);
		else
			try {

				this.vasteService.save(vaste);
				final String url = "redirect:/vaste/administrator/listVastes.do?conferenceId=" + conferenceId;
				result = new ModelAndView(url);
			} catch (final Throwable oops) {
				if (oops.getMessage().equals("The vaste was already in final mode"))
					result = this.ListSubmissionModelAndView(conferenceId, "You can't edit the vaste because is in final mode ");
				else
					result = this.createEditModelAndView(vaste, conferenceId, "vaste.commit.error");
			}

		return result;
	}

	// Delete --------------------------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int vasteId) {
		ModelAndView result;
		final Vaste vaste = this.vasteService.findOne(vasteId);
		final Conference conference = vaste.getConference();
		this.vasteService.findOneEditable(vasteId);
		try {
			this.vasteService.delete(vasteId);
			final String url = "redirect:/vaste/administrator/listVastes.do?conferenceId=" + conference.getId();
			result = new ModelAndView(url);
			//		result.addObject("conferenceId", conference.getId());
		} catch (final Throwable oops) {
			if (oops.getMessage().equals("The vaste was already in final mode"))
				result = this.ListSubmissionModelAndView(conference.getId(), "vaste.cantDeleteFinalMode");
			else
				result = this.ListSubmissionModelAndView(conference.getId());
		}

		return result;
	}
	// Ancillary methods ---------------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Vaste vaste, final int conferenceId) {
		ModelAndView result;

		result = this.createEditModelAndView(vaste, conferenceId, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Vaste vaste, final int conferenceId, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("vaste/edit");
		result.addObject("vaste", vaste);
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
		result = this.listAudit(conferenceId);
		result.addObject("message", messageCode);
		return result;
	}

}
