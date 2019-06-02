
package services;

import java.util.ArrayList;
import java.util.Collection;

import miscellaneous.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ArtistRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Artist;
import domain.CreditCard;
import domain.Offer;
import forms.ActorEditForm;
import forms.ArtistRegisterForm;

@Service
@Transactional
public class ArtistService {

	//Managed repository -------------------
	@Autowired
	private ArtistRepository	artistRepository;

	//Supporting Services ------------------

	@Autowired
	private ActorService		actorService;


	//COnstructors -------------------------
	public ArtistService() {
		super();
	}

	//Simple CRUD methods--------------------
	public Artist findByPrincipal() {
		final UserAccount user = LoginService.getPrincipal();
		Assert.notNull(user);

		final Artist a = this.findByUserId(user.getId());
		Assert.notNull(a);
		this.actorService.auth(a, Authority.ARTIST);
		return a;
	}
	public Artist findByUserId(final int id) {
		final Artist a = this.artistRepository.findByUserId(id);
		return a;
	}

	public Artist create() {
		Artist result;

		result = new Artist();

		final UserAccount ua = new UserAccount();

		result.setUserAccount(ua);

		final Authority a = new Authority();
		a.setAuthority(Authority.ARTIST);
		final Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(a);
		result.getUserAccount().setAuthorities(authorities);

		final CreditCard creditCard = new CreditCard();
		result.setCreditCard(creditCard);

		return result;
	}

	public Collection<Artist> findAll() {
		Collection<Artist> result;

		result = this.artistRepository.findAll();

		return result;
	}

	public Artist findOne(final int artistId) {
		Artist result;

		result = this.artistRepository.findOne(artistId);

		return result;
	}

	public Artist save(final Artist artist) {
		Assert.notNull(artist);

		return this.artistRepository.save(artist);
	}

	public void delete(final Artist artist) {
		this.artistRepository.delete(artist);
	}

	//Other Methods--------------------

	public Artist constructByForm(final ArtistRegisterForm artisRegisterForm) {
		Assert.isTrue(artisRegisterForm.getPassword().equals(artisRegisterForm.getConfirmPassword()));
		final Artist result = this.create();
		final Collection<String> emails = this.actorService.findAllEmails();
		final String email = artisRegisterForm.getEmail();
		final boolean bEmail = !emails.contains(email);
		Assert.isTrue(bEmail);

		final Collection<String> accounts = this.actorService.findAllAccounts();
		final UserAccount userAccount = result.getUserAccount();
		final Boolean bAccount = !accounts.contains(artisRegisterForm.getUsername());
		Assert.isTrue(bAccount);

		final Md5PasswordEncoder pe = new Md5PasswordEncoder();
		final String password = pe.encodePassword(artisRegisterForm.getPassword(), null);
		userAccount.setPassword(password);
		userAccount.setUsername(artisRegisterForm.getUsername());
		result.setUserAccount(userAccount);

		result.setAddress(artisRegisterForm.getAddress());
		final CreditCard creditCard = result.getCreditCard();
		creditCard.setBrandName(artisRegisterForm.getBrandName());
		creditCard.setCvv(artisRegisterForm.getCvv());
		creditCard.setExpirationMonth(artisRegisterForm.getExpirationMonth());
		creditCard.setExpirationYear(artisRegisterForm.getExpirationYear());
		creditCard.setHolderName(artisRegisterForm.getHolderName());
		creditCard.setNumber(artisRegisterForm.getNumber());
		final Boolean b = Utils.creditCardIsExpired(creditCard);
		Assert.isTrue(!b);
		result.setCreditCard(creditCard);

		result.setEmail(artisRegisterForm.getEmail());
		result.setName(artisRegisterForm.getName());
		if (artisRegisterForm.getPhone().isEmpty())
			result.setPhone(artisRegisterForm.getPhone());
		else
			result.setPhone("+34 " + artisRegisterForm.getPhone());
		result.setPhoto(artisRegisterForm.getPhoto());
		result.setSurnames(artisRegisterForm.getSurnames());

		result.setDni(artisRegisterForm.getDni());
		return result;
	}

	public Artist reconstructEdit(final ActorEditForm actorEditForm) {
		final Artist res;
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

	public Artist findByOffer(final Offer offer) {
		return this.artistRepository.findByOffer(offer.getId());
	}

}
