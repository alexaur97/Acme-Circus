
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Stop;

@Repository
public interface StopRepository extends JpaRepository<Stop, Integer> {

	@Query("select t.stops from Tour t where t.id = ?1")
	Collection<Stop> findAllStopsByTour(int tourId);

}
