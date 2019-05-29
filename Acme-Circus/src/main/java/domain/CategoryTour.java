
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class CategoryTour extends DomainEntity {

	// Attributes ..................

	public String	name;
	public Integer	minAge;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}

	@NotNull
	@Min(0)
	public Integer getMinAge() {
		return this.minAge;
	}
	public void setMinAge(final Integer minAge) {
		this.minAge = minAge;
	}

}
