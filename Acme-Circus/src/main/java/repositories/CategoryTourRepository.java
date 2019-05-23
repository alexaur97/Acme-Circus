
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.CategoryTour;

@Repository
public interface CategoryTourRepository extends JpaRepository<CategoryTour, Integer> {

	@Query("select t.categoryTour from Tour t")
	Collection<CategoryTour> findAllUsedCategoriesTour();

	//@Query("") 
	//Method 

}
