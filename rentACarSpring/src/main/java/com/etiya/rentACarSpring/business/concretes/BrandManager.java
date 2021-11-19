package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.BrandService;
import com.etiya.rentACarSpring.business.dtos.BrandSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateBrandRequest;
import com.etiya.rentACarSpring.business.requests.DeleteBrandRequest;
import com.etiya.rentACarSpring.business.requests.UpdateBrandRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.BrandDao;
import com.etiya.rentACarSpring.entities.Brand;

@Service
public class BrandManager implements BrandService {

	private BrandDao brandDao;
	private ModelMapperService modelMapperService;

	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public void add(CreateBrandRequest createBrandRequest) {
		Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);

	}

	@Override
	public void update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(brand);

	}

	@Override
	public void delete(DeleteBrandRequest deleteBrandRequest) {
		Brand brand = modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		brandDao.delete(brand);
	}

	@Override
	public List<BrandSearchListDto> getAll() {
		List<Brand> result = this.brandDao.findAll();
		List<BrandSearchListDto> response = result.stream()
				.map(brand -> modelMapperService.forDto().map(brand, BrandSearchListDto.class))
				.collect(Collectors.toList());
		
		return response;
	}

}
