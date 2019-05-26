
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PruchaseRepository;
import domain.Attendee;
import domain.Purchase;
import domain.Ticket;
import forms.PurchaseAttendeeForm;

@Service
@Transactional
public class PurchaseService {

	//Managed repository -------------------
	@Autowired
	private PruchaseRepository	pruchaseRepository;

	@Autowired
	private AttendeeService		attendeeService;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public PurchaseService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Purchase create() {
		Purchase result;

		result = new Purchase();

		return result;
	}

	public Collection<Purchase> findAll() {
		Collection<Purchase> result;

		result = this.pruchaseRepository.findAll();

		return result;
	}

	public Purchase findOne(final int pruchaseId) {
		Purchase result;

		result = this.pruchaseRepository.findOne(pruchaseId);

		return result;
	}

	public void save(final Purchase pruchase) {
		Assert.notNull(pruchase);

		this.pruchaseRepository.save(pruchase);
	}

	public void delete(final Purchase pruchase) {
		this.pruchaseRepository.delete(pruchase);
	}

	public Purchase reconstruct(final PurchaseAttendeeForm form, final Collection<Ticket> tickets) {
		final Purchase purchase = new Purchase();
		final Attendee attendee = this.attendeeService.findByPrincipal();
		purchase.setAttendee(attendee);
		purchase.setCreditCard(attendee.getCreditCard());
		purchase.setStop(form.getCategory().getStop());

		final Double i = form.getNum() * form.getCategory().getAmount();
		purchase.setTotalPrice(i);

		purchase.setTickets(tickets);

		return purchase;
	}

	//Other Methods--------------------
}
