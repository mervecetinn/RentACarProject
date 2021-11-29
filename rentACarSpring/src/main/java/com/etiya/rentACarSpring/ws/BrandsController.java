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
import org.springframework.web.bind.annotation.RestController;
import com.etiya.rentACarSpring.business.abstracts.BrandService;
import com.etiya.rentACarSpring.business.dtos.BrandSearchListDto;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateBrandRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteBrandRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateBrandRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;


@RestController
@RequestMapping("api/brands")
public class BrandsController {
	
	private BrandService brandService;
	
	@Autowired
	public BrandsController(BrandService brandService) {
		this.brandService = brandService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid  CreateBrandRequest createBrandRequest) {
		return this.brandService.add(createBrandRequest);
	}
	
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(updateBrandRequest);
	}
	
	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteBrandRequest deleteBrandRequest) {
		return this.brandService.delete(deleteBrandRequest);
	}
	
	@GetMapping("all")
	public DataResult<List<BrandSearchListDto>> getAll(){
		return this.brandService.getAll();
	}
	
	@GetMapping("getCarsOfRelatedBrand")
	public DataResult<List<CarSearchListDto>> getCarsOfRelatedBrand(int brandId){
		return this.brandService.getCarsOfRelatedBrand(brandId);
	}
	
	

}
