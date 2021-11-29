package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.requests.CreateIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.LoginRequest;
import com.etiya.rentACarSpring.business.requests.RegisterIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface AuthService {

	Result login(LoginRequest loginRequest);
	Result individualCustomerRegister(CreateIndividualCustomerRequest createIndividualCustomerRequest);
}
