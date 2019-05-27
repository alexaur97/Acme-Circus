
package controllers.organizer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryTourService;
import services.OrganizerService;
import services.TourService;
import controllers.AbstractController;
import domain.CategoryTour;
import domain.Tour;

@Controller
@RequestMapping("/tour/organizer")
public class TourOrganizerController extends AbstractController {

	@Autowired
	private TourService			tourService;

	@Autowired
	private OrganizerService	organizerService;

	@Autowired
	private CategoryTourService	categoryTourService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			Collection<Tour> tours;
			final int id = this.organizerService.findByPrincipal().getCircus().getId();
			tours = this.tourService.findByCircus(id);
			result = new ModelAndView("tour/listAll");
			result.addObject("requestURI", "tour/list.do");
			result.addObject("tours", tours);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			final Tour tour = this.tourService.create();
			result = this.createEditModelAndView(tour);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tourId) {
		ModelAndView res;
		try {

			final Tour tour = this.tourService.findOne(tourId);
			Assert.notNull(tour);

			res = this.createEditModelAndView(tour);

		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/#");
		}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("tour") Tour tour, final BindingResult binding) {
		ModelAndView res;

		tour = this.tourService.reconstruct(tour, binding);

		if (binding.hasErrors())
			res = this.createEditModelAndView(tour);
		else
			try {

				this.tourService.save(tour);
				res = new ModelAndView("redirect:/tour/organizer/list.do");

			} catch (final Throwable oops) {

				if (tour.getValidated().equals(true))
					res = this.createEditModelAndView(tour, "tour.validated.error");
				res = this.createEditModelAndView(tour, "tour.commit.error");

			}

		return res;
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Tour tour, final BindingResult binding) {
		ModelAndView result;
		final Tour res = this.tourService.findOne(tour.getId());

		try {
			this.tourService.delete(res);
			result = new ModelAndView("redirect:/tour/organizer/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(res, oops.getMessage());
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Tour tour) {
		return this.createEditModelAndView(tour, null);
	}
	protected ModelAndView createEditModelAndView(final Tour tour, final String messageCode) {
		final ModelAndView res;

		final Collection<CategoryTour> categoryTours = this.categoryTourService.findAll();
		res = new ModelAndView("tour/edit");
		res.addObject("tour", tour);
		res.addObject("categoryTours", categoryTours);
		res.addObject("message", messageCode);

		return res;
	}
}
