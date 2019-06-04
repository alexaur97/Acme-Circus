
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ArtistInvoiceService;
import services.BannerInvoiceService;
import services.CircusInvoiceService;
import controllers.AbstractController;
import domain.ArtistInvoice;
import domain.BannerInvoice;
import domain.CircusInvoice;

@Controller
@RequestMapping("/invoice/administrator")
public class InvoiceAdministratorController extends AbstractController {

	@Autowired
	private CircusInvoiceService	circusInvoiceService;

	@Autowired
	private BannerInvoiceService	bannerInvoiceService;

	@Autowired
	private ArtistInvoiceService	artistInvoiceService;


	@RequestMapping(value = "/generate", method = RequestMethod.GET)
	public ModelAndView generate() {
		ModelAndView result;
		try {
			final Boolean alreadyGenerated = this.circusInvoiceService.areAlreadyGenerated();
			result = new ModelAndView("invoice/generate");
			result.addObject("alreadyGenerated", alreadyGenerated);
			if (alreadyGenerated == true) {
				final Collection<CircusInvoice> circusInvoices = this.circusInvoiceService.findCurrentMonthInvoices();
				Double sum = 0.0;
				for (final CircusInvoice c : circusInvoices)
					sum = sum + c.getTotal();
				result.addObject("circusInvoices", circusInvoices);
				result.addObject("requestURI", "invoice/administrator/generate.do");
				result.addObject("sum", sum);
			}
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}
	@RequestMapping(value = "/generating", method = RequestMethod.GET)
	public ModelAndView generating() {
		ModelAndView result;
		try {
			this.circusInvoiceService.generateMonthlyInvoices();
			result = new ModelAndView("redirect:/invoice/administrator/generate.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			result = new ModelAndView("invoice/listAdmin");
			final Collection<CircusInvoice> circusInvoices = this.circusInvoiceService.findCurrentMonthInvoices();
			final Collection<BannerInvoice> bannerInvoices = this.bannerInvoiceService.findCurrentMonthInvoices();
			final Collection<ArtistInvoice> artistInvoices = this.artistInvoiceService.findCurrentMonthInvoices();
			final Double total = this.artistInvoiceService.findCurrentMonthArtistBenefits() + this.bannerInvoiceService.findCurrentMonthBannerBenefits() + this.circusInvoiceService.findCurrentMonthCircusBenefits();
			result.addObject("circusInvoices", circusInvoices);
			result.addObject("bannerInvoices", bannerInvoices);
			result.addObject("artistInvoices", artistInvoices);
			result.addObject("total", total);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

}
