
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.OwnerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.DomainEntity;
import domain.Owner;

@Service
@Transactional
public class OwnerService {

	//Managed repository -------------------
	@Autowired
	private OwnerRepository	ownerRepository;

	@Autowired
	private ActorService	actorService;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public OwnerService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Owner create() {
		Owner result;

		result = new Owner();

		return result;
	}

	public Collection<Owner> findAll() {
		Collection<Owner> result;

		result = this.ownerRepository.findAll();

		return result;
	}

	public Owner findOne(final int ownerId) {
		Owner result;

		result = this.ownerRepository.findOne(ownerId);

		return result;
	}

	public void save(final Owner owner) {
		Assert.notNull(owner);

		this.ownerRepository.save(owner);
	}

	public void delete(final Owner owner) {
		this.ownerRepository.delete(owner);
	}

	public DomainEntity findByPrincipal() {
		final UserAccount user = LoginService.getPrincipal();
		Assert.notNull(user);

		final Owner o = this.findByUserId(user.getId());
		Assert.notNull(o);
		this.actorService.auth(o, Authority.OWNER);
		return o;
	}

	private Owner findByUserId(final int id) {
		final Owner h = this.ownerRepository.findByUserId(id);
		return h;

	}

	//Other Methods--------------------
}
