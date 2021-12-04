package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.CarMaintenanceSearchListDto;
import com.etiya.rentACarSpring.business.dtos.CitySearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateCityRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCityRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCityRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.City;

import java.util.List;

public interface CityService {

	Result add(CreateCityRequest createCityRequest);

	Result update(UpdateCityRequest updateCityRequest);

	Result delete(DeleteCityRequest deleteCityRequest);

	DataResult<List<CitySearchListDto>> getAll();

	DataResult<City> getById(int cityId);

	Result checkCityExists(int id);
}
