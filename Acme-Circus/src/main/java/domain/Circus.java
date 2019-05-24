
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Circus extends DomainEntity {

	// Attributes ..................

	public String	VAT;
	public String	name;
	public String	history;
	public Boolean	active;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp = "^(ES)?[0-9A-Z][0-9]{7}[0-9A-Z]$")
	public String getVAT() {
		return this.VAT;
	}
	public void setVAT(final String vAT) {
		this.VAT = vAT;
	}
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
	public String getHistory() {
		return this.history;
	}
	public void setHistory(final String history) {
		this.history = history;
	}

	@NotNull
	public Boolean getActive() {
		return this.active;
	}
	public void setActive(final Boolean active) {
		this.active = active;
	}

}
