
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WorkerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Worker;

@Service
@Transactional
public class WorkerService {

	//Managed repository -------------------
	@Autowired
	private WorkerRepository	workerRepository;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public WorkerService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Worker create() {
		Worker result;

		result = new Worker();

		return result;
	}

	public Collection<Worker> findAll() {
		Collection<Worker> result;

		result = this.workerRepository.findAll();

		return result;
	}

	public Worker findOne(final int workerId) {
		Worker result;

		result = this.workerRepository.findOne(workerId);

		return result;
	}

	public void save(final Worker worker) {
		Assert.notNull(worker);

		this.workerRepository.save(worker);
	}

	public void delete(final Worker worker) {
		this.workerRepository.delete(worker);
	}

	//Other Methods--------------------

	public Worker findByPrincipal() {
		UserAccount userAccount;
		Worker result;

		userAccount = LoginService.getPrincipal();
		result = this.findByUserAccount(userAccount);
		return result;
	}

	public Worker findByUserAccount(final UserAccount userAccount) {
		Worker result;

		result = this.workerRepository.findByUserAccount(userAccount.getId());
		return result;
	}

	public Collection<Worker> findWorkersByCircus() {
		return this.workerRepository.findWorkersByCircus(this.findByPrincipal().getCircus().getId());
	}
}
