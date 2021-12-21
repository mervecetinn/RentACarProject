package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.abstracts.UserService;
import com.etiya.rentACarSpring.business.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiya.rentACarSpring.business.abstracts.IndividualCustomerService;
import com.etiya.rentACarSpring.business.dtos.IndividualCustomerSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.IndividualCustomerDao;
import com.etiya.rentACarSpring.entities.ApplicationUser;
import com.etiya.rentACarSpring.entities.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

	private IndividualCustomerDao individualCustomerDao;
	private ModelMapperService modelMapperService;
	private UserService userService;
	private MessageService messageService;

	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao,
			ModelMapperService modelMapperService,UserService userService,MessageService messageService) {
		super();
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapperService = modelMapperService;
		this.userService=userService;
		this.messageService=messageService;

	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {

		Result result= BusinessRules.run(checkEmailFormat(createIndividualCustomerRequest.getEmail()));
		if(result!=null) {
			return result;
		}
		ApplicationUser user = new ApplicationUser();
		user.setEmail(createIndividualCustomerRequest.getEmail());
		user.setPassword(createIndividualCustomerRequest.getPassword());
		IndividualCustomer individualCustomer = modelMapperService.forRequest().map(createIndividualCustomerRequest,
				IndividualCustomer.class);
		individualCustomer.setApplicationUser(user);
		this.userService.add(user);
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(this.messageService.getMessage(Messages.IndividualCustomerAdded));
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		Result result= BusinessRules.run(checkIfIndivudualCustomerIsNotExists(updateIndividualCustomerRequest.getIndividualCustomerId()));
		if(result!=null) {
			return result;
		}
		ApplicationUser user;
		IndividualCustomer individualCustomer=this.individualCustomerDao.getById(updateIndividualCustomerRequest.getIndividualCustomerId());
		user=individualCustomer.getApplicationUser();
		user.setPassword(updateIndividualCustomerRequest.getPassword());
		individualCustomer = modelMapperService.forRequest().map(updateIndividualCustomerRequest,
				IndividualCustomer.class);
		user.setEmail(updateIndividualCustomerRequest.getEmail());
		individualCustomer.setApplicationUser(user);
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(this.messageService.getMessage(Messages.IndividualCustomerUpdated));

	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		Result result= BusinessRules.run(checkIfIndivudualCustomerIsNotExists(deleteIndividualCustomerRequest.getIndividualCustomerId()));
		if(result!=null) {
			return result;
		}
		IndividualCustomer individualCustomer=this.individualCustomerDao.getById(deleteIndividualCustomerRequest.getIndividualCustomerId());
		ApplicationUser user=individualCustomer.getApplicationUser();
		this.userService.delete(user);
		this.individualCustomerDao.deleteById(deleteIndividualCustomerRequest.getIndividualCustomerId());
		return new SuccessResult(this.messageService.getMessage(Messages.IndividualCustomerDeleted));
	}

	@Override
	public DataResult<List<IndividualCustomerSearchListDto>> getAll() {
		List<IndividualCustomer> result = this.individualCustomerDao.findAll();
		List<IndividualCustomerSearchListDto> response = result.stream().map(individualCustomer -> modelMapperService
				.forDto().map(individualCustomer, IndividualCustomerSearchListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<IndividualCustomerSearchListDto>>(response,this.messageService.getMessage(Messages.IndividualCustomersListed));
	}

	@Override
	public DataResult<IndividualCustomer> getById(int id) {
		IndividualCustomer ic = this.individualCustomerDao.getById(id);
		return new SuccessDataResult<IndividualCustomer>(ic);
	}
	
	private Result checkEmailFormat(String email) {
		String pattern = "^[A-Za-z0-9+_.-]+@(.+)$";
		if (email.matches(pattern)) {
			return new SuccessResult();
		}
		
		return new ErrorResult(this.messageService.getMessage(Messages.EmailNotValid));
		
	}

	private Result checkIfIndivudualCustomerIsNotExists(int id){
		if(!this.individualCustomerDao.existsByIndividualCustomerId(id)){
			return new ErrorResult(this.messageService.getMessage(Messages.IndividualCustomerIsNotFound));
		}
		return new SuccessResult();
	}

}
