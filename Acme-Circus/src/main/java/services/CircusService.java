
package services;

import java.util.ArrayList;
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

	//Supporting Services ------------------

	@Autowired
	private OwnerService			ownerService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private Validator				validator;

	@Autowired
	private FeeService				feeService;


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
		if (circus.getId() != 0) {
			final Integer idO = this.ownerService.findByPrincipal().getId();
			final Owner owner = this.ownerService.findOne(idO);
			Assert.isTrue(owner.getCircus().equals(circus));
		}

		return this.circusRepository.save(circus);
	}

	public Circus saveDeactive(final Circus circus) {
		Assert.notNull(circus);

		this.administratorService.findByPrincipal();

		return this.circusRepository.save(circus);
	}

	public void delete(final Circus circus) {
		this.circusRepository.delete(circus);
	}

	public Collection<Circus> findAllWithTour() {
		final Collection<Circus> circus = this.circusRepository.findAllWithTour();
		final Collection<Circus> res = new ArrayList<>();
		for (final Circus c : circus)
			if (!res.contains(c))
				res.add(c);
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
		Assert.isTrue(circus.getActive().equals(true));
		final Circus res = circus;
		res.setActive(false);
		return res;
	}

	public Collection<Circus> findAllActive() {
		return this.circusRepository.findAllActive();
	}

	//Other Methods--------------------
}
