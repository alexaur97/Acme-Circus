
package service;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.CircusService;
import utilities.AbstractTest;
import domain.Circus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class CircusServiceTest extends AbstractTest {

	@Autowired
	private CircusService	circusService;


	//Requisito 15.2 Un Actor autenticado como Due�o puede editar la informacion de su circo.

	@Test
	public void testEditCircusGood() {
		super.authenticate("owner1");
		final int IdCircus = super.getEntityId("circus1");
		Circus circus = this.circusService.findOne(IdCircus);
		circus.setHistory("new History");
		circus = this.circusService.reconstruct(circus, null);
		this.circusService.save(circus);
		super.unauthenticate();
	}

	//	Para el caso negativo estamos intentando que un Due�o edite la informacion
	// de un circo que no es el suyo
	//An�lisis del sentence coverage: el sistema al llamar al metodo del servicio "save" comprueba
	// que el circo es de ese Due�o.
	@Test(expected = IllegalArgumentException.class)
	public void testEditCircusError1() {
		super.authenticate("owner1");

		final int IdCircus = super.getEntityId("circus2");
		Circus circus = this.circusService.findOne(IdCircus);
		circus.setHistory("new History");
		circus = this.circusService.reconstruct(circus, null);
		this.circusService.save(circus);
		super.unauthenticate();
	}

	//	Para el caso negativo estamos intentando que un Due�o edite la informacion
	// de un circo que no es el suyo
	//An�lisis del sentence coverage: el sistema al llamar al metodo del servicio "reconstruct" comprueba
	// que los atributos del circo cumplen las restricciones del dominio.
	@Test(expected = NullPointerException.class)
	public void testEditCircusError2() {
		super.authenticate("owner1");

		final int IdCircus = super.getEntityId("circus2");
		Circus circus = this.circusService.findOne(IdCircus);
		circus.setVAT("error");
		circus = this.circusService.reconstruct(circus, null);
		this.circusService.save(circus);
		super.unauthenticate();
	}
	//Requisito 19.3 Un Actor autenticado como Administrador puede desactivar un circo que ya no se usa en el sistema.

	@Test
	public void testDeactiveCircusGood() {
		super.authenticate("admin");
		final int IdCircus = super.getEntityId("circus1");
		Circus circus = this.circusService.findOne(IdCircus);
		circus = this.circusService.deactivate(circus);
		this.circusService.saveDeactive(circus);
		super.unauthenticate();
	}
	//Para el caso negativo estamos intentando que un Due�o 
	// desactive un circo.
	//An�lisis del sentence coverage: el sistema al llamar al metodo del servicio "deactive" y "saveDeactive"
	// comprueba que el actor que realice la accion sea un administrador.
	@Test(expected = IllegalArgumentException.class)
	public void testDeactiveCircusError1() {
		super.authenticate("owner1");
		final int IdCircus = super.getEntityId("circus1");
		Circus circus = this.circusService.findOne(IdCircus);
		circus = this.circusService.deactivate(circus);
		this.circusService.saveDeactive(circus);
		super.unauthenticate();
	}
	//Para el caso negativo estamos intentando que un administrador 
	// desactive un circo que no esta activo.
	//An�lisis del sentence coverage: el sistema al llamar al metodo del servicio "deactive" comprueba
	// que el circo este activo.
	@Test(expected = IllegalArgumentException.class)
	public void testDeactiveCircusError2() {
		super.authenticate("admin");
		final int IdCircus = super.getEntityId("circus3");
		Circus circus = this.circusService.findOne(IdCircus);
		circus = this.circusService.deactivate(circus);
		this.circusService.saveDeactive(circus);
		super.unauthenticate();
	}
}
