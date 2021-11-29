package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.requests.auth.LoginRequest;
import com.etiya.rentACarSpring.business.requests.auth.RegisterIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.create.CreateIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface AuthService {

	Result login(LoginRequest loginRequest);
	Result individualCustomerRegister(CreateIndividualCustomerRequest createIndividualCustomerRequest);
}
