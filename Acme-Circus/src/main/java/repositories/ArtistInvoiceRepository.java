
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ArtistInvoice;

@Repository
public interface ArtistInvoiceRepository extends JpaRepository<ArtistInvoice, Integer> {

	@Query("select sum(a.total) from ArtistInvoice a")
	Double totalBenefits();
	//@Query("") 
	//Method 

	@Query("select a from ArtistInvoice a where a.artist.id=?1")
	Collection<ArtistInvoice> findAllByPrincipal(int id);

	@Query("select a from ArtistInvoice a order by a.dateRequested desc")
	Collection<ArtistInvoice> findAllDesc();

}
