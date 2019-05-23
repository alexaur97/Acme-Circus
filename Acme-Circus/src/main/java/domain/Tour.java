
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Tour extends DomainEntity {

	// Attributes ..................
	public String					name;
	public String					description;
	public Date						startDate;
	public Date						endDate;
	public String					link;
	public Collection<String>		tags;
	public Boolean					validated;

	public Collection<Stop>			stops;
	public Organizer				organizers;
	public CategoryTour				categoryTour;
	public Collection<Performance>	performances;
	public Collection<Offer>		offers;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@URL
	public String getLink() {
		return this.link;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	@ElementCollection
	public Collection<String> getTags() {
		return this.tags;
	}

	public void setTags(final Collection<String> tags) {
		this.tags = tags;
	}

	@NotNull
	public Boolean getValidated() {
		return this.validated;
	}

	public void setValidated(final Boolean validated) {
		this.validated = validated;
	}

	@OneToMany
	public Collection<Stop> getStops() {
		return this.stops;
	}

	public void setStops(final Collection<Stop> stops) {
		this.stops = stops;
	}

	@ManyToOne(optional = false)
	public Organizer getOrganizers() {
		return this.organizers;
	}

	public void setOrganizers(final Organizer organizers) {
		this.organizers = organizers;
	}

	@ManyToOne(optional = false)
	public CategoryTour getCategoryTour() {
		return this.categoryTour;
	}

	public void setCategoryTour(final CategoryTour categoryTour) {
		this.categoryTour = categoryTour;
	}

	@OneToMany
	public Collection<Performance> getPerformances() {
		return this.performances;
	}

	public void setPerformances(final Collection<Performance> performances) {
		this.performances = performances;
	}

	@OneToMany
	public Collection<Offer> getOffers() {
		return this.offers;
	}

	public void setOffers(final Collection<Offer> offers) {
		this.offers = offers;
	}

}
