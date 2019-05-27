
package services;

import java.util.ArrayList;
import java.util.Collection;

import miscellaneous.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.OwnerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Circus;
import domain.CreditCard;
import domain.Owner;
import forms.ActorEditForm;
import forms.OwnerRegisterForm;

@Service
@Transactional
public class OwnerService {

	//Managed repository -------------------
	@Autowired
	private OwnerRepository	ownerRepository;

	//Supporting Services ------------------

	@Autowired
	private ActorService	actorService;

	@Autowired
	private CircusService	circusService;


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

		final UserAccount ua = new UserAccount();

		result.setUserAccount(ua);

		final Authority a = new Authority();
		a.setAuthority(Authority.OWNER);
		final Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(a);
		result.getUserAccount().setAuthorities(authorities);

		final CreditCard creditCard = new CreditCard();
		result.setCreditCard(creditCard);

		final Circus circus = new Circus();
		result.setCircus(circus);

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

	public Owner save(final Owner owner) {
		Assert.notNull(owner);

		return this.ownerRepository.save(owner);
	}

	public void delete(final Owner owner) {
		this.ownerRepository.delete(owner);
	}

	//Other Methods--------------------

	public Owner constructByForm(final OwnerRegisterForm ownerRegisterForm) {
		Assert.isTrue(ownerRegisterForm.getPassword().equals(ownerRegisterForm.getConfirmPassword()));
		final Owner result = this.create();
		final Collection<String> emails = this.actorService.findAllEmails();
		final String email = ownerRegisterForm.getEmail();
		final boolean bEmail = !emails.contains(email);
		Assert.isTrue(bEmail);

		final Collection<String> accounts = this.actorService.findAllAccounts();
		final UserAccount userAccount = result.getUserAccount();
		final Boolean bAccount = !accounts.contains(ownerRegisterForm.getUsername());
		Assert.isTrue(bAccount);

		final Md5PasswordEncoder pe = new Md5PasswordEncoder();
		final String password = pe.encodePassword(ownerRegisterForm.getPassword(), null);
		userAccount.setPassword(password);
		userAccount.setUsername(ownerRegisterForm.getUsername());
		result.setUserAccount(userAccount);

		result.setAddress(ownerRegisterForm.getAddress());
		final CreditCard creditCard = result.getCreditCard();
		creditCard.setBrandName(ownerRegisterForm.getBrandName());
		creditCard.setCvv(ownerRegisterForm.getCvv());
		creditCard.setExpirationMonth(ownerRegisterForm.getExpirationMonth());
		creditCard.setExpirationYear(ownerRegisterForm.getExpirationYear());
		creditCard.setHolderName(ownerRegisterForm.getHolderName());
		creditCard.setNumber(ownerRegisterForm.getNumber());
		final Boolean b = Utils.creditCardIsExpired(creditCard);
		Assert.isTrue(!b);
		result.setCreditCard(creditCard);

		final Circus circus = result.getCircus();
		circus.setActive(true);
		circus.setHistory(ownerRegisterForm.getHistory());
		circus.setName(ownerRegisterForm.getCircusName());
		circus.setVAT(ownerRegisterForm.getVAT());
		final Circus saved = this.circusService.save(circus);
		result.setCircus(saved);

		result.setEmail(ownerRegisterForm.getEmail());
		result.setName(ownerRegisterForm.getName());
		result.setPhone("+34 " + ownerRegisterForm.getPhone());
		result.setPhoto(ownerRegisterForm.getPhoto());
		result.setSurnames(ownerRegisterForm.getSurnames());

		result.setDni(ownerRegisterForm.getDni());
		return result;
	}

	public Collection<Owner> ownersByCircus(final int circusId) {
		return this.ownerRepository.ownersByCircus(circusId);
	}

	public Owner reconstructEdit(final ActorEditForm actorEditForm) {
		final Owner res;
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
