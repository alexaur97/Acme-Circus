package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.PruchaseRepository;

import domain.Purchase; 
import domain.Stop;

@Service 
@Transactional 
public class PruchaseService { 

	//Managed repository -------------------
	@Autowired
	private PruchaseRepository pruchaseRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public PruchaseService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Purchase create(){
		Purchase result;

		result = new Purchase();

		return result;
	}

	public Collection<Purchase> findAll(){
		Collection<Purchase> result;

		result = pruchaseRepository.findAll();

		return result;
	}

	public Purchase findOne(int pruchaseId){
		Purchase result;

		result = pruchaseRepository.findOne(pruchaseId);

		return result;
	}

	public void save(Purchase pruchase){
		Assert.notNull(pruchase);

		pruchaseRepository.save(pruchase);
	}

	public void delete(Purchase pruchase){
		pruchaseRepository.delete(pruchase);
	}


	//Other Methods--------------------
} 
