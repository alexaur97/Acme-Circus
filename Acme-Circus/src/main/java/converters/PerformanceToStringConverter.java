package converters; 

import org.springframework.core.convert.converter.Converter; 
import org.springframework.stereotype.Component; 
import org.springframework.transaction.annotation.Transactional; 

import domain.Performance; 

@Component 
@Transactional 
public class PerformanceToStringConverter implements Converter<Performance, String>{ 

	@Override 
	public String convert(Performance performance){ 
		String result; 
		if(performance == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(performance.getId()); 
		} 
		return result; 
	} 

} 
