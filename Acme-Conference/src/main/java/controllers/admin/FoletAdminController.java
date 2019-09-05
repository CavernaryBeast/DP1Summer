
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
import services.FoletService;
import controllers.AbstractController;
import domain.Actor;
import domain.Folet;

@Controller
@RequestMapping("/folet/company")
public class FoletAdminController extends AbstractController {

	@Autowired
	FoletService			foletService;
	@Autowired
	ActorService			actorService;
	@Autowired
	AdministratorService	administratorService;


	// Create --------------------------------------------------------------------------------

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int conferenceId) {
		ModelAndView result;
		Folet folet;
		//		Audit audit;
		//		audit = this.auditService.findOne2(auditId);
		final Actor actorLogged = this.actorService.findActorLogged();
		this.administratorService.findByPrincipal();
		folet = this.foletService.create();
		result = this.createEditModelAndView(folet, conferenceId);

		return result;
	}

	// List ----------------------------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int conferenceId) {
		ModelAndView result;
		Collection<Folet> folets;

		this.administratorService.findByPrincipal();
		final int year = Calendar.getInstance().get(Calendar.YEAR);
		final int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		folets = this.foletService.findAll();
		result = new ModelAndView("folet/list");
		result.addObject("folets", folets);
		result.addObject("requestURI", "conference/administrator/list.do");
		result.addObject("conferenceId", conferenceId);
		result.addObject("nowYear", year);
		result.addObject("nowMonth", month);
		result.addObject("nowDay", day);

		return result;
	}

	@RequestMapping(value = "/listFolets", method = RequestMethod.GET)
	public ModelAndView listAudit(@RequestParam final int conferenceId) {
		ModelAndView result;
		Collection<Folet> folets;
		this.administratorService.findByPrincipal();
		final int year = Calendar.getInstance().get(Calendar.YEAR);
		final int month = Calendar.getInstance().get(Calendar.MONTH);
		final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		folets = this.foletService.getFoletsByConferencetId(conferenceId);
		result = new ModelAndView("folet/list");
		result.addObject("folets", folets);
		result.addObject("nowYear", year);
		result.addObject("nowMonth", month);
		result.addObject("nowDay", day);
		return result;
	}

	// Show ----------------------------------------------------------------------------------

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int foletId) {
		ModelAndView result;
		Folet folet;
		folet = this.foletService.findOne(foletId);
		result = new ModelAndView("folet/show");
		result.addObject("folet", folet);
		return result;
	}

	// Edit ----------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int foletId, @RequestParam final int conferenceId) {
		ModelAndView result;
		Folet folet;
		this.administratorService.findByPrincipal();
		folet = this.foletService.findOne(foletId);
		result = this.createEditModelAndView(folet, conferenceId);
		result.addObject("conferenceId", conferenceId);
		return result;

	}

	// Save ----------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Folet folet, final BindingResult binding, @RequestParam final int conferenceId) {
		ModelAndView result;
		folet = this.foletService.reconstruct(folet, conferenceId, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(folet, conferenceId);
		else
			try {

				this.foletService.save(folet);
				final String url = "redirect:/folet/company/list.do?conferenceId=" + conferenceId;
				result = new ModelAndView(url);
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(folet, conferenceId, "problem.commit.error");
			}

		return result;
	}

	// Delete --------------------------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int foletId, @RequestParam final int conferenceId) {
		ModelAndView result;
		this.foletService.delete(foletId, conferenceId);
		final String url = "redirect:/folet/administrator/list.do?conferenceId=" + conferenceId;
		result = new ModelAndView(url);

		return result;
	}
	// Ancillary methods ---------------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Folet folet, final int auditId) {
		ModelAndView result;

		result = this.createEditModelAndView(folet, auditId, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Folet folet, final int conferenceId, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("folet/edit");
		result.addObject("folet", folet);
		result.addObject("conferenceId", conferenceId);
		result.addObject("message", messageCode);

		return result;
	}

}
