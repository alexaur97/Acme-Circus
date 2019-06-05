
package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.OrganizerService;
import utilities.AbstractTest;
import domain.Organizer;
import forms.OrganizerRegisterForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class OrganizerServiceTest extends AbstractTest {

	@Autowired
	OrganizerService	organizerService;


	// Este test testea el requisito 12.2  
	// Un actor autenticado como dueño en el sistema debe ser capaz de:
	// Registrarse nuevos organizadores.

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El usuario rellena los datos obligatorios para crear la cuenta.
	// 2. El organizador se crea correctamente

	// Análisis del data coverage:
	// Estamos verificando en nuestro modelo de datos que un dueño puede registrar un nuevo Organizer en el sistema
	@Test
	public void testCreateOrganizerGood() {
		super.authenticate("owner1");
		final OrganizerRegisterForm form = new OrganizerRegisterForm();
		form.setName("OrganizerTest");
		form.setSurnames("OrganizerTest");
		form.setAddress("");
		form.setPhone("");
		form.setPhoto("");
		form.setDni("12345678Z");
		form.setEmail("OrganizerTest@gmail.com");
		form.setUsername("OrganizerTest");
		form.setPassword("OrganizerTest");
		form.setConfirmPassword("OrganizerTest");
		form.setHolderName("OrganizerTest");
		form.setBrandName("OrganizerTest");
		form.setNumber("5410692363879625");
		form.setExpirationMonth(10);
		form.setExpirationYear(20);
		form.setCvv(255);
		form.setTerms(true);
		final Organizer organizer = this.organizerService.constructByForm(form);
		final Organizer saved = this.organizerService.save(organizer);
	}

	//	Para el caso negativo 1: DNI con formato incorrecto.
	//Esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "constructByForm" comprueba
	// que el dni tenga el formato correcto.
	@Test(expected = IllegalArgumentException.class)
	public void testCreateOrganizerBad1() {
		final OrganizerRegisterForm form = new OrganizerRegisterForm();
		form.setName("OrganizerTest");
		form.setSurnames("OrganizerTest");
		form.setAddress("");
		form.setPhone("");
		form.setPhoto("");
		form.setDni("123Z");
		form.setEmail("OrganizerTest@gmail.com");
		form.setUsername("OrganizerTest");
		form.setPassword("OrganizerTest");
		form.setConfirmPassword("OrganizerTest");
		form.setHolderName("OrganizerTest");
		form.setBrandName("OrganizerTest");
		form.setNumber("5410692363879625");
		form.setExpirationMonth(10);
		form.setExpirationYear(20);
		form.setCvv(255);
		form.setTerms(true);
		final Organizer organizer = this.organizerService.constructByForm(form);
		Assert.isTrue(form.getDni().matches("^[0-9]{8}[A-Z]$"));
		final Organizer saved = this.organizerService.save(organizer);
	}

	//	Para el caso negativo 2: Email en uso.
	//Esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "constructByForm" comprueba
	// que el email no se encuentre en uso.
	@Test(expected = IllegalArgumentException.class)
	public void testCreateOrganizerBad2() {
		final OrganizerRegisterForm form = new OrganizerRegisterForm();
		form.setName("OrganizerTest");
		form.setSurnames("OrganizerTest");
		form.setAddress("");
		form.setPhone("");
		form.setPhoto("");
		form.setDni("12345678Z");
		form.setEmail("admin@admin.com");
		form.setUsername("OrganizerTest");
		form.setPassword("OrganizerTest");
		form.setConfirmPassword("OrganizerTest");
		form.setHolderName("OrganizerTest");
		form.setBrandName("OrganizerTest");
		form.setNumber("5410692363879625");
		form.setExpirationMonth(10);
		form.setExpirationYear(20);
		form.setCvv(255);
		form.setTerms(true);
		final Organizer organizer = this.organizerService.constructByForm(form);
		final Organizer saved = this.organizerService.save(organizer);
	}
}
