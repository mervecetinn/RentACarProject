package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.CarDamageSearchListDto;
import com.etiya.rentACarSpring.business.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateCarDamageRequest;
import com.etiya.rentACarSpring.business.requests.create.CreateColorRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCarDamageRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteColorRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCarDamageRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateColorRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

import java.util.List;

public interface CarDamageService {
    Result add(CreateCarDamageRequest createCarDamageRequest);

    Result update(UpdateCarDamageRequest updateCarDamageRequest);

    Result delete(DeleteCarDamageRequest deleteCarDamageRequest);

    DataResult<List<CarDamageSearchListDto>> getAll();
}
