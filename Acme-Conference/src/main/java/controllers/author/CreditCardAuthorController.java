
package controllers.author;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Author;
import domain.CreditCard;
import services.AuthorService;
import services.ConfigurationParametersService;
import services.CreditCardService;

@Controller
@RequestMapping("/creditcard/author")
public class CreditCardAuthorController extends AbstractController {

	@Autowired
	private CreditCardService				creditCardService;

	@Autowired
	private AuthorService					authorService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	//Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		final ModelAndView res;

		final Collection<CreditCard> creditCards = this.creditCardService.findOwn();

		res = new ModelAndView("creditcard/list");

		res.addObject("creditcards", creditCards);
		res.addObject("requestURI", "creditcard/author/list.do");

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

	//Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView res;
		final CreditCard creditCard = this.creditCardService.create();

		res = this.createEditModelAndView(creditCard);

		//		final String lang = LocaleContextHolder.getLocale().getLanguage();
		//		res.addObject("lang", lang);

		return res;
	}

	//Edition --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int creditcardId) {

		final ModelAndView res;
		final CreditCard creditCard = this.creditCardService.findOne(creditcardId);
		final Author principal = this.authorService.findByPrincipal();

		Assert.isTrue(creditCard.getOwner().equals(principal));

		res = this.createEditModelAndView(creditCard);

		return res;
	}

	//Save --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("creditcard") @Valid final CreditCard creditCard, final BindingResult binding) {

		ModelAndView res;
		CreditCard saved;

		if (binding.hasErrors())
			res = this.createEditModelAndView(creditCard);
		else
			try {
				saved = this.creditCardService.save(creditCard);

				res = new ModelAndView("redirect:display.do?creditcardId=" + saved.getId());
				final String banner = this.configurationParametersService.getBanner();
				res.addObject("banner", banner);
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(creditCard, "creditcard.commit.error");
			}
		return res;
	}

	//Delete --------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int creditcardId) {

		ModelAndView res;
		final CreditCard toDelete = this.creditCardService.findOne(creditcardId);

		try {
			this.creditCardService.delete(toDelete);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:list.do");
			final String error = "Cannot delete this message";
			res.addObject("error", error);
		}
		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

	//Display --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int creditcardId) {

		ModelAndView res;
		final CreditCard creditCard = this.creditCardService.findOne(creditcardId);

		res = new ModelAndView("creditcard/display");
		res.addObject("creditcard", creditCard);

		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

	//Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final CreditCard creditCard) {

		ModelAndView res;

		res = this.createEditModelAndView(creditCard, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final CreditCard creditCard, final String messageCode) {

		ModelAndView res;

		final Collection<String> makes = this.configurationParametersService.getCreditCardMakes();

		res = new ModelAndView("creditcard/edit");

		res.addObject("creditcard", creditCard);

		res.addObject("makes", makes);
		res.addObject("message", messageCode);
		final String banner = this.configurationParametersService.getBanner();
		res.addObject("banner", banner);

		return res;
	}

}
