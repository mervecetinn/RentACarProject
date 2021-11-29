package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.requests.CreateCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.DeleteCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.UpdateCreditCardRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface CreditCardService {
	Result add(CreateCreditCardRequest createCreditCardRequest);
	Result update(UpdateCreditCardRequest updateCreditCardRequest);
	Result delete(DeleteCreditCardRequest deleteCreditCardRequest);
}
