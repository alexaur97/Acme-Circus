
package controllers.all;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CircusService;
import controllers.AbstractController;
import domain.Circus;

@Controller
@RequestMapping("/circus")
public class CircusController extends AbstractController {

	@Autowired
	private CircusService	circusService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			Collection<Circus> circus;
			circus = this.circusService.findAllWithTour();
			result = new ModelAndView("circus/list");
			result.addObject("requestURI", "circus/list.do");
			result.addObject("circus", circus);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int circusId) {
		ModelAndView result;
		try {
			final Circus circus = this.circusService.findOne(circusId);
			result = new ModelAndView("circus/show");
			result.addObject("requestURI", "circus/show.do");
			result.addObject("circus", circus);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}
}
