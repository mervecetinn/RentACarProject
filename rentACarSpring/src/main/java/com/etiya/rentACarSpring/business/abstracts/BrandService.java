package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.BrandSearchListDto;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateBrandRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteBrandRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateBrandRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface BrandService {
	Result add(CreateBrandRequest createBrandRequest);

	Result update(UpdateBrandRequest updateBrandRequest);

	Result delete(DeleteBrandRequest deleteBrandRequest);

	DataResult<List<BrandSearchListDto>> getAll();
	
	DataResult<List<CarSearchListDto>> getCarsOfRelatedBrand(int brandId);
	
	 Result checkBrandIsNotExists(int brandId);
	
	
	
}
