package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.AdditionalItemSearchListDto;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateAdditionalItemRequest;
import com.etiya.rentACarSpring.business.requests.create.CreateCarRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteAdditionalItemRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCarRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateAdditionalItemRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCarRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.AdditionalItem;

import java.util.List;

public interface AdditionalItemService {

    Result add(CreateAdditionalItemRequest createAdditionalItemRequest);

    Result update(UpdateAdditionalItemRequest updateAdditionalItemRequest);

    Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest);

    DataResult<List<AdditionalItemSearchListDto>> getAll();

    DataResult<AdditionalItem> getById(int id);
}
