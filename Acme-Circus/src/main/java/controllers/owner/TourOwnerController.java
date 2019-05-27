
package controllers.owner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.OwnerService;
import services.TourService;
import controllers.AbstractController;
import domain.Circus;
import domain.Tour;

@Controller
@RequestMapping("/tour/owner")
public class TourOwnerController extends AbstractController {

	@Autowired
	private TourService		tourService;

	@Autowired
	private OwnerService	ownerService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int tourId) {
		ModelAndView result;
		try {
			final Tour tour = this.tourService.findOne(tourId);

			final Circus circus = this.ownerService.findByPrincipal().getCircus();
			Assert.isTrue(tour.getOrganizers().getCircus().equals(circus));

			result = new ModelAndView("tour/show");
			result.addObject("requestURI", "tour/show.do");
			result.addObject("tour", tour);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			Collection<Tour> tours;
			final int id = this.ownerService.findByPrincipal().getCircus().getId();
			tours = this.tourService.findByCircus(id);
			result = new ModelAndView("tour/listAll");
			result.addObject("requestURI", "tour/list.do");
			result.addObject("tours", tours);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}

	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public ModelAndView validate(@RequestParam final int tourId) {
		ModelAndView result;
		try {
			this.ownerService.findByPrincipal();
			Tour tour = this.tourService.findOne(tourId);
			tour = this.tourService.validate(tour);
			tour = this.tourService.save(tour);
			result = new ModelAndView("redirect:/tour/owner/list.do");

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}
}
