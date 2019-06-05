
package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ArtistInvoiceService;
import services.ArtistService;
import services.AttendeeService;
import services.BannerInvoiceService;
import services.CircusInvoiceService;
import services.CircusService;
import services.OfferService;
import services.OrganizerService;
import services.OwnerService;
import services.StopService;
import controllers.AbstractController;
import domain.Attendee;
import domain.Circus;

@Controller
@RequestMapping("/stats/administrator")
public class StatsAdministratorController extends AbstractController {

	@Autowired
	private AttendeeService			attendeeService;

	@Autowired
	private CircusService			circusService;

	@Autowired
	private OwnerService			ownerService;

	@Autowired
	private OrganizerService		organizerService;

	@Autowired
	private ArtistService			artistService;

	@Autowired
	private BannerInvoiceService	bannerInvoiceService;

	@Autowired
	private CircusInvoiceService	circusInvoiceService;

	@Autowired
	private ArtistInvoiceService	artistInvoiceService;

	@Autowired
	private OfferService			offerService;

	@Autowired
	private StopService				stopService;


	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView result;
		try {
			final Integer attendeeNum = this.attendeeService.findAll().size();
			final Collection<Circus> circus = this.circusService.findAll();
			final Integer circusNum = circus.size();
			final Collection<Integer> ownersNum = new ArrayList<>();
			final Collection<Integer> organizersNum = new ArrayList<>();

			for (final Circus c : circus) {
				final Integer owners = this.ownerService.ownersByCircus(c.getId()).size();
				ownersNum.add(owners);
				final Integer organizers = this.organizerService.organizersByCircus(c.getId()).size();
				organizersNum.add(organizers);
			}

			final Integer artistNum = this.artistService.findAll().size();
			final Double circusEarning = this.circusInvoiceService.findCurrentMonthCircusBenefits();
			final Double bannerEarning = this.bannerInvoiceService.findCurrentMonthBannerBenefits();
			final Double artistEarning = this.artistInvoiceService.findCurrentMonthArtistBenefits();
			final Double totalBenefit = circusEarning + bannerEarning + artistEarning;
			final Double ratio1 = this.offerService.acceptedOffersPerArtistRatio();
			final Double ratio2 = this.stopService.stopsPerTour();
			final Integer totalOffers = this.offerService.findAll().size();
			final Attendee mostSpender = this.attendeeService.mostSpender();
			final Integer totalOwners = this.ownerService.findAll().size();
			final Integer totalOrganizers = this.organizerService.findAll().size();
			final Integer workersNum = totalOwners + totalOrganizers;

			result = new ModelAndView("stats/dashboard");

			result.addObject("attendeeNum", attendeeNum);
			result.addObject("circusNum", circusNum);
			result.addObject("circus", circus);
			result.addObject("ownersNum", ownersNum);
			result.addObject("organizersNum", organizersNum);
			result.addObject("totalOwners", totalOwners);
			result.addObject("totalOrganizers", totalOrganizers);
			result.addObject("artistNum", artistNum);
			result.addObject("circusEarning", circusEarning);
			result.addObject("bannerEarning", bannerEarning);
			result.addObject("artistEarning", artistEarning);
			result.addObject("totalBenefit", totalBenefit);
			result.addObject("ratio1", ratio1);
			result.addObject("ratio2", ratio2);
			result.addObject("totalOffers", totalOffers);
			result.addObject("mostSpender", mostSpender);
			result.addObject("workersNum", workersNum);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

}
