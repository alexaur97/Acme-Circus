
package controllers.artist;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArtistService;
import services.PerformanceService;
import controllers.AbstractController;
import domain.Artist;
import domain.Performance;

@Controller
@RequestMapping("performance/artist")
public class PerformanceArtistController extends AbstractController {

	@Autowired
	private ArtistService		artistService;

	@Autowired
	private PerformanceService	performanceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView performances() {
		ModelAndView result;
		try {
			final Artist artist = this.artistService.findByPrincipal();
			final Collection<Performance> performances = this.performanceService.findByArtist(artist.getId());
			result = new ModelAndView("performance/list");
			result.addObject("requestURI", "artist/organizer/performances.do");
			result.addObject("performances", performances);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		try {
			this.artistService.findByPrincipal();
			final Performance performance;
			performance = this.performanceService.create();
			result = new ModelAndView("performance/edit");
			result.addObject("performance", performance);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");

		}

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int performanceId) {

		ModelAndView result;

		try {
			final Artist a = this.artistService.findByPrincipal();
			final Performance performance = this.performanceService.findOne(performanceId);
			Assert.isTrue(performance.getArtist().equals(a));
			Assert.isTrue(!performance.getCopy());
			result = new ModelAndView("performance/edit");
			result.addObject("performance", performance);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");

		}

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView save(final Performance performance, final BindingResult binding) {

		ModelAndView result;
		final Performance performanceF = this.performanceService.reconstruct(performance, binding);
		if (binding.hasErrors()) {
			result = new ModelAndView("performance/edit");
			result.addObject("performance", performance);
		} else
			try {
				Assert.isTrue(!(performanceF.getCopy()));
				this.performanceService.save(performanceF);
				result = new ModelAndView("redirect:/performance/artist/list.do");

			} catch (final Throwable oops) {
				result = new ModelAndView("redirect:/#");

			}
		return result;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int performanceId) {

		ModelAndView result;

		try {
			final Artist a = this.artistService.findByPrincipal();
			final Performance performance = this.performanceService.findOne(performanceId);
			Assert.isTrue(performance.getArtist().equals(a));
			Assert.isTrue(!performance.getCopy());
			this.performanceService.delete(performance);
			result = new ModelAndView("redirect:/performance/artist/list.do");

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");

		}

		return result;
	}
}
