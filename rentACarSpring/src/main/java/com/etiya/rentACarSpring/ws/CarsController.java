package com.etiya.rentACarSpring.ws;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateCarRequest;
import com.etiya.rentACarSpring.business.requests.DeleteCarRequest;
import com.etiya.rentACarSpring.business.requests.UpdateCarRequest;


@RestController
@RequestMapping("api/cars")
public class CarsController {
	
	private CarService carService;

	@Autowired
	public CarsController(CarService carService) {
		this.carService = carService;
	}

	@PostMapping("add")
	public void add(@RequestBody CreateCarRequest createCarRequest) {
		this.carService.add(createCarRequest);
	}
	
	@PutMapping("update")
	public void update(@RequestBody UpdateCarRequest updateCarRequest) {
		this.carService.update(updateCarRequest);
	}
	
	@DeleteMapping("delete")
	public void delete(@RequestBody DeleteCarRequest deleteCarRequest) {
		this.carService.delete(deleteCarRequest);
	}
	
	@GetMapping("all")
	public List<CarSearchListDto> getAll() {
		return this.carService.getAll();
	}

}
