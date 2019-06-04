
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Organizer;

@Component
@Transactional
public class OrganizerToStringConverter implements Converter<Organizer, String> {

	@Override
	public String convert(final Organizer organizer) {
		String result;
		if (organizer == null)
			result = null;
		else
			result = String.valueOf(organizer.getId());
		return result;
	}

}
