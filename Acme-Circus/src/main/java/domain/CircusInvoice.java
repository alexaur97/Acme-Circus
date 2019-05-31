
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class CircusInvoice extends Invoice {

	// Attributes ..................

	public Circus	circus;
	public Double	circusFee;


	@NotNull
	@ManyToOne(optional = false)
	public Circus getCircus() {
		return this.circus;
	}

	public void setCircus(final Circus circus) {
		this.circus = circus;
	}

	@NotNull
	@Min(0)
	public Double getCircusFee() {
		return this.circusFee;
	}
	public void setCircusFee(final Double circusFee) {
		this.circusFee = circusFee;
	}
}
