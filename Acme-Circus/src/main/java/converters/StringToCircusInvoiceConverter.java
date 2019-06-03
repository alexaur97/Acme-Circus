
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CircusInvoiceRepository;
import domain.CircusInvoice;

@Component
@Transactional
public class StringToCircusInvoiceConverter implements Converter<String, CircusInvoice> {

	@Autowired
	CircusInvoiceRepository	circusInvoiceRepository;


	@Override
	public CircusInvoice convert(final String text) {
		CircusInvoice result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.circusInvoiceRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
