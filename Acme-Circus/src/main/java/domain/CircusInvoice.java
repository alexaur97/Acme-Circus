package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class CircusInvoice extends Invoice {

	// Attributes ..................
	
	public Circus circus;

	@NotNull
	@ManyToOne(optional = false)
	public Circus getCircus() {
		return circus;
	}

	public void setCircus(Circus circus) {
		this.circus = circus;
	}


}
