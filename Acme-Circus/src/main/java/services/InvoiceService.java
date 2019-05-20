package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.InvoiceRepository;

import domain.Invoice; 
import domain.Offer;

@Service 
@Transactional 
public class InvoiceService { 

	//Managed repository -------------------
	@Autowired
	private InvoiceRepository invoiceRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public InvoiceService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Invoice create(){
		Invoice result;

		result = new Invoice();

		return result;
	}

	public Collection<Invoice> findAll(){
		Collection<Invoice> result;

		result = invoiceRepository.findAll();

		return result;
	}

	public Invoice findOne(int invoiceId){
		Invoice result;

		result = invoiceRepository.findOne(invoiceId);

		return result;
	}

	public void save(Invoice invoice){
		Assert.notNull(invoice);

		invoiceRepository.save(invoice);
	}

	public void delete(Invoice invoice){
		invoiceRepository.delete(invoice);
	}


	//Other Methods--------------------
} 
