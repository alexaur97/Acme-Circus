
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Performance;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Integer> {

	//@Query("") 
	//Method 
	@Query("select p from Performance p where p.artist.id=?1 and p.copy is false")
	Collection<Performance> findByArtist(int id);
	@Query("select p from Performance p where p.copy is false")
	Collection<Performance> findAllNotCopy();

}
