package com.etiya.rentACarSpring.ws;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateCarRequest;
import com.etiya.rentACarSpring.business.requests.DeleteCarRequest;
import com.etiya.rentACarSpring.business.requests.UpdateCarRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetail;
import com.etiya.rentACarSpring.entities.complexTypes.CarImageDetail;



@RestController
@RequestMapping("api/cars")
public class CarsController {
	
	private CarService carService;

	@Autowired
	public CarsController(CarService carService) {
		this.carService = carService;
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
		return this.carService.add(createCarRequest);
	}
	
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);
	}
	
	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteCarRequest deleteCarRequest) {
		return this.carService.delete(deleteCarRequest);
	}
	
	@GetMapping("all")
	public DataResult<List<CarSearchListDto>> getAll() {
		return this.carService.getAll();
	}
	
	@GetMapping("detailedCars")
	public DataResult<List<CarDetail>> getDetailedCars() {
		return this.carService.getCarsWithDetails();
	}
	
	@GetMapping("getByModelYear")
	public DataResult<List<CarSearchListDto>> getByModelYear(@RequestParam int modelYear) {
		return this.carService.getByModelYear(modelYear);
	}
	
	@GetMapping("getByBrandName")
	public DataResult<List<CarSearchListDto>> getByBrandName(@RequestParam String brandName) {
		return this.carService.getByBrandName(brandName);
	}
	
	@GetMapping("getOneCarWithDetails")
	public DataResult<List<CarDetail>> getOneCarWithDetails(int carId) {
		return this.carService.getOneCarWithDetails(carId);
	}
	
	@GetMapping("getCarsWithBrandAndColorDetails")
	public DataResult<List<CarDetail>> getCarsWithBrandAndColorDetails(){
		return this.carService.getCarsWithBrandAndColorDetails();
	}
	

}
