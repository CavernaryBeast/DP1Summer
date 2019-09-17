
package controllers;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.VasteService;
import domain.Vaste;

@Controller
@RequestMapping("/vaste")
public class VasteController extends AbstractController {

	@Autowired
	VasteService	vasteService;


	@RequestMapping(value = "/listVastes", method = RequestMethod.GET)
	public ModelAndView listAudit(@RequestParam final int conferenceId) {
		ModelAndView result;
		Collection<Vaste> vastes;
		final int year = Calendar.getInstance().get(Calendar.YEAR);
		final int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		vastes = this.vasteService.getPublicVastes(conferenceId);
		result = new ModelAndView("vaste/listPublic");
		result.addObject("vastes", vastes);
		result.addObject("nowYear", year);
		result.addObject("nowMonth", month);
		result.addObject("nowDay", day);
		return result;
	}

}
