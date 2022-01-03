package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.*;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.requests.create.CreateCorporateCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiya.rentACarSpring.business.requests.auth.LoginRequest;
import com.etiya.rentACarSpring.business.requests.auth.RegisterCorporateCustomerRequest;
import com.etiya.rentACarSpring.business.requests.auth.RegisterIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.create.CreateIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.entities.ApplicationUser;


@Service
public class AuthManager implements AuthService {
	private UserService userService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;
	private ModelMapperService modelMapperService;
	private MessageService messageService;

	@Autowired
	public AuthManager(UserService userService,IndividualCustomerService individualCustomerService,
					   CorporateCustomerService corporateCustomerService,ModelMapperService modelMapperService,
					   MessageService messageService) {
		super();
		this.userService = userService;
		this.individualCustomerService=individualCustomerService;
		this.corporateCustomerService=corporateCustomerService;
		this.modelMapperService=modelMapperService;
		this.messageService=messageService;
	}

	@Override
	public Result login(LoginRequest loginRequest) {
		Result result=BusinessRules.run(checkIfUserExistsAndPasswordTrue(loginRequest.getEmail(), loginRequest.getPassword()));
		
		if(result!=null) {
			return result;
		}
		return new SuccessResult(this.messageService.getMessage(Messages.LoginSuccessful));
	}

	@Override
	public Result individualCustomerRegister(RegisterIndividualCustomerRequest registerIndividualCustomerRequest) {
		Result result=BusinessRules.run(checkIfUserAlreadyExists(registerIndividualCustomerRequest.getEmail()));
		
		if(result!=null) {
			return result;
		}
		
		CreateIndividualCustomerRequest createIndividualCustomerRequest=this.modelMapperService.forRequest().map(registerIndividualCustomerRequest, CreateIndividualCustomerRequest.class);
		this.individualCustomerService.add(createIndividualCustomerRequest);
		return new SuccessResult(this.messageService.getMessage(Messages.RegisterSuccessful));
	}
	
	@Override
	public Result corporateCustomerRegister(
			RegisterCorporateCustomerRequest registerCorporateCustomerRequest) {
		Result result = BusinessRules.run(checkIfUserAlreadyExists(registerCorporateCustomerRequest.getEmail()));

		if (result != null) {
			return result;

		}
		CreateCorporateCustomerRequest createCorporateCustomerRequest=this.modelMapperService.forRequest().map(registerCorporateCustomerRequest,CreateCorporateCustomerRequest.class);
		this.corporateCustomerService.add(createCorporateCustomerRequest);
		return new SuccessResult(this.messageService.getMessage(Messages.RegisterSuccessful));

	}

	
	private Result checkIfUserExists(String email) {
		if (this.userService.getByEmail(email).isSuccess()) {
			return new SuccessResult();
		}
		return new ErrorResult();

	}

	private Result checkIfUserExistsAndPasswordTrue(String email,String password) {
		ApplicationUser user=this.userService.getByEmail(email).getData();
		if(checkIfUserExists(email).isSuccess()&&user.getPassword().equals(password)) {
			
			return new SuccessResult();
		}
		return new ErrorResult(this.messageService.getMessage(Messages.UserNameOrPasswordWrong));
			
	}
	
	private Result checkIfUserAlreadyExists(String email) {
		if(this.userService.getByEmail(email).isSuccess()) {
			return new ErrorResult(this.messageService.getMessage(Messages.UserAlreadyExists));
		}
		return new SuccessResult();
	}
	

}
