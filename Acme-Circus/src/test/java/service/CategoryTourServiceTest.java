
package service;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.CategoryTourService;
import services.StopService;
import utilities.AbstractTest;
import domain.CategoryTour;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class CategoryTourServiceTest extends AbstractTest {

	@Autowired
	private CategoryTourService	categoryTourService;

	@Autowired
	private StopService			stopService;


	//  Este test testea el requisito 19.2: 

	// Un usuario autenticado como administrador debe ser capaz de 
	// registrar nuevas categorías para las giras y modificar las existentes en caso de
	// no estar siendo usadas.

	// Caso positivo 1: crear categoría

	@Test
	public void createCategoryTourGood() {
		super.authenticate("admin");
		final CategoryTour categoryTour = this.categoryTourService.create();
		categoryTour.setMinAge(10);
		categoryTour.setName("Name");
		this.categoryTourService.save(categoryTour);
		super.unauthenticate();

	}

	//	Para el caso negativo 1: actor no autenticado como administrador
	//Esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "create" comprueba
	// que el actor autenticado no es administrador.

	@Test(expected = IllegalArgumentException.class)
	public void createCategoryTourNotAdmin() {
		super.authenticate("owner1");
		final CategoryTour categoryTour = this.categoryTourService.create();
		categoryTour.setMinAge(10);
		categoryTour.setName("Name");
		this.categoryTourService.save(categoryTour);
		super.unauthenticate();

	}

	//  Caso positivo 2: Editar categoría existente que no esté en uso.

	@Test
	public void editCategoryTourGood() {
		super.authenticate("admin");
		final int id = super.getEntityId("categoryTour3");
		final CategoryTour categoryTour = this.categoryTourService.findOne(id);
		categoryTour.setMinAge(10);
		categoryTour.setName("Name");
		this.categoryTourService.save(categoryTour);
		super.unauthenticate();

	}

	//	Para el caso negativo 2: categoría ya usada
	//Esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "save" comprueba
	// que la categoría está en uso.

	@Test(expected = IllegalArgumentException.class)
	public void editUsedCategoryTour() {
		super.authenticate("admin");
		final int id = super.getEntityId("categoryTour1");
		final CategoryTour categoryTour = this.categoryTourService.findOne(id);
		categoryTour.setMinAge(10);
		categoryTour.setName("Name");
		this.categoryTourService.save(categoryTour);
		super.unauthenticate();

	}

}
