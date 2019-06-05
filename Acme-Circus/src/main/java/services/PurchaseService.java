
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

	public Purchase save(final Purchase pruchase) {
		Assert.notNull(pruchase);

		Purchase res;
		res = this.pruchaseRepository.save(pruchase);
		return res;
	}

	public void delete(final Purchase pruchase) {
		this.pruchaseRepository.delete(pruchase);
	}

	public Purchase reconstruct(final PurchaseAttendeeForm form, final Collection<Ticket> tickets) {
		Assert.isTrue(form.getCategory().getStop().getSpotsAvailable() > 0);

		final Purchase purchase = new Purchase();
		final Attendee attendee = this.attendeeService.findByPrincipal();
		purchase.setAttendee(attendee);
		purchase.setCreditCard(attendee.getCreditCard());
		purchase.setStop(form.getCategory().getStop());

		Assert.isTrue(form.getCategory().getStop().getSpotsAvailable() >= form.getNum());

		final int resta = purchase.getStop().getSpotsAvailable();
		purchase.getStop().setSpotsAvailable(resta - form.getNum());
		final Double i = form.getNum() * form.getCategory().getAmount();
		purchase.setTotalPrice(i);

		purchase.setTickets(tickets);

		return purchase;
	}

	public Collection<Purchase> findByAttendee(final int idA) {

		final Collection<Purchase> res = this.pruchaseRepository.findByAttendee(idA);
		return res;
	}

	public Double findSumByAttendee(final int id) {
		return this.pruchaseRepository.findSumByAttendee(id);
	}

	//Other Methods--------------------
}
