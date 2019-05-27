
package controllers.Owner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryPriceService;
import services.OwnerService;
import services.StopService;
import controllers.AbstractController;
import domain.CategoryPrice;
import domain.Owner;
import domain.Stop;

@Controller
@RequestMapping("categoryPrice/owner")
public class CategoryPriceOwnerController extends AbstractController {

	@Autowired
	CategoryPriceService	categoryPriceService;

	@Autowired
	OwnerService			ownerService;

	@Autowired
	StopService				stopService;


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
			final int circusId = this.ownerService.findByPrincipal().getCircus().getId();
			final Collection<Stop> stops = this.stopService.findAllStopsByCircus(circusId);
			result = new ModelAndView("categoryPrice/edit");
			result.addObject("categoryPrice", categoryPrice);
			result.addObject("stops", stops);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int categoryPriceId) {
		ModelAndView res;
		try {

			final CategoryPrice categoryPrice = this.categoryPriceService.findOne(categoryPriceId);
			Assert.notNull(categoryPrice);

			res = this.createEditModelAndView(categoryPrice);

		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/#");
		}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("categoryPrice") CategoryPrice categoryPrice, final BindingResult binding) {
		ModelAndView res;

		categoryPrice = this.categoryPriceService.reconstruct(categoryPrice);

		if (binding.hasErrors())
			res = this.createEditModelAndView(categoryPrice);
		else
			try {

				this.categoryPriceService.save(categoryPrice);
				res = new ModelAndView("redirect:/categoryPrice/owner/list.do");

			} catch (final Throwable oops) {

				res = this.createEditModelAndView(categoryPrice, "categoryPrice.commit.error");

			}

		return res;
	}

	//	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	//	public ModelAndView delete(@RequestParam final int problemId) {
	//		ModelAndView result;
	//		try {
	//			final Problem problem = this.problemService.findOne(problemId);
	//			this.problemService.delete(problem);
	//			result = new ModelAndView("redirect:/problem/company/list.do");
	//		} catch (final Throwable oops) {
	//			result = new ModelAndView("redirect:/#");
	//		}
	//		return result;
	//	}

	protected ModelAndView createEditModelAndView(final CategoryPrice categoryPrice) {
		return this.createEditModelAndView(categoryPrice, null);
	}
	protected ModelAndView createEditModelAndView(final CategoryPrice categoryPrice, final String messageCode) {
		final ModelAndView res;
		final int circusId = this.ownerService.findByPrincipal().getCircus().getId();
		final Collection<Stop> stops = this.stopService.findAllStopsByCircus(circusId);
		res = new ModelAndView("categoryPrice/edit");
		res.addObject("categoryPrice", categoryPrice);
		res.addObject("stops", stops);
		res.addObject("message", messageCode);

		return res;
	}
}
