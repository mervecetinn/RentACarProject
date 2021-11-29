package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.requests.create.CreateCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCreditCardRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface CreditCardService {
	Result add(CreateCreditCardRequest createCreditCardRequest);
	Result update(UpdateCreditCardRequest updateCreditCardRequest);
	Result delete(DeleteCreditCardRequest deleteCreditCardRequest);
}
