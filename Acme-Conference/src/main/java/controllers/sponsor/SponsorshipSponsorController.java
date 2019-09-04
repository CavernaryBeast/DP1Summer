
package controllers.sponsor;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;
import services.ConfigurationParametersService;
import services.SponsorService;
import services.SponsorshipService;
import controllers.AbstractController;
import domain.Conference;
import domain.Sponsor;
import domain.Sponsorship;

@Controller
@RequestMapping("/sponsorship/sponsor")
public class SponsorshipSponsorController extends AbstractController {

	@Autowired
	private SponsorshipService				sponsorshipService;

	@Autowired
	private SponsorService					sponsorService;

	@Autowired
	private ConferenceService				conferenceService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final Sponsor sponsor = this.sponsorService.findByPrincipal();
		final Collection<Sponsorship> sponsorships = this.sponsorshipService.findSponsorshipsFromSponsorId(sponsor.getId());
		final Date now = new Date(System.currentTimeMillis() - 1);
		res = new ModelAndView("sponsorship/list");
		res.addObject("sponsorships", sponsorships);
		res.addObject("now", now);
		return res;
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

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createPresentation() {

		ModelAndView res;

		try {
			final Sponsorship sponsorship = this.sponsorshipService.create();
			res = this.createEditModelAndView(sponsorship);
		} catch (final Throwable oops) {
			oops.printStackTrace();
			if (oops.getMessage().equals("StartDate elapsed"))
				res = this.ListModelAndView("activity.cantAddActivitiesSorry");
			else if (oops.getMessage().equals("Conference doesn't have available papers"))
				res = this.ListModelAndView("activity.error.noPapersAvailable");
			else
				res = this.ListModelAndView();
		}

		return res;
	}

	//Edition --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int sponsorshipId) {

		ModelAndView res;

		try {
			final Sponsorship sponsorship = this.sponsorshipService.findOne(sponsorshipId);
			res = this.createEditModelAndView(sponsorship);
		} catch (final Throwable oops) {
			oops.printStackTrace();
			if (oops.getMessage().equals("StartDate elapsed"))
				res = this.ListModelAndView("activity.cantEditActivitiesSorry");
			else
				res = this.ListModelAndView();
		}

		return res;
	}

	//Save --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Sponsorship sponsorship, final BindingResult binding) {

		ModelAndView res;
		Sponsorship saved;
		sponsorship = this.sponsorshipService.reconstruct(sponsorship, binding);
		if (binding.hasErrors())
			res = this.createEditModelAndView(sponsorship);
		else
			try {

				saved = this.sponsorshipService.save(sponsorship);
				res = new ModelAndView("redirect:/sponsorship/sponsor/list.do");

			} catch (final Throwable oops) {
				if (oops.getMessage().equals("StartMoment between the startDate and the endDate of the conference"))
					res = this.createEditModelAndView(sponsorship, "activity.betweenStartAndEndDate");
				else if (oops.getMessage().equals("The duration shorter than the conference total one"))
					res = this.createEditModelAndView(sponsorship, "activity.durationShorterThanConference");
				else
					res = this.createEditModelAndView(sponsorship, "activity.commit.error");

			}
		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int sponsorshipId) {
		ModelAndView result;
		Sponsorship sponsorship;
		sponsorship = this.sponsorshipService.findOne(sponsorshipId);
		result = new ModelAndView("sponsorship/show");
		result.addObject("sponsorship", sponsorship);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Sponsorship sponsorship) {
		ModelAndView res;
		res = this.createEditModelAndView(sponsorship, null);
		return res;
	}

	protected ModelAndView createEditModelAndView(final Sponsorship sponsorship, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("sponsorship/edit");
		final Collection<Conference> conferences = this.conferenceService.findAll();
		final Collection<String> makes = this.configurationParametersService.getCreditCardMakes();
		res.addObject("sponsorship", sponsorship);
		res.addObject("message", messageCode);
		res.addObject("conferences", conferences);
		res.addObject("makes", makes);
		return res;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int sponsorshipId) {

		ModelAndView res;

		try {
			this.sponsorshipService.delete(sponsorshipId);
			res = new ModelAndView("redirect:/sponsorship/sponsor/list.do");
		} catch (final Throwable oops) {
			oops.printStackTrace();
			if (oops.getMessage().equals("StartDate elapsed"))
				res = this.ListModelAndView("activity.cantDeleteActivitiesSorry");
			else if (oops.getMessage().equals("Activity must be from the conference"))
				res = this.ListModelAndView("activity.canOnlyDeleteActivittiesFromConference");
			else
				res = this.ListModelAndView();
		}

		return res;
	}

}
