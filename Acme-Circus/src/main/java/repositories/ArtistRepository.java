package repositories;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query; 
import org.springframework.stereotype.Repository; 

import domain.Artist; 

@Repository 
public interface ArtistRepository extends JpaRepository<Artist, Integer>{ 

	//@Query("") 
	//Method 

} 
