
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FeeRepository;
import domain.Fee;

@Service
@Transactional
public class FeeService {

	//Managed repository -------------------
	@Autowired
	private FeeRepository			feeRepository;

	//Supporting Services ------------------

	@Autowired
	private AdministratorService	administratorService;


	//COnstructors -------------------------
	public FeeService() {
		super();
	}

	//Simple CRUD methods--------------------

	//	public Fee create() {
	//		Fee result;
	//
	//		result = new Fee();
	//
	//		return result;
	//	}

	public Fee find() {

		Fee result;

		result = (Fee) this.feeRepository.findAll().toArray()[0];

		return result;
	}

	//	public Fee findOne(final int feeId) {
	//		Fee result;
	//
	//		result = this.feeRepository.findOne(feeId);
	//
	//		return result;
	//	}

	public Fee save(final Fee fee) {

		this.administratorService.findByPrincipal();
		Assert.notNull(fee);
		return this.feeRepository.save(fee);
	}

	//	public void delete(final Fee fee) {
	//		this.feeRepository.delete(fee);
	//	}

	//Other Methods--------------------
}
