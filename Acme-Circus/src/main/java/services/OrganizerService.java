package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.OrganizerRepository;

import domain.Organizer; 
import domain.Owner;

@Service 
@Transactional 
public class OrganizerService { 

	//Managed repository -------------------
	@Autowired
	private OrganizerRepository organizerRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public OrganizerService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Organizer create(){
		Organizer result;

		result = new Organizer();

		return result;
	}

	public Collection<Organizer> findAll(){
		Collection<Organizer> result;

		result = organizerRepository.findAll();

		return result;
	}

	public Organizer findOne(int organizerId){
		Organizer result;

		result = organizerRepository.findOne(organizerId);

		return result;
	}

	public void save(Organizer organizer){
		Assert.notNull(organizer);

		organizerRepository.save(organizer);
	}

	public void delete(Organizer organizer){
		organizerRepository.delete(organizer);
	}


	//Other Methods--------------------
} 
