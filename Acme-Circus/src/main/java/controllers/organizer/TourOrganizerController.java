
package controllers.organizer;

import java.util.Collection;
import java.util.Date;

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
import domain.Organizer;
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
			result.addObject("requestURI", "tour/listAll.do");
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

			final Organizer o = this.organizerService.findByPrincipal();
			final Boolean b = tour.getOrganizers().equals(o);
			Assert.isTrue(b);

			Assert.isTrue(!tour.validated);

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

				final Organizer o = this.organizerService.findByPrincipal();
				final Boolean b = tour.getOrganizers().equals(o);
				Assert.isTrue(b);

				Assert.isTrue(!tour.validated);

				Assert.isTrue(tour.getStartDate().before(tour.getEndDate()));

				final Date actual = new Date();
				Assert.isTrue(tour.getStartDate().after(actual));

				this.tourService.save(tour);
				res = new ModelAndView("redirect:/tour/organizer/list.do");

			} catch (final Throwable oops) {

				final Date actual = new Date();

				if (tour.getValidated().equals(true))
					res = this.createEditModelAndView(tour, "tour.validated.error");
				else if (!tour.getStartDate().before(tour.getEndDate()))
					res = this.createEditModelAndView(tour, "tour.date.error");
				else if (!tour.getStartDate().after(actual))
					res = this.createEditModelAndView(tour, "tour.actual.error");
				else
					res = this.createEditModelAndView(tour, "tour.commit.error");
			}
		return res;
	}
	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Tour tour, final BindingResult binding) {
		ModelAndView result;
		final Tour res = this.tourService.findOne(tour.getId());

		try {

			final Organizer o = this.organizerService.findByPrincipal();
			final Boolean b = tour.getOrganizers().equals(o);
			Assert.isTrue(b);
			Assert.isTrue(!tour.validated);

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
