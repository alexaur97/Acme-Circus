package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.CategoryTourRepository;

import domain.CategoryTour; 
import domain.CircusInvoice;

@Service 
@Transactional 
public class CategoryTourService { 

	//Managed repository -------------------
	@Autowired
	private CategoryTourRepository categoryTourRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public CategoryTourService(){
		super();
	}


	//Simple CRUD methods--------------------

	public CategoryTour create(){
		CategoryTour result;

		result = new CategoryTour();

		return result;
	}

	public Collection<CategoryTour> findAll(){
		Collection<CategoryTour> result;

		result = categoryTourRepository.findAll();

		return result;
	}

	public CategoryTour findOne(int categoryTourId){
		CategoryTour result;

		result = categoryTourRepository.findOne(categoryTourId);

		return result;
	}

	public void save(CategoryTour categoryTour){
		Assert.notNull(categoryTour);

		categoryTourRepository.save(categoryTour);
	}

	public void delete(CategoryTour categoryTour){
		categoryTourRepository.delete(categoryTour);
	}


	//Other Methods--------------------
} 
