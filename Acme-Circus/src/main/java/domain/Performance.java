package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Performance extends DomainEntity {

	// Attributes ..................

	public String name;
	public Collection<String> tags;
	public Integer persons;
	public String video;
	public Boolean copy;

	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@ElementCollection
	public Collection<String> getTags() {
		return tags;
	}
	public void setTags(Collection<String> tags) {
		this.tags = tags;
	}
	@NotNull
	@Range(min = 0)
	public Integer getPersons() {
		return persons;
	}
	public void setPersons(Integer persons) {
		this.persons = persons;
	}
	
	@NotNull
	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	
	@NotNull
	public Boolean getCopy() {
		return copy;
	}
	public void setCopy(Boolean copy) {
		this.copy = copy;
	}
	
	

}
