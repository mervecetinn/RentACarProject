package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.constants.FilePathConfiguration;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateCarRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCarRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCarRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.CarDao;
import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetail;

@Service
public class CarManager implements CarService {

	private CarDao carDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;

	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		Result result = BusinessRules.run(checkIfBrandNotExists(createCarRequest.getBrandId()),
				checkIfColorNotExists(createCarRequest.getColorId()));
		if (result != null) {
			return result;
		}

		Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
		car.setMinFindexScore((int) (Math.random() * 1900));

		this.carDao.save(car);
		return new SuccessResult("Car added.");

	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult("Car updated.");

	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {

		Result result = BusinessRules.run(checkCarExists(deleteCarRequest.getId()));
		if (result != null) {
			return result;
		}

		this.carDao.deleteById(deleteCarRequest.getId());
		return new SuccessResult("Car deleted.");

	}

	@Override
	public DataResult<List<CarSearchListDto>> getAll() {
		// List<Car> result = this.carDao.findAll();
		List<Car> result = this.carDao.findAllCarsWhichIsNotOnMaintenance();
		List<CarSearchListDto> response = result.stream()
				.map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CarSearchListDto>>(response);
	}

	@Override
	public DataResult<List<CarDetail>> getCarsWithDetails() {
		List<CarDetail> result = this.carDao.getCarsWithDetails();
		for (CarDetail carDetail : result) {
			if (carDetail.getImage() == null) {
				carDetail.setImage(FilePathConfiguration.defaultImagePath.getBytes());
			}
		}

		return new SuccessDataResult<List<CarDetail>>(result);

	}

	@Override
	public DataResult<List<CarSearchListDto>> getByModelYear(int modelYear) {

		List<CarSearchListDto> result = this.carDao.getByModelYear(modelYear).stream()
				.map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CarSearchListDto>>(result, "Cars listed.");
	}

	@Override
	public DataResult<List<CarSearchListDto>> getByBrandName(String brandName) {
		List<CarSearchListDto> result = this.carDao.getByBrandName(brandName);
		return new SuccessDataResult<List<CarSearchListDto>>(result, "Cars are listed with color and brand names.");
	}

	@Override
	public DataResult<List<CarSearchListDto>> getByBrandId(int brandId) {

		List<CarSearchListDto> result = this.carDao.getByBrandId(brandId).stream()
				.map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CarSearchListDto>>(result);
	}

	@Override
	public DataResult<List<CarSearchListDto>> getByColorId(int colorId) {
		List<CarSearchListDto> result = this.carDao.getByColorId(colorId).stream()
				.map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarSearchListDto>>(result);
	}

	@Override
	public DataResult<List<CarSearchListDto>> getByCityId(int cityId) {
		List<CarSearchListDto> result = this.carDao.getByCityId(cityId).stream()
				.map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarSearchListDto>>(result);
	}

	@Override
	public DataResult<List<CarDetail>> getOneCarWithDetails(int carId) {
		List<CarDetail> result = this.carDao.getOneCarWithDetails(carId);
		return new SuccessDataResult<List<CarDetail>>(result);
	}

	@Override
	public DataResult<Car> getById(int id) {
		Car car = this.carDao.getById(id);
		return new SuccessDataResult<Car>(car);
	}

	@Override
	public DataResult<List<CarDetail>> getCarsWithBrandAndColorDetails() {
		return new SuccessDataResult<List<CarDetail>>(this.carDao.getCarsWithBrandAndColorDetails());
	}

	@Override
	public Result checkCarIsNotOnRent(int id) {
		if (this.carDao.getCarIfItIsOnRent(id).size() > 0) {
			return new ErrorResult();
		}
		return new SuccessResult();

	}

	@Override
	public Result checkCarIsNotOnMaintenance(int id) {
		if (this.carDao.getCarIfItIsOnMaintenance(id).size() > 0) {
			return new ErrorResult();
		}
		return new SuccessResult();
	}

	@Override
	public Result checkCarExists(int id) {
		if (this.carDao.existsById(id)) {
			return new SuccessResult();
		}
		return new ErrorResult("Böyle bir araba zaten yok.");
	}

	private Result checkIfBrandNotExists(int brandId) {
		Object id = this.carDao.getOneBrandId(brandId);

		if (id != null) {
			return new SuccessResult();
		}
		return new ErrorResult("Böyle bir marka yok.");

	}

	private Result checkIfColorNotExists(int colorId) {
		Object id = this.carDao.getOneColorId(colorId);
		if (id != null) {
			return new SuccessResult();
		}
		return new ErrorResult("Böyle bir renk yok.");
	}

}