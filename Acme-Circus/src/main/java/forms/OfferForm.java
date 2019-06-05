
package forms;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import domain.Performance;
import domain.Tour;

public class OfferForm {// Attributes ..................

	public String		observations;
	public Double		money;
	public Performance	performance;
	public Tour			tour;


	@NotNull
	public Tour getTour() {
		return this.tour;
	}

	public void setTour(final Tour tour) {
		this.tour = tour;
	}
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getObservations() {
		return this.observations;
	}
	public void setObservations(final String observations) {
		this.observations = observations;
	}
	@NotNull
	@Min(value = 0)
	public Double getMoney() {
		return this.money;
	}
	public void setMoney(final Double money) {
		this.money = money;
	}
	@NotNull
	@ManyToOne(optional = false)
	public Performance getPerformance() {
		return this.performance;
	}
	public void setPerformance(final Performance performance) {
		this.performance = performance;
	}

}
