
package controllers;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ReckonService;
import domain.Reckon;

@Controller
@RequestMapping("/reckon")
public class ReckonController extends AbstractController {

	@Autowired
	ReckonService	reckonService;


	@RequestMapping(value = "/listReckons", method = RequestMethod.GET)
	public ModelAndView listAudit(@RequestParam final int conferenceId) {
		ModelAndView result;
		Collection<Reckon> reckons;
		final int year = Calendar.getInstance().get(Calendar.YEAR);
		final int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		reckons = this.reckonService.getPublicReckons(conferenceId);
		result = new ModelAndView("reckon/listPublic");
		result.addObject("reckons", reckons);
		result.addObject("nowYear", year);
		result.addObject("nowMonth", month);
		result.addObject("nowDay", day);
		result.addObject("requestURI", "reckon/listReckons.do?conferenceId=" + conferenceId);
		return result;
	}

}
