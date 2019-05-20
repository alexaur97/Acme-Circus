package converters; 

import org.apache.commons.lang.StringUtils; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.core.convert.converter.Converter; 
import org.springframework.stereotype.Component; 
import org.springframework.transaction.annotation.Transactional; 

import repositories.OrganizerRepository; 
import domain.Organizer; 

@Component 
@Transactional 
public class StringToOrganizerConverter implements Converter<String, Organizer>{ 

	@Autowired 
	OrganizerRepository organizerRepository; 

	@Override 
	public Organizer convert(String text){ 
		Organizer result; 
		int id; 

		try{ 
			if(StringUtils.isEmpty(text)){ 
				result = null; 
			}else{ 
				id = Integer.valueOf(text); 
				result = organizerRepository.findOne(id); 
			} 
		}catch (Throwable oops) { 
			throw new IllegalArgumentException(oops); 
		} 

		return result; 
	} 

} 
