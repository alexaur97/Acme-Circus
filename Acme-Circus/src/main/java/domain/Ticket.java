package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Ticket extends DomainEntity {

	// Attributes ..................

	public Integer refNumber;
	public CategoryPrice categoryPrice;
	
	@NotNull
	@Range(min= 0)
	public Integer getRefNumber() {
		return refNumber;
	}
	public void setRefNumber(Integer refNumber) {
		this.refNumber = refNumber;
	}
	
	@ManyToOne(optional=false)
	public CategoryPrice getCategoryPrice() {
		return categoryPrice;
	}
	public void setCategoryPrice(CategoryPrice categoryPrice) {
		this.categoryPrice = categoryPrice;
	}

}
