
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Fee extends DomainEntity {

	// Attributes ..................

	public Double	bannerFee;
	public Double	circusFee;
	public Double	acceptedOfferFee;


	@NotNull
	@Min(0)
	public Double getBannerFee() {
		return this.bannerFee;
	}
	public void setBannerFee(final Double bannerFee) {
		this.bannerFee = bannerFee;
	}

	@NotNull
	@Min(0)
	public Double getCircusFee() {
		return this.circusFee;
	}
	public void setCircusFee(final Double circusFee) {
		this.circusFee = circusFee;
	}

	@NotNull
	@Range(min = 0, max = 1)
	public Double getAcceptedOfferFee() {
		return this.acceptedOfferFee;
	}
	public void setAcceptedOfferFee(final Double acceptedOfferFee) {
		this.acceptedOfferFee = acceptedOfferFee;
	}

}
