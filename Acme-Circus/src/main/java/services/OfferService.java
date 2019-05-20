package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.OfferRepository;

import domain.Offer; 
import domain.Organizer;

@Service 
@Transactional 
public class OfferService { 

	//Managed repository -------------------
	@Autowired
	private OfferRepository offerRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public OfferService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Offer create(){
		Offer result;

		result = new Offer();

		return result;
	}

	public Collection<Offer> findAll(){
		Collection<Offer> result;

		result = offerRepository.findAll();

		return result;
	}

	public Offer findOne(int offerId){
		Offer result;

		result = offerRepository.findOne(offerId);

		return result;
	}

	public void save(Offer offer){
		Assert.notNull(offer);

		offerRepository.save(offer);
	}

	public void delete(Offer offer){
		offerRepository.delete(offer);
	}


	//Other Methods--------------------
} 
