
package controllers.owner;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryPriceService;
import services.OwnerService;
import services.StopService;
import services.TourService;
import controllers.AbstractController;
import domain.CategoryPrice;
import domain.Circus;
import domain.Stop;
import domain.Tour;

@Controller
@RequestMapping("/tour/owner")
public class TourOwnerController extends AbstractController {

	@Autowired
	private TourService				tourService;

	@Autowired
	private OwnerService			ownerService;

	@Autowired
	private StopService				stopService;

	@Autowired
	private CategoryPriceService	categoryPriceService;


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
			tours = this.tourService.findAllByCircus(id);
			result = new ModelAndView("tour/listAll");
			result.addObject("requestURI", "tour/listAll.do");
			result.addObject("tours", tours);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}

	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public ModelAndView validate(@RequestParam final int tourId) {
		ModelAndView result;
		Boolean error = false;
		try {
			Collection<Tour> tours;
			final int id = this.ownerService.findByPrincipal().getCircus().getId();
			tours = this.tourService.findAllByCircus(id);
			this.ownerService.findByPrincipal();
			Tour tour = this.tourService.findOne(tourId);
			Collection<Stop> stopsFromTour = new ArrayList<>();
			//Aquí tengo todas las paradas que tiene el tour que quiero validar
			stopsFromTour = this.stopService.findStopsByTour(tour.getId());
			//ahora tengo que comprobar que cada una de esas paradas tenga al menos 
			//un category price, ya que si no en la compra no te da a elegir
			// que entrada quieres pagar
			for (final Stop s : stopsFromTour) {
				final Collection<CategoryPrice> cp = this.categoryPriceService.findByStop(s.getId());
				Assert.notEmpty(cp);
			}
			tour = this.tourService.validate(tour);
			tour = this.tourService.save(tour);
			result = new ModelAndView("tour/listAll");
			result.addObject("requestURI", "tour/listAll.do");
			result.addObject("tours", tours);
			result.addObject("error", error);
		} catch (final Throwable oops) {
			Collection<Tour> tours;
			error = true;
			final int id = this.ownerService.findByPrincipal().getCircus().getId();
			tours = this.tourService.findAllByCircus(id);
			this.ownerService.findByPrincipal();
			result = new ModelAndView("tour/listAll");
			result.addObject("requestURI", "tour/listAll.do");
			result.addObject("tours", tours);
			result.addObject("error", error);
		}
		return result;
	}
}
