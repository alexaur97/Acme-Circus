
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BannerRepository;
import domain.Banner;
import domain.Circus;

@Service
@Transactional
public class BannerService {

	//Managed repository -------------------
	@Autowired
	private BannerRepository		bannerRepository;

	//Supporting Services ------------------

	@Autowired
	private BannerInvoiceService	bannerInvoiceService;

	@Autowired
	private OwnerService			ownerService;


	//COnstructors -------------------------
	public BannerService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Banner create() {

		Banner result;

		result = new Banner();

		return result;
	}

	public Collection<Banner> findAll() {
		Collection<Banner> result;

		result = this.bannerRepository.findAll();

		return result;
	}

	public Banner findOne(final int bannerId) {
		Banner result;

		result = this.bannerRepository.findOne(bannerId);

		return result;
	}

	public Banner save(final Banner banner) {
		Assert.notNull(banner);

		final Banner result = this.bannerRepository.save(banner);
		final Date date = new Date();
		Assert.isTrue(banner.getStartDate().before(banner.getEndDate()));
		Assert.isTrue(banner.getStartDate().after(date));

		final Circus circusOwner = this.ownerService.findByPrincipal().getCircus();
		final Circus circusBanner = banner.getTour().getOrganizers().getCircus();
		Assert.isTrue(circusOwner.equals(circusBanner));

		if (banner.getId() == 0)
			this.bannerInvoiceService.generate(result);
		return result;
	}

	public void delete(final Banner banner) {
		this.bannerRepository.delete(banner);
	}

	public Collection<Banner> findByCircus(final int id) {
		final Collection<Banner> res = this.bannerRepository.findByCircus(id);

		return res;
	}

	//Other Methods--------------------
}
