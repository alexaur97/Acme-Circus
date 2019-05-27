
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

	//@Query("") 
	//Method 
	@Query("select o from Artist o where o.userAccount.id=?1")
	Artist findByUserId(int id);

}
