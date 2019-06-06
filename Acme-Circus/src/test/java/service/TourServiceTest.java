
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

import services.CategoryTourService;
import services.OrganizerService;
import services.TourService;
import utilities.AbstractTest;
import domain.CategoryTour;
import domain.Organizer;
import domain.Tour;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class TourServiceTest extends AbstractTest {

	@Autowired
	private TourService			tourService;

	@Autowired
	private CategoryTourService	categoryTourService;

	@Autowired
	private OrganizerService	organizerService;


	//Este test testea el requisito 15.5 Un actor registrado como dueño del circo debe poder validar
	// las giras de su circo para que estén operativas para la venta

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El dueño del cirsco se loguea
	// 2. El dueño se dirije a su listado de tours para ver los tours que puede validar
	// 3. El provedor valida el tour

	// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que el dueño de un circo
	// puede validar las giras de su circo

	@Test
	public void validateTourGood() {

		super.authenticate("owner2");

		final int idTour = super.getEntityId("tour3");
		final Tour tour = this.tourService.findOne(idTour);
		this.tourService.validate(tour);
		super.unauthenticate();

	}

	//Este test testea el requisito 15.5 Un actor registrado como dueño del circo 
	// no podrá validar las giras de un circo que no sea suyo

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El dueño se loguea
	// 2. El dueño intenta validar un tour que no es suyo poniendo una id de forma
	//maliciosa
	// 3. El tour no puede ser validado

	// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que el dueño de un circo
	// solo puede validar las giras de su circo

	@Test(expected = IllegalArgumentException.class)
	public void validateTourError() throws ParseException {

		super.authenticate("owner1");

		final int idTour = super.getEntityId("tour3");
		final Tour tour = this.tourService.findOne(idTour);
		this.tourService.validate(tour);
		super.unauthenticate();

	}

	//Este test testea el requisito 15.5 Un actor registrado como dueño del circo 
	// no podrá validar las giras de un circo que no sea suyo

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El dueño se loguea
	// 2. El dueño intenta validar un tour que ya está validado
	// 3. El sistema no le permite validar un tour validado

	// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que el dueño de un circo
	// solo puede validar los tours que no estén ya validados

	// intenta validar una gira validada

	@Test(expected = IllegalArgumentException.class)
	public void validateTourError2() throws ParseException {

		super.authenticate("owner1");
		final int idTour = super.getEntityId("tour1");
		final Tour tour = this.tourService.findOne(idTour);
		this.tourService.validate(tour);
		super.unauthenticate();

	}

	//Este test testea el requisito 16.1 Un actor registrado como organizador del circo 
	// puede crear giras para su circo

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El organizador se loguea
	// 2. El organizador intenta crear una nueva gira para su circo
	// 3. La gira se crea correctamente

	// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que el organizador de un circo
	// puede crear giras para su circo

	@Test
	public void createTourGood() throws ParseException {
		super.authenticate("organizer2");
		final Tour tour = this.tourService.create();
		final Organizer o = this.organizerService.findByPrincipal();
		final int idCategoryTour = super.getEntityId("categoryTour1");
		final CategoryTour categoryTour = this.categoryTourService.findOne(idCategoryTour);
		tour.setCategoryTour(categoryTour);
		tour.setDescription("descripcion");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String stringFecha = "2035-10-10";
		final Date endDate = sdf.parse(stringFecha);
		tour.setEndDate(endDate);
		final String stringFecha1 = "2034-10-10";
		final Date startDate = sdf.parse(stringFecha1);
		tour.setStartDate(startDate);
		tour.setCategoryTour(categoryTour);
		tour.setLink("http://tour.es");
		tour.setOrganizers(o);
		tour.setName("nombre");
		tour.setTags(null);
		tour.setOffers(null);
		tour.setValidated(false);
		this.tourService.save(tour);
		super.unauthenticate();

	}

	//Este test testea el requisito 16.1 Un actor registrado como organizador del circo 
	// puede crear giras para su circo, pero la fecha de comienzo de una gira
	// siempre debe ser anterior a la fecha de fin de la gira

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El organizador se loguea
	// 2. El organizador intenta crear una nueva gira para su circo introduciendo
	// una fecha de comienzo posterior a la fecha de fin de la gira
	// 3. La gira no puede crearse

	// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que el organizador de un circo
	// puede crear giras para su circo pero tiene que tener concordancia con las fechas

	@Test(expected = IllegalArgumentException.class)
	public void createTourError() throws ParseException {
		super.authenticate("organizer2");
		final Tour tour = this.tourService.create();
		final Organizer o = this.organizerService.findByPrincipal();
		final int idCategoryTour = super.getEntityId("categoryTour1");
		final CategoryTour categoryTour = this.categoryTourService.findOne(idCategoryTour);
		tour.setCategoryTour(categoryTour);
		tour.setDescription("descripcion");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String stringFecha = "2035-10-10";
		final Date endDate = sdf.parse(stringFecha);
		tour.setEndDate(endDate);
		final String stringFecha1 = "2036-10-10";
		final Date startDate = sdf.parse(stringFecha1);
		tour.setStartDate(startDate);
		tour.setCategoryTour(categoryTour);
		tour.setLink("http://tour.es");
		tour.setOrganizers(o);
		tour.setName("nombre");
		tour.setTags(null);
		tour.setOffers(null);
		tour.setValidated(false);
		this.tourService.save(tour);
		super.unauthenticate();
	}

	//Este test testea el requisito 16.1 Un actor registrado como organizador del circo 
	// puede crear giras para su circo

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El dueño se loguea
	// 2. El dueño intenta crear una nueva gira para su circo
	// 3. La gira no puede crearse ya que solo la puede crear el organizador

	// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que las giras solo las puede crear
	// el organizador

	@Test(expected = IllegalArgumentException.class)
	public void createTourError2() throws ParseException {
		super.authenticate("owner1");
		final Tour tour = this.tourService.create();
		final Organizer o = this.organizerService.findByPrincipal();
		final int idCategoryTour = super.getEntityId("categoryTour1");
		final CategoryTour categoryTour = this.categoryTourService.findOne(idCategoryTour);
		tour.setCategoryTour(categoryTour);
		tour.setDescription("descripcion");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String stringFecha = "2035-10-10";
		final Date endDate = sdf.parse(stringFecha);
		tour.setEndDate(endDate);
		final String stringFecha1 = "2034-10-10";
		final Date startDate = sdf.parse(stringFecha1);
		tour.setStartDate(startDate);
		tour.setCategoryTour(categoryTour);
		tour.setLink("hola");
		tour.setOrganizers(o);
		tour.setName("nombre");
		tour.setTags(null);
		tour.setOffers(null);
		tour.setValidated(false);
		this.tourService.save(tour);
		super.unauthenticate();
		//Este test testea el requisito 16.1 Un actor registrado como organizador del circo 
		// puede editar giras para su circo

		// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
		// 1. El organizador se loguea
		// 2. El organizador intenta editar una gira para su circo
		// 3. La gira se edita correctamente

		// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

		// Estamos verificando en nuestro modelo de datos que el organizador de un circo
		// puede editar giras para su circo
	}
	@Test
	public void editTourGood() {
		super.authenticate("organizer2");
		final int tourId = super.getEntityId("tour3");
		final Tour tour = this.tourService.findOne(tourId);
		tour.setDescription("cambiada");
		this.tourService.reconstruct(tour, null);
		this.tourService.save(tour);
		super.unauthenticate();

	}

	//Este test testea el requisito 16.1 Un actor registrado como organizador del circo 
	// puede editar giras para su circo

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El organizador se loguea
	// 2. El organizador intenta editar una gira para su circo pero introduce
	// una fecha de fin anterior a la fecha de inicio del tour
	// 3. La gira se no puede editarse 

	// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que el organizador de un circo
	// puede editar giras para su circo pero debe tener concordancia con las fechas

	@Test(expected = IllegalArgumentException.class)
	public void editTourError() throws ParseException {
		super.authenticate("organizer2");
		final int tourId = super.getEntityId("tour3");
		final Tour tour = this.tourService.findOne(tourId);
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String stringFecha = "2020-10-10";
		final Date endDate = sdf.parse(stringFecha);
		tour.setEndDate(endDate);
		this.tourService.reconstruct(tour, null);
		this.tourService.save(tour);
		super.unauthenticate();

	}

	//Este test testea el requisito 16.1 Un actor registrado como organizador del circo 
	// puede editar giras para su circo

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El organizador se loguea
	// 2. El organizador intenta editar una gira para su circo
	// estando la gira ya validada
	// 3. La gira se no puede editarse 

	// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que el organizador de un circo
	// puede editar giras para su circo pero no puede estar validada

	@Test(expected = IllegalArgumentException.class)
	public void editTourError2() throws ParseException {
		super.authenticate("organizer1");
		final int tourId = super.getEntityId("tour1");
		final Tour tour = this.tourService.findOne(tourId);
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String stringFecha = "2020-10-10";
		final Date endDate = sdf.parse(stringFecha);
		tour.setEndDate(endDate);
		this.tourService.reconstruct(tour, null);
		this.tourService.save(tour);
		super.unauthenticate();

	}

	//Este test testea el requisito 16.1 Un actor registrado como organizador del circo 
	// puede eliminar giras de su circo

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El organizador se loguea
	// 2. El organizador intenta eliminar una gira de su circo
	// 3. La gira se elimina correctamente

	// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que el organizador de un circo
	// puede eliminar giras de su circo
	@Test
	public void testDeleteTourGood() {
		super.authenticate("organizer2");
		final int id = super.getEntityId("tour3");
		final Tour tour = this.tourService.findOne(id);
		this.tourService.delete(tour);
		super.unauthenticate();
	}

	//Este test testea el requisito 16.1 Un actor registrado como organizador del circo 
	// puede eliminar giras de su circo

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El organizador se loguea
	// 2. El organizador intenta eliminar una gira de un circo que no es suyo
	// 3. La gira no se elimina correctamente

	// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que el organizador de un circo
	// puede eliminar giras de su circo pero no de otro circo que no le pertenece

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteTourError() throws ParseException {
		super.authenticate("organizer1");
		final int id = super.getEntityId("tour3");
		final Tour tour = this.tourService.findOne(id);
		this.tourService.delete(tour);
		super.unauthenticate();
	}

	//Este test testea el requisito 16.1 Un actor registrado como organizador del circo 
	// puede eliminar giras de su circo

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El organizador se loguea
	// 2. El organizador intenta eliminar una gira de un circo que ya estaba validada
	// 3. La gira no se elimina correctamente

	// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que el organizador de un circo
	// puede eliminar giras de su circo siempre que no estén validadas
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteTourError2() throws ParseException {
		super.authenticate("organizer1");
		final int id = super.getEntityId("tour1");
		final Tour tour = this.tourService.findOne(id);
		this.tourService.delete(tour);
		super.unauthenticate();
	}

	//Este test testea el requisito 16.1 Un actor registrado como organizador del circo 
	// puede editar giras para su circo

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El dueño se loguea
	// 2. El dueño intenta editar una gira para su circo
	// 3. La gira se no puede editarse ya que solo puede editarlas el organizador

	// Análisis del data coverage (¿Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que solo el organizador
	// puede editar las giras del circo

	@Test(expected = IllegalArgumentException.class)
	public void editTourError3() throws ParseException {
		super.authenticate("owner1");
		final int tourId = super.getEntityId("tour1");
		final Tour tour = this.tourService.findOne(tourId);
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String stringFecha = "2020-10-10";
		final Date endDate = sdf.parse(stringFecha);
		tour.setEndDate(endDate);
		this.tourService.reconstruct(tour, null);
		this.tourService.save(tour);
		super.unauthenticate();

	}

}
