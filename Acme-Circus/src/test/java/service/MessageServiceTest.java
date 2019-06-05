
package service;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.MessageService;
import services.OwnerService;
import utilities.AbstractTest;
import domain.Message;
import domain.Owner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class MessageServiceTest extends AbstractTest {

	@Autowired
	MessageService	messageService;

	@Autowired
	OwnerService	ownerService;


	// Este test testea el requisito 14.1 
	// Un actor autenticado como trabajador en el sistema debe ser capaz de:
	// Enviar un mensaje al resto de trabajadores de su circo o eliminar alguno de sus mensajes.

	// Análisis del sentence coverage (Pasos que sigue el test en nuestro código): 
	// 1. El usuario rellena los datos para enviar el mensaje.
	// 2. El mensaje se envía correctamente

	// Análisis del data coverage:
	// Estamos verificando en nuestro modelo de datos que un usuario puede registrarse como Artist en el sistema
	@Test
	public void testCreateMessageGood() {
		super.authenticate("owner1");
		final Owner o = this.ownerService.findByPrincipal();
		Message msg;
		msg = new Message();
		msg.setId(0);
		msg.setBody("TestMessage");
		msg.setSubject("TestMessage");
		msg.setRecipient(o);
		final ArrayList<String> tags = new ArrayList<>();
		msg.setTags(tags);
		final Message res = this.messageService.reconstruct(msg, null);
		this.messageService.save(res);
	}

	//	Para el caso negativo 1: enviar email a un trabajador de otro circo.
	// 	Esto debe provocar un error.
	// 	Análisis del sentence coverage: el sistema al llamar al metodo del servicio "reconstruct" comprueba
	// 	que el email sea de un trabajador del mismo circo.

	@Test(expected = IllegalArgumentException.class)
	public void testCreateMessageBad1() {
		super.authenticate("owner2");
		final Owner o = this.ownerService.findByPrincipal();
		super.unauthenticate();
		super.authenticate("owner1");
		Message msg;
		msg = new Message();
		msg.setId(0);
		msg.setBody("TestMessage");
		msg.setSubject("TestMessage");
		msg.setRecipient(o);
		final ArrayList<String> tags = new ArrayList<>();
		msg.setTags(tags);
		final Message res = this.messageService.reconstruct(msg, null);
		this.messageService.save(res);
	}

	//	Para el caso negativo 2: intentamos eliminar un mensaje de otro actor.
	// 	Esto debe provocar un error.
	// 	Análisis del sentence coverage: el sistema al llamar al metodo del servicio "delete" comprueba
	// 	que el mensaje sea del actor logueado.

	@Test(expected = IllegalArgumentException.class)
	public void testCreateMessageBad2() {
		super.authenticate("owner1");
		final int messageId = super.getEntityId("message1");
		Assert.notNull(messageId);
		final Message msg = this.messageService.findOne(messageId);
		Assert.isTrue(msg.getOwner().getId() == this.ownerService.findByPrincipal().getUserAccount().getId());
		if (!msg.getDeleted()) {
			final String tag = "DELETED";
			final Collection<String> tags = msg.getTags();
			tags.add(tag);
			msg.setTags(tags);
			msg.setDeleted(true);
			this.messageService.save(msg);
		} else
			this.messageService.delete(msg);

	}
}
