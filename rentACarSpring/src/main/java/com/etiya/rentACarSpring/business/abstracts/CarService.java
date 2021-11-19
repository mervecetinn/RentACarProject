package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateCarRequest;
import com.etiya.rentACarSpring.business.requests.DeleteCarRequest;
import com.etiya.rentACarSpring.business.requests.UpdateCarRequest;

public interface CarService {
	void add(CreateCarRequest createCarRequest);

	void update(UpdateCarRequest updateCarRequest);

	void delete(DeleteCarRequest deleteCarRequest);

	List<CarSearchListDto> getAll();
}
