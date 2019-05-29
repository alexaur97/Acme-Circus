
package controllers.artist;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArtistService;
import services.OfferService;
import services.OrganizerService;
import services.PerformanceService;
import services.TourService;
import controllers.AbstractController;
import domain.Artist;
import domain.Offer;

@Controller
@RequestMapping("offer/artist")
public class OfferArtistController extends AbstractController {

	@Autowired
	private ArtistService		artistService;
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
			final Artist artist = this.artistService.findByPrincipal();
			final Collection<Offer> offers = this.offerService.findByArt(artist.getId());
			result = new ModelAndView("offer/list");
			result.addObject("requestURI", "offer/artist/list.do");
			result.addObject("offers", offers);
			result.addObject("s", "PENDING");
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
			final Offer a = this.offerService.rejectResticc(offerId);
			this.offerService.save(a);
			result = new ModelAndView("redirect:/offer/artist/list.do");

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");

		}

		return result;
	}
	//	@RequestMapping(value = "/create", method = RequestMethod.POST)
	//	public ModelAndView save(@Valid final OfferForm offer, final BindingResult binding) {
	//
	//		ModelAndView result;
	//
	//		if (binding.hasErrors()) {
	//			final Collection<Tour> tours = this.tourService.findAllAvailable();
	//			final Collection<Performance> performances = this.performanceService.findAllNotCopy();
	//			result = new ModelAndView("offer/edit");
	//			result.addObject("performances", performances);
	//			result.addObject("tours", tours);
	//			result.addObject("offer", offer);
	//		} else {
	//			//			try {
	//			final Offer offerF = this.offerService.reconstruct(offer, binding);
	//			final Offer offerFinal = this.offerService.save(offerF);
	//
	//			final Tour tour = offer.getTour();
	//			tour.getOffers().add(offerFinal);
	//
	//			this.tourService.save(tour);
	//			result = new ModelAndView("redirect:/offer/organizer/list.do");
	//
	//			//			} catch (final Throwable oops) {
	//			//				result = new ModelAndView("redirect:/#");
	//			//
	//			//			}
	//		}
	//		return result;
	//	}
}
