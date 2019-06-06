
package controllers.all;

import java.util.Collection;
import java.util.Date;
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
import services.AttendeeService;
import controllers.AbstractController;
import domain.Attendee;
import forms.AttendeeRegisterForm;

@Controller
@RequestMapping("/attendee")
public class AttendeeController extends AbstractController {

	@Autowired
	private AttendeeService	attendeeService;

	@Autowired
	private ActorService	actorService;


	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			final AttendeeRegisterForm attendeeRegisterForm = new AttendeeRegisterForm();
			result = this.createEditModelAndView(attendeeRegisterForm);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}
	protected ModelAndView createEditModelAndView(final AttendeeRegisterForm attendeeRegisterForm) {
		return this.createEditModelAndView(attendeeRegisterForm, null);
	}

	protected ModelAndView createEditModelAndView(final AttendeeRegisterForm attendeeRegisterForm, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("attendee/signup");
		result.addObject("attendeeRegisterForm", attendeeRegisterForm);
		result.addObject("message", messageCode);

		final Locale l = LocaleContextHolder.getLocale();
		final String lang = l.getLanguage();
		result.addObject("lang", lang);

		return result;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final AttendeeRegisterForm attendeeRegisterForm, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(attendeeRegisterForm);
		else
			try {
				final Attendee attendee = this.attendeeService.constructByForm(attendeeRegisterForm);
				final Attendee saved = this.attendeeService.save(attendee);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(attendeeRegisterForm);

				final Collection<String> accounts = this.actorService.findAllAccounts();
				final Collection<String> emails = this.actorService.findAllEmails();

				if (accounts.contains(attendeeRegisterForm.getUsername()))
					result.addObject("message", "attendee.username.error");
				else if (emails.contains(attendeeRegisterForm.getEmail()))
					result.addObject("message", "attendee.email.error");
				else if (!attendeeRegisterForm.getConfirmPassword().equals(attendeeRegisterForm.getPassword()))
					result.addObject("message", "attendee.password.error");
				else if (Utils.creditCardIsExpired(attendeeRegisterForm.getExpirationMonth(), attendeeRegisterForm.getExpirationYear()))
					result.addObject("message", "attendee.expired.card.error");
				else if (attendeeRegisterForm.getBornDate().after(new Date()))
					result.addObject("message", "attendee.date.error");
				else
					result.addObject("message", "attendee.commit.error");
			}
		return result;
	}

}
