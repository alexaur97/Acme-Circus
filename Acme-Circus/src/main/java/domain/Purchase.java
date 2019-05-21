package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Purchase extends DomainEntity {

	// Attributes ..................
	
	public CreditCard creditCard;
	public Double totalPrice;
	public Attendee attendee;
	public Stop stop;
	public Collection<Ticket> tickets;
	
	@NotNull
	@ManyToOne(optional = false)
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	@NotNull
	@Range(min=0)
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@NotNull
	@ManyToOne(optional = false)
	public Attendee getAttendee() {
		return attendee;
	}	
	public void setAttendee(Attendee attendee) {
		this.attendee = attendee;
	}
	
	@NotNull
	@ManyToOne(optional = false)
	public Stop getStop() {
		return stop;
	}
	public void setStop(Stop stop) {
		this.stop = stop;
	}
	
	@NotNull
	@OneToMany
	public Collection<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(Collection<Ticket> tickets) {
		this.tickets = tickets;
	}


}
