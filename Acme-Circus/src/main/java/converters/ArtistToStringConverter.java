
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Artist;

@Component
@Transactional
public class ArtistToStringConverter implements Converter<Artist, String> {

	@Override
	public String convert(final Artist artist) {
		String result;
		if (artist == null)
			result = null;
		else
			result = String.valueOf(artist.getId());
		return result;
	}

}
