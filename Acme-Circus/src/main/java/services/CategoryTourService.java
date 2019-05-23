
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryTourRepository;
import domain.CategoryTour;

@Service
@Transactional
public class CategoryTourService {

	//Managed repository -------------------
	@Autowired
	private CategoryTourRepository	categoryTourRepository;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public CategoryTourService() {
		super();
	}

	//Simple CRUD methods--------------------

	public CategoryTour create() {
		CategoryTour result;

		result = new CategoryTour();

		return result;
	}

	public Collection<CategoryTour> findAll() {
		Collection<CategoryTour> result;
		result = this.categoryTourRepository.findAll();
		return result;
	}

	public CategoryTour findOne(final int categoryTourId) {
		CategoryTour result;
		result = this.categoryTourRepository.findOne(categoryTourId);
		return result;
	}

	public CategoryTour save(final CategoryTour categoryTour) {
		Assert.notNull(categoryTour);
		final Collection<CategoryTour> used = this.findAllUsedCategoriesTour();
		Assert.isTrue(!used.contains(categoryTour));
		return this.categoryTourRepository.save(categoryTour);
	}

	public void delete(final CategoryTour categoryTour) {
		this.categoryTourRepository.delete(categoryTour);
	}

	public Collection<CategoryTour> findAllUsedCategoriesTour() {
		return this.categoryTourRepository.findAllUsedCategoriesTour();
	}

	//Other Methods--------------------
}
