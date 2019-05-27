
package controllers.owner;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BannerService;
import services.OwnerService;
import services.TourService;
import controllers.AbstractController;
import domain.Banner;
import domain.Circus;
import domain.Owner;
import domain.Tour;

@Controller
@RequestMapping("/banner/owner")
public class BannerOwnerController extends AbstractController {

	@Autowired
	private BannerService	bannerService;

	@Autowired
	private OwnerService	ownerService;

	@Autowired
	private TourService		tourService;


	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView myList() {
		ModelAndView res;
		try {
			final Circus circus = this.ownerService.findByPrincipal().getCircus();

			final Collection<Banner> banners = this.bannerService.findByCircus(circus.getId());

			res = new ModelAndView("banner/myList");
			res.addObject("banners", banners);
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/#");
		}
		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		try {

			final Banner banner = this.bannerService.create();

			res = this.createEditModelAndView(banner);

		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/#");
		}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int bannerId) {
		ModelAndView res;
		try {

			final Banner banner = this.bannerService.findOne(bannerId);
			Assert.notNull(banner);

			final Circus circusOwner = this.ownerService.findByPrincipal().getCircus();
			final Circus circusBanner = banner.getTour().getOrganizers().getCircus();

			Assert.isTrue(circusOwner.equals(circusBanner));

			res = this.createEditModelAndView(banner);

		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/#");
		}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Banner banner, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(banner);
		else
			try {

				final Date date = new Date();
				Assert.isTrue(banner.getStartDate().before(banner.getEndDate()));
				Assert.isTrue(banner.getStartDate().after(date));

				final Circus circusOwner = this.ownerService.findByPrincipal().getCircus();
				final Circus circusBanner = banner.getTour().getOrganizers().getCircus();
				Assert.isTrue(circusOwner.equals(circusBanner));

				this.bannerService.save(banner);
				res = new ModelAndView("redirect:/banner/owner/myList.do");

			} catch (final Throwable oops) {

				if (!banner.getStartDate().before(banner.getEndDate()))
					res = this.createEditModelAndView(banner, "banner.commit.errorEndDate");
				else if (banner.getStartDate().before(new Date()))
					res = this.createEditModelAndView(banner, "banner.commit.errorStartDate");
				else
					res = this.createEditModelAndView(banner, "banner.commit.error");

			}

		return res;
	}

	protected ModelAndView createEditModelAndView(final Banner banner) {
		return this.createEditModelAndView(banner, null);
	}
	protected ModelAndView createEditModelAndView(final Banner banner, final String messageCode) {
		final ModelAndView res;

		final Owner owner = this.ownerService.findByPrincipal();
		final Collection<Tour> tours = this.tourService.findByCircus(owner.getCircus().getId());

		res = new ModelAndView("banner/edit");
		res.addObject("banner", banner);
		res.addObject("tours", tours);
		res.addObject("message", messageCode);

		return res;
	}

}
