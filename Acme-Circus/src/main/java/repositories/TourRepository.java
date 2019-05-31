
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {

	@Query("select s.tour from Stop s where s.tour.validated = true and s.spotsAvailable>0")
	Collection<Tour> findAllAvailable();
	@Query("select t from Tour t where (t.validated = true and t.organizers.id=?1)")
	Collection<Tour> findAllAvailableByOrg(int id);

	@Query("select t from Tour t where (t.validated = false and t.organizers.id=?1)")
	Collection<Tour> findAllNotAvailableByOrg(int id);

	@Query("select t from Tour t where t.categoryTour.id = ?1")
	Collection<Tour> findAllToursByCategory(int categoryTourId);

	@Query("select t from Tour t where ((t.name like %?1%) or (t.description like %?1%))")
	Collection<Tour> searchToursByKeyWord(String keyword);

	@Query("select t from Tour t where t.organizers.circus.id=?1 and t.validated=true")
	Collection<Tour> findByCircus(int id);
	//	@Query("select t from Tour t join t.offers o where o.performance.artist.id=?1 and o.status='CONFIRMED' and((t.startDate<=?2 and t.endDate>=?3)or(t.startDate<=?2 and t.endDate<=?3 and t.endDate>=?2)or (t.startDate>=?2 and t.startDate<=?3 and t.endDate<=?3 and t.endDate>=?2)or(t.startDate>=?2 and t.startDate<=?3 and t.endDate>=?2))")
	//	Collection<Tour> findConfirmedAndNotTimeByArt(int artId, Date startDate, Date endDate);
	//	
	@Query("select t from Tour t join t.offers o where o.performance.artist.id=?1 and o.status='CONFIRMED' and ((t.startDate between ?2 and ?3) or (t.endDate between ?2 and ?3)or(t.startDate>= ?2 and t.endDate<=CURDATE()))")
	Collection<Tour> findConfirmedAndNotTimeByArt(int artId, Date startDate, Date endDate);

	@Query("select t from Tour t join t.offers o where o.id=?1")
	Tour findByOffer(int artId);

}
