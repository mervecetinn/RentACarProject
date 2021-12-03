package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.CityService;
import com.etiya.rentACarSpring.business.requests.create.CreateCityRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCityRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCityRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.CityDao;
import com.etiya.rentACarSpring.entities.City;

@Service
public class CityManager implements CityService {
	
	private CityDao cityDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
		super();
		this.cityDao = cityDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCityRequest createCityRequest) {
		City city=this.modelMapperService.forRequest().map(createCityRequest, City.class);
		this.cityDao.save(city);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateCityRequest updateCityRequest) {
		City city=this.cityDao.getById(updateCityRequest.getId());
		city.setName(updateCityRequest.getName());
		this.cityDao.save(city);
		return new SuccessResult();
	}

	@Override
	public Result delete(DeleteCityRequest deleteCityRequest) {
		this.cityDao.deleteById(deleteCityRequest.getId());
		return new SuccessResult();
	}

	@Override
	public DataResult<City> getById(int cityId) {
		return new SuccessDataResult<City>(this.cityDao.getById(cityId));
	}

	@Override
	public Result checkCityExists(int id) {
		if(this.cityDao.existsById(id)==true){
			return  new SuccessResult();
		}
		return  new ErrorResult();
	}

}
