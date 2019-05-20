package converters; 

import org.springframework.core.convert.converter.Converter; 
import org.springframework.stereotype.Component; 
import org.springframework.transaction.annotation.Transactional; 

import domain.Stop; 

@Component 
@Transactional 
public class StopToStringConverter implements Converter<Stop, String>{ 

	@Override 
	public String convert(Stop stop){ 
		String result; 
		if(stop == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(stop.getId()); 
		} 
		return result; 
	} 

} 
