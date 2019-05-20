package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.TourRepository;

import domain.Tour; 

@Service 
@Transactional 
public class TourService { 

	//Managed repository -------------------
	@Autowired
	private TourRepository tourRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public TourService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Tour create(){
		Tour result;

		result = new Tour();

		return result;
	}

	public Collection<Tour> findAll(){
		Collection<Tour> result;

		result = tourRepository.findAll();

		return result;
	}

	public Tour findOne(int tourId){
		Tour result;

		result = tourRepository.findOne(tourId);

		return result;
	}

	public void save(Tour tour){
		Assert.notNull(tour);

		tourRepository.save(tour);
	}

	public void delete(Tour tour){
		tourRepository.delete(tour);
	}


	//Other Methods--------------------
} 
