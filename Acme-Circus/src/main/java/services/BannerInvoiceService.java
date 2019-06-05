
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BannerInvoiceRepository;
import domain.Banner;
import domain.BannerInvoice;
import domain.Owner;

@Service
@Transactional
public class BannerInvoiceService {

	//Managed repository -------------------
	@Autowired
	private BannerInvoiceRepository	bannerInvoiceRepository;

	//Supporting Services ------------------

	@Autowired
	private OwnerService			ownerService;

	@Autowired
	private FeeService				feeService;


	//COnstructors -------------------------
	public BannerInvoiceService() {
		super();
	}

	//Simple CRUD methods--------------------

	public BannerInvoice create(final Banner banner) {
		BannerInvoice result;

		result = new BannerInvoice();
		result.setBanner(banner);
		final Double bannerFee = this.feeService.find().getBannerFee();
		result.setBannerFee(bannerFee);
		final Date dateRequested = new Date();
		result.setDateRequested(dateRequested);
		result.setGenerated(false);
		result.setTotal(bannerFee);

		return result;
	}

	public Collection<BannerInvoice> findAll() {
		Collection<BannerInvoice> result;

		result = this.bannerInvoiceRepository.findAll();

		return result;
	}

	public BannerInvoice findOne(final int bannerInvoiceId) {
		final Collection<BannerInvoice> bannerInvoices = this.findAllByPrincipal();
		BannerInvoice result;

		result = this.bannerInvoiceRepository.findOne(bannerInvoiceId);
		Assert.isTrue(bannerInvoices.contains(result));

		return result;
	}

	public void save(final BannerInvoice bannerInvoice) {
		Assert.notNull(bannerInvoice);

		this.bannerInvoiceRepository.save(bannerInvoice);
	}

	public void delete(final BannerInvoice bannerInvoice) {
		this.bannerInvoiceRepository.delete(bannerInvoice);
	}

	public Double totalBenefits() {
		Double result = this.bannerInvoiceRepository.totalBenefits();
		if (result == null)
			result = 0.0;
		return result;
	}

	public Collection<BannerInvoice> findAllByPrincipal() {
		final Owner principal = this.ownerService.findByPrincipal();
		return this.bannerInvoiceRepository.findAllByPrincipal(principal.getId());
	}

	public void generate(final Banner banner) {
		final BannerInvoice created = this.create(banner);
		this.save(created);
	}
	//Other Methods--------------------
	public Collection<BannerInvoice> findCurrentMonthInvoices() {
		final Collection<BannerInvoice> result = new ArrayList<>();
		final Collection<BannerInvoice> all = this.bannerInvoiceRepository.findAllDesc();
		for (final BannerInvoice b : all) {
			final Calendar invoiceDate = Calendar.getInstance();
			invoiceDate.setTime(b.getDateRequested());
			final Calendar now = Calendar.getInstance();
			now.setTime(new Date());
			if (now.get(Calendar.MONTH) == invoiceDate.get(Calendar.MONTH) && now.get(Calendar.YEAR) == invoiceDate.get(Calendar.YEAR))
				result.add(b);
			else
				break;
		}
		return result;
	}

	public Double findCurrentMonthBannerBenefits() {
		final Collection<BannerInvoice> all = this.findCurrentMonthInvoices();
		Double total = 0.0;
		for (final BannerInvoice b : all)
			total = total + b.getTotal();
		return total;
	}

}
