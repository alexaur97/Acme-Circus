
package controllers.attendee;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PurchaseService;
import controllers.AbstractController;
import domain.Purchase;
import domain.Ticket;

@Controller
@RequestMapping("/ticket/attendee")
public class TicketAttendeeController extends AbstractController {

	@Autowired
	private PurchaseService	purchaseService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int purchaseId) {
		ModelAndView result;
		try {
			final Purchase purchase = this.purchaseService.findOne(purchaseId);
			final Collection<Ticket> tickets = purchase.getTickets();
			result = new ModelAndView("ticket/list");
			result.addObject("requestURI", "ticket/attendee/list.do");
			result.addObject("tickets", tickets);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;

	}

}
