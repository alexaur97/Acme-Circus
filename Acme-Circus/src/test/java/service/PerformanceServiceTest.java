
package service;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.Validator;

import services.ArtistService;
import services.OfferService;
import services.PerformanceService;
import services.TourService;
import utilities.AbstractTest;
import domain.Artist;
import domain.Performance;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class PerformanceServiceTest extends AbstractTest {

	@Autowired
	private OfferService		offerService;
	@Autowired
	private PerformanceService	performanceService;
	@Autowired
	private TourService			tourService;
	@Autowired
	private ArtistService		artistService;
	@Autowired
	private Validator			validator;


	//Requisito 17.1 Un actor autenticado como artista en el sistema debe ser capaz de añadir nuevas actuaciones de su propia cosecha al sistema.
	@Test
	public void testCreateEditPerformanceGood() {
		super.authenticate("artist1");
		final Artist artist = this.artistService.findByPrincipal();
		final Performance performance = new Performance();
		performance.setName("PRUEBA");
		performance.setPersons(4);
		final Collection<String> tags = new ArrayList<>();
		tags.add("PRUEBA");
		performance.setTags(tags);
		performance.setVideo("https://ev.us.es/webapps/blackboard/content/content_access_denied.jsp");
		this.performanceService.reconstruct(performance, null);
		this.performanceService.save(performance);
	}
	//	Para el caso negativo estamos intentando que un Artista cree una performance con numero de participantes negativo
	//esto debe provocar un fallo en el sistema porque este solo puede ser positivo
	//Análisis del sentence coverage: el sistema al llamar al metodo reconstruction que comprueba
	// que las restricciones del dominio se cumplen.

	@Test(expected = NullPointerException.class)
	public void testCreateEditPerformanceError() {
		super.authenticate("artist1");
		final Artist artist = this.artistService.findByPrincipal();
		final Performance performance = new Performance();
		performance.setName("PRUEBA");
		performance.setPersons(-4);
		final Collection<String> tags = new ArrayList<>();
		tags.add("PRUEBA");
		performance.setTags(tags);
		performance.setVideo("https://ev.us.es/webapps/blackboard/content/content_access_denied.jsp");
		this.performanceService.reconstruct(performance, null);
		this.performanceService.save(performance);
	}
	//Requisito 17.1 Un actor autenticado como artista en el sistema debe ser capaz de eleminar sus actuaciones .

	@Test
	public void testDeletePerformanceGood() {
		super.authenticate("artist1");
		final int IdPerformance = super.getEntityId("performance2");
		final Performance performance = this.performanceService.findOne(IdPerformance);
		this.performanceService.delete(performance);
	}
	//	Para el caso negativo estamos intentando que un Artista elimine una performance que no es suya
	//esto debe provocar un fallo en el sistema porque este solo puede eliminar sus actuaciones
	//Análisis del sentence coverage: el sistema al llamar al metodo delete que comprueba
	//el dueño de la actuacion es el artista que esta logueado

	@Test(expected = IllegalArgumentException.class)
	public void testDeletePerformanceError() {
		super.authenticate("artist2");
		final int IdPerformance = super.getEntityId("performance2");
		final Performance performance = this.performanceService.findOne(IdPerformance);
		this.performanceService.delete(performance);
	}
}
