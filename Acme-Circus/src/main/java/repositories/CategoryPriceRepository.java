
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.CategoryPrice;

@Repository
public interface CategoryPriceRepository extends JpaRepository<CategoryPrice, Integer> {

	@Query("select c from CategoryPrice c where c.stop.tour.organizers.circus.id = ?1")
	Collection<CategoryPrice> findAllCategoryPriceByCircus(int circusId);

}
