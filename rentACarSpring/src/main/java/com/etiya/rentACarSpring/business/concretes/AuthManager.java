package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.requests.create.CreateCorporateCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.AuthService;
import com.etiya.rentACarSpring.business.abstracts.CorporateCustomerService;
import com.etiya.rentACarSpring.business.abstracts.IndividualCustomerService;
import com.etiya.rentACarSpring.business.abstracts.UserService;
import com.etiya.rentACarSpring.business.requests.auth.LoginRequest;
import com.etiya.rentACarSpring.business.requests.auth.RegisterCorporateCustomerRequest;
import com.etiya.rentACarSpring.business.requests.auth.RegisterIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.create.CreateIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorDataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.entities.ApplicationUser;
import com.etiya.rentACarSpring.entities.CorporateCustomer;

@Service
public class AuthManager implements AuthService {
	private UserService userService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;
	private ModelMapperService modelMapperService;

	@Autowired
	public AuthManager(UserService userService,IndividualCustomerService individualCustomerService,CorporateCustomerService corporateCustomerService,ModelMapperService modelMapperService) {
		super();
		this.userService = userService;
		this.individualCustomerService=individualCustomerService;
		this.corporateCustomerService=corporateCustomerService;
		this.modelMapperService=modelMapperService;
	}

	@Override
	public Result login(LoginRequest loginRequest) {
		Result result=BusinessRules.run(checkIfUserExistsAndPasswordTrue(loginRequest.getEmail(), loginRequest.getPassword()));
		
		if(result!=null) {
			return result;
		}
		return new SuccessResult("Login işlemi başarılı.");
	}

	@Override
	public Result individualCustomerRegister(RegisterIndividualCustomerRequest registerIndividualCustomerRequest) {
		Result result=BusinessRules.run(checkIfUserAlreadyExists(registerIndividualCustomerRequest.getEmail()),checkEmailFormat(registerIndividualCustomerRequest.getEmail()));
		
		if(result!=null) {
			return result;
		}
		
		CreateIndividualCustomerRequest createIndividualCustomerRequest=this.modelMapperService.forRequest().map(registerIndividualCustomerRequest, CreateIndividualCustomerRequest.class);
		this.individualCustomerService.add(createIndividualCustomerRequest);
		return new SuccessResult();
	}
	
	@Override
	public Result corporateCustomerRegister(
			RegisterCorporateCustomerRequest registerCorporateCustomerRequest) {
		Result result = BusinessRules.run(checkIfUserAlreadyExists(registerCorporateCustomerRequest.getEmail()),checkEmailFormat(registerCorporateCustomerRequest.getEmail()));

		if (result != null) {
			return result;

		}
		CreateCorporateCustomerRequest createCorporateCustomerRequest=this.modelMapperService.forRequest().map(registerCorporateCustomerRequest,CreateCorporateCustomerRequest.class);
		this.corporateCustomerService.add(createCorporateCustomerRequest);
		return new SuccessResult("Tebrikler, kayıt işleminiz başarıyla gerçekleşti.");

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
		return new ErrorResult("Kullanıcı adı veya şifre hatalı");
			
	}
	
	private Result checkIfUserAlreadyExists(String email) {
		if(this.userService.getByEmail(email).isSuccess()) {
			return new ErrorResult("Bu emaile sahip kullanıcı bulunmaktadır.");
		}
		return new SuccessResult();
	}
	
	private Result checkEmailFormat(String email) {
		String pattern = "^[A-Za-z0-9+_.-]+@(.+)$";
		if (email.matches(pattern)) {
			return new SuccessResult();
		}
		
		return new ErrorResult("Email is not valid.");
		
	}
//	private Result checkIfPasswordsMatch(String password,String passwordRepeat) {
//		if(password.equals(passwordRepeat)) {
//			return new SuccessResult();
//		}
//		return new ErrorResult("Şifreler eşleşmiyor.");
//	}

}
