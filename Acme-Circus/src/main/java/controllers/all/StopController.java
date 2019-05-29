
package controllers.all;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.OrganizerService;
import services.StopService;
import domain.Stop;

@Controller
@RequestMapping("/stop")
public class StopController {

	@Autowired
	StopService					stopService;

	@Autowired
	private OrganizerService	organizerService;


	@RequestMapping(value = "/listByTour", method = RequestMethod.GET)
	public ModelAndView list(final int tourId) {
		ModelAndView result;
		try {
			Assert.notNull(tourId);
			Collection<Stop> stops;
			stops = this.stopService.findStopsByTour(tourId);
			result = new ModelAndView("stop/list");
			result.addObject("requestURI", "stop/listByTour.do");
			result.addObject("stops", stops);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listOrganizer() {
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

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int stopId) {
		ModelAndView result;

		try {
			Assert.notNull(stopId);
			final Stop stop = this.stopService.findOne(stopId);
			result = new ModelAndView("stop/show");
			result.addObject("stop", stop);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}
}
