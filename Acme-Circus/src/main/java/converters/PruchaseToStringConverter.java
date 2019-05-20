package converters; 

import org.springframework.core.convert.converter.Converter; 
import org.springframework.stereotype.Component; 
import org.springframework.transaction.annotation.Transactional; 

import domain.Pruchase; 

@Component 
@Transactional 
public class PruchaseToStringConverter implements Converter<Pruchase, String>{ 

	@Override 
	public String convert(Pruchase pruchase){ 
		String result; 
		if(pruchase == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(pruchase.getId()); 
		} 
		return result; 
	} 

} 
