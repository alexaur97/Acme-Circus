package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.FeeRepository;

import domain.Fee; 
import domain.Invoice;

@Service 
@Transactional 
public class FeeService { 

	//Managed repository -------------------
	@Autowired
	private FeeRepository feeRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public FeeService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Fee create(){
		Fee result;

		result = new Fee();

		return result;
	}

	public Collection<Fee> findAll(){
		Collection<Fee> result;

		result = feeRepository.findAll();

		return result;
	}

	public Fee findOne(int feeId){
		Fee result;

		result = feeRepository.findOne(feeId);

		return result;
	}

	public void save(Fee fee){
		Assert.notNull(fee);

		feeRepository.save(fee);
	}

	public void delete(Fee fee){
		feeRepository.delete(fee);
	}


	//Other Methods--------------------
} 
