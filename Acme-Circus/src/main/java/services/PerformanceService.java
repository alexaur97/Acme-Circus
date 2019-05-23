
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PerformanceRepository;
import domain.Performance;

@Service
@Transactional
public class PerformanceService {

	//Managed repository -------------------
	@Autowired
	private PerformanceRepository	performanceRepository;


	//Supporting Services ------------------

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

	public Performance findOne(final int performanceId) {
		Performance result;

		result = this.performanceRepository.findOne(performanceId);

		return result;
	}

	public void save(final Performance performance) {
		Assert.notNull(performance);

		this.performanceRepository.save(performance);
	}

	public void delete(final Performance performance) {
		this.performanceRepository.delete(performance);
	}

	//Other Methods--------------------
	public Collection<Performance> findByArtist(final int artistId) {
		return this.performanceRepository.findByArtist(artistId);
	}
}
