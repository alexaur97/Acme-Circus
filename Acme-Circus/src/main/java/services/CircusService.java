package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.CircusRepository;

import domain.Circus; 
import domain.Fee;

@Service 
@Transactional 
public class CircusService { 

	//Managed repository -------------------
	@Autowired
	private CircusRepository circusRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public CircusService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Circus create(){
		Circus result;

		result = new Circus();

		return result;
	}

	public Collection<Circus> findAll(){
		Collection<Circus> result;

		result = circusRepository.findAll();

		return result;
	}

	public Circus findOne(int circusId){
		Circus result;

		result = circusRepository.findOne(circusId);

		return result;
	}

	public void save(Circus circus){
		Assert.notNull(circus);

		circusRepository.save(circus);
	}

	public void delete(Circus circus){
		circusRepository.delete(circus);
	}


	//Other Methods--------------------
} 
