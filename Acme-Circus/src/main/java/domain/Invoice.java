package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Invoice extends DomainEntity {

	// Attributes ..................
	
	public Date dateRequested;
	public Boolean generated;
	public Double total;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getDateRequested() {
		return dateRequested;
	}
	public void setDateRequested(Date dateRequested) {
		this.dateRequested = dateRequested;
	}
	
	@NotNull	
	public Boolean getGenerated() {
		return generated;
	}
	public void setGenerated(Boolean generated) {
		this.generated = generated;
	}
	
	@NotNull
	@Range(min= 0)
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}


}
