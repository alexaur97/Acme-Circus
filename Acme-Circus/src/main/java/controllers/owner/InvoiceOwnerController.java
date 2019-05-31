
package controllers.owner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BannerInvoiceService;
import services.CircusInvoiceService;
import controllers.AbstractController;
import domain.BannerInvoice;
import domain.CircusInvoice;

@Controller
@RequestMapping("/invoice/owner")
public class InvoiceOwnerController extends AbstractController {

	@Autowired
	private CircusInvoiceService	circusInvoiceService;

	@Autowired
	private BannerInvoiceService	bannerInvoiceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			result = new ModelAndView("invoice/list");
			final Collection<CircusInvoice> circusInvoices = this.circusInvoiceService.findAllByPrincipal();
			final Collection<BannerInvoice> bannerInvoices = this.bannerInvoiceService.findAllByPrincipal();
			result.addObject("circusInvoices", circusInvoices);
			result.addObject("bannerInvoices", bannerInvoices);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}
}
