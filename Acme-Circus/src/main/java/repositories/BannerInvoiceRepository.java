
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.BannerInvoice;

@Repository
public interface BannerInvoiceRepository extends JpaRepository<BannerInvoice, Integer> {

	@Query("select sum(b.total) from BannerInvoice b")
	Double totalBenefits();

	@Query("select bi from BannerInvoice bi join bi.banner.tour t where exists (select k from Owner o join o.circus k where o.id=?1 and k=t.organizers.circus) order by bi.dateRequested desc")
	Collection<BannerInvoice> findAllByPrincipal(int id);

	@Query("select b from BannerInvoice b order by b.dateRequested desc")
	Collection<BannerInvoice> findAllDesc();

	//@Query("") 
	//Method 

}
