
package service;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.ActorService;
import services.OwnerService;
import utilities.AbstractTest;
import domain.Actor;
import domain.Owner;
import forms.ActorEditForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ActorServiceTest extends AbstractTest {

	@Autowired
	private ActorService	actorService;

	@Autowired
	private OwnerService	ownerService;


	//Este test testea el requisito 13.1  Un actor autenticado 
	//puede editar sus datos personales

	// Análisis del sentence coverage: 
	// 1. El usuario edita sus datos personales.
	// 2. Los datos editados se guardan correctamente.

	// Análisis del data coverage:

	// Estamos verificando en nuestro modelo de datos que un usuario puede editar sus datos personales

	@Test
	public void testEditActorGood() {
		super.authenticate("owner1");
		final Actor a = this.actorService.findByPrincipal();
		final ActorEditForm actorEditForm = this.actorService.toForm(a);
		actorEditForm.setPhone("123456789");
		final Owner owner = this.ownerService.reconstructEdit(actorEditForm);
		this.ownerService.save(owner);

		super.unauthenticate();
	}

	//	Para el caso negativo 2: Email en uso.
	// Esto debe provocar un error.
	// Análisis del sentence coverage: el sistema al llamar al metodo del servicio "reconstructEdit" comprueba
	// que el email no se encuentre en uso.
	@Test(expected = IllegalArgumentException.class)
	public void testEditActorBad1() {
		super.authenticate("owner1");
		final Actor a = this.actorService.findByPrincipal();
		final ActorEditForm actorEditForm = this.actorService.toForm(a);
		actorEditForm.setEmail("admin@admin.com");
		final Owner owner = this.ownerService.reconstructEdit(actorEditForm);
		Assert.isTrue(!this.actorService.findAllEmails().contains(actorEditForm.getEmail()));
		this.ownerService.save(owner);
		super.unauthenticate();
	}

	//	Para el caso negativo 2: intentamos editar los datos de otro actor.
	// Esto debe provocar un error.
	// Análisis del sentence coverage: el sistema al llamar al metodo del servicio "reconstructEdit" comprueba
	// que el email no se encuentre en uso.
	@Test(expected = IllegalArgumentException.class)
	public void testEditActorBad2() {
		super.authenticate("owner1");
		final Actor a = this.actorService.findByPrincipal();
		super.unauthenticate();
		super.authenticate("owner2");
		final ActorEditForm actorEditForm = this.actorService.toForm(a);
		actorEditForm.setPhone("123456789");
		final Owner owner = this.ownerService.reconstructEdit(actorEditForm);
		this.ownerService.save(owner);
		super.unauthenticate();
	}
}
