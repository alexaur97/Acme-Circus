
package services;

import java.util.ArrayList;
import java.util.Collection;

import miscellaneous.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.OrganizerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.CreditCard;
import domain.Organizer;
import domain.Owner;
import forms.ActorEditForm;
import forms.OrganizerRegisterForm;

@Service
@Transactional
public class OrganizerService {

	//Managed repository -------------------
	@Autowired
	private OrganizerRepository	organizerRepository;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private OwnerService		ownerService;

	@Autowired
	private CircusService		circusService;


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

		final UserAccount ua = new UserAccount();

		result.setUserAccount(ua);

		final Authority a = new Authority();
		a.setAuthority(Authority.ORGANIZER);
		final Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(a);
		final Authority b = new Authority();
		b.setAuthority(Authority.WORKER);
		authorities.add(b);
		result.getUserAccount().setAuthorities(authorities);

		final CreditCard creditCard = new CreditCard();
		result.setCreditCard(creditCard);

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

	public Organizer save(final Organizer organizer) {
		Assert.notNull(organizer);

		return this.organizerRepository.save(organizer);
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
		if (actorEditForm.getPhone().contains("+") || actorEditForm.getPhone().isEmpty())
			res.setPhone(actorEditForm.getPhone());
		else
			res.setPhone("+34 " + actorEditForm.getPhone());

		res.setAddress(actorEditForm.getAddress());
		Assert.notNull(res);
		return res;
	}

	public Collection<Organizer> organizersByPrincipalOwner() {
		final Owner owner = this.ownerService.findByPrincipal();
		return this.organizersByCircus(owner.getCircus().getId());
	}

	public Organizer constructByForm(final OrganizerRegisterForm organizerRegisterForm) {
		Assert.isTrue(organizerRegisterForm.getPassword().equals(organizerRegisterForm.getConfirmPassword()));
		final Organizer result = this.create();
		final Collection<String> emails = this.actorService.findAllEmails();
		final String email = organizerRegisterForm.getEmail();
		final boolean bEmail = !emails.contains(email);
		Assert.isTrue(bEmail);

		final Collection<String> accounts = this.actorService.findAllAccounts();
		final UserAccount userAccount = result.getUserAccount();
		final Boolean bAccount = !accounts.contains(organizerRegisterForm.getUsername());
		Assert.isTrue(bAccount);

		final Md5PasswordEncoder pe = new Md5PasswordEncoder();
		final String password = pe.encodePassword(organizerRegisterForm.getPassword(), null);
		userAccount.setPassword(password);
		userAccount.setUsername(organizerRegisterForm.getUsername());
		result.setUserAccount(userAccount);

		result.setAddress(organizerRegisterForm.getAddress());
		final CreditCard creditCard = result.getCreditCard();
		creditCard.setBrandName(organizerRegisterForm.getBrandName());
		creditCard.setCvv(organizerRegisterForm.getCvv());
		creditCard.setExpirationMonth(organizerRegisterForm.getExpirationMonth());
		creditCard.setExpirationYear(organizerRegisterForm.getExpirationYear());
		creditCard.setHolderName(organizerRegisterForm.getHolderName());
		creditCard.setNumber(organizerRegisterForm.getNumber());
		final Boolean b = Utils.creditCardIsExpired(creditCard);
		Assert.isTrue(!b);
		result.setCreditCard(creditCard);

		result.setEmail(organizerRegisterForm.getEmail());
		result.setName(organizerRegisterForm.getName());
		if (organizerRegisterForm.getPhone().isEmpty())
			result.setPhone(organizerRegisterForm.getPhone());
		else
			result.setPhone("+34 " + organizerRegisterForm.getPhone());
		result.setPhoto(organizerRegisterForm.getPhoto());
		result.setSurnames(organizerRegisterForm.getSurnames());

		result.setDni(organizerRegisterForm.getDni());
		result.setCircus(this.circusService.findByOwner());
		return result;
	}
}
