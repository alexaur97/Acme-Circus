
package service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.AttendeeService;
import utilities.AbstractTest;
import domain.Attendee;
import forms.AttendeeRegisterForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class AttendeeServiceTest extends AbstractTest {

	@Autowired
	private AttendeeService	attendeeService;


	// Este test testea el requisito 12.1  
	// Un actor no autenticado en el sistema debe ser capaz de:
	// Registrarse en el sistema como un asistente.

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El usuario rellena los datos obligatorios para crear su cuenta.
	// 2. El asistente se crea correctamente

	// Análisis del data coverage:
	// Estamos verificando en nuestro modelo de datos que un usuario puede registrarse como Attendee en el sistema
	@Test
	public void testCreateAttendeeGood() {
		final AttendeeRegisterForm form = new AttendeeRegisterForm();
		form.setName("AttendeeTest");
		form.setSurnames("AttendeeTest");
		form.setAddress("");
		form.setPhone("");
		form.setPhoto("");
		form.setDni("12345678Z");
		form.setEmail("AttendeeTest@gmail.com");
		form.setUsername("AttendeeTest");
		form.setPassword("AttendeeTest");
		form.setConfirmPassword("AttendeeTest");
		form.setHolderName("AttendeeTest");
		form.setBrandName("AttendeeTest");
		form.setNumber("5410692363879625");
		form.setExpirationMonth(10);
		form.setExpirationYear(20);
		form.setCvv(255);
		form.setTerms(true);
		final Date born = new Date();
		born.setDate(01);
		form.setBornDate(born);
		final Attendee attendee = this.attendeeService.constructByForm(form);
		final Attendee saved = this.attendeeService.save(attendee);
	}

	//	Para el caso negativo 1: Nombre en blanco.
	//Esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "constructByForm" comprueba
	// que el nombre no esté en blanco.
	@Test(expected = IllegalArgumentException.class)
	public void testCreateAttendeeBad1() {
		final AttendeeRegisterForm form = new AttendeeRegisterForm();
		form.setName("");
		form.setSurnames("AttendeeTest");
		form.setAddress("");
		form.setPhone("");
		form.setPhoto("");
		form.setDni("12345678Z");
		form.setEmail("AttendeeTest@gmail.com");
		form.setUsername("AttendeeTest");
		form.setPassword("AttendeeTest");
		form.setConfirmPassword("AttendeeTest");
		form.setHolderName("AttendeeTest");
		form.setBrandName("AttendeeTest");
		form.setNumber("5410692363879625");
		form.setExpirationMonth(10);
		form.setExpirationYear(20);
		form.setCvv(255);
		form.setTerms(true);
		final Date born = new Date();
		born.setDate(01);
		form.setBornDate(born);
		final Attendee attendee = this.attendeeService.constructByForm(form);
		Assert.isTrue(!form.getName().isEmpty());
		final Attendee saved = this.attendeeService.save(attendee);
	}

	//	Para el caso negativo 2: Apellidos en blanco.
	//Esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "constructByForm" comprueba
	// que los apellidos no estén en blanco.
	@Test(expected = IllegalArgumentException.class)
	public void testCreateAttendeeBad2() {
		final AttendeeRegisterForm form = new AttendeeRegisterForm();
		form.setName("AttendeeTest");
		form.setSurnames("");
		form.setAddress("");
		form.setPhone("");
		form.setPhoto("");
		form.setDni("12345678Z");
		form.setEmail("AttendeeTest@gmail.com");
		form.setUsername("AttendeeTest");
		form.setPassword("AttendeeTest");
		form.setConfirmPassword("AttendeeTest");
		form.setHolderName("AttendeeTest");
		form.setBrandName("AttendeeTest");
		form.setNumber("5410692363879625");
		form.setExpirationMonth(10);
		form.setExpirationYear(20);
		form.setCvv(255);
		form.setTerms(true);
		final Date born = new Date();
		born.setDate(01);
		form.setBornDate(born);
		final Attendee attendee = this.attendeeService.constructByForm(form);
		Assert.isTrue(!form.getSurnames().isEmpty());
		final Attendee saved = this.attendeeService.save(attendee);
	}
}
