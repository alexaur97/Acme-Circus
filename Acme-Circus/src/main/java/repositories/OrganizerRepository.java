
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Organizer;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {

	//@Query("") 
	//Method 
	@Query("select o from Organizer o where o.userAccount.id=?1")
	Organizer findByUserId(int id);

	@Query("select o from Organizer o where o.circus.id=?1")
	Collection<Organizer> organizersByCircus(int circusId);

}
