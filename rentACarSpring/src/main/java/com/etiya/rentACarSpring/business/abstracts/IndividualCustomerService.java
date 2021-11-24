package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.IndividualCustomerSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.DeleteIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.UpdateIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface IndividualCustomerService {

	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);
	DataResult<List<IndividualCustomerSearchListDto>> getAll();
}
