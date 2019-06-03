
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CategoryTourRepository;
import domain.CategoryTour;

@Component
@Transactional
public class StringToCategoryTourConverter implements Converter<String, CategoryTour> {

	@Autowired
	CategoryTourRepository	categoryTourRepository;


	@Override
	public CategoryTour convert(final String text) {
		CategoryTour result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.categoryTourRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
