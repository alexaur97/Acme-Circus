
package services;

import java.util.ArrayList;
import java.util.Collection;

import miscellaneous.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AttendeeRepository;
import security.Authority;
import security.UserAccount;
import domain.Attendee;
import domain.CreditCard;
import forms.AttendeeRegisterForm;

@Service
@Transactional
public class AttendeeService {

	//Managed repository -------------------
	@Autowired
	private AttendeeRepository	attendeeRepository;

	//Supporting Services ------------------

	@Autowired
	private ActorService		actorService;


	//COnstructors -------------------------
	public AttendeeService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Attendee create() {
		Attendee result;

		result = new Attendee();
		final UserAccount ua = new UserAccount();

		result.setUserAccount(ua);

		final Authority a = new Authority();
		a.setAuthority(Authority.ATTENDEE);
		final Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(a);
		result.getUserAccount().setAuthorities(authorities);

		final CreditCard creditCard = new CreditCard();
		result.setCreditCard(creditCard);

		return result;

	}

	public Collection<Attendee> findAll() {
		Collection<Attendee> result;

		result = this.attendeeRepository.findAll();

		return result;
	}

	public Attendee findOne(final int attendeeId) {
		Attendee result;

		result = this.attendeeRepository.findOne(attendeeId);

		return result;
	}

	public Attendee save(final Attendee attendee) {
		Assert.notNull(attendee);

		return this.attendeeRepository.save(attendee);
	}

	public void delete(final Attendee attendee) {
		this.attendeeRepository.delete(attendee);
	}

	//Other Methods--------------------

	public Attendee constructByForm(final AttendeeRegisterForm attendeeRegisterForm) {
		Assert.isTrue(attendeeRegisterForm.getPassword().equals(attendeeRegisterForm.getConfirmPassword()));
		final Attendee result = this.create();
		final Collection<String> emails = this.actorService.findAllEmails();
		final String email = attendeeRegisterForm.getEmail();
		final boolean bEmail = !emails.contains(email);
		Assert.isTrue(bEmail);

		final Collection<String> accounts = this.actorService.findAllAccounts();
		final UserAccount userAccount = result.getUserAccount();
		final Boolean bAccount = !accounts.contains(attendeeRegisterForm.getUsername());
		Assert.isTrue(bAccount);

		final Md5PasswordEncoder pe = new Md5PasswordEncoder();
		final String password = pe.encodePassword(attendeeRegisterForm.getPassword(), null);
		userAccount.setPassword(password);
		userAccount.setUsername(attendeeRegisterForm.getUsername());
		result.setUserAccount(userAccount);

		result.setAddress(attendeeRegisterForm.getAddress());
		result.setBornDate(attendeeRegisterForm.getBornDate());

		final CreditCard creditCard = result.getCreditCard();
		creditCard.setBrandName(attendeeRegisterForm.getBrandName());
		creditCard.setCvv(attendeeRegisterForm.getCvv());
		creditCard.setExpirationMonth(attendeeRegisterForm.getExpirationMonth());
		creditCard.setExpirationYear(attendeeRegisterForm.getExpirationYear());
		creditCard.setHolderName(attendeeRegisterForm.getHolderName());
		creditCard.setNumber(attendeeRegisterForm.getNumber());
		final Boolean b = Utils.creditCardIsExpired(creditCard);
		Assert.isTrue(!b);
		result.setCreditCard(creditCard);

		result.setEmail(attendeeRegisterForm.getEmail());
		result.setName(attendeeRegisterForm.getName());
		result.setPhone("+34 " + attendeeRegisterForm.getPhone());
		result.setPhoto(attendeeRegisterForm.getPhoto());
		result.setSurnames(attendeeRegisterForm.getSurnames());

		result.setDni(attendeeRegisterForm.getDni());
		return result;
	}
}
