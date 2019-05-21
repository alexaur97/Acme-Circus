package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.PerformanceRepository;

import domain.Performance; 
import domain.Purchase;

@Service 
@Transactional 
public class PerformanceService { 

	//Managed repository -------------------
	@Autowired
	private PerformanceRepository performanceRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public PerformanceService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Performance create(){
		Performance result;

		result = new Performance();

		return result;
	}

	public Collection<Performance> findAll(){
		Collection<Performance> result;

		result = performanceRepository.findAll();

		return result;
	}

	public Performance findOne(int performanceId){
		Performance result;

		result = performanceRepository.findOne(performanceId);

		return result;
	}

	public void save(Performance performance){
		Assert.notNull(performance);

		performanceRepository.save(performance);
	}

	public void delete(Performance performance){
		performanceRepository.delete(performance);
	}


	//Other Methods--------------------
} 
