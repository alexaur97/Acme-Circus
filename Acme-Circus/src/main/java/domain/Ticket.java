
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Ticket extends DomainEntity {

	// Attributes ..................

	public String			refNumber;
	public CategoryPrice	categoryPrice;


	@NotBlank
	public String getRefNumber() {
		return this.refNumber;
	}
	public void setRefNumber(final String refNumber) {
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
