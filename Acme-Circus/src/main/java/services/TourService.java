
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TourRepository;
import domain.Stop;
import domain.Tour;

@Service
@Transactional
public class TourService {

	//Managed repository -------------------
	@Autowired
	private TourRepository	tourRepository;

	@Autowired
	private StopService		stopService;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public TourService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Tour create() {
		Tour result;

		result = new Tour();

		return result;
	}

	public Collection<Tour> findAll() {
		Collection<Tour> result;

		result = this.tourRepository.findAll();

		return result;
	}

	public Tour findOne(final int tourId) {
		Tour result;

		result = this.tourRepository.findOne(tourId);

		return result;
	}

	public void save(final Tour tour) {
		Assert.notNull(tour);

		this.tourRepository.save(tour);
	}

	public void delete(final Tour tour) {
		this.tourRepository.delete(tour);
	}

	public Collection<Tour> findAllToursByCategoryTour(final int categoryTourId) {
		Assert.notNull(categoryTourId);
		final Collection<Tour> res = this.tourRepository.findAllToursByCategory(categoryTourId);
		return res;
	}

	//Other Methods--------------------

	public Collection<Tour> searchTours(final String keyword) {

		final Collection<Tour> tours = this.tourRepository.searchToursByKeyWord(keyword);
		final Collection<Tour> res = new ArrayList<>();
		res.addAll(tours);
		final Collection<Stop> stops = this.stopService.findStopsByKeyword(keyword);
		final List<Stop> ls = new ArrayList<>(stops);
		for (int i = 0; i < ls.size(); i++) {
			final Tour t = ls.get(i).getTour();
			res.add(t);
		}
		final Set<Tour> eliminarRepetidos = new HashSet<>();
		eliminarRepetidos.addAll(res);
		res.clear();
		res.addAll(eliminarRepetidos);
		return res;

	}
}
