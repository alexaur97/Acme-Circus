package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Fee extends DomainEntity {

	// Attributes ..................
	
	public Double bannerFee;
	public Double circusFee;
	public Double acceptedOfferFee;
	
	
	@NotNull
	@Range(min = 0)
	public Double getBannerFee() {
		return bannerFee;
	}
	public void setBannerFee(Double bannerFee) {
		this.bannerFee = bannerFee;
	}
	
	@NotNull
	@Range(min = 0)
	public Double getCircusFee() {
		return circusFee;
	}
	public void setCircusFee(Double circusFee) {
		this.circusFee = circusFee;
	}
	
	@NotNull
	@Range(min = 0)
	public Double getAcceptedOfferFee() {
		return acceptedOfferFee;
	}
	public void setAcceptedOfferFee(Double acceptedOfferFee) {
		this.acceptedOfferFee = acceptedOfferFee;
	}


}
