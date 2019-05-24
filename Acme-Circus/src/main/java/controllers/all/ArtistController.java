
package controllers.all;

import java.util.Collection;
import java.util.Locale;

import javax.validation.Valid;

import miscellaneous.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ArtistService;
import controllers.AbstractController;
import domain.Artist;
import forms.ArtistRegisterForm;

@Controller
@RequestMapping("/artist")
public class ArtistController extends AbstractController {

	@Autowired
	private ArtistService	artistService;

	@Autowired
	private ActorService	actorService;


	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			final ArtistRegisterForm artistRegisterForm = new ArtistRegisterForm();
			result = this.createEditModelAndView(artistRegisterForm);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}
	protected ModelAndView createEditModelAndView(final ArtistRegisterForm artistRegisterForm) {
		return this.createEditModelAndView(artistRegisterForm, null);
	}

	protected ModelAndView createEditModelAndView(final ArtistRegisterForm artistRegisterForm, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("artist/signup");
		result.addObject("artistRegisterForm", artistRegisterForm);
		result.addObject("message", messageCode);

		final Locale l = LocaleContextHolder.getLocale();
		final String lang = l.getLanguage();
		result.addObject("lang", lang);

		return result;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ArtistRegisterForm artistRegisterForm, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(artistRegisterForm);
		else
			try {
				final Artist artist = this.artistService.constructByForm(artistRegisterForm);
				final Artist saved = this.artistService.save(artist);
				System.out.println(saved);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(artistRegisterForm);

				final Collection<String> accounts = this.actorService.findAllAccounts();
				final Collection<String> emails = this.actorService.findAllEmails();

				if (accounts.contains(artistRegisterForm.getUsername()))
					result.addObject("message", "artist.username.error");
				else if (emails.contains(artistRegisterForm.getEmail()))
					result.addObject("message", "artist.email.error");
				else if (!artistRegisterForm.getConfirmPassword().equals(artistRegisterForm.getPassword()))
					result.addObject("message", "artist.password.error");
				else if (Utils.creditCardIsExpired(artistRegisterForm.getExpirationMonth(), artistRegisterForm.getExpirationYear()))
					result.addObject("message", "artist.expired.card.error");
				else
					result.addObject("message", "artist.commit.error");
			}
		return result;
	}

}
