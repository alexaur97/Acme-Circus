
package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryTourService;
import controllers.AbstractController;
import domain.CategoryTour;

@Controller
@RequestMapping("/categoryTour/administrator")
public class CategoryTourAdministratorController extends AbstractController {

	@Autowired
	private CategoryTourService	categoryTourService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Collection<CategoryTour> categoriesTour = this.categoryTourService.findAll();
			result = new ModelAndView("categoryTour/listAdmin");
			result.addObject("categoriesTour", categoriesTour);
			result.addObject("requestURI", "categoryTour/administrator/list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			final CategoryTour categoryTour = this.categoryTourService.create();
			result = this.createEditModelAndView(categoryTour);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int categoryTourId) {
		ModelAndView result;
		try {
			final CategoryTour categoryTour = this.categoryTourService.findOne(categoryTourId);
			final Collection<CategoryTour> used = this.categoryTourService.findAllUsedCategoriesTour();
			Assert.isTrue(!used.contains(categoryTour));
			result = this.createEditModelAndView(categoryTour);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int categoryTourId) {
		ModelAndView result;
		try {
			final CategoryTour categoryTour = this.categoryTourService.findOne(categoryTourId);
			final Collection<CategoryTour> used = this.categoryTourService.findAllUsedCategoriesTour();
			result = new ModelAndView("categoryTour/show");
			result.addObject("categoryTour", categoryTour);
			Boolean isNotUsed;
			if (used.contains(categoryTour))
				isNotUsed = false;
			else
				isNotUsed = true;
			result.addObject("isNotUsed", isNotUsed);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid final CategoryTour categoryTour, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(categoryTour);
		else
			try {
				this.categoryTourService.save(categoryTour);
				result = new ModelAndView("redirect:/categoryTour/administrator/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(categoryTour, "categoryTour.commit.error");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(final CategoryTour categoryTour) {
		return this.createEditModelAndView(categoryTour, null);
	}

	protected ModelAndView createEditModelAndView(final CategoryTour categoryTour, final String messageCode) {
		ModelAndView result;
		if (categoryTour.getId() == 0)
			result = new ModelAndView("categoryTour/create");
		else
			result = new ModelAndView("categoryTour/edit");
		result.addObject("categoryTour", categoryTour);
		result.addObject("message", messageCode);
		return result;
	}

}
