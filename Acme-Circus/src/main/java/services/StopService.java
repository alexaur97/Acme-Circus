
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StopRepository;
import domain.Stop;

@Service
@Transactional
public class StopService {

	//Managed repository -------------------
	@Autowired
	private StopRepository	stopRepository;


	//Supporting Services ------------------

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

		this.stopRepository.save(stop);
	}

	public void delete(final Stop stop) {
		this.stopRepository.delete(stop);
	}

	public Collection<Stop> findStopsByTour(final int tourId) {
		Assert.notNull(tourId);
		final Collection<Stop> res = this.stopRepository.findAllStopsByTour(tourId);
		return res;
	}
	//Other Methods--------------------
}
