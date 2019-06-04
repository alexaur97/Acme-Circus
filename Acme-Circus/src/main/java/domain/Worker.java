
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Worker extends Actor {

	// Attributes ..................

	public Circus	circus;


	@NotNull
	@ManyToOne(optional = false)
	public Circus getCircus() {
		return this.circus;
	}

	public void setCircus(final Circus circus) {
		this.circus = circus;
	}

}
