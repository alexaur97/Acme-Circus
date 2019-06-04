
package service;

import java.text.ParseException;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.CategoryPriceService;
import services.StopService;
import utilities.AbstractTest;
import domain.CategoryPrice;
import domain.Stop;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class CategoryPriceServiceTest extends AbstractTest {

	@Autowired
	private CategoryPriceService	categoryPriceService;

	@Autowired
	private StopService				stopService;


	//Este test testea el requisito 15.7 Un Actor autenticado como due�o debe poder
	// crear category prices

	// An�lisis del sentence coverage (Pasos que sigue el test en nuestro c�digo): 
	// 1. El due�o se loguea
	// 2. El due�o visualiza el listado de sus categoryPrice y selecciona crear una nueva
	// 3. El due�o crea una nueva category price

	// An�lisis del data coverage (�Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que un due�o puede
	// crear category prices para las paradas de los tours de su circo

	@Test
	public void createCategoryPriceGood() {
		super.authenticate("owner1");
		final CategoryPrice categoryPrice = this.categoryPriceService.create();
		categoryPrice.setAmount(80.0);
		categoryPrice.setCategory("Entrada vip");
		final int idStop = super.getEntityId("stop1");
		final Stop stop = this.stopService.findOne(idStop);
		categoryPrice.setStop(stop);
		this.categoryPriceService.save(categoryPrice);
		super.unauthenticate();

	}

	//Este test testea el requisito 15.7 Solo un actor logueado como due�o puede
	//crear category prices

	// An�lisis del sentence coverage (Pasos que sigue el test en nuestro c�digo): 
	// 1. El organizador se loguea
	// 2. El organizador intenta crear una categoryPrice
	// 3. El sistema no le permite crearla al no ser el due�o de un circo

	// An�lisis del data coverage (�Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que un solo un due�o puede
	// crear category prices para las paradas de los tours de su circo

	@Test(expected = IllegalArgumentException.class)
	public void createCategoryPriceError() throws ParseException {
		super.authenticate("organizer1");
		final CategoryPrice categoryPrice = this.categoryPriceService.create();
		categoryPrice.setAmount(80.0);
		categoryPrice.setCategory("Entrada vip");
		final int idStop = super.getEntityId("stop1");
		final Stop stop = this.stopService.findOne(idStop);
		categoryPrice.setStop(stop);
		this.categoryPriceService.save(categoryPrice);
		super.unauthenticate();

	}

	//Este test testea el requisito 15.7 Un Actor autenticado como due�o debe poder
	// editar category prices

	// An�lisis del sentence coverage (Pasos que sigue el test en nuestro c�digo): 
	// 1. El due�o se loguea
	// 2. El due�o visualiza el listado de sus categoryPrice y selecciona editar una nueva
	// 3. El due�o edita una nueva category price

	// An�lisis del data coverage (�Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que un due�o puede
	// editar category prices para las paradas de los tours de su circo
	@Test
	public void editCategoryPriceGood() {
		super.authenticate("owner1");
		final int idCategoryPrice = super.getEntityId("categoryPrice1");
		CategoryPrice categoryPrice = this.categoryPriceService.findOne(idCategoryPrice);
		categoryPrice.setAmount(100.0);
		categoryPrice = this.categoryPriceService.reconstruct(categoryPrice, null);
		this.categoryPriceService.save(categoryPrice);
		super.unauthenticate();
	}

	//Este test testea el requisito 15.7 Un Actor autenticado como due�o debe poder
	// editar category prices solo de las paradas de las giras de su circo

	// An�lisis del sentence coverage (Pasos que sigue el test en nuestro c�digo): 
	// 1. El due�o se loguea
	// 2. El due�o visualiza el listado de sus categoryPrice y selecciona editar una nueva
	// 3. El due�o intenta editar una category price que no es suya

	// An�lisis del data coverage (�Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que un due�o no puede
	// editar category prices que no sean suyas

	@Test(expected = IllegalArgumentException.class)
	public void editCategoryPriceError() throws ParseException {
		super.authenticate("owner2");
		final int idCategoryPrice = super.getEntityId("categoryPrice1");
		CategoryPrice categoryPrice = this.categoryPriceService.findOne(idCategoryPrice);
		categoryPrice.setAmount(100.0);
		categoryPrice = this.categoryPriceService.reconstruct(categoryPrice, null);
		this.categoryPriceService.save(categoryPrice);
		super.unauthenticate();
	}

	//Este test testea el requisito 15.7 Un Actor autenticado como due�o debe poder
	// eliminar category prices solo de las paradas de las giras de su circo

	// An�lisis del sentence coverage (Pasos que sigue el test en nuestro c�digo): 
	// 1. El due�o se loguea
	// 2. El due�o visualiza el listado de sus categoryPrice y selecciona editar una nueva
	// 3. El due�o elimina una de sus categoryPrice

	// An�lisis del data coverage (�Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que un due�o puede
	// eliminar category prices para las paradas de los tours de su circo

	@Test
	public void deleteCategoryPriceGood() {
		super.authenticate("owner1");
		final int idCategoryPrice = super.getEntityId("categoryPrice1");
		final CategoryPrice categoryPrice = this.categoryPriceService.findOne(idCategoryPrice);
		this.categoryPriceService.delete(categoryPrice);
		super.unauthenticate();
	}

	//Este test testea el requisito 15.7 Un Actor autenticado como due�o debe poder
	// eliminar category prices solo de las paradas de las giras de su circo

	// An�lisis del sentence coverage (Pasos que sigue el test en nuestro c�digo): 
	// 1. El due�o se loguea
	// 2. El due�o visualiza el listado de sus categoryPrice y selecciona editar una nueva
	// 3. El due�o intenta eliminar una categoryPrice que no es suya 

	// An�lisis del data coverage (�Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que un due�o no puede
	// eliminar category prices que no son suyas

	@Test(expected = IllegalArgumentException.class)
	public void deleteCategoryPriceError() throws ParseException {
		super.authenticate("owner2");
		final int idCategoryPrice = super.getEntityId("categoryPrice1");
		final CategoryPrice categoryPrice = this.categoryPriceService.findOne(idCategoryPrice);
		this.categoryPriceService.delete(categoryPrice);
		super.unauthenticate();
	}
}
