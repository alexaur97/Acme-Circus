
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ArtistRepository;
import domain.Artist;

@Component
@Transactional
public class StringToArtistConverter implements Converter<String, Artist> {

	@Autowired
	ArtistRepository	artistRepository;


	@Override
	public Artist convert(final String text) {
		Artist result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.artistRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
