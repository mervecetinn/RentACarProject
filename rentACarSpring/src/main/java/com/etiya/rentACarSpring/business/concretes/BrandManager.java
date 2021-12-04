package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.business.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.BrandService;
import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.dtos.BrandSearchListDto;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateBrandRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteBrandRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateBrandRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.BrandDao;
import com.etiya.rentACarSpring.entities.Brand;
import com.etiya.rentACarSpring.entities.Car;

@Service
public class BrandManager implements BrandService {

	private BrandDao brandDao;
	private ModelMapperService modelMapperService;
	private CarService carService;

	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService, CarService carService) {
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		Result result = BusinessRules.run(checkIfBrandAlreadyExists(createBrandRequest.getName()));

		if (result != null) {
			return result;
		}
		Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult(Messages.DataAdded);

	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		Result result = BusinessRules.run(checkBrandIsNotExists(updateBrandRequest.getId()),
				checkIfBrandAlreadyExists(updateBrandRequest.getName()));
		if (result != null) {
			return result;
		}

		Brand brand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult(Messages.DataUpdated);

	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		Result result = BusinessRules.run(checkBrandIsNotExists(deleteBrandRequest.getId()),
				checkIfBrandHasNotAnyCar(deleteBrandRequest.getId()));
		if (result != null) {
			return result;
		}

		brandDao.deleteById(deleteBrandRequest.getId());
		return new SuccessResult(Messages.DataDeleted);
	}

	@Override
	public DataResult<List<BrandSearchListDto>> getAll() {
		List<Brand> result = this.brandDao.findAll();
		List<BrandSearchListDto> response = result.stream()
				.map(brand -> modelMapperService.forDto().map(brand, BrandSearchListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<BrandSearchListDto>>(response);
	}

	@Override
	public DataResult<List<CarSearchListDto>> getCarsOfRelatedBrand(int brandId) {

		List<CarSearchListDto> result = this.carService.getByBrandId(brandId).getData();

		return new SuccessDataResult<List<CarSearchListDto>>(result);
	}

	@Override
	public Result checkBrandIsNotExists(int brandId) {
		if (!this.brandDao.existsById(brandId)) {
			return new ErrorResult(Messages.BrandIsNotFound);

		}
		return new SuccessResult();

	}

	private Result checkIfBrandAlreadyExists(String brandName) {
		List<Brand> brands=this.brandDao.findAll();
		for(Brand brand:brands){
			if(brand.getName().equalsIgnoreCase(brandName.toLowerCase())){
				return new ErrorResult(Messages.BrandAlreadyExists);
			}
		}
		return new SuccessResult();
	}


	private Result checkIfBrandHasNotAnyCar(int brandId) {
		List<CarSearchListDto> carsInRelevantBrand = this.carService.getByBrandId(brandId).getData();
		if (carsInRelevantBrand.size() > 0) {
			return new ErrorResult(Messages.BrandCanNotBeDeletedBeforeItsCars);
		}
		return new SuccessResult();
	}

}
