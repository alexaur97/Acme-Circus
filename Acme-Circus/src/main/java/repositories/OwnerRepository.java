
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

	@Query("select o from Owner o where o.userAccount.id=?1")
	Owner findByUserId(int id);

	@Query("select o from Owner o where o.circus.id=?1")
	Collection<Owner> ownersByCircus(int circusId);

	//@Query("")
	//Method

}
