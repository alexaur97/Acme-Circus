
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.CircusInvoice;

@Component
@Transactional
public class CircusInvoiceToStringConverter implements Converter<CircusInvoice, String> {

	@Override
	public String convert(final CircusInvoice circusInvoice) {
		String result;
		if (circusInvoice == null)
			result = null;
		else
			result = String.valueOf(circusInvoice.getId());
		return result;
	}

}
