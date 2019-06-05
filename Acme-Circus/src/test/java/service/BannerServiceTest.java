
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

import services.BannerService;
import services.TourService;
import utilities.AbstractTest;
import domain.Banner;
import domain.Tour;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class BannerServiceTest extends AbstractTest {

	@Autowired
	private BannerService	bannerService;

	@Autowired
	private TourService		tourService;


	//Requisito 15.3 Un Actor autenticado como Dueño puede crear anuncios para su circo.

	@Test
	public void testCreateBannerGood() throws ParseException {
		super.authenticate("owner1");
		final int IdTour = super.getEntityId("tour1");
		final Tour tour = this.tourService.findOne(IdTour);

		final Banner banner = this.bannerService.create();
		banner.setDescription("new description");

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String stringFecha = "2020-09-09";
		final Date fecha = sdf.parse(stringFecha);
		banner.setStartDate(fecha);

		final String stringFechaFinal = "2021-09-09";
		final Date fechaFinal = sdf.parse(stringFechaFinal);
		banner.setEndDate(fechaFinal);

		banner.setImg("https://foto.com");

		banner.setTour(tour);

		this.bannerService.save(banner);
		super.unauthenticate();
	}

	//Para el caso negativo estamos intentado que un Dueño cree un banner con 
	//la fecha final anterior a la de inicio.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "save" comprueba
	// que las fechas cumplen los requisitos adecuados.
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBannerError1() throws ParseException {
		super.authenticate("owner1");
		final int IdTour = super.getEntityId("tour1");
		final Tour tour = this.tourService.findOne(IdTour);

		final Banner banner = this.bannerService.create();
		banner.setDescription("new description");

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String stringFecha = "2022-09-09";
		final Date fecha = sdf.parse(stringFecha);
		banner.setStartDate(fecha);

		final String stringFechaFinal = "2021-09-09";
		final Date fechaFinal = sdf.parse(stringFechaFinal);
		banner.setEndDate(fechaFinal);

		banner.setImg("https://foto.com");

		banner.setTour(tour);

		this.bannerService.save(banner);
		super.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBannerError2() throws ParseException {
		super.authenticate("owner1");
		final int IdTour = super.getEntityId("tour1");
		final Tour tour = this.tourService.findOne(IdTour);

		final Banner banner = this.bannerService.create();
		banner.setDescription("new description");

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String stringFecha = "2017-09-09";
		final Date fecha = sdf.parse(stringFecha);
		banner.setStartDate(fecha);

		final String stringFechaFinal = "2021-09-09";
		final Date fechaFinal = sdf.parse(stringFechaFinal);
		banner.setEndDate(fechaFinal);

		banner.setImg("https://foto.com");

		banner.setTour(tour);

		this.bannerService.save(banner);
		super.unauthenticate();
	}
	//Requisito 15.3 Un Actor autenticado como Dueño puede editar anuncios para su circo.

	@Test
	public void testEditBannerGood() {
		super.authenticate("owner1");

		final int Idbanner = super.getEntityId("banner1");

		final Banner banner = this.bannerService.findOne(Idbanner);
		banner.setDescription("new description");

		this.bannerService.save(banner);
		super.unauthenticate();
	}

	//Para el caso negativo estamos intentado que un Dueño edite un anuncio  
	//que no pertece a su circo.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "save" comprueba
	// que el circo pertece a ese dueño.
	@Test(expected = IllegalArgumentException.class)
	public void testEditBannerError1() throws ParseException {
		super.authenticate("owner1");
		super.authenticate("owner1");

		final int Idbanner = super.getEntityId("banner3");

		final Banner banner = this.bannerService.findOne(Idbanner);
		banner.setDescription("new description");

		this.bannerService.save(banner);
		super.unauthenticate();
	}
	//Para el caso negativo estamos intentado que un Dueño edite un anuncio  
	//con una fecha en pasado
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "save" comprueba
	// que el circo cumple las restricciones de las fechas.
	@Test(expected = IllegalArgumentException.class)
	public void testEditBannerError2() throws ParseException {
		super.authenticate("owner1");
		super.authenticate("owner1");

		final int Idbanner = super.getEntityId("banner1");

		final Banner banner = this.bannerService.findOne(Idbanner);
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String stringFecha = "2017-09-09";
		final Date fecha = sdf.parse(stringFecha);
		banner.setStartDate(fecha);

		this.bannerService.save(banner);
		super.unauthenticate();
	}
}
