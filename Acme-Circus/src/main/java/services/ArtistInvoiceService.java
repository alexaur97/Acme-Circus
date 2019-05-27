
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ArtistInvoiceRepository;
import domain.ArtistInvoice;

@Service
@Transactional
public class ArtistInvoiceService {

	//Managed repository -------------------
	@Autowired
	private ArtistInvoiceRepository	artistInvoiceRepository;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public ArtistInvoiceService() {
		super();
	}

	//Simple CRUD methods--------------------

	public ArtistInvoice create() {
		ArtistInvoice result;

		result = new ArtistInvoice();

		return result;
	}

	public Collection<ArtistInvoice> findAll() {
		Collection<ArtistInvoice> result;

		result = this.artistInvoiceRepository.findAll();

		return result;
	}

	public ArtistInvoice findOne(final int artistInvoiceId) {
		ArtistInvoice result;

		result = this.artistInvoiceRepository.findOne(artistInvoiceId);

		return result;
	}

	public void save(final ArtistInvoice artistInvoice) {
		Assert.notNull(artistInvoice);

		this.artistInvoiceRepository.save(artistInvoice);
	}

	public void delete(final ArtistInvoice artistInvoice) {
		this.artistInvoiceRepository.delete(artistInvoice);
	}

	public Double totalBenefits() {
		Double result = this.artistInvoiceRepository.totalBenefits();
		if (result == null)
			result = 0.0;
		return result;
	}

	//Other Methods--------------------
}
