package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.TicketRepository;

import domain.Ticket; 
import domain.Tour;

@Service 
@Transactional 
public class TicketService { 

	//Managed repository -------------------
	@Autowired
	private TicketRepository ticketRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public TicketService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Ticket create(){
		Ticket result;

		result = new Ticket();

		return result;
	}

	public Collection<Ticket> findAll(){
		Collection<Ticket> result;

		result = ticketRepository.findAll();

		return result;
	}

	public Ticket findOne(int ticketId){
		Ticket result;

		result = ticketRepository.findOne(ticketId);

		return result;
	}

	public void save(Ticket ticket){
		Assert.notNull(ticket);

		ticketRepository.save(ticket);
	}

	public void delete(Ticket ticket){
		ticketRepository.delete(ticket);
	}


	//Other Methods--------------------
} 
