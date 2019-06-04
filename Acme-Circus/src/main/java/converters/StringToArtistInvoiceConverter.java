
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ArtistInvoiceRepository;
import domain.ArtistInvoice;

@Component
@Transactional
public class StringToArtistInvoiceConverter implements Converter<String, ArtistInvoice> {

	@Autowired
	ArtistInvoiceRepository	artistInvoiceRepository;


	@Override
	public ArtistInvoice convert(final String text) {
		ArtistInvoice result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.artistInvoiceRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
