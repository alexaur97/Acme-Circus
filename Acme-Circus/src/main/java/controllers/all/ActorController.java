
package controllers.all;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdministratorService;
import services.ArtistService;
import services.AttendeeService;
import services.OrganizerService;
import services.OwnerService;
import controllers.AbstractController;
import domain.Actor;
import domain.Administrator;
import domain.Artist;
import domain.Attendee;
import domain.Organizer;
import domain.Owner;
import forms.ActorEditForm;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private AttendeeService			attendeeService;

	@Autowired
	private ArtistService			artistService;

	@Autowired
	private OrganizerService		organizerService;

	@Autowired
	private OwnerService			ownerService;


	@Autowired
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;

		try {
			final Actor actor = this.actorService.findByPrincipal();
			result = new ModelAndView("actor/edit");
			final ActorEditForm actorEditForm = this.actorService.toForm(actor);
			result.addObject("actorEditForm", actorEditForm);
			final Locale l = LocaleContextHolder.getLocale();
			final String lang = l.getLanguage();
			result.addObject("lang", lang);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorEditForm actorEditForm, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = new ModelAndView("actor/edit");
			res.addObject("actorEditForm", actorEditForm);
			final Locale l = LocaleContextHolder.getLocale();
			final String lang = l.getLanguage();
			res.addObject("lang", lang);

		} else
			try {
				final Actor actor = this.actorService.findByPrincipal();
				if (this.actorService.authEdit(actor, "ATTENDEE")) {
					final Attendee attendee = this.attendeeService.reconstructEdit(actorEditForm);
					this.attendeeService.save(attendee);
				} else if (this.actorService.authEdit(actor, "ARTIST")) {
					final Artist artist = this.artistService.reconstructEdit(actorEditForm);
					this.artistService.save(artist);
				} else if (this.actorService.authEdit(actor, "ORGANIZER")) {
					final Organizer organizer = this.organizerService.reconstructEdit(actorEditForm);
					this.organizerService.save(organizer);
				} else if (this.actorService.authEdit(actor, "OWNER")) {
					final Owner owner = this.ownerService.reconstructEdit(actorEditForm);
					this.ownerService.save(owner);
				} else if (this.actorService.authEdit(actor, "ADMINISTRATOR")) {
					final Administrator administrator = this.administratorService.reconstructEdit(actorEditForm);
					this.administratorService.save(administrator);
				}
				res = new ModelAndView("redirect:/#");
			} catch (final Throwable oops) {
				res = new ModelAndView("actor/edit");
				final Locale l = LocaleContextHolder.getLocale();
				final String lang = l.getLanguage();
				res.addObject("lang", lang);

				res.addObject("requestURI", "actor/edit.do");
				res.addObject("message", "actor.commit.error");
			}
		return res;
	}

}
