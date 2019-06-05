
package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.StopService;
import services.TourService;
import utilities.AbstractTest;
import domain.Stop;
import domain.Tour;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class StopServiceTest extends AbstractTest {

	@Autowired
	private StopService	stopService;

	@Autowired
	private TourService	tourService;


	//Este test testea el requisito 16.2 Un actor registrado como organizador del circo 
	// puede crear paradas para las giras de su circo siempre que la gira no esté validada

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El organizador se loguea
	// 2. El organizador intenta crear una parada para una gira de su circo
	// 3. La parada se crea correctamente

	// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que el organizador de un circo
	// puede crear paradas para las giras de su circo siempre que no esté validada

	@Test
	public void createStopGood() throws ParseException {
		super.authenticate("organizer2");
		final Stop stop = this.stopService.create();
		stop.setCity("Ciudad");
		stop.setCountry("Pais");
		stop.setLocation("Localizacion");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String stringFecha = "2021-5-15";
		final Date date = sdf.parse(stringFecha);
		stop.setDate(date);
		final int id = super.getEntityId("tour3");
		final Tour tour = this.tourService.findOne(id);
		stop.setTour(tour);
		stop.setSpotsAvailable(100);
		stop.setSpotsTotal(200);
		this.stopService.save(stop);
		super.unauthenticate();

	}

	//Este test testea el requisito 16.2 Un actor registrado como organizador del circo 
	// puede crear paradas para las giras de su circo siempre que no esté validada

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El organizador se loguea
	// 2. El organizador intenta crear una parada para una gira de su circo que ya está validada
	// 3. La parada no se crea correctamente pues la gira ya estaba validada

	// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que el organizador de un circo
	// no puede crear paradas para las giras de su circo que ya esten validadas

	@Test(expected = IllegalArgumentException.class)
	public void createStopError() throws ParseException {
		super.authenticate("organizer1");
		final Stop stop = this.stopService.create();
		stop.setCity("Ciudad");
		stop.setCountry("Pais");
		stop.setLocation("Localizacion");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String stringFecha = "2020-10-11";
		final Date date = sdf.parse(stringFecha);
		stop.setDate(date);
		final int id = super.getEntityId("tour1");
		final Tour tour = this.tourService.findOne(id);
		stop.setTour(tour);
		stop.setSpotsAvailable(100);
		stop.setSpotsTotal(200);
		this.stopService.save(stop);
		super.unauthenticate();

	}

	// intenta crear una parada con una fecha anterior a la actual

	@Test(expected = IllegalArgumentException.class)
	public void createStopError2() throws ParseException {
		super.authenticate("organizer2");
		final Stop stop = this.stopService.create();
		stop.setCity("Ciudad");
		stop.setCountry("Pais");
		stop.setLocation("Localizacion");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String stringFecha = "2016-10-11";
		final Date date = sdf.parse(stringFecha);
		stop.setDate(date);
		final int id = super.getEntityId("tour3");
		final Tour tour = this.tourService.findOne(id);
		stop.setTour(tour);
		stop.setSpotsAvailable(100);
		stop.setSpotsTotal(200);
		this.stopService.save(stop);
		super.unauthenticate();

	}

	//Este test testea el requisito 16.2 Un actor registrado como organizador del circo 
	// puede eliminar paradas para las giras de su circo que no estén validadas

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El organizador se loguea
	// 2. El organizador intenta eliminar una parada para una gira de su circo que no esté validada
	// 3. La parada se elimina correctamente

	// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que el organizador de un circo
	// puede eliminar paradas para las giras de su circo que no estén validadas

	@Test
	public void testDeleteStopGood() {
		super.authenticate("organizer2");
		final int id = super.getEntityId("stop8");
		final Stop stop = this.stopService.findOne(id);
		this.stopService.delete(stop);
		super.unauthenticate();
	}

	//Este test testea el requisito 16.2 Un actor registrado como organizador del circo 
	// puede eliminar paradas para las giras de su circo que no estén validadas

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El organizador se loguea
	// 2. El organizador intenta eliminar una parada para una gira de su circo que ya estaba validada
	// 3. La parada no se elimina correctamente pues la gira ya estaba validada

	// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que el organizador de un circo
	// no puede eliminar paradas para las giras de su circo que ya están validadas

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteStopError() throws ParseException {
		super.authenticate("organizer2");
		final int id = super.getEntityId("stop3");
		final Stop stop = this.stopService.findOne(id);
		this.stopService.delete(stop);
		super.unauthenticate();
	}

	// Intenta borrar una parada que no es suya

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteStopError2() throws ParseException {
		super.authenticate("organizer2");
		final int id = super.getEntityId("stop10");
		final Stop stop = this.stopService.findOne(id);
		this.stopService.delete(stop);
		super.unauthenticate();
	}
}
