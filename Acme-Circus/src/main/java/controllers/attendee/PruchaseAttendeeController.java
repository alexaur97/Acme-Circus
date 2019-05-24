
package controllers.attendee;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryPriceService;
import controllers.AbstractController;
import domain.CategoryPrice;
import forms.PruchaseAttendeeForm;

@Controller
@RequestMapping("/pruchase/attendee")
public class PruchaseAttendeeController extends AbstractController {

	@Autowired
	private CategoryPriceService	categoryPriceService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int stopId) {
		ModelAndView res;
		try {
			final PruchaseAttendeeForm form = new PruchaseAttendeeForm();

			final Collection<CategoryPrice> categories = this.categoryPriceService.findByStop(stopId);
			res = new ModelAndView("pruchase/create");
			res.addObject("form", form);
			res.addObject("categoriess", categories);
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/#");
		}
		return res;
	}

}
