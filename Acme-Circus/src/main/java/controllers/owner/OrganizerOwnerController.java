
package controllers.owner;

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
import services.OrganizerService;
import controllers.AbstractController;
import domain.Organizer;
import forms.OrganizerRegisterForm;

@Controller
@RequestMapping("organizer/owner")
public class OrganizerOwnerController extends AbstractController {

	@Autowired
	private OrganizerService	organizerService;

	@Autowired
	private ActorService		actorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Collection<Organizer> organizers = this.organizerService.organizersByPrincipalOwner();
			result = new ModelAndView("organizer/list");
			result.addObject("requestURI", "organizer/owner/list.do");
			result.addObject("organizers", organizers);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}
		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			final OrganizerRegisterForm organizerRegisterForm = new OrganizerRegisterForm();
			result = this.createEditModelAndView(organizerRegisterForm);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}
	protected ModelAndView createEditModelAndView(final OrganizerRegisterForm organizerRegisterForm) {
		return this.createEditModelAndView(organizerRegisterForm, null);
	}

	protected ModelAndView createEditModelAndView(final OrganizerRegisterForm organizerRegisterForm, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("organizer/create");
		result.addObject("organizerRegisterForm", organizerRegisterForm);
		result.addObject("message", messageCode);

		final Locale l = LocaleContextHolder.getLocale();
		final String lang = l.getLanguage();
		result.addObject("lang", lang);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final OrganizerRegisterForm organizerRegisterForm, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(organizerRegisterForm);
		else
			try {
				final Organizer organizer = this.organizerService.constructByForm(organizerRegisterForm);
				final Organizer saved = this.organizerService.save(organizer);
				result = new ModelAndView("redirect:/organizer/owner/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(organizerRegisterForm);

				final Collection<String> accounts = this.actorService.findAllAccounts();
				final Collection<String> emails = this.actorService.findAllEmails();

				if (accounts.contains(organizerRegisterForm.getUsername()))
					result.addObject("message", "organizer.username.error");
				else if (emails.contains(organizerRegisterForm.getEmail()))
					result.addObject("message", "organizer.email.error");
				else if (!organizerRegisterForm.getConfirmPassword().equals(organizerRegisterForm.getPassword()))
					result.addObject("message", "organizer.password.error");
				else if (Utils.creditCardIsExpired(organizerRegisterForm.getExpirationMonth(), organizerRegisterForm.getExpirationYear()))
					result.addObject("message", "organizer.expired.card.error");
				else
					result.addObject("message", "organizer.commit.error");
			}
		return result;
	}
}
