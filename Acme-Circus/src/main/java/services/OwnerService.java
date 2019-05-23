
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
	public Owner findByPrincipal() {
		final UserAccount user = LoginService.getPrincipal();
		Assert.notNull(user);

		final Owner a = this.findByUserId(user.getId());
		Assert.notNull(a);
		this.actorService.auth(a, Authority.OWNER);
		return a;
	}
	public Owner findByUserId(final int id) {
		final Owner a = this.ownerRepository.findByUserId(id);
		return a;
	}
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

	//Other Methods--------------------
}
