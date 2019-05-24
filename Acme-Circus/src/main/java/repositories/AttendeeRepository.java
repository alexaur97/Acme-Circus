
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Attendee;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Integer> {

	@Query("select a from Attendee a where a.userAccount.id=?1")
	Attendee findByUserId(int id);

	//@Query("") 
	//Method 

}
