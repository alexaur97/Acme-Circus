
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import services.ActorService;
import controllers.AbstractController;
import domain.Actor;

@Controller
@RequestMapping("/actor/administrator")
public class ActorAdministratorController extends AbstractController {

	@Autowired
	private ActorService	actorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			result = new ModelAndView("actor/listAdmin");
			final Collection<Actor> attendees = this.actorService.findAllAttendees();
			final Collection<Actor> owners = this.actorService.findAllOwners();
			final Collection<Actor> organizers = this.actorService.findAllOrganizers();
			final Collection<Actor> artists = this.actorService.findAllArtists();
			result.addObject("attendees", attendees);
			result.addObject("owners", owners);
			result.addObject("organizers", organizers);
			result.addObject("artists", artists);
			result.addObject("requestURI", "actor/administrator/list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int userId) {
		ModelAndView result;
		try {
			result = new ModelAndView("actor/showAdmin");
			final Actor actor = this.actorService.findByUserAccount(userId);
			result.addObject("actor", actor);
			final Authority au = new Authority();
			au.setAuthority("ATTENDEE");
			if (actor.getUserAccount().getAuthorities().contains(au))
				result.addObject("isAttendee", 1);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}
}
