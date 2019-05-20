package converters; 

import org.springframework.core.convert.converter.Converter; 
import org.springframework.stereotype.Component; 
import org.springframework.transaction.annotation.Transactional; 

import domain.BannerInvoice; 

@Component 
@Transactional 
public class BannerInvoiceToStringConverter implements Converter<BannerInvoice, String>{ 

	@Override 
	public String convert(BannerInvoice bannerInvoice){ 
		String result; 
		if(bannerInvoice == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(bannerInvoice.getId()); 
		} 
		return result; 
	} 

} 
