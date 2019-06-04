
package controllers.artist;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArtistInvoiceService;
import services.OfferService;
import controllers.AbstractController;
import domain.ArtistInvoice;

@Controller
@RequestMapping("/invoice/artist")
public class InvoiceArtistController extends AbstractController {

	@Autowired
	private ArtistInvoiceService	artistInvoiceService;

	@Autowired
	private OfferService			offerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			result = new ModelAndView("invoice/list");
			final Collection<ArtistInvoice> artistInvoices = this.artistInvoiceService.findAllByPrincipal();
			result.addObject("artistInvoices", artistInvoices);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int artistInvoiceId) {
		ModelAndView result;
		try {
			result = new ModelAndView("invoice/show");
			final ArtistInvoice artistInvoice = this.artistInvoiceService.findOne(artistInvoiceId);
			result.addObject("artistInvoice", artistInvoice);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

}
