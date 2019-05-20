package repositories;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query; 
import org.springframework.stereotype.Repository; 

import domain.CategoryPrice; 

@Repository 
public interface CategoryPriceRepository extends JpaRepository<CategoryPrice, Integer>{ 

	//@Query("") 
	//Method 

} 
