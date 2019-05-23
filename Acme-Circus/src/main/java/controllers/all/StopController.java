
package controllers.all;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.StopService;
import domain.Stop;

@Controller
@RequestMapping("/stop")
public class StopController {

	@Autowired
	StopService	stopService;


	@RequestMapping(value = "/listByTour", method = RequestMethod.GET)
	public ModelAndView list(final int tourId) {
		ModelAndView result;
		try {
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
}
