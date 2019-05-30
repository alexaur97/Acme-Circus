
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.StopRepository;
import domain.Organizer;
import domain.Stop;

@Service
@Transactional
public class StopService {

	//Managed repository -------------------
	@Autowired
	private StopRepository		stopRepository;

	//Supporting Services ------------------
	@Autowired
	private TourService			tourService;

	@Autowired
	private OrganizerService	organizerService;

	@Autowired
	private Validator			validator;


	//COnstructors -------------------------
	public StopService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Stop create() {
		Stop result;

		result = new Stop();

		return result;
	}

	public Collection<Stop> findAll() {
		Collection<Stop> result;

		result = this.stopRepository.findAll();

		return result;
	}

	public Stop findOne(final int stopId) {
		Stop result;

		result = this.stopRepository.findOne(stopId);

		return result;
	}

	public void save(final Stop stop) {
		Assert.notNull(stop);

		final Organizer o = this.organizerService.findByPrincipal();
		final Boolean b = stop.getTour().getOrganizers().equals(o);
		Assert.isTrue(b);

		Assert.isTrue(!stop.getTour().validated);

		this.stopRepository.save(stop);
	}

	public void delete(final Stop stop) {

		Assert.notNull(stop);
		final Organizer o = this.organizerService.findByPrincipal();
		final Boolean b = stop.getTour().getOrganizers().equals(o);
		Assert.isTrue(b);
		Assert.isTrue(!stop.getTour().validated);

		this.stopRepository.delete(stop);
	}

	public Collection<Stop> findStopsByTour(final int tourId) {
		Assert.notNull(tourId);
		final Collection<Stop> res = this.stopRepository.findAllStopsByTour(tourId);
		return res;
	}

	public Collection<Stop> findStopsByKeyword(final String keyword) {
		return this.stopRepository.searchStopsByKeyWord(keyword);

	}
	public Collection<Stop> findAllAvailable(final int tourId) {

		final Collection<Stop> res = this.stopRepository.findAllAvailable(tourId);

		return res;
	}

	public Collection<Stop> findAllStopsByCircus(final int circusId) {

		final Collection<Stop> res = this.stopRepository.findAllStopsByCircus(circusId);

		return res;
	}

	public Double stopsPerTour() {
		Double result;
		final Double a = (double) this.stopRepository.findAll().size();
		final Double b = (double) this.tourService.findAll().size();
		if (b == 0.0)
			result = a;
		else
			result = a / b;
		return result;
	}

	public Stop reconstruct(final Stop stop, final BindingResult binding) {

		final Stop result = stop;
		this.validator.validate(result, binding);
		return result;

	}

	//Other Methods--------------------
}
