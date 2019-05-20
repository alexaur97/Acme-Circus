package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.CategoryPriceRepository;

import domain.CategoryPrice; 
import domain.CategoryTour;

@Service 
@Transactional 
public class CategoryPriceService { 

	//Managed repository -------------------
	@Autowired
	private CategoryPriceRepository categoryPriceRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public CategoryPriceService(){
		super();
	}


	//Simple CRUD methods--------------------

	public CategoryPrice create(){
		CategoryPrice result;

		result = new CategoryPrice();

		return result;
	}

	public Collection<CategoryPrice> findAll(){
		Collection<CategoryPrice> result;

		result = categoryPriceRepository.findAll();

		return result;
	}

	public CategoryPrice findOne(int categoryPriceId){
		CategoryPrice result;

		result = categoryPriceRepository.findOne(categoryPriceId);

		return result;
	}

	public void save(CategoryPrice categoryPrice){
		Assert.notNull(categoryPrice);

		categoryPriceRepository.save(categoryPrice);
	}

	public void delete(CategoryPrice categoryPrice){
		categoryPriceRepository.delete(categoryPrice);
	}


	//Other Methods--------------------
} 
