
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TicketRepository;
import domain.Ticket;
import forms.PurchaseAttendeeForm;

@Service
@Transactional
public class TicketService {

	//Managed repository -------------------
	@Autowired
	private TicketRepository	ticketRepository;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public TicketService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Ticket create() {
		Ticket result;

		result = new Ticket();

		return result;
	}

	public Collection<Ticket> findAll() {
		Collection<Ticket> result;

		result = this.ticketRepository.findAll();

		return result;
	}

	public Ticket findOne(final int ticketId) {
		Ticket result;

		result = this.ticketRepository.findOne(ticketId);

		return result;
	}

	public void save(final Ticket ticket) {
		Assert.notNull(ticket);

		this.ticketRepository.save(ticket);
	}

	public void delete(final Ticket ticket) {
		this.ticketRepository.delete(ticket);
	}

	public Collection<Ticket> reconstruct(final PurchaseAttendeeForm form) {
		List<Ticket> res = new ArrayList<>();

		for (Integer i = 0; i < form.getNum(); i++) {
			final Ticket ticket = new Ticket();
			ticket.setCategoryPrice(form.getCategory());
			final Integer refNumber = this.creaNum();
			ticket.setRefNumber(refNumber);
			res.add(ticket);

		}
		//final List<Ticket> r = res.subList(0, form.getNum() - 1);
		res = res.subList(0, form.getNum());
		return res;
	}
	public Integer creaNum() {
		String cadena = null;

		final char[] elementos = {
			'1', '2', '3', '4', '5', '6', '7', '8', '9'
		};

		final char[] conjunto = new char[6];

		for (int i = 0; i < 6; i++) {

			final int el = (int) (Math.random() * 9);

			conjunto[i] = elementos[el];
		}
		cadena = new String(conjunto);

		final Integer num = Integer.parseInt(cadena);

		return num;
	}

	//Other Methods--------------------
}
