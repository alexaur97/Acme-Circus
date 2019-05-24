
package controllers.organizer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArtistService;
import services.OrganizerService;
import services.PerformanceService;
import controllers.AbstractController;
import domain.Artist;
import domain.Organizer;
import domain.Performance;

@Controller
@RequestMapping("artist/organizer")
public class ArtistOrganizerController extends AbstractController {

	@Autowired
	private ArtistService		artistService;

	@Autowired
	private PerformanceService	performanceService;
	@Autowired
	private OrganizerService	organizerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Organizer organizer = this.organizerService.findByPrincipal();
			final Collection<Artist> artists = this.artistService.findAll();
			result = new ModelAndView("artist/list");
			result.addObject("requestURI", "artist/organizer/list.do");
			result.addObject("artists", artists);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}
	@RequestMapping(value = "/performances", method = RequestMethod.GET)
	public ModelAndView performances(@RequestParam final int artistId) {
		ModelAndView result;
		try {
			final Organizer organizer = this.organizerService.findByPrincipal();
			final Collection<Performance> performances = this.performanceService.findByArtist(artistId);
			result = new ModelAndView("performance/list");
			result.addObject("requestURI", "artist/organizer/performances.do");
			result.addObject("performances", performances);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}
}
