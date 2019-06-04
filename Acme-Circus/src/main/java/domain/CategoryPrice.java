
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class CategoryPrice extends DomainEntity {

	// Attributes ..................

	public Double	amount;
	public String	category;
	public Stop		stop;


	@NotNull
	@Range(min = 0)
	public Double getAmount() {
		return this.amount;
	}
	public void setAmount(final Double amount) {
		this.amount = amount;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getCategory() {
		return this.category;
	}
	public void setCategory(final String category) {
		this.category = category;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Stop getStop() {
		return this.stop;
	}
	public void setStop(final Stop stop) {
		this.stop = stop;
	}

}
