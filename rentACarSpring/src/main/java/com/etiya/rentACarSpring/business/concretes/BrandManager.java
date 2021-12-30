package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;
import com.etiya.rentACarSpring.business.abstracts.MessageService;
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

@Service
public class BrandManager implements BrandService {

	private BrandDao brandDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	private MessageService messageService;

	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService, CarService carService,MessageService messageService) {
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
		this.messageService=messageService;
	}


	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		Result result = BusinessRules.run(checkIfBrandAlreadyExists(createBrandRequest.getName()));

		if (result != null) {
			return result;
		}
		Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult(this.messageService.getMessage(Messages.BrandAdded));

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
		return new SuccessResult(this.messageService.getMessage(Messages.BrandUpdated));

	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		Result result = BusinessRules.run(checkBrandIsNotExists(deleteBrandRequest.getId()),
				checkIfBrandHasNotAnyCar(deleteBrandRequest.getId()));
		if (result != null) {
			return result;
		}

		brandDao.deleteById(deleteBrandRequest.getId());
		return new SuccessResult(this.messageService.getMessage(Messages.BrandDeleted));
	}

	@Override
	public DataResult<List<BrandSearchListDto>> getAll() {
		List<Brand> result = this.brandDao.findAll();
		List<BrandSearchListDto> response = result.stream()
				.map(brand -> modelMapperService.forDto().map(brand, BrandSearchListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<BrandSearchListDto>>(response,this.messageService.getMessage(Messages.BrandsListed));
	}

	@Override
	public DataResult<List<CarSearchListDto>> getCarsOfRelatedBrand(int brandId) {

		List<CarSearchListDto> result = this.carService.getByBrandId(brandId).getData();

		return new SuccessDataResult<List<CarSearchListDto>>(result);
	}

	@Override
	public Result checkBrandIsNotExists(int brandId) {
		if (!this.brandDao.existsById(brandId)) {
			return new ErrorResult(this.messageService.getMessage(Messages.BrandNotFound));

		}
		return new SuccessResult();

	}

	private Result checkIfBrandAlreadyExists(String brandName) {
		List<Brand> brands=this.brandDao.findAll();
		for(Brand brand:brands){
			if(brand.getName().equalsIgnoreCase(brandName.trim())){
				return new ErrorResult(this.messageService.getMessage(Messages.BrandAlreadyExists));
			}
		}
		return new SuccessResult();
	}


	private Result checkIfBrandHasNotAnyCar(int brandId) {
		List<CarSearchListDto> result = this.carService.getByBrandId(brandId).getData();
		if (result.size() > 0) {
			return new ErrorResult(this.messageService.getMessage(Messages.BrandCanNotDelete));
		}
		return new SuccessResult();
	}

}
