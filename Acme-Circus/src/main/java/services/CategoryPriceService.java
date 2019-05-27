
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.CategoryPriceRepository;
import domain.CategoryPrice;

@Service
@Transactional
public class CategoryPriceService {

	//Managed repository -------------------
	@Autowired
	private CategoryPriceRepository	categoryPriceRepository;

	//Supporting Services ------------------

	@Autowired
	private Validator				validator;


	//COnstructors -------------------------
	public CategoryPriceService() {
		super();
	}

	//Simple CRUD methods--------------------

	public CategoryPrice create() {
		CategoryPrice result;

		result = new CategoryPrice();

		return result;
	}

	public Collection<CategoryPrice> findAll() {
		Collection<CategoryPrice> result;

		result = this.categoryPriceRepository.findAll();

		return result;
	}

	public CategoryPrice findOne(final int categoryPriceId) {
		CategoryPrice result;

		result = this.categoryPriceRepository.findOne(categoryPriceId);

		return result;
	}

	public void save(final CategoryPrice categoryPrice) {
		Assert.notNull(categoryPrice);

		this.categoryPriceRepository.save(categoryPrice);
	}

	public void delete(final CategoryPrice categoryPrice) {
		this.categoryPriceRepository.delete(categoryPrice);
	}

	public Collection<CategoryPrice> findCategoryPriceByCircus(final int circusId) {
		final Collection<CategoryPrice> res = this.categoryPriceRepository.findAllCategoryPriceByCircus(circusId);
		return res;
	}
	public Collection<CategoryPrice> findByStop(final int stopId) {
		final Collection<CategoryPrice> res = this.categoryPriceRepository.findByStop(stopId);
		return res;
	}

	//	public CategoryPrice reconstruct(final CategoryPrice categoryPrice, final BindingResult binding) {
	//
	//		final CategoryPrice result = categoryPrice;
	//		result.setStop(categoryPrice.getStop());
	//		this.validator.validate(result, binding);
	//		return result;
	//	}

	public CategoryPrice reconstruct(final CategoryPrice categoryPrice) {
		final CategoryPrice res = categoryPrice;
		final CategoryPrice c = this.findOne(categoryPrice.getId());

		if (categoryPrice.getId() != 0)
			res.setStop(c.getStop());
		return res;
	}

	//Other Methods--------------------
}
