package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.OwnerRepository;

import domain.Owner; 
import domain.Performance;

@Service 
@Transactional 
public class OwnerService { 

	//Managed repository -------------------
	@Autowired
	private OwnerRepository ownerRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public OwnerService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Owner create(){
		Owner result;

		result = new Owner();

		return result;
	}

	public Collection<Owner> findAll(){
		Collection<Owner> result;

		result = ownerRepository.findAll();

		return result;
	}

	public Owner findOne(int ownerId){
		Owner result;

		result = ownerRepository.findOne(ownerId);

		return result;
	}

	public void save(Owner owner){
		Assert.notNull(owner);

		ownerRepository.save(owner);
	}

	public void delete(Owner owner){
		ownerRepository.delete(owner);
	}


	//Other Methods--------------------
} 
