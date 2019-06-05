
package service;

import java.text.ParseException;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.Validator;

import services.OfferService;
import services.OrganizerService;
import services.PerformanceService;
import services.TourService;
import utilities.AbstractTest;
import domain.Offer;
import domain.Organizer;
import domain.Performance;
import domain.Tour;
import forms.OfferForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class OfferServiceTest extends AbstractTest {

	@Autowired
	private OfferService		offerService;
	@Autowired
	private PerformanceService	performanceService;
	@Autowired
	private TourService			tourService;
	@Autowired
	private OrganizerService	organizerService;
	@Autowired
	private Validator			validator;


	//Requisito 16.3 Un actor autenticado como organizador en el sistema debe ser capaz de crear ofertas a diferentes artistas respecto a sus actuaciones.
	@Test
	public void testCreateOfferGood() {
		super.authenticate("organizer1");
		final Organizer organizer = this.organizerService.findByPrincipal();
		final OfferForm offerForm = new OfferForm();
		offerForm.setMoney(100.0);
		offerForm.setObservations("hola");
		final int IdPerformance = super.getEntityId("performance2");
		final int IdTour = super.getEntityId("tour1");
		final Tour tour = this.tourService.findOne(IdTour);
		final Performance performance = this.performanceService.findOne(IdPerformance);
		offerForm.setPerformance(performance);
		offerForm.setTour(tour);
		this.validator.validate(offerForm, null);
		final Offer offerF = this.offerService.reconstruct(offerForm, null);
		final Offer offerFinal = this.offerService.save(offerF);
		tour.getOffers().add(offerFinal);
		this.tourService.save(tour);
	}

	//	Para el caso negativo estamos intentando que un Organizador cree una oferta con dinero negativo
	//esto debe provocar un fallo en el sistema porque este solo puede ser positivo
	//Análisis del sentence coverage: el sistema al llamar al validate comprueba
	// que las restricciones del dominio se cumplen.

	@Test(expected = NullPointerException.class)
	public void testCreateOfferError() throws ParseException {
		super.authenticate("organizer1");
		final Organizer organizer = this.organizerService.findByPrincipal();
		final OfferForm offerForm = new OfferForm();
		offerForm.setMoney(-100.0);
		offerForm.setObservations("hola");
		final int IdPerformance = super.getEntityId("performance2");
		final int IdTour = super.getEntityId("tour1");
		final Tour tour = this.tourService.findOne(IdTour);
		final Performance performance = this.performanceService.findOne(IdPerformance);
		offerForm.setPerformance(performance);
		offerForm.setTour(tour);
		this.validator.validate(offerForm, null);
		final Offer offerF = this.offerService.reconstruct(offerForm, null);
		final Offer offerFinal = this.offerService.save(offerF);
		tour.getOffers().add(offerFinal);

		this.tourService.save(tour);
	}
	//	Para el segundo caso negativo estamos intentando que un Organizador cree una oferta con una copia de una performance
	//esto debe provocar un fallo en el sistema porque solo se puede elegir la performance original
	//Análisis del sentence coverage: el sistema al llamar al validate comprueba
	// que las restricciones del dominio se cumplen.

	@Test(expected = IllegalArgumentException.class)
	public void testCreateOfferError2() throws ParseException {
		super.authenticate("organizer1");
		final Organizer organizer = this.organizerService.findByPrincipal();
		final OfferForm offerForm = new OfferForm();
		offerForm.setMoney(100.0);
		offerForm.setObservations("hola");
		final int IdPerformance = super.getEntityId("performance13");
		final int IdTour = super.getEntityId("tour1");
		final Tour tour = this.tourService.findOne(IdTour);
		final Performance performance = this.performanceService.findOne(IdPerformance);
		offerForm.setPerformance(performance);
		offerForm.setTour(tour);
		this.validator.validate(offerForm, null);
		final Offer offerF = this.offerService.reconstruct(offerForm, null);
		final Offer offerFinal = this.offerService.save(offerF);
		tour.getOffers().add(offerFinal);

		this.tourService.save(tour);
	}
	//Requisito 17.3 Un actor autenticado como artista en el sistema debe ser capaz derechazar ofertas de circos para actuar en ellos.
	@Test
	public void testRejectOfferGood() {
		super.authenticate("artist1");
		final int offerId = super.getEntityId("offer2");
		final Offer a = this.offerService.rejectResticc(offerId);
		this.offerService.save(a);
	}
	//	Para el caso negativo estamos intentando que un Artista rechace una oferta que no es suya
	//esto debe provocar un fallo en el sistema porque este solo puede rechazar sus ofertas
	//Análisis del sentence coverage: el sistema al llamar al metodo rejectRestricc que comprueba
	// que la el atributo artist de la oferta es el propio artista logueado.

	@Test(expected = IllegalArgumentException.class)
	public void testRejectOfferError() {
		super.authenticate("artist2");
		final int offerId = super.getEntityId("offer2");
		final Offer a = this.offerService.rejectResticc(offerId);
		this.offerService.save(a);
	}
	//	Para el segundo caso negativo estamos intentando que un Artista rechace una oferta ya aceptada
	//esto debe provocar un fallo en el sistema porque este solo puede rechazar sus ofertas en PENDING
	//Análisis del sentence coverage: el sistema al llamar al metodo rejectRestricc que comprueba
	// que el estado de la oferta es PENDING

	@Test(expected = IllegalArgumentException.class)
	public void testRejectOfferError2() {
		super.authenticate("artist2");
		final int offerId = super.getEntityId("offer11");
		final Offer a = this.offerService.rejectResticc(offerId);
		this.offerService.save(a);
	}
	//Requisito 17.2 Un actor autenticado como artista en el sistema debe ser capaz de aceptar las ofertas de los circos siempre que no tengan ya alguna oferta aceptada en dichas fechas.

	@Test
	public void testAcceptOfferGood() {
		super.authenticate("artist2");
		final int offerId = super.getEntityId("offer6");
		this.offerService.acceptRestricGet(offerId);
		final Offer offer = this.offerService.findOne(offerId);
		final Offer offerF = this.offerService.reconstructArtist(offer, null);
		this.offerService.save(offerF);

	}
	//	Para el caso negativo estamos intentando que un Artista acepte una oferta de una fecha que ya tiene otro espectaculo
	//esto debe provocar un fallo en el sistema porque este solo puede aceptar ofertas en fechas disponibles
	//Análisis del sentence coverage: el sistema al llamar al metodo acceptRestricGet que comprueba
	// que no tiene ninguna oferta confirmada para la fecha de esta oferta en cuestion.

	@Test(expected = IllegalArgumentException.class)
	public void testAcceptOfferError() {
		super.authenticate("artist1");
		final int offerId = super.getEntityId("offer2");
		this.offerService.acceptRestricGet(offerId);
		final Offer offer = this.offerService.findOne(offerId);
		final Offer offerF = this.offerService.reconstructArtist(offer, null);
		this.offerService.save(offerF);

	}
	//	Para el caso negativo estamos intentando que un Artista acepte una oferta de un tour ya finalizado
	//esto debe provocar un fallo en el sistema porque este solo puede aceptar ofertas en fechas disponibles
	//Análisis del sentence coverage: el sistema al llamar al metodo acceptRestricGet que comprueba
	// que el tour que realizo la oferta aun no ha finalizado.

	@Test(expected = IllegalArgumentException.class)
	public void testAcceptOfferError2() {
		super.authenticate("artist2");
		final int offerId = super.getEntityId("offer4");
		this.offerService.acceptRestricGet(offerId);
		final Offer offer = this.offerService.findOne(offerId);
		final Offer offerF = this.offerService.reconstructArtist(offer, null);
		this.offerService.save(offerF);

	}
}
