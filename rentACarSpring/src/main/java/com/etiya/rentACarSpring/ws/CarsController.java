package com.etiya.rentACarSpring.ws;

import java.util.List;
import javax.validation.Valid;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetailWithImage;
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
import com.etiya.rentACarSpring.business.requests.create.CreateCarRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCarRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCarRequest;
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
	public Result update(@RequestBody(required = false) @Valid  UpdateCarRequest updateCarRequest) {
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
	

	@GetMapping("getByModelYear")
	public DataResult<List<CarSearchListDto>> getByModelYear(@RequestParam int modelYear) {
		return this.carService.getByModelYear(modelYear);
	}
	
	@GetMapping("getByBrandName")
	public DataResult<List<CarSearchListDto>> getByBrandName(@RequestParam @Valid String brandName) {
		return this.carService.getByBrandName(brandName);
	}

	@GetMapping("getByBrandId")
	public DataResult<List<CarSearchListDto>> getByBrandId(@RequestParam @Valid int brandId) {

		return this.carService.getByBrandId(brandId);
	}

	@GetMapping("getByColorId")
	public DataResult<List<CarSearchListDto>> getCarsOfRelatedColor(@RequestParam @Valid int colorId){
		return this.carService.getByColorId(colorId);
	}

	@GetMapping("getByCityId")
	public DataResult<List<CarSearchListDto>> getByCityId(@RequestParam @Valid int cityId){
		return this.carService.getByCityId(cityId);
	}

	@GetMapping("getCarsWithBrandAndColorDetails")
	public DataResult<List<CarDetail>> getCarsWithBrandAndColorDetails(){
		return this.carService.getCarsWithBrandAndColorDetails();
	}

	@GetMapping("getCarsWithDetails")
	public DataResult<List<CarDetailWithImage>> getCarsWithDetails(){
		return this.carService.getCarsWithDetails();
	}

	@GetMapping("getOneCarWithDetails")
	public DataResult<CarDetailWithImage> getOneCarWithDetails(@RequestParam @Valid int carId){
		return this.carService.getOneCarWithDetails(carId);
	}

	@GetMapping("getImagesByCarId")
	public DataResult<List<CarImageDetail>> getImagesByCarId(@RequestParam @Valid int carId){
		return this.carService.getImagesByCarId(carId);
	}

	@GetMapping("getCarsNotOnMaintenance")
	public DataResult<List<CarSearchListDto>> getCarsNotOnMaintenance(){
		return this.carService.getCarsNotOnMaintenance();
	}


}
