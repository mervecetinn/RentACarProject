package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateCarRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCarRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCarRequest;
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

	DataResult<List<CarSearchListDto>> getByCityId(int cityId);

	DataResult<List<CarSearchListDto>> getCarsNotOnMaintenance();

	Result updateCarKilometer(int carId, int kilometer);
	
	DataResult<Car> getById(int id);
	
	Result checkCarIsNotOnRent(int id);
	
	Result checkCarIsNotOnMaintenance(int id);
	
	Result checkCarExists(int id);


	
	
}
