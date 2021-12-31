package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.CitySearchListDto;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityManager implements CityService {
	
	private CityDao cityDao;
	private ModelMapperService modelMapperService;
	private MessageService messageService;

	@Autowired
	public CityManager(CityDao cityDao, ModelMapperService modelMapperService,MessageService messageService) {
		super();
		this.cityDao = cityDao;
		this.modelMapperService = modelMapperService;
		this.messageService=messageService;
	}

	@Override
	public Result add(CreateCityRequest createCityRequest) {
		Result result = BusinessRules.run(checkIfCityAlreadyExists(createCityRequest.getName()));

		if (result != null) {
			return result;
		}
		City city=this.modelMapperService.forRequest().map(createCityRequest, City.class);
		this.cityDao.save(city);
		return new SuccessResult(this.messageService.getMessage(Messages.CityAdded));
	}

	@Override
	public Result update(UpdateCityRequest updateCityRequest) {
		Result result= BusinessRules.run(checkIfCityNotExists(updateCityRequest.getId()),checkIfCityAlreadyExists(updateCityRequest.getName()));
		if(result!=null){
			return result;
		}
		City city=this.cityDao.getById(updateCityRequest.getId());
		city.setName(updateCityRequest.getName());
		this.cityDao.save(city);
		return new SuccessResult(this.messageService.getMessage(Messages.CityUpdated));
	}

	@Override
	public Result delete(DeleteCityRequest deleteCityRequest) {
		Result result= BusinessRules.run(checkIfCityHasNotAnyCar(deleteCityRequest.getId()),checkIfCityNotExists(deleteCityRequest.getId()));
		if(result!=null){
			return result;
		}
		this.cityDao.deleteById(deleteCityRequest.getId());
		return new SuccessResult(this.messageService.getMessage(Messages.CityDeleted));
	}

	@Override
	public DataResult<List<CitySearchListDto>> getAll() {
		List<City> result = this.cityDao.findAll();
		List<CitySearchListDto> response = result.stream()
				.map(city -> modelMapperService.forDto().map(city, CitySearchListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<CitySearchListDto>>(response,this.messageService.getMessage(Messages.CitiesListed));
	}

	@Override
	public DataResult<City> getById(int cityId) {
		if(!this.cityDao.existsById(cityId)){
			return new ErrorDataResult<>(null);
		}
		return new SuccessDataResult<City>(this.cityDao.getById(cityId));
	}

	@Override
	public Result checkCityExists(int id) {
		if(this.cityDao.existsById(id)==true){
			return  new SuccessResult();
		}
		return  new ErrorResult();
	}

	private Result checkIfCityAlreadyExists(String cityName) {
		List<City> cities=this.cityDao.findAll();
		for(City city:cities){
			if(city.getName().equalsIgnoreCase(cityName.trim())){
				return new ErrorResult(this.messageService.getMessage(Messages.CityAlreadyExists));
			}
		}
		return new SuccessResult();
	}

	private Result checkIfCityHasNotAnyCar(int cityId) {
		List<City> result=this.cityDao.getCitiesRelatedWithCars();
		for(City city:result){
			if(city.getId()==cityId){
				return new ErrorResult(this.messageService.getMessage(Messages.CityCanNotDelete));
			}
		}
		return new SuccessResult();
	}

	private Result checkIfCityNotExists(int cityId){
		if(!this.cityDao.existsById(cityId)){
			return new ErrorResult(this.messageService.getMessage(Messages.CityNotFound));
		}
		return new SuccessResult();
	}

}
