
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

	//@Query("") 
	//Method 
	@Query("select a from Owner a where a.userAccount.id=?1")
	Owner findByUserId(int id);
}
