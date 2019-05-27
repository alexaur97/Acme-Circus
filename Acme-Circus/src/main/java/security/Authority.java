/*
 * Authority.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package security;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

@Embeddable
@Access(AccessType.PROPERTY)
public class Authority implements GrantedAuthority {

	// Constructors -----------------------------------------------------------

	private static final long	serialVersionUID	= 1L;


	public Authority() {
		super();
	}


	// Values -----------------------------------------------------------------

	public static final String	ADMINISTRATOR	= "ADMINISTRATOR";
	public static final String	ARTIST			= "ARTIST";
	public static final String	ORGANIZER		= "ORGANIZER";
	public static final String	ATTENDEE		= "ATTENDEE";
	public static final String	OWNER			= "OWNER";
	public static final String	WORKER			= "WORKER";

	// Attributes -------------------------------------------------------------

	private String				authority;


	@NotBlank
	@Pattern(regexp = "^" + Authority.ADMINISTRATOR + "|" + Authority.WORKER + "|" + Authority.ARTIST + "|" + Authority.ORGANIZER + "|" + Authority.ATTENDEE + "|" + Authority.OWNER + "$")
	@Override
	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(final String authority) {
		this.authority = authority;
	}

	public static Collection<Authority> listAuthorities() {
		Collection<Authority> result;
		Authority authority;

		result = new ArrayList<Authority>();

		authority = new Authority();
		authority.setAuthority(Authority.ADMINISTRATOR);
		result.add(authority);

		authority = new Authority();
		authority.setAuthority(Authority.ARTIST);
		result.add(authority);

		authority = new Authority();
		authority.setAuthority(Authority.ORGANIZER);
		result.add(authority);

		authority = new Authority();
		authority.setAuthority(Authority.ATTENDEE);
		result.add(authority);

		authority = new Authority();
		authority.setAuthority(Authority.OWNER);
		result.add(authority);

		authority = new Authority();
		authority.setAuthority(Authority.WORKER);
		result.add(authority);

		return result;
	}

	// Object interface -------------------------------------------------------

	@Override
	public int hashCode() {
		return this.getAuthority().hashCode();
	}

	@Override
	public boolean equals(final Object other) {
		boolean result;

		if (this == other)
			result = true;
		else if (other == null)
			result = false;
		else if (!this.getClass().isInstance(other))
			result = false;
		else
			result = (this.getAuthority().equals(((Authority) other).getAuthority()));

		return result;
	}

	@Override
	public String toString() {
		return this.authority;
	}

}
