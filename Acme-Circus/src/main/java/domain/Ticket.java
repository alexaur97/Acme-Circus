
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Ticket extends DomainEntity {

	// Attributes ..................

	public Integer			refNumber;
	public CategoryPrice	categoryPrice;


	@NotNull
	@Range(min = 0)
	public Integer getRefNumber() {
		return this.refNumber;
	}
	public void setRefNumber(final Integer refNumber) {
		this.refNumber = refNumber;
	}

	@ManyToOne(optional = false)
	public CategoryPrice getCategoryPrice() {
		return this.categoryPrice;
	}
	public void setCategoryPrice(final CategoryPrice categoryPrice) {
		this.categoryPrice = categoryPrice;
	}

}
