package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateCarRequest;
import com.etiya.rentACarSpring.business.requests.DeleteCarRequest;
import com.etiya.rentACarSpring.business.requests.UpdateCarRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetail;
import com.etiya.rentACarSpring.entities.complexTypes.CarImageDetail;


public interface CarService {
	Result add(CreateCarRequest createCarRequest);

	Result update(UpdateCarRequest updateCarRequest);

	Result delete(DeleteCarRequest deleteCarRequest);

	DataResult<List<CarSearchListDto>> getAll();
	
	DataResult<List<CarDetail>> getCarsWithBrandAndColorDetails();
	
	DataResult<List<CarDetail>> getCarsWithDetails();
	
	DataResult<List<CarDetail>> getOneCarWithDetails(int carId);
	
	DataResult<List<CarSearchListDto>> getByModelYear(int modelYear);
	
	DataResult<List<CarSearchListDto>> getByBrandName(String brandName);
	
	DataResult<List<CarSearchListDto>> getByBrandId(int brandId);
	
	DataResult<List<CarSearchListDto>> getByColorId(int colorId);
	
	DataResult<Car> getById(int id);
	
	
}
