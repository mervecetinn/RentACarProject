package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.requests.CreateCarMaintenanceRequest;
import com.etiya.rentACarSpring.business.requests.DeleteCarMaintenanceRequest;
import com.etiya.rentACarSpring.business.requests.UpdateCarMaintenanceRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface CarMaintenanceService {

	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);

	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);

	Result delete(DeleteCarMaintenanceRequest deleteMaintenanceCarRequest);

}
