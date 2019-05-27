
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FeeService;
import domain.Fee;

@Controller
@RequestMapping("/fee/administrator")
public class FeeAdministratorController {

	@Autowired
	private FeeService	feeService;


	@RequestMapping(value = "/setup", method = RequestMethod.GET)
	public ModelAndView setup() {
		ModelAndView result;
		try {
			result = new ModelAndView("fee/setup");
			final Fee fee = this.feeService.find();
			result.addObject("fee", fee);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/setup", method = RequestMethod.POST, params = "save")
	public ModelAndView setup(@Valid final Fee fee, final BindingResult binding) {
		ModelAndView result;
		Integer success;
		if (binding.hasErrors()) {
			result = new ModelAndView("fee/setup");
			result.addObject("fee", fee);
			success = 0;
			result.addObject("success", success);
		} else
			try {
				final Fee saved = this.feeService.save(fee);
				success = 1;
				result = new ModelAndView("fee/setup");
				result.addObject("fee", saved);
				result.addObject("success", success);
			} catch (final Throwable oops) {
				result = new ModelAndView("fee/setup");
				result.addObject("fee", fee);
				success = 0;
				result.addObject("success", success);
				result.addObject("message", "fee.commit.error");
			}
		return result;
	}

}
