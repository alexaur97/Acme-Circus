
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Circus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class PurchaseServiceTest extends AbstractTest {

	@Autowired
	private PurchaseService	purchaseService;


	//Requisito 18.1 Un Actor autenticado como Asistente comprar tickets.

	@Test
	public void testEditCircusGood() {
		super.authenticate("attendee1");
		Circus circus = this.circusService.findOne(IdCircus);
		circus.setHistory("new History");
		circus = this.circusService.reconstruct(circus, null);
		this.circusService.save(circus);
		super.unauthenticate();
	}

}
