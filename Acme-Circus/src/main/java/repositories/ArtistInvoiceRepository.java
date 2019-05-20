package repositories;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query; 
import org.springframework.stereotype.Repository; 

import domain.ArtistInvoice; 

@Repository 
public interface ArtistInvoiceRepository extends JpaRepository<ArtistInvoice, Integer>{ 

	//@Query("") 
	//Method 

} 
