package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.IndividualCustomerSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.IndividualCustomer;

public interface IndividualCustomerService {

	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);
	DataResult<List<IndividualCustomerSearchListDto>> getAll();
	DataResult<IndividualCustomer> getById(int id);
}
