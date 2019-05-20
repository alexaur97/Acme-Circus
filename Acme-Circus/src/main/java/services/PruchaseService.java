package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.PruchaseRepository;

import domain.Pruchase; 
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

	public Pruchase create(){
		Pruchase result;

		result = new Pruchase();

		return result;
	}

	public Collection<Pruchase> findAll(){
		Collection<Pruchase> result;

		result = pruchaseRepository.findAll();

		return result;
	}

	public Pruchase findOne(int pruchaseId){
		Pruchase result;

		result = pruchaseRepository.findOne(pruchaseId);

		return result;
	}

	public void save(Pruchase pruchase){
		Assert.notNull(pruchase);

		pruchaseRepository.save(pruchase);
	}

	public void delete(Pruchase pruchase){
		pruchaseRepository.delete(pruchase);
	}


	//Other Methods--------------------
} 
