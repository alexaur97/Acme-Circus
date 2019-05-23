
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {

	@Query("select t from Tour t where t.categoryTour.id = ?1")
	Collection<Tour> findAllToursByCategory(int categoryTourId);
}
