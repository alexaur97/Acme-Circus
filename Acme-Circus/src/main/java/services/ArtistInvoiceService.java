package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.ArtistInvoiceRepository;

import domain.Artist;
import domain.ArtistInvoice; 

@Service 
@Transactional 
public class ArtistInvoiceService { 

	//Managed repository -------------------
	@Autowired
	private ArtistInvoiceRepository artistInvoiceRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public ArtistInvoiceService(){
		super();
	}


	//Simple CRUD methods--------------------

	public ArtistInvoice create(){
		ArtistInvoice result;

		result = new ArtistInvoice();

		return result;
	}

	public Collection<ArtistInvoice> findAll(){
		Collection<ArtistInvoice> result;

		result = artistInvoiceRepository.findAll();

		return result;
	}

	public ArtistInvoice findOne(int artistInvoiceId){
		ArtistInvoice result;

		result = artistInvoiceRepository.findOne(artistInvoiceId);

		return result;
	}

	public void save(ArtistInvoice artistInvoice){
		Assert.notNull(artistInvoice);

		artistInvoiceRepository.save(artistInvoice);
	}

	public void delete(ArtistInvoice artistInvoice){
		artistInvoiceRepository.delete(artistInvoice);
	}


	//Other Methods--------------------
} 
