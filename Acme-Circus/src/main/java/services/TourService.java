
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.TourRepository;
import domain.CategoryPrice;
import domain.Organizer;
import domain.Owner;
import domain.Stop;
import domain.Tour;

@Service
@Transactional
public class TourService {

	//Managed repository -------------------
	@Autowired
	private TourRepository			tourRepository;

	@Autowired
	private StopService				stopService;

	@Autowired
	private OwnerService			ownerService;

	@Autowired
	private OrganizerService		organizerService;

	@Autowired
	private CategoryPriceService	categoryPriceService;

	@Autowired
	private Validator				validator;


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

	public Tour save(final Tour tour) {

		final Date actual = new Date();
		Assert.isTrue(tour.getStartDate().after(actual));
		Assert.isTrue(tour.getStartDate().before(tour.getEndDate()));
		Assert.notNull(tour);

		return this.tourRepository.save(tour);
	}
	public void delete(final Tour tour) {

		final Organizer o = this.organizerService.findByPrincipal();
		final Boolean organizer = tour.getOrganizers().equals(o);
		Assert.isTrue(organizer);

		final Boolean b = tour.getOffers().isEmpty();
		Assert.isTrue(b);
		Assert.isTrue(!tour.validated);
		this.tourRepository.delete(tour);
	}

	public Collection<Tour> findAllToursByCategoryTour(final int categoryTourId) {
		Assert.notNull(categoryTourId);
		final Collection<Tour> res = this.tourRepository.findAllToursByCategory(categoryTourId);
		return res;
	}

	public Collection<Tour> findAllAvailable() {

		final Collection<Tour> tours = this.tourRepository.findAllAvailable();
		final Collection<Tour> res = new ArrayList<>();
		for (final Tour t : tours)
			if (!res.contains(t))
				res.add(t);
		return res;
	}
	public Collection<Tour> findAllAvailableOrg(final int id) {

		final Collection<Tour> tours = this.tourRepository.findAllAvailableOrg(id);
		final Collection<Tour> res = new ArrayList<>();
		for (final Tour t : tours)
			if (!res.contains(t))
				res.add(t);
		return res;
	}
	public Collection<Tour> findAllValidated() {

		final Collection<Tour> res = this.tourRepository.findAvailable();
		return res;
	}

	public Collection<Tour> findAllAvailableByOrganize() {
		final int id = this.organizerService.findByPrincipal().getId();
		final Collection<Tour> res = this.tourRepository.findAllAvailableByOrg(id);
		return res;
	}

	public Collection<Tour> findAllNotAvailableByOrganize() {
		final int id = this.organizerService.findByPrincipal().getId();
		final Collection<Tour> res = this.tourRepository.findAllNotAvailableByOrg(id);
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

	public Collection<Tour> findByCircus(final int id) {
		final Collection<Tour> res = this.tourRepository.findByCircus(id);
		return res;
	}

	public Collection<Tour> findAllByCircus(final int id) {
		final Collection<Tour> res = this.tourRepository.findAllByCircus(id);
		return res;
	}

	public Tour validate(final Tour tour) {

		Assert.isTrue(!tour.getValidated());
		final Owner o = this.ownerService.findByPrincipal();
		Assert.isTrue(tour.getOrganizers().getCircus().equals(o.getCircus()));
		final Tour res = tour;
		Collection<Stop> stopsFromTour = new ArrayList<>();
		//Aqu� tengo todas las paradas que tiene el tour que quiero validar
		stopsFromTour = this.stopService.findStopsByTour(tour.getId());
		//ahora tengo que comprobar que cada una de esas paradas tenga al menos 
		//un category price, ya que si no en la compra no te da a elegir
		// que entrada quieres pagar
		for (final Stop s : stopsFromTour) {
			final Collection<CategoryPrice> cp = this.categoryPriceService.findByStop(s.getId());
			Assert.notEmpty(cp);
		}
		res.setValidated(true);
		return res;
	}

	public Tour reconstruct(final Tour tour, final BindingResult binding) {

		final Organizer o = this.organizerService.findByPrincipal();
		//S� est� validado no podremos editarlo
		final Tour result = tour;
		final Tour t = this.findOne(tour.getId());

		result.setOrganizers(o);

		if (tour.getId() != 0) {
			result.setValidated(t.getValidated());
			result.setPerformances(t.getPerformances());
			result.setOffers(t.getOffers());

		} else
			result.setValidated(false);
		this.validator.validate(result, binding);
		return result;
	}
	public Collection<Tour> findConfirmedAndNotTimeByArt(final int artId, final Date startDate, final Date endDate) {
		return this.tourRepository.findConfirmedAndNotTimeByArt(artId, startDate, endDate);
	}
	public Tour findByOffer(final int id) {
		return this.tourRepository.findByOffer(id);
	}

}
