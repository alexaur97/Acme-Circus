
package controllers.organizer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.OfferService;
import services.OrganizerService;
import services.PerformanceService;
import services.TourService;
import controllers.AbstractController;
import domain.Offer;
import domain.Organizer;
import domain.Performance;
import domain.Tour;
import forms.OfferForm;

@Controller
@RequestMapping("offer/organizer")
public class OfferOrganizerController extends AbstractController {

	@Autowired
	private OfferService		offerService;
	@Autowired
	private PerformanceService	performanceService;
	@Autowired
	private OrganizerService	organizerService;
	@Autowired
	private TourService			tourService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Organizer organizer = this.organizerService.findByPrincipal();
			final Collection<Offer> offers = this.offerService.findByOrg(organizer.getId());
			final Boolean b = !offers.isEmpty();
			result = new ModelAndView("offer/list");
			result.addObject("requestURI", "offer/organizer/list.do");
			result.addObject("offers", offers);
			result.addObject("b", b);
			result.addObject("p", "PENDING");
			result.addObject("c", "CONFIRMED");
			result.addObject("w", "WAITINGFORCONFIRMATION");
			result.addObject("r", "REJECTED");

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		try {
			final Organizer o = this.organizerService.findByPrincipal();
			final OfferForm offer;
			offer = new OfferForm();
			final Collection<Tour> tours = this.tourService.findAllAvailableByOrg(o.getId());
			final Collection<Performance> performances = this.performanceService.findAllNotCopy();
			result = new ModelAndView("offer/edit");
			result.addObject("offerForm", offer);
			result.addObject("performances", performances);
			result.addObject("tours", tours);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");

		}

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView save(@Valid final OfferForm offer, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			final Organizer o = this.organizerService.findByPrincipal();
			final Collection<Tour> tours = this.tourService.findAllAvailableOrg(o.getId());
			final Collection<Performance> performances = this.performanceService.findAllNotCopy();
			result = new ModelAndView("offer/edit");
			result.addObject("performances", performances);
			result.addObject("tours", tours);
			result.addObject("offer", offer);
		} else
			try {
				final Offer offerF = this.offerService.reconstruct(offer, binding);
				final Offer offerFinal = this.offerService.save(offerF);

				final Tour tour = offer.getTour();
				tour.getOffers().add(offerFinal);

				this.tourService.save(tour);
				result = new ModelAndView("redirect:/offer/organizer/list.do");

			} catch (final Throwable oops) {
				result = new ModelAndView("redirect:/#");

			}
		return result;
	}
}
