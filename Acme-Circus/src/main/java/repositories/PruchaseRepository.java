
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Purchase;

@Repository
public interface PruchaseRepository extends JpaRepository<Purchase, Integer> {

	@Query("select p from Purchase p where p.attendee.id=?1")
	Collection<Purchase> findByAttendee(int idA);

	//@Query("") 
	//Method 

}
