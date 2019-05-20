package converters; 

import org.springframework.core.convert.converter.Converter; 
import org.springframework.stereotype.Component; 
import org.springframework.transaction.annotation.Transactional; 

import domain.CategoryPrice; 

@Component 
@Transactional 
public class CategoryPriceToStringConverter implements Converter<CategoryPrice, String>{ 

	@Override 
	public String convert(CategoryPrice categoryPrice){ 
		String result; 
		if(categoryPrice == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(categoryPrice.getId()); 
		} 
		return result; 
	} 

} 
