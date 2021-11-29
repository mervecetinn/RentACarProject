package com.etiya.rentACarSpring.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.AuthService;
import com.etiya.rentACarSpring.business.abstracts.UserService;
import com.etiya.rentACarSpring.business.requests.CreateIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.LoginRequest;
import com.etiya.rentACarSpring.business.requests.RegisterIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.entities.ApplicationUser;

@Service
public class AuthManager implements AuthService {
	private UserService userService;

	@Autowired
	public AuthManager(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public Result login(LoginRequest loginRequest) {
		Result result=BusinessRules.run(checkIfUserExists(loginRequest.getEmail()),checkIfPasswordTrue(loginRequest.getEmail(), loginRequest.getPassword()));
		
		if(result!=null) {
			return result;
		}
		return new SuccessResult("Login işlemi başarılı.");
	}

	@Override
	public Result individualCustomerRegister(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		Result result=BusinessRules.run(checkIfUserAlreadyExists(createIndividualCustomerRequest.getEmail()),checkIfPasswordsMatch(createIndividualCustomerRequest.getPassword(), createIndividualCustomerRequest.getPasswordRepeat()));
		
		if(result!=null) {
			return result;
		}
		
		return null;
	}

	private Result checkIfUserExists(String email) {
		if (this.userService.getByEmail(email).isSuccess()) {
			return new SuccessResult();
		}
		return new ErrorResult("Bu emaile sahip bir kullanıcı bulunamadı.");

	}

	private Result checkIfPasswordTrue(String email,String password) {
		ApplicationUser user=this.userService.getByEmail(email).getData();
		if(user!=null) {
			if(user.getPassword()==password) {
				return new SuccessResult();
			}
			return new ErrorResult("Şifre yanlış");
		}
		return new ErrorResult();
		
	}
	
	private Result checkIfUserAlreadyExists(String email) {
		if(this.userService.getByEmail(email).isSuccess()) {
			return new ErrorResult("Bu emaile sahip kullanıcı bulunmaktadır.");
		}
		return new SuccessResult();
	}
	
	private Result checkIfPasswordsMatch(String password,String passwordRepeat) {
		if(password.equals(passwordRepeat)) {
			return new SuccessResult();
		}
		return new ErrorResult("Şifreler eşleşmiyor.");
	}

}
