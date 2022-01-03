package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.CreditCardSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCreditCardRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import java.util.List;

public interface CreditCardService {
	Result add(CreateCreditCardRequest createCreditCardRequest);
	Result update(UpdateCreditCardRequest updateCreditCardRequest);
	Result delete(DeleteCreditCardRequest deleteCreditCardRequest);
	DataResult<List<CreditCardSearchListDto>> getAll();
}
