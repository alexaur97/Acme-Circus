
package controllers.Owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CircusService;
import services.OwnerService;
import controllers.AbstractController;
import domain.Circus;
import domain.Owner;

@Controller
@RequestMapping("/circus/owner")
public class CircusOwnerController extends AbstractController {

	@Autowired
	private CircusService	circusService;

	@Autowired
	private OwnerService	ownerService;


	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView myList() {
		ModelAndView result;
		try {
			this.ownerService.findByPrincipal();
			Circus circus;
			circus = this.circusService.findByOwner();
			result = new ModelAndView("circus/myList");
			result.addObject("requestURI", "circus/owner/myList.do");
			result.addObject("circus", circus);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int circusId) {
		ModelAndView res;
		try {

			final Circus circus = this.circusService.findOne(circusId);
			Assert.notNull(circus);

			final Integer idO = this.ownerService.findByPrincipal().getId();
			final Owner owner = this.ownerService.findOne(idO);
			Assert.isTrue(owner.getCircus().equals(circus));
			res = this.createEditModelAndView(circus);

		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/#");
		}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("circus") Circus circus, final BindingResult binding) {
		ModelAndView res;

		circus = this.circusService.reconstruct(circus);

		if (binding.hasErrors())
			res = this.createEditModelAndView(circus);
		else
			try {

				this.circusService.save(circus);
				res = new ModelAndView("redirect:/circus/owner/myList.do");

			} catch (final Throwable oops) {

				res = this.createEditModelAndView(circus, "circus.commit.error");

			}

		return res;
	}

	protected ModelAndView createEditModelAndView(final Circus circus) {
		return this.createEditModelAndView(circus, null);
	}
	protected ModelAndView createEditModelAndView(final Circus circus, final String messageCode) {
		final ModelAndView res;
		res = new ModelAndView("circus/edit");
		res.addObject("circus", circus);
		res.addObject("message", messageCode);

		return res;
	}
}
