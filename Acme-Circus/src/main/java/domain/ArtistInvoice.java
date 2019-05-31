
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class ArtistInvoice extends Invoice {

	// Attributes ..................

	public Artist	artist;
	public Double	acceptedOfferFee;


	@NotNull
	@ManyToOne(optional = false)
	public Artist getArtist() {
		return this.artist;
	}

	public void setArtist(final Artist artist) {
		this.artist = artist;
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
