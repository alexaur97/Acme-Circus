
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Stop;

@Repository
public interface StopRepository extends JpaRepository<Stop, Integer> {

	@Query("select s from Tour t join t.stops s where  s.spotsAvailable >0 and t.id=?1")
	Collection<Stop> findAllAvailable(int id);

	//@Query("") 
	//Method 

}
