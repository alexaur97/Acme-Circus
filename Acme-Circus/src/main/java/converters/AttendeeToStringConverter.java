package converters; 

import org.springframework.core.convert.converter.Converter; 
import org.springframework.stereotype.Component; 
import org.springframework.transaction.annotation.Transactional; 

import domain.Attendee; 

@Component 
@Transactional 
public class AttendeeToStringConverter implements Converter<Attendee, String>{ 

	@Override 
	public String convert(Attendee attendee){ 
		String result; 
		if(attendee == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(attendee.getId()); 
		} 
		return result; 
	} 

} 
