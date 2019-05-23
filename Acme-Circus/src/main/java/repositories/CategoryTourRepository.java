
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.CategoryTour;

@Repository
public interface CategoryTourRepository extends JpaRepository<CategoryTour, Integer> {

}
