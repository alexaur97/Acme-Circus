
package controllers.owner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArtistService;
import services.OwnerService;
import services.PerformanceService;
import controllers.AbstractController;
import domain.Artist;
import domain.Owner;
import domain.Performance;

@Controller
@RequestMapping("artist/owner")
public class ArtistOwnerController extends AbstractController {

	@Autowired
	private OwnerService		ownerService;

	@Autowired
	private ArtistService		artistService;

	@Autowired
	private PerformanceService	performanceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Owner owner = this.ownerService.findByPrincipal();
			final Collection<Artist> artists = this.artistService.findAll();
			result = new ModelAndView("artist/list");
			result.addObject("requestURI", "artist/owner/list.do");
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
			final Owner owner = this.ownerService.findByPrincipal();
			final Collection<Performance> performances = this.performanceService.findByArtist(artistId);
			result = new ModelAndView("performance/list");
			result.addObject("requestURI", "artist/owner/performances.do");
			result.addObject("performances", performances);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}
}
