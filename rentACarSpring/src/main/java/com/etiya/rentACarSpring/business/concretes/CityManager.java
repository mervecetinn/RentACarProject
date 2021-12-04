package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.CitySearchListDto;
import com.etiya.rentACarSpring.business.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.entities.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.CityService;
import com.etiya.rentACarSpring.business.requests.create.CreateCityRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCityRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCityRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.CityDao;
import com.etiya.rentACarSpring.entities.City;

import java.util.List;
import java.util.stream.Collectors;

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
		return new SuccessResult(Messages.DataAdded);
	}

	@Override
	public Result update(UpdateCityRequest updateCityRequest) {
		City city=this.cityDao.getById(updateCityRequest.getId());
		city.setName(updateCityRequest.getName());
		this.cityDao.save(city);
		return new SuccessResult(Messages.DataUpdated);
	}

	@Override
	public Result delete(DeleteCityRequest deleteCityRequest) {
		this.cityDao.deleteById(deleteCityRequest.getId());
		return new SuccessResult(Messages.DataDeleted);
	}

	@Override
	public DataResult<List<CitySearchListDto>> getAll() {
		List<City> result = this.cityDao.findAll();
		List<CitySearchListDto> response = result.stream()
				.map(city -> modelMapperService.forDto().map(city, CitySearchListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<CitySearchListDto>>(response);
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
