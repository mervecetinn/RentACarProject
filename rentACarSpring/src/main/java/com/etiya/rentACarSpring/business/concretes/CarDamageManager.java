package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.CarDamageService;
import com.etiya.rentACarSpring.business.dtos.CarDamageSearchListDto;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateCarDamageRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCarDamageRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCarDamageRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
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

    @Autowired
    public CarDamageManager(CarDamageDao carDamageDao,ModelMapperService modelMapperService){
        this.carDamageDao=carDamageDao;
        this.modelMapperService=modelMapperService;
    }

    @Override
    public Result add(CreateCarDamageRequest createCarDamageRequest) {
        CarDamage carDamage=this.modelMapperService.forRequest().map(createCarDamageRequest,CarDamage.class);
        this.carDamageDao.save(carDamage);
        return  new SuccessResult();
    }

    @Override
    public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
        CarDamage carDamage=this.carDamageDao.getById(updateCarDamageRequest.getId());
        carDamage.setDamageInformation(updateCarDamageRequest.getDamageInformation());
        this.carDamageDao.save(carDamage);
        return new SuccessResult();
    }

    @Override
    public Result delete(DeleteCarDamageRequest deleteCarDamageRequest) {
        this.carDamageDao.deleteById(deleteCarDamageRequest.getId());
        return new SuccessResult();
    }

    @Override
    public DataResult<List<CarDamageSearchListDto>> getAll() {
        List<CarDamage> result=this.carDamageDao.findAll();
        List<CarDamageSearchListDto> response=result.stream()
                .map(carDamage -> modelMapperService.forDto().map(carDamage,CarDamageSearchListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<CarDamageSearchListDto>>(response);
    }
}
