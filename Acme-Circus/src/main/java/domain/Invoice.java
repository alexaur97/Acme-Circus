
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

	public Date		dateRequested;
	public Boolean	generated;
	public Double	total;


	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getDateRequested() {
		return this.dateRequested;
	}
	public void setDateRequested(final Date dateRequested) {
		this.dateRequested = dateRequested;
	}

	@NotNull
	public Boolean getGenerated() {
		return this.generated;
	}
	public void setGenerated(final Boolean generated) {
		this.generated = generated;
	}

	@NotNull
	@Range(min = 0)
	public Double getTotal() {
		return this.total;
	}
	public void setTotal(final Double total) {
		this.total = total;
	}

}
