package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateCarRequest;
import com.etiya.rentACarSpring.business.requests.DeleteCarRequest;
import com.etiya.rentACarSpring.business.requests.UpdateCarRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.CarDao;
import com.etiya.rentACarSpring.entities.Car;

@Service
public class CarManager implements CarService {
	
	private CarDao carDao;
	private ModelMapperService modelMapperService;

	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public void add(CreateCarRequest createCarRequest) {
		Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		
	}

	@Override
	public void update(UpdateCarRequest updateCarRequest) {
		Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(car);
		
	}

	@Override
	public void delete(DeleteCarRequest deleteCarRequest) {
		Car car = modelMapperService.forRequest().map(deleteCarRequest, Car.class);
		this.carDao.delete(car);
		
	}

	@Override
	public List<CarSearchListDto> getAll() {
		List<Car> result = this.carDao.findAll();
		List<CarSearchListDto> response = result.stream()
				.map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class))
				.collect(Collectors.toList());
		
		return response;
	}

}
