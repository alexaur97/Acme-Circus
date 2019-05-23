
package controllers.all;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryTourService;
import domain.CategoryTour;

@Controller
@RequestMapping("/categoryTour")
public class CategoryTourController {

	@Autowired
	CategoryTourService	categoryTourService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			Collection<CategoryTour> categoryTour;
			categoryTour = this.categoryTourService.findAll();
			result = new ModelAndView("categoryTour/list");
			result.addObject("requestURI", "categoryTour/list.do");
			result.addObject("categoryTour", categoryTour);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}

}
