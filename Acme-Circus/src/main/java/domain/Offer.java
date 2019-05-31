
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Offer extends DomainEntity {

	// Attributes ..................

	public String		observations;
	public String		status;
	public Date			lastUpdate;
	public String		conditions;
	public Double		money;
	public Performance	performance;


	@NotNull
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getObservations() {
		return this.observations;
	}
	public void setObservations(final String observations) {
		this.observations = observations;
	}

	@NotBlank
	@Pattern(regexp = "^CONFIRMED|WAITINGFORCONFIMATION|REJECTED|PENDING$")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getStatus() {
		return this.status;
	}
	public void setStatus(final String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getLastUpdate() {
		return this.lastUpdate;
	}
	public void setLastUpdate(final Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@NotBlank
	public String getConditions() {
		return this.conditions;
	}
	public void setConditions(final String conditions) {
		this.conditions = conditions;
	}
	@NotNull
	@Range(min = 0)
	public Double getMoney() {
		return this.money;
	}
	public void setMoney(final Double money) {
		this.money = money;
	}

	@ManyToOne(optional = false)
	public Performance getPerformance() {
		return this.performance;
	}
	public void setPerformance(final Performance performance) {
		this.performance = performance;
	}

}
