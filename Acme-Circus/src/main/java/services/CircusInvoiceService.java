package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.CircusInvoiceRepository;

import domain.Circus;
import domain.CircusInvoice; 

@Service 
@Transactional 
public class CircusInvoiceService { 

	//Managed repository -------------------
	@Autowired
	private CircusInvoiceRepository circusInvoiceRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public CircusInvoiceService(){
		super();
	}


	//Simple CRUD methods--------------------

	public CircusInvoice create(){
		CircusInvoice result;

		result = new CircusInvoice();

		return result;
	}

	public Collection<CircusInvoice> findAll(){
		Collection<CircusInvoice> result;

		result = circusInvoiceRepository.findAll();

		return result;
	}

	public CircusInvoice findOne(int circusInvoiceId){
		CircusInvoice result;

		result = circusInvoiceRepository.findOne(circusInvoiceId);

		return result;
	}

	public void save(CircusInvoice circusInvoice){
		Assert.notNull(circusInvoice);

		circusInvoiceRepository.save(circusInvoice);
	}

	public void delete(CircusInvoice circusInvoice){
		circusInvoiceRepository.delete(circusInvoice);
	}


	//Other Methods--------------------
} 
