package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.AttendeeRepository;

import domain.Attendee; 
import domain.BannerInvoice;

@Service 
@Transactional 
public class AttendeeService { 

	//Managed repository -------------------
	@Autowired
	private AttendeeRepository attendeeRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public AttendeeService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Attendee create(){
		Attendee result;

		result = new Attendee();

		return result;
	}

	public Collection<Attendee> findAll(){
		Collection<Attendee> result;

		result = attendeeRepository.findAll();

		return result;
	}

	public Attendee findOne(int attendeeId){
		Attendee result;

		result = attendeeRepository.findOne(attendeeId);

		return result;
	}

	public void save(Attendee attendee){
		Assert.notNull(attendee);

		attendeeRepository.save(attendee);
	}

	public void delete(Attendee attendee){
		attendeeRepository.delete(attendee);
	}


	//Other Methods--------------------
} 
