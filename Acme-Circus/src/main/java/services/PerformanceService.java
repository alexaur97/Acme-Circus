
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PerformanceRepository;
import domain.Artist;
import domain.Performance;

@Service
@Transactional
public class PerformanceService {

	@Autowired
	private Validator				validator;

	//Managed repository -------------------
	@Autowired
	private PerformanceRepository	performanceRepository;

	//Supporting Services ------------------
	@Autowired
	private ArtistService			artistService;
	@Autowired
	private OfferService			offerService;


	//COnstructors -------------------------
	public PerformanceService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Performance create() {
		Performance result;

		result = new Performance();

		return result;
	}

	public Collection<Performance> findAll() {
		Collection<Performance> result;

		result = this.performanceRepository.findAll();

		return result;
	}
	public Collection<Performance> findAllNotCopy() {
		Collection<Performance> result;

		result = this.performanceRepository.findAllNotCopy();

		return result;
	}
	public Performance findOne(final int performanceId) {
		Performance result;

		result = this.performanceRepository.findOne(performanceId);

		return result;
	}

	public Performance save(final Performance performance) {
		Assert.notNull(performance);

		return this.performanceRepository.save(performance);
	}

	public void delete(final Performance performance) {
		Assert.isTrue(!performance.getCopy());
		this.performanceRepository.delete(performance);
	}
	//Other Methods--------------------
	public Collection<Performance> findByArtist(final int artistId) {
		return this.performanceRepository.findByArtist(artistId);
	}
	public Performance reconstruct(final Performance performance, final BindingResult binding) {
		if (performance.getCopy() != null)
			Assert.isTrue(!performance.getCopy());
		final Performance res = new Performance();

		final Artist a = this.artistService.findByPrincipal();

		res.setArtist(a);
		res.setId(performance.getId());
		res.setVersion(performance.getVersion());
		res.setName(performance.getName());
		res.setPersons(performance.getPersons());
		res.setTags(performance.getTags());
		res.setVideo(performance.getVideo());

		if (res.getCopy() == null)
			res.setCopy(false);

		this.validator.validate(res, binding);
		return res;
	}
	public Performance copy(final Performance performance) {
		final Performance copia = new Performance();
		copia.setArtist(performance.getArtist());
		copia.setCopy(true);
		copia.setName(performance.getName());
		copia.setPersons(performance.getPersons());
		copia.setTags(performance.getTags());
		copia.setVideo(performance.getVideo());
		return this.save(copia);
	}
}
