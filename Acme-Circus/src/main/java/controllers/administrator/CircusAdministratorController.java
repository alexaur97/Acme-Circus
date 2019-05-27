
package controllers.administrator;

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
@RequestMapping("/circus/administrator")
public class CircusAdministratorController extends AbstractController {

	@Autowired
	private CircusService	circusService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Collection<Circus> circus = this.circusService.findAll();
			result = new ModelAndView("circus/list");
			result.addObject("circus", circus);
			result.addObject("requestURI", "circus/administrator/list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/deactivate", method = RequestMethod.GET)
	public ModelAndView deactivate(@RequestParam final int circusId) {
		ModelAndView result;
		try {
			Circus circus = this.circusService.findOne(circusId);
			circus = this.circusService.deactivate(circus);
			circus = this.circusService.save(circus);
			result = new ModelAndView("redirect:/circus/administrator/list.do");

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

}
