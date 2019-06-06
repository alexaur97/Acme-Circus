
package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import services.CircusInvoiceService;
import services.CircusService;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class CircusInvoiceServiceTest extends AbstractTest {

	@Autowired
	private CircusInvoiceService	circusInvoiceService;

	@Autowired
	private CircusService			circusService;


	// Este test testea el requisito 19.6  

	//	Un administrador podrá generar las facturas del mes actual de los circos activos en el sistema,
	//	dicha factura podrá generarse una vez cuando el administrador 
	//	requiera el pago de la misma.

	@Test
	public void testGenerateCircusInvoiceGood() {
		super.authenticate("admin");
		this.circusInvoiceService.generateMonthlyInvoices();
	}

	//	Caso negativo 1: actor no autenticado

	//Esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "generateMonthlyInvoices" comprueba
	// que el actor no está autenticado

	@Test(expected = IllegalArgumentException.class)
	public void testGenerateCircusInvoiceBad1() {
		this.circusInvoiceService.generateMonthlyInvoices();
	}

	//	Caso negativo 2: Email en uso.

	//Esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "generateMonthlyInvoices" por segunda vez, comprueba
	// que las facturas ya fueron generadas este mes.

	@Test(expected = IllegalArgumentException.class)
	public void testGenerateCircusInvoiceBad2() {
		super.authenticate("admin");
		this.circusInvoiceService.generateMonthlyInvoices();
		this.circusInvoiceService.generateMonthlyInvoices();
	}

}
