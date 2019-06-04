
package service;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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


	//Requisito 9.1 Un Actor autenticado como Empresa puede crear una posicion.
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
		final Offer offerF = this.offerService.reconstruct(offerForm, null);
		final Offer offerFinal = this.offerService.save(offerF);
		tour.getOffers().add(offerFinal);
		this.tourService.save(tour);
	}

}
