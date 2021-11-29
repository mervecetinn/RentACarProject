package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.AuthService;
import com.etiya.rentACarSpring.business.abstracts.IndividualCustomerService;
import com.etiya.rentACarSpring.business.dtos.IndividualCustomerSearchListDto;
import com.etiya.rentACarSpring.business.requests.auth.RegisterIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.create.CreateIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
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
	private AuthService authService;

	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao,
			ModelMapperService modelMapperService,AuthService authService) {
		super();
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapperService = modelMapperService;
		this.authService=authService;

	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		Result result=this.authService.individualCustomerRegister(createIndividualCustomerRequest);
		
		if(result!=null) {
			return result;
		}
		
		ApplicationUser user = new ApplicationUser();
		user.setEmail(createIndividualCustomerRequest.getEmail());
		user.setPassword(createIndividualCustomerRequest.getPassword());
		IndividualCustomer individualCustomer = modelMapperService.forRequest().map(createIndividualCustomerRequest,
				IndividualCustomer.class);
		individualCustomer.setApplicationUser(user);
		individualCustomer.setFindexScore((int)(Math.random() *1900));
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		IndividualCustomer individualCustomer = modelMapperService.forRequest().map(updateIndividualCustomerRequest,
				IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult();
	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		this.individualCustomerDao.deleteById(deleteIndividualCustomerRequest.getId());
		return new SuccessResult();
	}

	@Override
	public DataResult<List<IndividualCustomerSearchListDto>> getAll() {
		List<IndividualCustomer> result = this.individualCustomerDao.findAll();
		List<IndividualCustomerSearchListDto> response = result.stream().map(individualCustomer -> modelMapperService
				.forDto().map(individualCustomer, IndividualCustomerSearchListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<IndividualCustomerSearchListDto>>(response);
	}

	@Override
	public DataResult<IndividualCustomer> getById(int id) {
		IndividualCustomer ic=this.individualCustomerDao.getById(id);
		return new SuccessDataResult<IndividualCustomer>(ic);
	}

}
