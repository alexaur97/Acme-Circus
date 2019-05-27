
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Stop;

@Repository
public interface StopRepository extends JpaRepository<Stop, Integer> {

	@Query("select s from Stop s where  s.spotsAvailable >0 and s.tour.id=?1")
	Collection<Stop> findAllAvailable(int id);

	@Query("select s from Stop s where s.tour.id = ?1")
	Collection<Stop> findAllStopsByTour(int tourId);

	@Query("select s from Stop s where ((s.city like %?1%) or (s.country like %?1%) or (s.location like %?1%))")
	Collection<Stop> searchStopsByKeyWord(String keyword);
}
