
package services;

import java.util.ArrayList;
import java.util.Collection;

import miscellaneous.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.CreditCard;
import forms.ActorEditForm;
import forms.AdministratorRegisterForm;

@Service
@Transactional
public class AdministratorService {

	// Repositorios propios
	@Autowired
	private AdministratorRepository	administratorRepository;

	@Autowired
	private ActorService			actorService;


	// Servicios ajenos

	// M�todos CRUD

	public Collection<Administrator> findAll() {
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(Authority.ADMINISTRATOR));
		Collection<Administrator> result;
		result = this.administratorRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Administrator findOne(final int administratorId) {
		Assert.isTrue(administratorId != 0);
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(Authority.ADMINISTRATOR));
		Administrator result;
		result = this.administratorRepository.findOne(administratorId);
		return result;
	}
	public void delete(final Administrator administrator) {
		Assert.notNull(administrator);
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(Authority.ADMINISTRATOR));
		this.administratorRepository.delete(administrator);
	}

	public void save(final Administrator administrator) {
		if (administrator.getId() != 0) {
			final Authority auth = new Authority();
			auth.setAuthority(Authority.ADMINISTRATOR);
			Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(auth));
		}
		Assert.notNull(administrator);
		this.administratorRepository.save(administrator);
	}

	// FR 12.1
	public Administrator create() {
		this.findByPrincipal();
		final Administrator result = new Administrator();
		final UserAccount ua = new UserAccount();
		result.setUserAccount(ua);
		final Authority authority = new Authority();
		authority.setAuthority(Authority.ADMINISTRATOR);
		final Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		result.getUserAccount().setAuthorities(authorities);
		final CreditCard creditCard = new CreditCard();
		result.setCreditCard(creditCard);

		return result;

	}

	public Administrator findByPrincipal() {
		final UserAccount user = LoginService.getPrincipal();
		Assert.notNull(user);

		final Administrator a = this.findByUserId(user.getId());
		Assert.notNull(a);
		this.actorService.auth(a, Authority.ADMINISTRATOR);
		return a;
	}

	public Administrator findByUserId(final int id) {
		final Administrator a = this.administratorRepository.findByUserId(id);
		return a;
	}

	public Administrator reconstruct(final AdministratorRegisterForm r) {
		Assert.isTrue(r.getPassword().equals(r.getConfirmPassword()));

		final Collection<String> accounts = this.actorService.findAllAccounts();
		final Administrator result = this.create();
		final UserAccount userAccount = result.getUserAccount();
		final Boolean bAccount = !accounts.contains(userAccount.getUsername());
		Assert.isTrue(bAccount);

		final Collection<String> emails = this.actorService.findAllEmails();
		final String email = r.getEmail();
		final Boolean bEmail = !emails.contains(email);
		Assert.isTrue(bEmail);

		final Md5PasswordEncoder pe = new Md5PasswordEncoder();
		final String password = pe.encodePassword(r.getPassword(), null);

		userAccount.setUsername(r.getUsername());
		userAccount.setPassword(password);

		result.setUserAccount(userAccount);

		result.setName(r.getName());
		result.setDni(r.getDni());
		result.setSurnames(r.getSurnames());
		result.setPhoto(r.getPhoto());
		result.setEmail(r.getEmail());
		if (r.getPhone().isEmpty())
			result.setPhone(r.getPhone());
		else
			result.setPhone("+34 " + r.getPhone());
		result.setAddress(r.getAddress());

		final CreditCard creditCard = result.getCreditCard();
		creditCard.setBrandName(r.getBrandName());
		creditCard.setCvv(r.getCvv());
		creditCard.setExpirationMonth(r.getExpirationMonth());
		creditCard.setExpirationYear(r.getExpirationYear());
		creditCard.setHolderName(r.getHolderName());
		creditCard.setNumber(r.getNumber());
		final Boolean b2 = Utils.creditCardIsExpired(creditCard);
		Assert.isTrue(!b2);
		result.setCreditCard(creditCard);

		return result;
	}

	public Administrator reconstructEdit(final ActorEditForm actorEditForm) {
		final Administrator res;
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
