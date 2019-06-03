
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

	public Ticket save(final Ticket ticket) {
		Assert.notNull(ticket);

		Ticket res;
		res = this.ticketRepository.save(ticket);
		return res;
	}

	public void delete(final Ticket ticket) {
		this.ticketRepository.delete(ticket);
	}

	public Collection<Ticket> reconstruct(final PurchaseAttendeeForm form) {
		List<Ticket> res = new ArrayList<>();

		for (Integer i = 0; i < form.getNum(); i++) {
			final Ticket ticket = new Ticket();
			ticket.setCategoryPrice(form.getCategory());
			final String number = this.creaNum();
			final String name = this.createName(form.getCategory().getStop().getCity());

			final String refNumber = name.concat(number);

			ticket.setRefNumber(refNumber);
			res.add(ticket);

		}
		res = res.subList(0, form.getNum());
		return res;
	}

	private String createName(final String name) {
		String resultado;
		final char[] res = new char[2];

		final Collection<String> elementos = new ArrayList<>();
		elementos.add("A");
		elementos.add("N");
		elementos.add("B");
		elementos.add("O");
		elementos.add("C");
		elementos.add("P");
		elementos.add("D");
		elementos.add("Q");
		elementos.add("E");
		elementos.add("R");
		elementos.add("F");
		elementos.add("S");
		elementos.add("G");
		elementos.add("T");
		elementos.add("H");
		elementos.add("U");
		elementos.add("I");
		elementos.add("V");
		elementos.add("J");
		elementos.add("W");
		elementos.add("K");
		elementos.add("X");
		elementos.add("L");
		elementos.add("Y");
		elementos.add("M");
		elementos.add("Z");

		final char[] nuevo = name.toUpperCase().toCharArray();
		int a = 0;
		for (int i = 0; i <= nuevo.length; i++) {
			char letra;
			final String s = String.valueOf(nuevo[i]);
			if (elementos.contains(s)) {
				letra = name.charAt(i);
				res[a] = letra;
				a++;
				if (a == 2)
					break;
			}
		}
		resultado = new String(res);
		if (resultado.length() < 2)
			for (int j = resultado.length(); j <= 2; j++)
				resultado = resultado.concat("X");
		return resultado;
	}

	public String creaNum() {
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

		return cadena;
	}

	//Other Methods--------------------
}
