
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BannerRepository;
import domain.Banner;

@Service
@Transactional
public class BannerService {

	//Managed repository -------------------
	@Autowired
	private BannerRepository	bannerRepository;


	//Supporting Services ------------------

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

	public void save(final Banner banner) {
		Assert.notNull(banner);

		this.bannerRepository.save(banner);
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
