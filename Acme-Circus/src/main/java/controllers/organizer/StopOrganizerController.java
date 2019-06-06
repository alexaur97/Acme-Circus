
package controllers.organizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.OrganizerService;
import services.StopService;
import services.TourService;
import controllers.AbstractController;
import domain.Organizer;
import domain.Stop;
import domain.Tour;

@Controller
@RequestMapping("/stop/organizer")
public class StopOrganizerController extends AbstractController {

	@Autowired
	private StopService			stopService;

	@Autowired
	private TourService			tourService;
	@Autowired
	private OrganizerService	organizerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			Collection<Stop> stops;
			final int id = this.organizerService.findByPrincipal().getCircus().getId();
			stops = this.stopService.findAllStopsByCircus(id);
			result = new ModelAndView("stop/list");
			result.addObject("requestURI", "stop/list.do");
			result.addObject("stops", stops);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			final Collection<Tour> tours = this.tourService.findAllNotAvailableByOrganize();
			Assert.notNull(tours);
			final Stop stop = this.stopService.create();
			result = this.createEditModelAndView(stop);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int stopId) {
		ModelAndView res;
		try {

			final Collection<Tour> tours = this.tourService.findAllNotAvailableByOrganize();
			final Stop stop = this.stopService.findOne(stopId);
			Assert.notNull(stop);

			Assert.isTrue(stop.getDate().after(stop.getTour().getStartDate()));
			Assert.isTrue(stop.getDate().before(stop.getTour().getEndDate()));

			final Organizer o = this.organizerService.findByPrincipal();
			final Boolean b = stop.getTour().getOrganizers().equals(o);
			Assert.isTrue(b);
			Assert.isTrue(stop.getSpotsAvailable() <= stop.getSpotsTotal());
			Assert.isTrue(!stop.getTour().validated);
			Assert.notEmpty(tours);

			res = this.createEditModelAndView(stop);

		} catch (final Throwable oops) {

			res = new ModelAndView("redirect:/#");
		}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("stop") Stop stop, final BindingResult binding) {
		ModelAndView res;

		stop = this.stopService.reconstruct(stop, binding);

		if (binding.hasErrors())
			res = this.createEditModelAndView(stop);
		else
			try {
				final Collection<Tour> tours = this.tourService.findAllNotAvailableByOrganize();
				Assert.notEmpty(tours);

				final Organizer o = this.organizerService.findByPrincipal();
				final Boolean b = stop.getTour().getOrganizers().equals(o);
				Assert.isTrue(b);

				final Collection<Stop> stopsC = this.stopService.findStopsByTour(stop.getTour().getId());
				final List<Stop> stops = new ArrayList<>(stopsC);
				for (final Stop s : stops)
					if (!(s.getId() == stop.getId()))
						Assert.isTrue(!s.getDate().equals(stop.getDate()));

				Assert.isTrue(!stop.getTour().validated);

				Assert.isTrue(stop.getDate().after(stop.getTour().getStartDate()));
				Assert.isTrue(stop.getDate().before(stop.getTour().getEndDate()));

				Assert.isTrue(stop.getSpotsAvailable() <= stop.getSpotsTotal());

				this.stopService.save(stop);
				res = new ModelAndView("redirect:/stop/list.do");

			} catch (final Throwable oops) {

				final Collection<Tour> tours = this.tourService.findAllNotAvailableByOrganize();

				if (tours.isEmpty())
					res = this.createEditModelAndView(stop, "stop.toursempty.error");
				else if (!stop.getDate().after(stop.getTour().getStartDate()))
					res = this.createEditModelAndView(stop, "stop.afterDate.error");
				else if (!stop.getDate().before(stop.getTour().getEndDate()))
					res = this.createEditModelAndView(stop, "stop.beforeDate.error");
				else if (stop.getSpotsAvailable() > stop.getSpotsTotal())
					res = this.createEditModelAndView(stop, "stop.spots.error");
				else if (stop.getTour().getValidated().equals(true))
					res = this.createEditModelAndView(stop, "stop.validated.error");
				else
					res = this.createEditModelAndView(stop, "stop.samedate.error");
			}
		return res;
	}
	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Stop stop, final BindingResult binding) {
		ModelAndView result;
		final Stop res = this.stopService.findOne(stop.getId());

		try {

			final Organizer o = this.organizerService.findByPrincipal();
			final Boolean b = stop.getTour().getOrganizers().equals(o);
			Assert.isTrue(b);
			Assert.isTrue(!stop.getTour().validated);

			this.stopService.delete(res);
			result = new ModelAndView("redirect:/stop/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(res, oops.getMessage());
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Stop stop) {
		return this.createEditModelAndView(stop, null);
	}
	protected ModelAndView createEditModelAndView(final Stop stop, final String messageCode) {
		final ModelAndView res;

		final Collection<Tour> tours = this.tourService.findAllNotAvailableByOrganize();
		res = new ModelAndView("stop/edit");
		res.addObject("stop", stop);
		res.addObject("tours", tours);
		res.addObject("message", messageCode);

		return res;
	}

}
