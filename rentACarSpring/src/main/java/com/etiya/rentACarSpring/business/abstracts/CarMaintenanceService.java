package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.CarMaintenanceSearchListDto;
import com.etiya.rentACarSpring.business.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateCarMaintenanceRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCarMaintenanceRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCarMaintenanceRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

import java.util.List;

public interface CarMaintenanceService {

	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);

	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);

	Result delete(DeleteCarMaintenanceRequest deleteMaintenanceCarRequest);

	DataResult<List<CarMaintenanceSearchListDto>> getAll();

}
