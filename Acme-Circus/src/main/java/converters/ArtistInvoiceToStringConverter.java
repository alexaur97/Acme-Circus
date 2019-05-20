package converters; 

import org.springframework.core.convert.converter.Converter; 
import org.springframework.stereotype.Component; 
import org.springframework.transaction.annotation.Transactional; 

import domain.ArtistInvoice; 

@Component 
@Transactional 
public class ArtistInvoiceToStringConverter implements Converter<ArtistInvoice, String>{ 

	@Override 
	public String convert(ArtistInvoice artistInvoice){ 
		String result; 
		if(artistInvoice == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(artistInvoice.getId()); 
		} 
		return result; 
	} 

} 
