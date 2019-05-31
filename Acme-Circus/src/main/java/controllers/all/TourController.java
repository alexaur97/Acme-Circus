
package controllers.all;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryTourService;
import services.TourService;
import controllers.AbstractController;
import domain.Tour;

@Controller
@RequestMapping("/tour")
public class TourController extends AbstractController {

	@Autowired
	TourService			tourService;

	@Autowired
	CategoryTourService	categoryTourService;


	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchForm() {
		ModelAndView result;
		final Tour tour = new Tour();
		try {
			result = new ModelAndView("tour/search");
			result.addObject("tour", tour);
			result.addObject("requestURI", "tour/search.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchList(final Tour tour, final BindingResult binding) {
		ModelAndView result;
		try {
			result = new ModelAndView("tour/search");
			final Collection<Tour> tours = this.tourService.searchTours(tour.getName());
			result.addObject("tours", tours);
			result.addObject("requestURI", "tour/search.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			Collection<Tour> tours;
			tours = this.tourService.findAll();
			result = new ModelAndView("tour/list");
			result.addObject("requestURI", "tour/list.do");
			result.addObject("tours", tours);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView result;
		try {
			Collection<Tour> tours;
			tours = this.tourService.findAll();
			result = new ModelAndView("tour/listAll");
			result.addObject("requestURI", "tour/listAll.do");
			result.addObject("tours", tours);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}

	@RequestMapping(value = "/listPublic", method = RequestMethod.GET)
	public ModelAndView listPublic() {
		ModelAndView result;
		try {
			Collection<Tour> tours;
			tours = this.tourService.findAllValidated();
			result = new ModelAndView("tour/listAll");
			result.addObject("requestURI", "tour/listAll.do");
			result.addObject("tours", tours);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}
	@RequestMapping(value = "/listByCategories", method = RequestMethod.GET)
	public ModelAndView listByCategories(final int categoryTourId) {
		ModelAndView result;
		try {
			Assert.notNull(categoryTourId);
			this.categoryTourService.findOne(categoryTourId);
			Collection<Tour> tours;
			tours = this.tourService.findAllToursByCategoryTour(categoryTourId);
			result = new ModelAndView("tour/listAll");
			result.addObject("requestURI", "tour/listByCategories.do");
			result.addObject("tours", tours);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}

}
