
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ArtistInvoiceRepository;
import domain.Artist;
import domain.ArtistInvoice;
import domain.Offer;

@Service
@Transactional
public class ArtistInvoiceService {

	//Managed repository -------------------
	@Autowired
	private ArtistInvoiceRepository	artistInvoiceRepository;

	//Supporting Services ------------------

	@Autowired
	private ArtistService			artistService;

	@Autowired
	private FeeService				feeService;


	//COnstructors -------------------------
	public ArtistInvoiceService() {
		super();
	}

	//Simple CRUD methods--------------------

	public ArtistInvoice create(final Offer offer) {
		ArtistInvoice result;

		result = new ArtistInvoice();
		final Double acceptedOfferFee = this.feeService.find().getAcceptedOfferFee();
		result.setAcceptedOfferFee(acceptedOfferFee);
		final Artist artist = this.artistService.findByOffer(offer);
		result.setArtist(artist);
		final Date date = new Date();
		result.setDateRequested(date);
		result.setGenerated(false);
		result.setTotal(acceptedOfferFee * offer.getMoney());

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

	public void generate(final Offer result) {
		final ArtistInvoice created = this.create(result);
		this.save(created);

	}

	//Other Methods--------------------
}
