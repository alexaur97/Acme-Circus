
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

	@Query("select count(o) from Offer o where o.status='CONFIRMED'")
	Integer confirmedOffersPerArtistRatio();

	@Query("select o from Tour t join t.offers o where t.organizers.id=?1")
	Collection<Offer> findByOrg(int orgId);
	@Query("select o from Offer o where o.performance.artist.id=?1")
	Collection<Offer> findByArt(int artId);
	//@Query("") 
	//Method 

	@Query("select o from Offer o where o.performance.artist.id=?1 and o.status='CONFIRMED'")
	Collection<Offer> findConfirmedByPrincipal(int id);

	@Query("select t.offers from Tour t where t.organizers.circus.id=?1")
	Collection<Offer> findByCircus(int circusId);

}
