
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.OrganizerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Organizer;
import forms.ActorEditForm;

@Service
@Transactional
public class OrganizerService {

	//Managed repository -------------------
	@Autowired
	private OrganizerRepository	organizerRepository;
	@Autowired
	private ActorService		actorService;


	//Supporting Services ------------------
	public Organizer findByPrincipal() {
		final UserAccount user = LoginService.getPrincipal();
		Assert.notNull(user);

		final Organizer a = this.findByUserId(user.getId());
		Assert.notNull(a);
		this.actorService.auth(a, Authority.ORGANIZER);
		return a;
	}
	public Organizer findByUserId(final int id) {
		final Organizer a = this.organizerRepository.findByUserId(id);
		return a;
	}

	//COnstructors -------------------------
	public OrganizerService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Organizer create() {
		Organizer result;

		result = new Organizer();

		return result;
	}

	public Collection<Organizer> findAll() {
		Collection<Organizer> result;

		result = this.organizerRepository.findAll();

		return result;
	}

	public Organizer findOne(final int organizerId) {
		Organizer result;

		result = this.organizerRepository.findOne(organizerId);

		return result;
	}

	public void save(final Organizer organizer) {
		Assert.notNull(organizer);

		this.organizerRepository.save(organizer);
	}

	public void delete(final Organizer organizer) {
		this.organizerRepository.delete(organizer);
	}
	public Collection<Organizer> organizersByCircus(final int circusId) {
		return this.organizerRepository.organizersByCircus(circusId);
	}

	//Other Methods--------------------

	public Organizer reconstructEdit(final ActorEditForm actorEditForm) {
		final Organizer res;
		res = this.findByPrincipal();
		res.setName(actorEditForm.getName());
		res.setDni(actorEditForm.getDni());
		res.setSurnames(actorEditForm.getSurnames());
		res.setPhoto(actorEditForm.getPhoto());
		res.setEmail(actorEditForm.getEmail());
		if (actorEditForm.getPhone().contains("+"))
			res.setPhone(actorEditForm.getPhone());
		else
			res.setPhone("+34 " + actorEditForm.getPhone());

		res.setAddress(actorEditForm.getAddress());
		Assert.notNull(res);
		return res;
	}
}
