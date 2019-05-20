package repositories;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query; 
import org.springframework.stereotype.Repository; 

import domain.Performance; 

@Repository 
public interface PerformanceRepository extends JpaRepository<Performance, Integer>{ 

	//@Query("") 
	//Method 

} 
