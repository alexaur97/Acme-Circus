
package controllers.owner;

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
}
