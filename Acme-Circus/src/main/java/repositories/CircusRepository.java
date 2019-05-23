
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Circus;

@Repository
public interface CircusRepository extends JpaRepository<Circus, Integer> {

	@Query("select t.organizers.circus from Tour t")
	Collection<Circus> findAllWithTour();

	//@Query("") 
	//Method 

}
