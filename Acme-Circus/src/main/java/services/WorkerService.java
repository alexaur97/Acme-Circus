package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.WorkerRepository;

import domain.Worker; 

@Service 
@Transactional 
public class WorkerService { 

	//Managed repository -------------------
	@Autowired
	private WorkerRepository workerRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public WorkerService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Worker create(){
		Worker result;

		result = new Worker();

		return result;
	}

	public Collection<Worker> findAll(){
		Collection<Worker> result;

		result = workerRepository.findAll();

		return result;
	}

	public Worker findOne(int workerId){
		Worker result;

		result = workerRepository.findOne(workerId);

		return result;
	}

	public void save(Worker worker){
		Assert.notNull(worker);

		workerRepository.save(worker);
	}

	public void delete(Worker worker){
		workerRepository.delete(worker);
	}


	//Other Methods--------------------
} 
