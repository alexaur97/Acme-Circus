package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.BannerRepository;

import domain.Banner; 
import domain.CategoryPrice;

@Service 
@Transactional 
public class BannerService { 

	//Managed repository -------------------
	@Autowired
	private BannerRepository bannerRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public BannerService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Banner create(){
		Banner result;

		result = new Banner();

		return result;
	}

	public Collection<Banner> findAll(){
		Collection<Banner> result;

		result = bannerRepository.findAll();

		return result;
	}

	public Banner findOne(int bannerId){
		Banner result;

		result = bannerRepository.findOne(bannerId);

		return result;
	}

	public void save(Banner banner){
		Assert.notNull(banner);

		bannerRepository.save(banner);
	}

	public void delete(Banner banner){
		bannerRepository.delete(banner);
	}


	//Other Methods--------------------
} 
