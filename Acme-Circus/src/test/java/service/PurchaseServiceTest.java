
package service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.PurchaseService;
import utilities.AbstractTest;
import domain.CategoryPrice;
import domain.Purchase;
import domain.Ticket;
import forms.PurchaseAttendeeForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class PurchaseServiceTest extends AbstractTest {

	@SuppressWarnings("unused")
	@Autowired
	private PurchaseService			purchaseService;

	@Autowired
	private CategoryPriceService	categoryPriceService;

	@Autowired
	private TicketService			ticketService;


	//Requisito 18.1 Un Actor autenticado como Asistente comprar tickets.

	@Test
	public void testEditCircusGood() {
		super.authenticate("attendee1");
		final PurchaseAttendeeForm form = new PurchaseAttendeeForm();
		form.setNum(2);
		final int IdCatPr = super.getEntityId("categoryPrice1");

		final CategoryPrice categoryPrice = this.categoryPriceService.findOne(IdCatPr);
		form.setCategory(categoryPrice);
		final Collection<Ticket> tickets;
		tickets = this.ticketService.reconstruct(form);

		Purchase purchase = this.purchaseService.create();
		purchase = this.purchaseService.reconstruct(form, tickets);

		this.purchaseService.save(purchase);
		super.unauthenticate();
	}

	//Para el caso negativo un asistente va a intentar comprar mas entradas de
	//las disponibles en esa parada.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "reconstruct" comprueba
	// que las entradas que el asistente está intentando comprar están disponibles.
	@Test(expected = IllegalArgumentException.class)
	public void testEditCircusError1() {
		super.authenticate("attendee1");
		final PurchaseAttendeeForm form = new PurchaseAttendeeForm();
		form.setNum(500);
		final int IdCatPr = super.getEntityId("categoryPrice1");

		final CategoryPrice categoryPrice = this.categoryPriceService.findOne(IdCatPr);
		form.setCategory(categoryPrice);
		final Collection<Ticket> tickets;
		tickets = this.ticketService.reconstruct(form);

		Purchase purchase = this.purchaseService.create();
		purchase = this.purchaseService.reconstruct(form, tickets);

		this.purchaseService.save(purchase);
		super.unauthenticate();
	}
	//Para el caso negativo un asistente va a intentar comprar  entradas de
	//una parada que no tiene entradas disponibles.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "reconstruct" comprueba
	// que existen entradas disponibles en esa parada.
	@Test(expected = IllegalArgumentException.class)
	public void testEditCircusError2() {
		super.authenticate("attendee1");
		final PurchaseAttendeeForm form = new PurchaseAttendeeForm();
		form.setNum(2);
		final int IdCatPr = super.getEntityId("categoryPrice19");

		final CategoryPrice categoryPrice = this.categoryPriceService.findOne(IdCatPr);
		form.setCategory(categoryPrice);
		final Collection<Ticket> tickets;
		tickets = this.ticketService.reconstruct(form);

		Purchase purchase = this.purchaseService.create();
		purchase = this.purchaseService.reconstruct(form, tickets);

		this.purchaseService.save(purchase);
		super.unauthenticate();
	}

}
