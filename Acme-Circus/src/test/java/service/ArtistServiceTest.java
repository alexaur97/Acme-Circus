
package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.ArtistService;
import utilities.AbstractTest;
import domain.Artist;
import forms.ArtistRegisterForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ArtistServiceTest extends AbstractTest {

	@Autowired
	private ArtistService	artistService;


	// Este test testea el requisito 12.2  
	// Un actor no autenticado en el sistema debe ser capaz de:
	// Registrarse en el sistema como un artista.

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El usuario rellena los datos obligatorios para crear su cuenta.
	// 2. El artista se crea correctamente

	// Análisis del data coverage:
	// Estamos verificando en nuestro modelo de datos que un usuario puede registrarse como Artist en el sistema
	@Test
	public void testCreateArtistGood() {
		final ArtistRegisterForm form = new ArtistRegisterForm();
		form.setName("ArtistTest");
		form.setSurnames("ArtistTest");
		form.setAddress("");
		form.setPhone("");
		form.setPhoto("");
		form.setDni("12345678Z");
		form.setEmail("ArtistTest@gmail.com");
		form.setUsername("ArtistTest");
		form.setPassword("ArtistTest");
		form.setConfirmPassword("ArtistTest");
		form.setHolderName("ArtistTest");
		form.setBrandName("ArtistTest");
		form.setNumber("5410692363879625");
		form.setExpirationMonth(10);
		form.setExpirationYear(20);
		form.setCvv(255);
		form.setTerms(true);
		final Artist artist = this.artistService.constructByForm(form);
		final Artist saved = this.artistService.save(artist);
	}

	//	Para el caso negativo 1: DNI con formato incorrecto.
	//Esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "constructByForm" comprueba
	// que el dni tenga el formato correcto.
	@Test(expected = IllegalArgumentException.class)
	public void testCreateArtistBad1() {
		final ArtistRegisterForm form = new ArtistRegisterForm();
		form.setName("ArtistTest");
		form.setSurnames("ArtistTest");
		form.setAddress("");
		form.setPhone("");
		form.setPhoto("");
		form.setDni("123Z");
		form.setEmail("ArtistTest@gmail.com");
		form.setUsername("ArtistTest");
		form.setPassword("ArtistTest");
		form.setConfirmPassword("ArtistTest");
		form.setHolderName("ArtistTest");
		form.setBrandName("ArtistTest");
		form.setNumber("5410692363879625");
		form.setExpirationMonth(10);
		form.setExpirationYear(20);
		form.setCvv(255);
		form.setTerms(true);
		final Artist artist = this.artistService.constructByForm(form);
		Assert.isTrue(form.getDni().matches("^[0-9]{8}[A-Z]$"));
		final Artist saved = this.artistService.save(artist);
	}

	//	Para el caso negativo 2: Email en uso.
	//Esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "constructByForm" comprueba
	// que el email no se encuentre en uso.
	@Test(expected = IllegalArgumentException.class)
	public void testCreateArtistBad2() {
		final ArtistRegisterForm form = new ArtistRegisterForm();
		form.setName("ArtistTest");
		form.setSurnames("ArtistTest");
		form.setAddress("");
		form.setPhone("");
		form.setPhoto("");
		form.setDni("12345678Z");
		form.setEmail("admin@admin.com");
		form.setUsername("ArtistTest");
		form.setPassword("ArtistTest");
		form.setConfirmPassword("ArtistTest");
		form.setHolderName("ArtistTest");
		form.setBrandName("ArtistTest");
		form.setNumber("5410692363879625");
		form.setExpirationMonth(10);
		form.setExpirationYear(20);
		form.setCvv(255);
		form.setTerms(true);
		final Artist artist = this.artistService.constructByForm(form);
		final Artist saved = this.artistService.save(artist);
	}
}
