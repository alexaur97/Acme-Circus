package converters; 

import org.springframework.core.convert.converter.Converter; 
import org.springframework.stereotype.Component; 
import org.springframework.transaction.annotation.Transactional; 

import domain.Tour; 

@Component 
@Transactional 
public class TourToStringConverter implements Converter<Tour, String>{ 

	@Override 
	public String convert(Tour tour){ 
		String result; 
		if(tour == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(tour.getId()); 
		} 
		return result; 
	} 

} 
