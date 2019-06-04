
package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.OwnerService;
import utilities.AbstractTest;
import domain.Owner;
import forms.OwnerRegisterForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class OwnerServiceTest extends AbstractTest {

	@Autowired
	private OwnerService	ownerService;


	// Este test testea el requisito 12.3  
	// Un actor no autenticado en el sistema debe ser capaz de:
	// Registrarse en el sistema como un dueño acompañado de su circo.

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El usuario rellena los datos obligatorios para crear su cuenta.
	// 2. El dueño y el circo se crean correctamente.

	// Análisis del data coverage:
	// Estamos verificando en nuestro modelo de datos que un usuario puede registrarse como Owner en el sistema
	@Test
	public void testCreateOwnerGood() {
		final OwnerRegisterForm form = new OwnerRegisterForm();
		form.setName("OwnerTest");
		form.setSurnames("OwnerTest");
		form.setAddress("");
		form.setPhone("");
		form.setPhoto("");
		form.setDni("12345678Z");
		form.setEmail("OwnerTest@gmail.com");
		form.setUsername("OwnerTest");
		form.setPassword("OwnerTest");
		form.setConfirmPassword("OwnerTest");
		form.setHolderName("OwnerTest");
		form.setBrandName("OwnerTest");
		form.setNumber("5410692363879625");
		form.setExpirationMonth(10);
		form.setExpirationYear(20);
		form.setCvv(255);
		form.setTerms(true);
		form.setVAT("ESG8941672A");
		form.setCircusName("OwnerTest");
		form.setHistory("OwnerTest");
		final Owner owner = this.ownerService.constructByForm(form);
		final Owner saved = this.ownerService.save(owner);
	}

	//	Para el caso negativo 1: VAT con formato incorrecto.
	//Esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "constructByForm" comprueba
	// que el VAT tenga el formato correcto.
	@Test(expected = IllegalArgumentException.class)
	public void testCreateOwnerBad1() {
		final OwnerRegisterForm form = new OwnerRegisterForm();
		form.setName("OwnerTest");
		form.setSurnames("OwnerTest");
		form.setAddress("");
		form.setPhone("");
		form.setPhoto("");
		form.setDni("12345678Z");
		form.setEmail("OwnerTest@gmail.com");
		form.setUsername("OwnerTest");
		form.setPassword("OwnerTest");
		form.setConfirmPassword("OwnerTest");
		form.setHolderName("OwnerTest");
		form.setBrandName("OwnerTest");
		form.setNumber("5410692363879625");
		form.setExpirationMonth(10);
		form.setExpirationYear(20);
		form.setCvv(255);
		form.setTerms(true);
		form.setVAT("ESG89A");
		form.setCircusName("OwnerTest");
		form.setHistory("OwnerTest");
		Assert.isTrue(form.getVAT().matches("^(ES)?[0-9A-Z][0-9]{7}[0-9A-Z]$"));
		final Owner owner = this.ownerService.constructByForm(form);
		final Owner saved = this.ownerService.save(owner);
	}

	//	Para el caso negativo 2: Nombre del circo en blanco.
	//Esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "constructByForm" comprueba
	// que el email nombre del circo no se encuentre en blanco.
	@Test(expected = IllegalArgumentException.class)
	public void testCreateOwnerBad2() {
		final OwnerRegisterForm form = new OwnerRegisterForm();
		form.setName("OwnerTest");
		form.setSurnames("OwnerTest");
		form.setAddress("");
		form.setPhone("");
		form.setPhoto("");
		form.setDni("12345678Z");
		form.setEmail("OwnerTest@gmail.com");
		form.setUsername("OwnerTest");
		form.setPassword("OwnerTest");
		form.setConfirmPassword("OwnerTest");
		form.setHolderName("OwnerTest");
		form.setBrandName("OwnerTest");
		form.setNumber("5410692363879625");
		form.setExpirationMonth(10);
		form.setExpirationYear(20);
		form.setCvv(255);
		form.setTerms(true);
		form.setVAT("ESG8941672A");
		form.setCircusName("");
		form.setHistory("OwnerTest");
		Assert.isTrue(!form.getCircusName().isEmpty());
		final Owner owner = this.ownerService.constructByForm(form);
		final Owner saved = this.ownerService.save(owner);
	}
}
