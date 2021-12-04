package com.etiya.rentACarSpring.ws;

import javax.validation.Valid;

import com.etiya.rentACarSpring.business.dtos.CitySearchListDto;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.etiya.rentACarSpring.business.abstracts.CityService;
import com.etiya.rentACarSpring.business.requests.create.CreateCityRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCityRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCityRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

import java.util.List;

@RestController
@RequestMapping("api/cities")
public class CitiesController {

	private CityService cityService;

	@Autowired
	public CitiesController(CityService cityService) {
		super();
		this.cityService = cityService;
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCityRequest createCityRequest) {

		return this.cityService.add(createCityRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCityRequest UpdateCityRequest) {

		return this.cityService.update(UpdateCityRequest);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteCityRequest deleteCityRequest) {

		return this.cityService.delete(deleteCityRequest);
	}

	@GetMapping("all")
	public DataResult<List<CitySearchListDto>> getAll(){
		return this.cityService.getAll();
	}

}
