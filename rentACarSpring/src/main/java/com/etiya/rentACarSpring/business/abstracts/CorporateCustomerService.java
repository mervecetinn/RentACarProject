package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;
import com.etiya.rentACarSpring.business.dtos.CorporateCustomerSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateCorporateCustomerRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCorporateCustomerRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCorporateCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.CorporateCustomer;

public interface CorporateCustomerService {
	
	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
	Result update (UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
	Result delete (DeleteCorporateCustomerRequest deleteCorporationCustomerRequest);
	DataResult<List<CorporateCustomerSearchListDto>> getAll();
}
