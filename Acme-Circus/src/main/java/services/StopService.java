package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.StopRepository;

import domain.Stop; 
import domain.Ticket;

@Service 
@Transactional 
public class StopService { 

	//Managed repository -------------------
	@Autowired
	private StopRepository stopRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public StopService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Stop create(){
		Stop result;

		result = new Stop();

		return result;
	}

	public Collection<Stop> findAll(){
		Collection<Stop> result;

		result = stopRepository.findAll();

		return result;
	}

	public Stop findOne(int stopId){
		Stop result;

		result = stopRepository.findOne(stopId);

		return result;
	}

	public void save(Stop stop){
		Assert.notNull(stop);

		stopRepository.save(stop);
	}

	public void delete(Stop stop){
		stopRepository.delete(stop);
	}


	//Other Methods--------------------
} 
