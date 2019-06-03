
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.CategoryTour;

@Component
@Transactional
public class CategoryTourToStringConverter implements Converter<CategoryTour, String> {

	@Override
	public String convert(final CategoryTour categoryTour) {
		String result;
		if (categoryTour == null)
			result = null;
		else
			result = String.valueOf(categoryTour.getId());
		return result;
	}

}
