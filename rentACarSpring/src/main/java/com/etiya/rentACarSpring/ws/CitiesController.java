package com.etiya.rentACarSpring.ws;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.business.abstracts.CityService;
import com.etiya.rentACarSpring.business.requests.create.CreateCityRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCityRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCityRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

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
	private Result add(@RequestBody @Valid CreateCityRequest createCityRequest) {

		return this.cityService.add(createCityRequest);
	}

	@PutMapping("update")
	private Result update(@RequestBody @Valid UpdateCityRequest UpdateCityRequest) {

		return this.cityService.update(UpdateCityRequest);
	}

	@DeleteMapping("delete")
	private Result delete(@RequestBody @Valid DeleteCityRequest deleteCityRequest) {

		return this.cityService.delete(deleteCityRequest);
	}

}
