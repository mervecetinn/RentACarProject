package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.results.*;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.abstracts.ColorService;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateColorRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteColorRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateColorRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.ColorDao;
import com.etiya.rentACarSpring.entities.Color;

@Service
public class ColorManager implements ColorService {

	private ColorDao colorDao;
	private ModelMapperService modelMapperService;
	private CarService carService;

	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService, CarService carService) {
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorDao.save(color);
		return new SuccessResult("Color added.");

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {

		Result result= BusinessRules.run(checkIfColorIsNotExists(updateColorRequest.getId()));
		if(result!=null){
			return result;
		}

		Color color = modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorDao.save(color);
		return new SuccessResult("Color updated.");

	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		Result result= BusinessRules.run(checkIfColorIsNotExists(deleteColorRequest.getId()));
        if(result!=null){
			return result;
		}
		this.colorDao.deleteById(deleteColorRequest.getId());
		return new SuccessResult("Color deleted.");

	}

	@Override
	public DataResult<List<ColorSearchListDto>> getAll() {
		List<Color> result = this.colorDao.findAll();
		List<ColorSearchListDto> response = result.stream()
				.map(color -> modelMapperService.forDto().map(color, ColorSearchListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ColorSearchListDto>>(response);
	}

	@Override
	public DataResult<List<CarSearchListDto>> getCarsOfRelatedColor(int colorId) {
		List<CarSearchListDto> result = this.carService.getByColorId(colorId).getData();

		return new SuccessDataResult<List<CarSearchListDto>>(result);
	}

	private Result checkIfColorIsNotExists(int colorId){
		if(!this.colorDao.existsById(colorId)){
			return new ErrorResult("Böyle bir renk yok");
		}
		return new SuccessResult();
	}

}
