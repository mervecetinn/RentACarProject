package com.etiya.rentACarSpring.business.abstracts;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.etiya.rentACarSpring.business.dtos.CarImageSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateCarImageRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCarImageRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCarImageRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.CarImage;
import com.etiya.rentACarSpring.entities.complexTypes.CarImageDetail;

public interface CarImageService {
	Result add(CreateCarImageRequest createCarImageRequest) throws IOException;
	Result update(UpdateCarImageRequest updateCarImageRequest) throws IOException;
	Result delete(DeleteCarImageRequest deleteCarImageRequest);
	DataResult<List<CarImageSearchListDto>> getAll();
	
}
