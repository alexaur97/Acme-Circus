
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.OfferRepository;
import domain.Artist;
import domain.Offer;
import forms.OfferForm;

@Service
@Transactional
public class OfferService {

	@Autowired
	private Validator			validator;

	//Managed repository -------------------
	@Autowired
	private OfferRepository		offerRepository;

	//Supporting Services ------------------
	@Autowired
	private ArtistService		artistService;
	@Autowired
	private PerformanceService	performance;


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

	public Offer save(final Offer offer) {
		Assert.notNull(offer);

		final Offer result = this.offerRepository.save(offer);
		return result;
	}

	public void delete(final Offer offer) {
		this.offerRepository.delete(offer);
	}

	public Double acceptedOffersPerArtistRatio() {
		Double result;
		Double a = (double) this.offerRepository.confirmedOffersPerArtistRatio();
		if (a == null)
			a = 0.0;
		final Double b = (double) this.artistService.findAll().size();
		if (b == 0.0)
			result = a;
		else
			result = a / b;
		return result;
	}
	public Collection<Offer> findByOrg(final int orgId) {
		return this.offerRepository.findByOrg(orgId);
	}
	public Collection<Offer> findByArt(final int orgId) {
		return this.offerRepository.findByArt(orgId);
	}

	//Other Methods--------------------
	public Offer reconstruct(final OfferForm offer, final BindingResult binding) {
		final Offer result = new Offer();
		result.setMoney(offer.getMoney());
		result.setObservations(offer.getObservations());
		result.setPerformance(this.performance.copy(offer.getPerformance()));
		result.setStatus("PENDING");
		final Date a = new Date();
		result.setLastUpdate(a);
		return result;

	}
	public Offer rejectResticc(final int id) {
		final Artist a = this.artistService.findByPrincipal();
		final Collection<Offer> offers = this.findByArt(a.getId());
		final Offer offer = this.offerRepository.findOne(id);
		Assert.isTrue(offers.contains(offer));
		Assert.isTrue(offer.getStatus().equals("PENDING"));
		offer.setStatus("REJECTED");
		offer.setLastUpdate(new Date());
		return offer;
	}
	public Offer acceptRestricGet(final int id) {
		final Artist a = this.artistService.findByPrincipal();
		final Collection<Offer> offers = this.findByArt(a.getId());
		final Offer offer = this.offerRepository.findOne(id);
		Assert.isTrue(offers.contains(offer));
		Assert.isTrue(offer.getStatus().equals("PENDING"));
		return offer;
	}
	public Offer reconstructArtist(final Offer offer, final BindingResult binding) {
		final Offer result = offer;
		final Offer offerDb = this.findOne(result.getId());
		result.setMoney(offerDb.getMoney());
		result.setObservations(offerDb.getObservations());
		result.setPerformance(offerDb.getPerformance());
		result.setStatus("WAITINGFORCONFIMATION");
		final Date a = new Date();
		result.setLastUpdate(a);
		this.validator.validate(result, binding);

		return result;

	}
}
