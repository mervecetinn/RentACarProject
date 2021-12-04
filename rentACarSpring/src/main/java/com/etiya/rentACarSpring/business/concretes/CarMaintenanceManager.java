package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.CarMaintenanceSearchListDto;
import com.etiya.rentACarSpring.business.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.entities.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.CarMaintenanceService;
import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.requests.create.CreateCarMaintenanceRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCarMaintenanceRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCarMaintenanceRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.CarMaintenanceDao;
import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.CarMaintenance;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private CarService carService;

	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService,
			CarService carService) {
		super();
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
 
		Result result=BusinessRules.run(checkCarIsNotOnRent(createCarMaintenanceRequest.getCarId()),checkIfCarIsNotExists(createCarMaintenanceRequest.getCarId()),
				checkCarIsNotOnMaintenance(createCarMaintenanceRequest.getCarId()));
		
		if(result!=null) {
			return result;
		}
		Car car=this.carService.getById(createCarMaintenanceRequest.getCarId()).getData();
		CarMaintenance carMaintenance=modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
		carMaintenance.setCar(car);
		this.carMaintenanceDao.save(carMaintenance);
		
		
		return new SuccessResult(Messages.CarMaintenanceAdded);
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {

		CarMaintenance carMaintenance=this.carMaintenanceDao.getById(updateCarMaintenanceRequest.getId());
		CarMaintenance updatedCarMaintenance=this.modelMapperService.forRequest().map(updateCarMaintenanceRequest,CarMaintenance.class);
		updatedCarMaintenance.setCar(carMaintenance.getCar());
		this.carMaintenanceDao.save(updatedCarMaintenance);
		return new SuccessResult(Messages.CarMaintenanceUpdated);
	}

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteMaintenanceCarRequest) {
		this.carMaintenanceDao.deleteById(deleteMaintenanceCarRequest.getId());
		return new SuccessResult(Messages.CarMaintenanceDeleted);
	}

	@Override
	public DataResult<List<CarMaintenanceSearchListDto>> getAll() {
		List<CarMaintenance> result = this.carMaintenanceDao.findAll();
		List<CarMaintenanceSearchListDto> response = result.stream()
				.map(carMaintenance -> modelMapperService.forDto().map(carMaintenance, CarMaintenanceSearchListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<CarMaintenanceSearchListDto>>(response);
	}

	private Result checkCarIsNotOnRent(int carId) {
		if(!this.carService.checkCarIsNotOnRent(carId).isSuccess()) {
			return new ErrorResult(Messages.CarIsOnRent);
		}
		
		return new SuccessResult();
	}

	private Result checkIfCarIsNotExists(int carId) {
		if(!this.carService.checkCarExists(carId).isSuccess()) {
			return new ErrorResult(Messages.CarIsNotFound);
		}

		return new SuccessResult();
	}
	private Result checkCarIsNotOnMaintenance(int carId) {
		if (!this.carService.checkCarIsNotOnMaintenance(carId).isSuccess()) {
			return new ErrorResult(Messages.CarIsAlreadyOnMaintenance);
		}
		return new SuccessResult();
	}


}
