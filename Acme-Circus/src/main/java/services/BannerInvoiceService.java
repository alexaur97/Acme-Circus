package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.BannerInvoiceRepository;

import domain.Banner;
import domain.BannerInvoice; 

@Service 
@Transactional 
public class BannerInvoiceService { 

	//Managed repository -------------------
	@Autowired
	private BannerInvoiceRepository bannerInvoiceRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public BannerInvoiceService(){
		super();
	}


	//Simple CRUD methods--------------------

	public BannerInvoice create(){
		BannerInvoice result;

		result = new BannerInvoice();

		return result;
	}

	public Collection<BannerInvoice> findAll(){
		Collection<BannerInvoice> result;

		result = bannerInvoiceRepository.findAll();

		return result;
	}

	public BannerInvoice findOne(int bannerInvoiceId){
		BannerInvoice result;

		result = bannerInvoiceRepository.findOne(bannerInvoiceId);

		return result;
	}

	public void save(BannerInvoice bannerInvoice){
		Assert.notNull(bannerInvoice);

		bannerInvoiceRepository.save(bannerInvoice);
	}

	public void delete(BannerInvoice bannerInvoice){
		bannerInvoiceRepository.delete(bannerInvoice);
	}


	//Other Methods--------------------
} 
