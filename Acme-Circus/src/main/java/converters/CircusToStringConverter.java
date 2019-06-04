
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Circus;

@Component
@Transactional
public class CircusToStringConverter implements Converter<Circus, String> {

	@Override
	public String convert(final Circus circus) {
		String result;
		if (circus == null)
			result = null;
		else
			result = String.valueOf(circus.getId());
		return result;
	}

}
