
package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import services.FeeService;
import utilities.AbstractTest;
import domain.Fee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class FeeServiceTest extends AbstractTest {

	@Autowired
	private FeeService	feeService;


	// Este test testea el requisito 19.5  

	//	Un administrador podrá editar los 
	//	porcentajes de beneficio de banners, circos 
	//	matriculados y ofertas aceptadas y validadas 
	//	a los artistas.

	// Test positivo:

	@Test
	public void testEditFeeGood() {
		super.authenticate("admin");
		final Fee fee = this.feeService.find();
		fee.setAcceptedOfferFee(0.5);
		fee.setBannerFee(0.5);
		fee.setCircusFee(0.5);
		this.feeService.save(fee);
		super.unauthenticate();
	}

	//	Caso negativo 1: actor no autenticado

	//Esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "save" comprueba
	// que el actor no está autenticado.

	@Test(expected = IllegalArgumentException.class)
	public void testEditFeeNotAuthenticatedError() {
		final Fee fee = this.feeService.find();
		fee.setAcceptedOfferFee(0.5);
		fee.setBannerFee(0.5);
		fee.setCircusFee(0.5);
		this.feeService.save(fee);
	}

	//	Caso negativo 2: actor autenticado como Owner

	//Esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "save" comprueba
	// que el actor no es un administrador.

	@Test(expected = IllegalArgumentException.class)
	public void testEditFeeAuthLikeOwnerError() {
		super.authenticate("owner1");
		final Fee fee = this.feeService.find();
		fee.setAcceptedOfferFee(0.5);
		fee.setBannerFee(0.5);
		fee.setCircusFee(0.5);
		this.feeService.save(fee);
		super.unauthenticate();
	}
}
