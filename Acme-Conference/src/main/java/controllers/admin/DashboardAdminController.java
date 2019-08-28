
package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.ConferenceService;
import services.RegistrationService;
import services.SubmissionService;
import controllers.AbstractController;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdminController extends AbstractController {

	@Autowired
	private ConferenceService		conferenceService;

	@Autowired
	private SubmissionService		submissionService;

	@Autowired
	private RegistrationService		registrationService;

	@Autowired
	private AdministratorService	administratorService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		final ModelAndView result;
		// -------------------------------------------------------------------------------

		final String[] submissionsPerConferenceStats = this.submissionService.getSubmissionsPerConferenceStats().split(",");
		final String submissionsPerConferenceStatsMin = submissionsPerConferenceStats[0];
		final String submissionsPerConferenceStatsMax = submissionsPerConferenceStats[1];
		final String submissionsPerConferenceStatsAvg = submissionsPerConferenceStats[2];
		final String submissionsPerConferenceStatsStd = submissionsPerConferenceStats[3];

		// -------------------------------------------------------------------------------

		final String[] registrationsPerConferenceStats = this.registrationService.getRegistrationsPerConferenceStats().split(",");
		final String registrationsPerConferenceStatsMin = registrationsPerConferenceStats[0];
		final String registrationsPerConferenceStatsMax = registrationsPerConferenceStats[1];
		final String registrationsPerConferenceStatsAvg = registrationsPerConferenceStats[2];
		final String registrationsPerConferenceStatsStd = registrationsPerConferenceStats[3];

		// -------------------------------------------------------------------------------

		final String[] feesPerConferenceStats = this.conferenceService.getFeesPerConferenceStats().split(",");
		final String feesPerConferenceStatsMin = feesPerConferenceStats[0];
		final String feesPerConferenceStatseStatsMax = feesPerConferenceStats[1];
		final String feesPerConferenceStatsAvg = feesPerConferenceStats[2];
		final String feesPerConferenceStatsStd = feesPerConferenceStats[3];

		// --------------------------------------------------------------------------------------------

		final String[] daysPerConferenceStats = this.conferenceService.getDaysPerConferenceStats().split(",");
		final String daysPerConferenceStatsMin = daysPerConferenceStats[0];
		final String daysPerConferenceStatsMax = daysPerConferenceStats[1];
		final String daysPerConferenceStatsAvg = daysPerConferenceStats[2];
		final String daysPerConferenceStatsStd = daysPerConferenceStats[3];

		// -------------------------------------------------------------------------------------

		result = new ModelAndView("dashboard/show");

		result.addObject("submissionsPerConferenceStatsMin", submissionsPerConferenceStatsMin);
		result.addObject("submissionsPerConferenceStatsMax", submissionsPerConferenceStatsMax);
		result.addObject("submissionsPerConferenceStatsAvg", submissionsPerConferenceStatsAvg);
		result.addObject("submissionsPerConferenceStatsStd", submissionsPerConferenceStatsStd);

		result.addObject("registrationsPerConferenceStatsMin", registrationsPerConferenceStatsMin);
		result.addObject("registrationsPerConferenceStatsMax", registrationsPerConferenceStatsMax);
		result.addObject("registrationsPerConferenceStatsAvg", registrationsPerConferenceStatsAvg);
		result.addObject("registrationsPerConferenceStatsStd", registrationsPerConferenceStatsStd);

		result.addObject("feesPerConferenceStatsMin", feesPerConferenceStatsMin);
		result.addObject("feesPerConferenceStatseStatsMax", feesPerConferenceStatseStatsMax);
		result.addObject("feesPerConferenceStatsAvg", feesPerConferenceStatsAvg);
		result.addObject("feesPerConferenceStatsStd", feesPerConferenceStatsStd);

		result.addObject("daysPerConferenceStatsMin", daysPerConferenceStatsMin);
		result.addObject("daysPerConferenceStatsMax", daysPerConferenceStatsMax);
		result.addObject("daysPerConferenceStatsAvg", daysPerConferenceStatsAvg);
		result.addObject("daysPerConferenceStatsStd", daysPerConferenceStatsStd);

		return result;

	}

}
