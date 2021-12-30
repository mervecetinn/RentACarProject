package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.CarDamageService;
import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.CarDamageSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateCarDamageRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCarDamageRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCarDamageRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.CarDamageDao;
import com.etiya.rentACarSpring.entities.CarDamage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarDamageManager implements CarDamageService {

    private CarDamageDao carDamageDao;
    private ModelMapperService modelMapperService;
    private MessageService messageService;
    private CarService carService;

    @Autowired
    public CarDamageManager(CarDamageDao carDamageDao,ModelMapperService modelMapperService,MessageService messageService,CarService carService){
        this.carDamageDao=carDamageDao;
        this.modelMapperService=modelMapperService;
        this.messageService=messageService;
        this.carService=carService;
    }

    @Override
    public Result add(CreateCarDamageRequest createCarDamageRequest) {
        Result result = BusinessRules.run(checkIfCarIsNotExists(createCarDamageRequest.getCarId()));

        if (result != null) {
            return result;
        }
        CarDamage carDamage=this.modelMapperService.forRequest().map(createCarDamageRequest,CarDamage.class);
        this.carDamageDao.save(carDamage);
        return  new SuccessResult(this.messageService.getMessage(Messages.CarDamageAdded));
    }

    @Override
    public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
        Result result = BusinessRules.run(checkIfCarDamageIsNotExists(updateCarDamageRequest.getId()));
        if (result != null) {
            return result;
        }
        CarDamage carDamage=this.carDamageDao.getById(updateCarDamageRequest.getId());
        carDamage.setDamageInformation(updateCarDamageRequest.getDamageInformation());
        this.carDamageDao.save(carDamage);
        return new SuccessResult(this.messageService.getMessage(Messages.CarDamageUpdated));
    }

    @Override
    public Result delete(DeleteCarDamageRequest deleteCarDamageRequest) {
        Result result = BusinessRules.run(checkIfCarDamageIsNotExists(deleteCarDamageRequest.getId()));
        if (result != null) {
            return result;
        }
        this.carDamageDao.deleteById(deleteCarDamageRequest.getId());
        return new SuccessResult(this.messageService.getMessage(Messages.CarDamageDeleted));
    }

    @Override
    public DataResult<List<CarDamageSearchListDto>> getAll() {
        List<CarDamage> result=this.carDamageDao.findAll();
        List<CarDamageSearchListDto> response=result.stream()
                .map(carDamage -> modelMapperService.forDto().map(carDamage,CarDamageSearchListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<CarDamageSearchListDto>>(response,this.messageService.getMessage(Messages.CarDamagesListed));
    }

    private Result checkIfCarIsNotExists(int carId){
       if(!this.carService.checkCarExists(carId).isSuccess()){
           return new ErrorResult(this.messageService.getMessage(Messages.CarNotFound));
       }
       return new SuccessResult();
    }

    private Result checkIfCarDamageIsNotExists(int id) {
        if (!this.carDamageDao.existsById(id)) {
            return new ErrorResult(this.messageService.getMessage(Messages.CarDamageNotFound));

        }
        return new SuccessResult();

    }
}
