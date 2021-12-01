package com.etiya.rentACarSpring.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.CarMaintenanceService;
import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.requests.create.CreateCarMaintenanceRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCarMaintenanceRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCarMaintenanceRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.CarMaintenanceDao;
import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.CarMaintenance;

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
 
		Result result=BusinessRules.run(checkCarIsNotOnRent(createCarMaintenanceRequest.getCarId()));
		
		if(result!=null) {
			return result;
		}
		Car car=this.carService.getById(createCarMaintenanceRequest.getCarId()).getData();
		CarMaintenance carMaintenance=modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
		carMaintenance.setCar(car);
		this.carMaintenanceDao.save(carMaintenance);
		
		
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteMaintenanceCarRequest) {
		this.carMaintenanceDao.deleteById(deleteMaintenanceCarRequest.getId());
		return new SuccessResult();
	}
	
	private Result checkCarIsNotOnRent(int carId) {
		if(!this.carService.checkCarIsNotOnRent(carId).isSuccess()) {
			return new ErrorResult("Car is on rent.");
		}
		
		return new SuccessResult();
	}

//	private Result checkIfCarIsAvailableForMaintenance(int carId) {
//		boolean carIsAvailable = this.carService.getById(carId).getData().isAvailable();
//		if(carIsAvailable) {
//			return new SuccessResult();
//		}
//		return new ErrorResult("Car is not available for maintenance");
//	}

}
