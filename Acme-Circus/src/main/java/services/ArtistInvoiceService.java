
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ArtistInvoiceRepository;
import domain.Artist;
import domain.ArtistInvoice;

@Service
@Transactional
public class ArtistInvoiceService {

	//Managed repository -------------------
	@Autowired
	private ArtistInvoiceRepository	artistInvoiceRepository;

	//Supporting Services ------------------

	@Autowired
	private ArtistService			artistService;


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
		final Collection<ArtistInvoice> artistInvoices = this.findAllByPrincipal();

		ArtistInvoice result;

		result = this.artistInvoiceRepository.findOne(artistInvoiceId);

		Assert.isTrue(artistInvoices.contains(result));

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

	public Collection<ArtistInvoice> findAllByPrincipal() {
		final Artist principal = this.artistService.findByPrincipal();
		return this.artistInvoiceRepository.findAllByPrincipal(principal.getId());
	}

	//Other Methods--------------------
}
