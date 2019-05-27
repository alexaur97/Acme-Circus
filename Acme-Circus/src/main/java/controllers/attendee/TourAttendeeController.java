
package controllers.attendee;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.TourService;
import controllers.AbstractController;
import domain.Tour;

@Controller
@RequestMapping("/tour/attendee")
public class TourAttendeeController extends AbstractController {

	@Autowired
	private TourService	tourService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Collection<Tour> tours = this.tourService.findAllAvailable();
			result = new ModelAndView("tour/list");
			result.addObject("requestURI", "tour/attendee/list.do");
			result.addObject("tours", tours);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}

}
