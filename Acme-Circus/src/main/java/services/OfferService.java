
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.OfferRepository;
import domain.Offer;
import forms.OfferForm;

@Service
@Transactional
public class OfferService {

	@Autowired
	private Validator		validator;

	//Managed repository -------------------
	@Autowired
	private OfferRepository	offerRepository;

	//Supporting Services ------------------
	@Autowired
	private ArtistService	artistService;


	//COnstructors -------------------------
	public OfferService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Offer create() {
		Offer result;

		result = new Offer();

		return result;
	}

	public Collection<Offer> findAll() {
		Collection<Offer> result;

		result = this.offerRepository.findAll();

		return result;
	}

	public Offer findOne(final int offerId) {
		Offer result;

		result = this.offerRepository.findOne(offerId);

		return result;
	}

	public void save(final Offer offer) {
		Assert.notNull(offer);

		this.offerRepository.save(offer);
	}

	public void delete(final Offer offer) {
		this.offerRepository.delete(offer);
	}

	public Double acceptedOffersPerArtistRatio() {
		Double a = (double) this.offerRepository.confirmedOffersPerArtistRatio();
		if (a == null)
			a = 0.0;
		Double b = (double) this.artistService.findAll().size();
		if (b == null)
			b = 1.0;
		final Double result = a / b;
		return result;
	}
	public Collection<Offer> findByOrg(final int orgId) {
		return this.offerRepository.findByOrg(orgId);
	}
	//Other Methods--------------------
	public Offer reconstruct(final OfferForm offer, final BindingResult binding) {
		final Offer result = new Offer();
		//		result.setId(offer.getId());
		result.setMoney(offer.getMoney());
		result.setObservations(offer.getObservations());
		result.setPerformance(offer.getPerformance());
		//		result.setVersion(offer.getVersion());
		result.setStatus("PENDING");
		//		final Date a = new Date();
		//		result.setLastUpdate(a);
		return result;

	}
}
