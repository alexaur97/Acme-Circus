
package controllers.Owner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryPriceService;
import services.OwnerService;
import controllers.AbstractController;
import domain.CategoryPrice;
import domain.Owner;

@Controller
@RequestMapping("categoryPrice/owner")
public class CategoryPriceOwnerController extends AbstractController {

	@Autowired
	CategoryPriceService	categoryPriceService;

	@Autowired
	OwnerService			ownerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Owner owner = this.ownerService.findByPrincipal();
			final int circusId = owner.getCircus().getId();
			final Collection<CategoryPrice> categoryPrice = this.categoryPriceService.findCategoryPriceByCircus(circusId);
			result = new ModelAndView("categoryPrice/list");
			result.addObject("requestURI", "categoryPrice/owner/list.do");
			result.addObject("categoryPrice", categoryPrice);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			final CategoryPrice categoryPrice = this.categoryPriceService.create();
			result = new ModelAndView("categoryPrice/edit");
			result.addObject("categoryPrice", categoryPrice);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

}
