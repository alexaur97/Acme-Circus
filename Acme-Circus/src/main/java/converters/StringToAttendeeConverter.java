package converters; 

import org.apache.commons.lang.StringUtils; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.core.convert.converter.Converter; 
import org.springframework.stereotype.Component; 
import org.springframework.transaction.annotation.Transactional; 

import repositories.AttendeeRepository; 
import domain.Attendee; 

@Component 
@Transactional 
public class StringToAttendeeConverter implements Converter<String, Attendee>{ 

	@Autowired 
	AttendeeRepository attendeeRepository; 

	@Override 
	public Attendee convert(String text){ 
		Attendee result; 
		int id; 

		try{ 
			if(StringUtils.isEmpty(text)){ 
				result = null; 
			}else{ 
				id = Integer.valueOf(text); 
				result = attendeeRepository.findOne(id); 
			} 
		}catch (Throwable oops) { 
			throw new IllegalArgumentException(oops); 
		} 

		return result; 
	} 

} 
