
package controllers;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.FoletService;
import domain.Folet;

@Controller
@RequestMapping("/folet")
public class FoletController extends AbstractController {

	@Autowired
	FoletService	foletService;


	@RequestMapping(value = "/listFolets", method = RequestMethod.GET)
	public ModelAndView listAudit(@RequestParam final int conferenceId) {
		ModelAndView result;
		Collection<Folet> folets;
		final int year = Calendar.getInstance().get(Calendar.YEAR);
		final int month = Calendar.getInstance().get(Calendar.MONTH);
		final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		folets = this.foletService.getPublicFolets(conferenceId);
		result = new ModelAndView("folet/listPublic");
		result.addObject("folets", folets);
		result.addObject("nowYear", year);
		result.addObject("nowMonth", month);
		result.addObject("nowDay", day);
		return result;
	}

}
