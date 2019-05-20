package repositories;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query; 
import org.springframework.stereotype.Repository; 

import domain.Stop; 

@Repository 
public interface StopRepository extends JpaRepository<Stop, Integer>{ 

	//@Query("") 
	//Method 

} 
