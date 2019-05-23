
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CircusRepository;
import domain.Circus;

@Service
@Transactional
public class CircusService {

	//Managed repository -------------------
	@Autowired
	private CircusRepository	circusRepository;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public CircusService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Circus create() {
		Circus result;

		result = new Circus();

		return result;
	}

	public Collection<Circus> findAll() {
		Collection<Circus> result;

		result = this.circusRepository.findAll();

		return result;
	}

	public Circus findOne(final int circusId) {
		Circus result;

		result = this.circusRepository.findOne(circusId);

		return result;
	}

	public void save(final Circus circus) {
		Assert.notNull(circus);

		this.circusRepository.save(circus);
	}

	public void delete(final Circus circus) {
		this.circusRepository.delete(circus);
	}

	public Collection<Circus> findAllWithTour() {
		final Collection<Circus> res = this.circusRepository.findAllWithTour();
		return res;
	}

	//Other Methods--------------------
}
