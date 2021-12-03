package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.requests.auth.LoginRequest;
import com.etiya.rentACarSpring.business.requests.auth.RegisterCorporateCustomerRequest;
import com.etiya.rentACarSpring.business.requests.auth.RegisterIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;


public interface AuthService {

	Result login(LoginRequest loginRequest);
	Result individualCustomerRegister(RegisterIndividualCustomerRequest registerIndividualCustomerRequest);
	Result corporateCustomerRegister(RegisterCorporateCustomerRequest registerCorporateCustomerRequest);

	
}
