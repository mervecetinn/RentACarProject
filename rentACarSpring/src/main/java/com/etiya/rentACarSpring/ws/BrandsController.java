package com.etiya.rentACarSpring.ws;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.business.abstracts.BrandService;
import com.etiya.rentACarSpring.business.dtos.BrandSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateBrandRequest;
import com.etiya.rentACarSpring.business.requests.DeleteBrandRequest;
import com.etiya.rentACarSpring.business.requests.UpdateBrandRequest;
import com.etiya.rentACarSpring.entities.Brand;

@RestController
@RequestMapping("api/brands")
public class BrandsController {
	
	private BrandService brandService;
	
	@Autowired
	public BrandsController(BrandService brandService) {
		this.brandService = brandService;
	}
	
	@PostMapping("add")
	public void add(@RequestBody CreateBrandRequest createBrandRequest) {
		this.brandService.add(createBrandRequest);
	}
	
	@PutMapping("update")
	public void update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		this.brandService.update(updateBrandRequest);
	}
	
	@DeleteMapping("delete")
	public void delete(@RequestBody DeleteBrandRequest deleteBrandRequest) {
		this.brandService.delete(deleteBrandRequest);
	}
	
	@GetMapping("all")
	public List<BrandSearchListDto> getAll(){
		return this.brandService.getAll();
	}

}
