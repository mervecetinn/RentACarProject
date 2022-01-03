package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.RentalAdditionalSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateRentalAdditionalRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

import java.util.List;

public interface RentalAdditionalService {

    Result add(CreateRentalAdditionalRequest createRentalAdditionalRequest);

    Result update(UpdateRentalAdditionalRequest updateRentalAdditionalRequest);

    Result delete(DeleteRentalAdditionalRequest deleteRentalAdditionalRequest);

    DataResult<List<RentalAdditionalSearchListDto>> getAll();



}
