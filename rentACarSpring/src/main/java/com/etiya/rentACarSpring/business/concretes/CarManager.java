package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateCarRequest;
import com.etiya.rentACarSpring.business.requests.DeleteCarRequest;
import com.etiya.rentACarSpring.business.requests.UpdateCarRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.CarDao;
import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetail;

@Service
public class CarManager implements CarService {
	
	private CarDao carDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
		
		this.carDao.save(car);
		return new SuccessResult("Car added.");
		
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult("Car updated.");
		
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		
		this.carDao.deleteById(deleteCarRequest.getId());
		return new SuccessResult("Car deleted.");
		
	}

	@Override
	public DataResult<List<CarSearchListDto>> getAll() {
		List<Car> result = this.carDao.findAll();
		List<CarSearchListDto> response = result.stream()
				.map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarSearchListDto>>(response) ;
	}

	@Override
	public DataResult<List<CarDetail>> getCarWithBrandAndColorDetails() {
		List<CarDetail> result=this.carDao.getCarWithBrandAndColorDetails();
		return new SuccessDataResult<List<CarDetail>>(result,"Cars are listed with color and brand names.");
		
	}

	@Override
	public DataResult<List<CarSearchListDto>> getByModelYear(int modelYear) {
		
		List<CarSearchListDto> result = this.carDao.getByModelYear(modelYear).stream()
				.map(car->modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());
				
		
		return new SuccessDataResult<List<CarSearchListDto>>(result,"Cars listed.") ;
	}

	@Override
	public DataResult<List<CarSearchListDto>> getByBrandName(String brandName) {
		List<CarSearchListDto> result=this.carDao.getByBrandName(brandName);
		return new SuccessDataResult<List<CarSearchListDto>>(result,"Cars are listed with color and brand names.");
	}

}
