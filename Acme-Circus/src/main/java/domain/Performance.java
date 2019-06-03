
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Performance extends DomainEntity {

	// Attributes ..................

	public String				name;
	public Collection<String>	tags;
	public Integer				persons;
	public String				video;
	public Boolean				copy;
	public Artist				artist;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	@ElementCollection
	public Collection<String> getTags() {
		return this.tags;
	}
	public void setTags(final Collection<String> tags) {
		this.tags = tags;
	}
	@NotNull
	@Min(value = 0)
	public Integer getPersons() {
		return this.persons;
	}
	public void setPersons(final Integer persons) {
		this.persons = persons;
	}

	@NotNull
	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getVideo() {
		return this.video;
	}
	public void setVideo(final String video) {
		this.video = video;
	}

	@NotNull
	public Boolean getCopy() {
		return this.copy;
	}
	public void setCopy(final Boolean copy) {
		this.copy = copy;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Artist getArtist() {
		return this.artist;
	}

	public void setArtist(final Artist artist) {
		this.artist = artist;
	}

}
