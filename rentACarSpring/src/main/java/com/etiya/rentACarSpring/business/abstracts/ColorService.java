package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateColorRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteColorRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateColorRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface ColorService {
	Result add(CreateColorRequest createColorRequest);

	Result update(UpdateColorRequest updateColorRequest);

	Result delete(DeleteColorRequest deleteColorRequest);

	DataResult<List<ColorSearchListDto>> getAll();

}
