
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
import services.OwnerService;
import controllers.AbstractController;
import domain.Owner;
import forms.OwnerRegisterForm;

@Controller
@RequestMapping("/owner")
public class OwnerController extends AbstractController {

	@Autowired
	private OwnerService	ownerService;

	@Autowired
	private ActorService	actorService;


	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			final OwnerRegisterForm ownerRegisterForm = new OwnerRegisterForm();
			result = this.createEditModelAndView(ownerRegisterForm);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}
	protected ModelAndView createEditModelAndView(final OwnerRegisterForm ownerRegisterForm) {
		return this.createEditModelAndView(ownerRegisterForm, null);
	}

	protected ModelAndView createEditModelAndView(final OwnerRegisterForm ownerRegisterForm, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("owner/signup");
		result.addObject("ownerRegisterForm", ownerRegisterForm);
		result.addObject("message", messageCode);

		final Locale l = LocaleContextHolder.getLocale();
		final String lang = l.getLanguage();
		result.addObject("lang", lang);

		return result;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final OwnerRegisterForm ownerRegisterForm, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(ownerRegisterForm);
		else
			try {
				final Owner owner = this.ownerService.constructByForm(ownerRegisterForm);
				final Owner saved = this.ownerService.save(owner);
				System.out.println(saved);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(ownerRegisterForm);

				final Collection<String> accounts = this.actorService.findAllAccounts();
				final Collection<String> emails = this.actorService.findAllEmails();

				if (accounts.contains(ownerRegisterForm.getUsername()))
					result.addObject("message", "owner.username.error");
				else if (emails.contains(ownerRegisterForm.getEmail()))
					result.addObject("message", "owner.email.error");
				else if (!ownerRegisterForm.getConfirmPassword().equals(ownerRegisterForm.getPassword()))
					result.addObject("message", "owner.password.error");
				else if (Utils.creditCardIsExpired(ownerRegisterForm.getExpirationMonth(), ownerRegisterForm.getExpirationYear()))
					result.addObject("message", "owner.expired.card.error");
				else
					result.addObject("message", "owner.commit.error");
			}
		return result;
	}

}
