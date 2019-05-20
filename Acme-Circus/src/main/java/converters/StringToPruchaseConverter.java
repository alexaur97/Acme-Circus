package converters; 

import org.apache.commons.lang.StringUtils; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.core.convert.converter.Converter; 
import org.springframework.stereotype.Component; 
import org.springframework.transaction.annotation.Transactional; 

import repositories.PruchaseRepository; 
import domain.Pruchase; 

@Component 
@Transactional 
public class StringToPruchaseConverter implements Converter<String, Pruchase>{ 

	@Autowired 
	PruchaseRepository pruchaseRepository; 

	@Override 
	public Pruchase convert(String text){ 
		Pruchase result; 
		int id; 

		try{ 
			if(StringUtils.isEmpty(text)){ 
				result = null; 
			}else{ 
				id = Integer.valueOf(text); 
				result = pruchaseRepository.findOne(id); 
			} 
		}catch (Throwable oops) { 
			throw new IllegalArgumentException(oops); 
		} 

		return result; 
	} 

} 
