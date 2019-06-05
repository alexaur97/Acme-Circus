
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CircusInvoiceRepository;
import domain.Circus;
import domain.CircusInvoice;
import domain.Owner;

@Service
@Transactional
public class CircusInvoiceService {

	//Managed repository -------------------
	@Autowired
	private CircusInvoiceRepository	circusInvoiceRepository;

	//Supporting Services ------------------

	@Autowired
	private OwnerService			ownerService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private CircusService			circusService;

	@Autowired
	private FeeService				feeService;


	//COnstructors -------------------------
	public CircusInvoiceService() {
		super();
	}

	//Simple CRUD methods--------------------

	public CircusInvoice create(final Circus circus) {
		CircusInvoice result;

		result = new CircusInvoice();

		result.setCircus(circus);
		final Double fee = this.feeService.find().getCircusFee();
		result.setCircusFee(fee);
		final Date dateRequested = new Date();
		result.setDateRequested(dateRequested);
		result.setGenerated(false);
		result.setTotal(fee);

		return result;
	}

	public Collection<CircusInvoice> findAll() {
		Collection<CircusInvoice> result;

		result = this.circusInvoiceRepository.findAll();

		return result;
	}

	public CircusInvoice findOne(final int circusInvoiceId) {
		final Collection<CircusInvoice> circusInvoices = this.findAllByPrincipal();

		CircusInvoice result;

		result = this.circusInvoiceRepository.findOne(circusInvoiceId);

		Assert.isTrue(circusInvoices.contains(result));

		return result;
	}
	public CircusInvoice save(final CircusInvoice circusInvoice) {
		Assert.notNull(circusInvoice);

		return this.circusInvoiceRepository.save(circusInvoice);
	}

	public void delete(final CircusInvoice circusInvoice) {
		this.circusInvoiceRepository.delete(circusInvoice);
	}

	public Double totalBenefits() {
		Double result = this.circusInvoiceRepository.totalBenefits();
		if (result == null)
			result = 0.0;
		return result;

	}

	public Collection<CircusInvoice> findAllByPrincipal() {
		final Owner principal = this.ownerService.findByPrincipal();
		return this.circusInvoiceRepository.findAllByPrincipal(principal.getId());
	}

	public Boolean areAlreadyGenerated() {
		Boolean result = false;
		final CircusInvoice last = (CircusInvoice) this.circusInvoiceRepository.findAllDesc().toArray()[0];
		final Date a = last.getDateRequested();
		final Date b = new Date();
		final Calendar invoiceDate = Calendar.getInstance();
		invoiceDate.setTime(a);
		final Calendar now = Calendar.getInstance();
		now.setTime(b);
		if (now.get(Calendar.MONTH) == invoiceDate.get(Calendar.MONTH) && now.get(Calendar.YEAR) == invoiceDate.get(Calendar.YEAR))
			result = true;
		return result;
	}

	public void generateMonthlyInvoices() {
		this.administratorService.findByPrincipal();
		Assert.isTrue(!this.areAlreadyGenerated());

		final Collection<Circus> circus = this.circusService.findAllActive();
		for (final Circus c : circus) {
			final CircusInvoice created = this.create(c);
			this.save(created);
		}
	}
	//Other Methods--------------------

	public Collection<CircusInvoice> findCurrentMonthInvoices() {
		final Collection<CircusInvoice> result = new ArrayList<>();
		final Collection<CircusInvoice> all = this.circusInvoiceRepository.findAllDesc();
		for (final CircusInvoice c : all) {
			final Calendar invoiceDate = Calendar.getInstance();
			invoiceDate.setTime(c.getDateRequested());
			final Calendar now = Calendar.getInstance();
			now.setTime(new Date());
			if (now.get(Calendar.MONTH) == invoiceDate.get(Calendar.MONTH) && now.get(Calendar.YEAR) == invoiceDate.get(Calendar.YEAR))
				result.add(c);
			else
				break;
		}
		return result;
	}

	public Double findCurrentMonthCircusBenefits() {
		final Collection<CircusInvoice> all = this.findCurrentMonthInvoices();
		Double total = 0.0;
		for (final CircusInvoice c : all)
			total = total + c.getTotal();
		return total;
	}

}
