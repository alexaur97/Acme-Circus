
package controllers.owner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.OfferService;
import services.OwnerService;
import controllers.AbstractController;
import domain.Offer;
import domain.Owner;

@Controller
@RequestMapping("offer/owner")
public class OfferOwnerController extends AbstractController {

	@Autowired
	private OfferService	offerService;

	@Autowired
	private OwnerService	ownerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Owner owner = this.ownerService.findByPrincipal();
			final Collection<Offer> offers = this.offerService.findByCircus(owner.getCircus().getId());
			final Boolean b = !offers.isEmpty();
			result = new ModelAndView("offer/list");
			result.addObject("requestURI", "offer/artist/list.do");
			result.addObject("offers", offers);
			result.addObject("b", b);
			result.addObject("p", "PENDING");
			result.addObject("c", "CONFIRMED");
			result.addObject("w", "WAITINGFORCONFIRMATION");
			result.addObject("r", "REJECTED");

			if (!offers.isEmpty())
				result.addObject("noVacio", true);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

	@RequestMapping(value = "/reject", method = RequestMethod.GET)
	public ModelAndView reject(@RequestParam final int offerId) {

		ModelAndView result;

		try {
			final Offer a = this.offerService.rejectOwner(offerId);
			this.offerService.save(a);
			result = new ModelAndView("redirect:/offer/owner/list.do");

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/#");

		}

		return result;
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public ModelAndView confirm(@RequestParam final int offerId) {

		ModelAndView result;

		try {
			final Offer a = this.offerService.confirmOwner(offerId);
			this.offerService.save(a);
			result = new ModelAndView("redirect:/offer/owner/list.do");

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/#");

		}

		return result;
	}
}
