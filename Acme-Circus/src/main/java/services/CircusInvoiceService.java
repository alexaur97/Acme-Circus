
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CircusInvoiceRepository;
import domain.CircusInvoice;
import domain.Owner;

@Service
@Transactional
public class CircusInvoiceService {

	//Managed repository -------------------
	@Autowired
	private CircusInvoiceRepository	circusInvoiceRepository;

	@Autowired
	private OwnerService			ownerService;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public CircusInvoiceService() {
		super();
	}

	//Simple CRUD methods--------------------

	public CircusInvoice create() {
		CircusInvoice result;

		result = new CircusInvoice();

		return result;
	}

	public Collection<CircusInvoice> findAll() {
		Collection<CircusInvoice> result;

		result = this.circusInvoiceRepository.findAll();

		return result;
	}

	public CircusInvoice findOne(final int circusInvoiceId) {
		CircusInvoice result;

		result = this.circusInvoiceRepository.findOne(circusInvoiceId);

		return result;
	}

	public void save(final CircusInvoice circusInvoice) {
		Assert.notNull(circusInvoice);

		this.circusInvoiceRepository.save(circusInvoice);
	}

	public void delete(final CircusInvoice circusInvoice) {
		this.circusInvoiceRepository.delete(circusInvoice);
	}

	public Double totalBenefits() {
		Double result = this.circusInvoiceRepository.totalBenefits();
		if (result == null)
			result = 0.0;
		return result;

	}

	public Collection<CircusInvoice> findAllByPrincipal() {
		final Owner principal = this.ownerService.findByPrincipal();
		return this.circusInvoiceRepository.findAllByPrincipal(principal.getId());
	}

	//Other Methods--------------------
}
