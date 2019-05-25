
package controllers.attendee;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryPriceService;
import services.PurchaseService;
import services.TicketService;
import controllers.AbstractController;
import domain.CategoryPrice;
import domain.Purchase;
import domain.Ticket;
import forms.PurchaseAttendeeForm;

@Controller
@RequestMapping("/purchase/attendee")
public class PurchaseAttendeeController extends AbstractController {

	@Autowired
	private CategoryPriceService	categoryPriceService;

	@Autowired
	private PurchaseService			purchaseService;

	@Autowired
	private TicketService			tickerService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int stopId) {
		ModelAndView res;
		try {
			final PurchaseAttendeeForm form = new PurchaseAttendeeForm();

			final Collection<CategoryPrice> categories = this.categoryPriceService.findByStop(stopId);
			res = new ModelAndView("purchase/create");
			res.addObject("form", form);
			res.addObject("categories", categories);
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/#");
		}
		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView create(@ModelAttribute("form") final PurchaseAttendeeForm form, final BindingResult binding) {
		ModelAndView res;

		try {
			final Purchase purchase;
			final Collection<Ticket> tickets;

			tickets = this.tickerService.reconstruct(form, binding);
			purchase = this.purchaseService.reconstruct(form, tickets, binding);

			for (final Ticket t : tickets)
				this.tickerService.save(t);

			this.purchaseService.save(purchase);

			res = new ModelAndView("purchase/show");
			res.addObject("purchase", purchase);
			res.addObject("tickets", tickets);

		} catch (final Throwable oops) {

			res = new ModelAndView("purchase/create");
			res.addObject("message", "purchase.commit.error");

		}

		return res;
	}

}
