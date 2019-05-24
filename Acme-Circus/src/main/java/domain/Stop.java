
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Stop extends DomainEntity {

	// Attributes ..................

	public String	city;
	public String	country;
	public String	location;
	public Date		date;
	public Integer	spotsTotal;
	public Integer	spotsAvailable;
	public Tour		tour;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getCity() {
		return this.city;
	}
	public void setCity(final String city) {
		this.city = city;
	}
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getCountry() {
		return this.country;
	}
	public void setCountry(final String country) {
		this.country = country;
	}
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getLocation() {
		return this.location;
	}
	public void setLocation(final String location) {
		this.location = location;
	}
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	public Date getDate() {
		return this.date;
	}
	public void setDate(final Date date) {
		this.date = date;
	}
	@NotNull
	@Range(min = 0)
	public Integer getSpotsTotal() {
		return this.spotsTotal;
	}
	public void setSpotsTotal(final Integer spotsTotal) {
		this.spotsTotal = spotsTotal;
	}
	@NotNull
	@Range(min = 0)
	public Integer getSpotsAvailable() {
		return this.spotsAvailable;
	}
	public void setSpotsAvailable(final Integer spotsAvailable) {
		this.spotsAvailable = spotsAvailable;
	}

	@ManyToOne(optional = false)
	public Tour getTour() {
		return this.tour;
	}

	public void setTour(final Tour tour) {
		this.tour = tour;
	}

}
