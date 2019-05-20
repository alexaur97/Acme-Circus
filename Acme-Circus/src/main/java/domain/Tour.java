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
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Tour extends DomainEntity {

	// Attributes ..................
	public String name;
	public String description;
	public Date startDate;
	public Date endDate;
	public String link;
	public Collection<String> tags;
	public Boolean validated;
	
	public Collection<Stop> stops;
	public Organizer organizers;
	public CategoryTour categoryTour;
	public Collection<Performance> performances;
	public Collection<Offer> offers;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@URL
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	
	@ElementCollection
	public Collection<String> getTags() {
		return tags;
	}

	public void setTags(Collection<String> tags) {
		this.tags = tags;
	}

	@NotNull
	public Boolean getValidated() {
		return validated;
	}

	public void setValidated(Boolean validated) {
		this.validated = validated;
	}

	@OneToMany
	public Collection<Stop> getStops() {
		return stops;
	}

	public void setStops(Collection<Stop> stops) {
		this.stops = stops;
	}

	@ManyToOne(optional = false)
	public Organizer getOrganizers() {
		return organizers;
	}

	public void setOrganizers(Organizer organizers) {
		this.organizers = organizers;
	}

	@ManyToOne(optional = false)
	public CategoryTour getCategoryTour() {
		return categoryTour;
	}

	public void setCategoryTour(CategoryTour categoryTour) {
		this.categoryTour = categoryTour;
	}

	@OneToMany
	public Collection<Performance> getPerformances() {
		return performances;
	}

	public void setPerformances(Collection<Performance> performances) {
		this.performances = performances;
	}

	@OneToMany
	public Collection<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Collection<Offer> offers) {
		this.offers = offers;
	}

}
