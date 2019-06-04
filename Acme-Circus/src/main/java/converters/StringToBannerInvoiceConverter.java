
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.BannerInvoiceRepository;
import domain.BannerInvoice;

@Component
@Transactional
public class StringToBannerInvoiceConverter implements Converter<String, BannerInvoice> {

	@Autowired
	BannerInvoiceRepository	bannerInvoiceRepository;


	@Override
	public BannerInvoice convert(final String text) {
		BannerInvoice result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.bannerInvoiceRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
