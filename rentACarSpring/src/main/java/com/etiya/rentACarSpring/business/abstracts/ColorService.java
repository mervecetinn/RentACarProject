package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateColorRequest;
import com.etiya.rentACarSpring.business.requests.DeleteColorRequest;
import com.etiya.rentACarSpring.business.requests.UpdateColorRequest;

public interface ColorService {
	void add(CreateColorRequest createColorRequest);

	void update(UpdateColorRequest updateColorRequest);

	void delete(DeleteColorRequest deleteColorRequest);

	List<ColorSearchListDto> getAll();
}
