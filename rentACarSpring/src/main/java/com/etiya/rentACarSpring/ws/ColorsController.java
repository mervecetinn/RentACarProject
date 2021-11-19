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

import com.etiya.rentACarSpring.business.abstracts.ColorService;
import com.etiya.rentACarSpring.business.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateColorRequest;
import com.etiya.rentACarSpring.business.requests.DeleteColorRequest;
import com.etiya.rentACarSpring.business.requests.UpdateColorRequest;


@RestController
@RequestMapping("api/colors")
public class ColorsController {
	
	private ColorService colorService;
	

	@Autowired
	public ColorsController(ColorService colorService) {
		this.colorService = colorService;
	}
	
	@PostMapping("add")
	public void add(@RequestBody CreateColorRequest createColorRequest) {
		this.colorService.add(createColorRequest);
	}
	
	@PutMapping("update")
	public void update(@RequestBody UpdateColorRequest updateColorRequest) {
		this.colorService.update(updateColorRequest);
		
	}
	
	@DeleteMapping("delete")
	public void delete(@RequestBody DeleteColorRequest deleteColorRequest) {
		this.colorService.delete(deleteColorRequest);
	}
	
	@GetMapping("all")
	public List<ColorSearchListDto> getAll(){
		return this.colorService.getAll();
	}

}
