package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Stop extends DomainEntity {

	// Attributes ..................

	public String city;
	public String country;
	public String location;
	public Date date;
	public Integer spotsTotal;
	public Integer spotsAvailable;
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@NotNull
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@NotNull
	@Range(min=0)
	public Integer getSpotsTotal() {
		return spotsTotal;
	}
	public void setSpotsTotal(Integer spotsTotal) {
		this.spotsTotal = spotsTotal;
	}
	@NotNull
	@Range(min=0)
	public Integer getSpotsAvailable() {
		return spotsAvailable;
	}
	public void setSpotsAvailable(Integer spotsAvailable) {
		this.spotsAvailable = spotsAvailable;
	}

}
