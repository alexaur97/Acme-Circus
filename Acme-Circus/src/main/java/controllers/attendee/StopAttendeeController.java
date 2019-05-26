
package controllers.attendee;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.StopService;
import services.TourService;
import controllers.AbstractController;
import domain.Stop;
import domain.Tour;

@Controller
@RequestMapping("/stop/attendee")
public class StopAttendeeController extends AbstractController {

	@Autowired
	private StopService	stopService;

	@Autowired
	private TourService	tourService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int tourId) {
		ModelAndView result;
		try {
			final Tour tour = this.tourService.findOne(tourId);
			Assert.isTrue(tour.getValidated());
			final Collection<Stop> stops = this.stopService.findAllAvailable(tourId);
			result = new ModelAndView("stop/list");
			result.addObject("requestURI", "stop/attendee/list.do");
			result.addObject("stops", stops);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}

}
