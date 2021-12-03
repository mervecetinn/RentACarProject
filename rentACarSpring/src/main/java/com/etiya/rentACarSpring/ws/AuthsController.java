package com.etiya.rentACarSpring.ws;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.business.abstracts.AuthService;
import com.etiya.rentACarSpring.business.requests.auth.LoginRequest;
import com.etiya.rentACarSpring.business.requests.auth.RegisterCorporateCustomerRequest;
import com.etiya.rentACarSpring.business.requests.auth.RegisterIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

@RestController
@RequestMapping("api/authentications")
public class AuthsController {
	
	private AuthService authService;
	
	public AuthsController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("login")
	public Result login(@RequestBody @Valid LoginRequest loginRequest) {
		return this.authService.login(loginRequest);
	}
	
	@PostMapping("individualCustomerRegister")
	public Result individualCustomerRegister(@RequestBody @Valid RegisterIndividualCustomerRequest registerIndividualCustomerRequest) {
		return this.authService.individualCustomerRegister(registerIndividualCustomerRequest);
	}
	
	@PostMapping("Corporate Customer Register")
	public Result corporateCustomerRegister
			(@RequestBody @Valid  RegisterCorporateCustomerRequest registerCorporateCustomerRequest){
		return this.authService.corporateCustomerRegister(registerCorporateCustomerRequest);	
	}

}
