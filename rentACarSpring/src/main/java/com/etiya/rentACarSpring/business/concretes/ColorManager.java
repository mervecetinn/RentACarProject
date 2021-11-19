package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.ColorService;
import com.etiya.rentACarSpring.business.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateColorRequest;
import com.etiya.rentACarSpring.business.requests.DeleteColorRequest;
import com.etiya.rentACarSpring.business.requests.UpdateColorRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.ColorDao;
import com.etiya.rentACarSpring.entities.Color;

@Service
public class ColorManager implements ColorService {
	
	private ColorDao colorDao;
	private ModelMapperService modelMapperService;

	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public void add(CreateColorRequest createColorRequest) {
		Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorDao.save(color);
		
	}

	@Override
	public void update(UpdateColorRequest updateColorRequest) {
		Color color = modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorDao.save(color);
		
	}

	@Override
	public void delete(DeleteColorRequest deleteColorRequest) {
		Color color = modelMapperService.forRequest().map(deleteColorRequest, Color.class);
		this.colorDao.delete(color);
		
	}

	@Override
	public List<ColorSearchListDto> getAll() {
		List<Color> result = this.colorDao.findAll();
		List<ColorSearchListDto> response = result.stream()
				.map(color -> modelMapperService.forDto().map(color, ColorSearchListDto.class))
				.collect(Collectors.toList());
		
		return response;
	}

}
