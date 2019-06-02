
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.CircusInvoice;

@Repository
public interface CircusInvoiceRepository extends JpaRepository<CircusInvoice, Integer> {

	@Query("select sum(c.total) from CircusInvoice c")
	Double totalBenefits();

	@Query("select ci from CircusInvoice ci join ci.circus c where exists (select k from Owner o join o.circus k where o.id=?1 and k=c) order by ci.dateRequested desc")
	Collection<CircusInvoice> findAllByPrincipal(int id);

	@Query("select c from CircusInvoice c order by c.dateRequested desc")
	Collection<CircusInvoice> findAllDesc();

	//@Query("") 
	//Method 

}
