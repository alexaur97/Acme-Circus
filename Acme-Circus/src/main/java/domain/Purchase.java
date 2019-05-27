
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Purchase extends DomainEntity {

	// Attributes ..................

	public CreditCard			creditCard;
	public Double				totalPrice;
	public Attendee				attendee;
	public Stop					stop;
	public Collection<Ticket>	tickets;


	@NotNull
	@ManyToOne(optional = false)
	public CreditCard getCreditCard() {
		return this.creditCard;
	}
	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@NotNull
	@Range(min = 0)
	public Double getTotalPrice() {
		return this.totalPrice;
	}
	public void setTotalPrice(final Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Attendee getAttendee() {
		return this.attendee;
	}
	public void setAttendee(final Attendee attendee) {
		this.attendee = attendee;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Stop getStop() {
		return this.stop;
	}
	public void setStop(final Stop stop) {
		this.stop = stop;
	}

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Ticket> getTickets() {
		return this.tickets;
	}
	public void setTickets(final Collection<Ticket> tickets) {
		this.tickets = tickets;
	}

}
