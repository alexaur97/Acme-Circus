
package controllers.attendee;

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

import services.CategoryPriceService;
import services.PurchaseService;
import services.StopService;
import services.TicketService;
import controllers.AbstractController;
import domain.CategoryPrice;
import domain.Purchase;
import domain.Stop;
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

	@Autowired
	private StopService				stopService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int stopId) {
		ModelAndView res;
		try {
			final Stop stop = this.stopService.findOne(stopId);
			Assert.isTrue(stop.getSpotsAvailable() > 0);
			final PurchaseAttendeeForm purchaseAttendeeForm = new PurchaseAttendeeForm();

			final Collection<CategoryPrice> categories = this.categoryPriceService.findByStop(stopId);
			res = new ModelAndView("purchase/create");
			res.addObject("purchaseAttendeeForm", purchaseAttendeeForm);
			res.addObject("categories", categories);
			res.addObject("stopId", stopId);
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/#");
		}
		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView create(@RequestParam final int stopId, @Valid final PurchaseAttendeeForm purchaseAttendeeForm, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			final Collection<CategoryPrice> categories = this.categoryPriceService.findByStop(stopId);

			res = new ModelAndView("purchase/create");
			res.addObject("purchaseAttendeeForm", purchaseAttendeeForm);
			res.addObject("categories", categories);
			res.addObject("stopId", stopId);

		}

		else

			try {
				final Purchase purchase;
				final Collection<Ticket> tickets;

				tickets = this.tickerService.reconstruct(purchaseAttendeeForm);

				//				for (final Ticket t : tickets)
				//					this.tickerService.save(t);

				purchase = this.purchaseService.reconstruct(purchaseAttendeeForm, tickets);

				this.purchaseService.save(purchase);

				res = new ModelAndView("purchase/show");
				res.addObject("purchase", purchase);
				res.addObject("tickets", tickets);

			} catch (final Throwable oops) {
				final Collection<CategoryPrice> categories = this.categoryPriceService.findByStop(stopId);

				res = new ModelAndView("purchase/create");
				res.addObject("purchaseAttendeeForm", purchaseAttendeeForm);
				res.addObject("categories", categories);
				res.addObject("stopId", stopId);
				res.addObject("message", "purchase.commit.error");

			}

		return res;
	}

}
