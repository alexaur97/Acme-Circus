
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {

	//@Query("") 
	//Method 

	@Query("select w from Worker w where w.userAccount.id = ?1")
	Worker findByUserAccount(int id);

	@Query("select w from Worker w where w.circus.id = ?1")
	Collection<Worker> findWorkersByCircus(int id);

}
