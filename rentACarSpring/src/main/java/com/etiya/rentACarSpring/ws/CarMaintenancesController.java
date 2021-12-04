package com.etiya.rentACarSpring.ws;

import javax.validation.Valid;

import com.etiya.rentACarSpring.business.dtos.CarMaintenanceSearchListDto;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCarMaintenanceRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCarMaintenanceRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.etiya.rentACarSpring.business.abstracts.CarMaintenanceService;
import com.etiya.rentACarSpring.business.requests.create.CreateCarMaintenanceRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

import java.util.List;

@RestController
@RequestMapping("api/carMaintenances")
public class CarMaintenancesController {
	
	private CarMaintenanceService carMaintenanceService;
	
	@Autowired
	public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
		super();
		this.carMaintenanceService = carMaintenanceService;
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		return this.carMaintenanceService.add(createCarMaintenanceRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		return this.carMaintenanceService.update(updateCarMaintenanceRequest);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
		return this.carMaintenanceService.delete(deleteCarMaintenanceRequest);
	}

	@GetMapping("all")
	public DataResult<List<CarMaintenanceSearchListDto>> getAll() {
		return this.carMaintenanceService.getAll();
	}

}
