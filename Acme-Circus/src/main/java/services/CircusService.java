
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CircusRepository;
import domain.Circus;
import domain.Owner;

@Service
@Transactional
public class CircusService {

	//Managed repository -------------------
	@Autowired
	private CircusRepository		circusRepository;

	@Autowired
	private OwnerService			ownerService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private Validator				validator;


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

	public Circus save(final Circus circus) {
		Assert.notNull(circus);

		return this.circusRepository.save(circus);
	}

	public void delete(final Circus circus) {
		this.circusRepository.delete(circus);
	}

	public Collection<Circus> findAllWithTour() {
		final Collection<Circus> res = this.circusRepository.findAllWithTour();
		return res;
	}

	public Circus reconstruct(final Circus circus, final BindingResult binding) {
		final Circus res = circus;
		final Circus c = this.findOne(circus.getId());
		if (circus.getId() != 0)
			res.setActive(c.getActive());

		this.validator.validate(circus, binding);
		return res;
	}

	public Circus findByOwner() {
		final int idO = this.ownerService.findByPrincipal().getId();
		final Owner owner = this.ownerService.findOne(idO);
		final Circus res = owner.getCircus();
		return res;
	}

	public Circus deactivate(final Circus circus) {
		this.administratorService.findByPrincipal();
		final Circus res = circus;
		res.setActive(false);
		return res;
	}

	//Other Methods--------------------
}
