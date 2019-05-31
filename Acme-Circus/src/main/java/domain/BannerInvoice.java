
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class BannerInvoice extends Invoice {

	// Attributes ..................

	public Banner	banner;
	public Double	bannerFee;


	@NotNull
	@Min(0)
	public Double getBannerFee() {
		return this.bannerFee;
	}

	public void setBannerFee(final Double bannerFee) {
		this.bannerFee = bannerFee;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Banner getBanner() {
		return this.banner;
	}

	public void setBanner(final Banner banner) {
		this.banner = banner;
	}

}
