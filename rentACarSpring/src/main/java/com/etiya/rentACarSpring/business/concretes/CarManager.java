package com.etiya.rentACarSpring.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.etiya.rentACarSpring.business.abstracts.CityService;
import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetailWithImage;
import com.etiya.rentACarSpring.entities.complexTypes.CarImageDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.etiya.rentACarSpring.business.abstracts.CarService;
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
	private CityService cityService;
	private Environment environment;
	private MessageService messageService;

	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService,CityService cityService,Environment environment,MessageService messageService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
		this.cityService=cityService;
		this.environment=environment;
		this.messageService=messageService;

	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		Result result = BusinessRules.run(checkIfBrandNotExists(createCarRequest.getBrandId()),
				checkIfColorNotExists(createCarRequest.getColorId()),checkIfCityIsNotExists(createCarRequest.getCityId()));
		if (result != null) {
			return result;
		}

		Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult(this.messageService.getMessage(Messages.CarAdded));

	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		Result result = BusinessRules.run(checkIfCarIsNotExists(updateCarRequest.getId()),
				checkIfBrandNotExists(updateCarRequest.getBrandId()),checkIfColorNotExists(updateCarRequest.getColorId()),
				checkIfCityIsNotExists(updateCarRequest.getCityId()));
		if (result != null) {
			return result;
		}

		Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult(this.messageService.getMessage(Messages.CarUpdated));

	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {

		Result result = BusinessRules.run(checkIfCarIsNotExists(deleteCarRequest.getId()),checkIfCarCanNotDelete(deleteCarRequest.getId()));
		if (result != null) {
			return result;
		}

		this.carDao.deleteById(deleteCarRequest.getId());
		return new SuccessResult(this.messageService.getMessage(Messages.CarDeleted));

	}

	@Override
	public DataResult<List<CarSearchListDto>> getAll() {
		List<Car> result = this.carDao.findAll();
		List<CarSearchListDto> response = result.stream()
				.map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CarSearchListDto>>(response,this.messageService.getMessage(Messages.CarsListed));
	}

	@Override
	public DataResult<List<CarDetailWithImage>> getCarsWithDetails() {
		List<CarDetail> result= this.carDao.getCarsWithBrandAndColorDetails();
		List<CarDetailWithImage> response=new ArrayList<>();

		for(CarDetail carDetail:result){
			List<CarImageDetail> imageList=this.carDao.getImagesOfRelatedCar(carDetail.getId());
			if(imageList.size()==0){
				CarImageDetail carImageDetail=new CarImageDetail();
				carImageDetail.setImage(this.environment.getProperty("defaultImagePath").getBytes());
				imageList.add(carImageDetail);
			}

				CarDetailWithImage carDetailWithImage=this.modelMapperService.forDto().map(carDetail,CarDetailWithImage.class);
				carDetailWithImage.setImages(imageList);
			    response.add(carDetailWithImage);


		}

		return new SuccessDataResult<>(response);


	}

	@Override
	public DataResult<List<CarSearchListDto>> getByModelYear(int modelYear) {

		List<CarSearchListDto> result = this.carDao.getByModelYear(modelYear).stream()
				.map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CarSearchListDto>>(result, this.messageService.getMessage(Messages.CarsListed));
	}

	@Override
	public DataResult<List<CarSearchListDto>> getByBrandName(String brandName) {
		List<CarSearchListDto> result = this.carDao.getByBrandName(brandName);
		return new SuccessDataResult<List<CarSearchListDto>>(result, this.messageService.getMessage(Messages.CarsListed));
	}

	@Override
	public DataResult<List<CarSearchListDto>> getByBrandId(int brandId) {

		List<CarSearchListDto> result = this.carDao.getByBrandId(brandId).stream()
				.map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CarSearchListDto>>(result,this.messageService.getMessage(Messages.CarsListed));
	}

	@Override
	public DataResult<List<CarSearchListDto>> getByColorId(int colorId) {
		List<CarSearchListDto> result = this.carDao.getByColorId(colorId).stream()
				.map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarSearchListDto>>(result,this.messageService.getMessage(Messages.CarsListed));
	}

	@Override
	public DataResult<List<CarSearchListDto>> getByCityId(int cityId) {
		List<CarSearchListDto> result = this.carDao.getByCityId(cityId).stream()
				.map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarSearchListDto>>(result,this.messageService.getMessage(Messages.CarsListed));
	}

	@Override
	public DataResult<List<CarImageDetail>> getImagesByCarId(int carId) {
		List<CarImageDetail> result=this.carDao.getImagesOfRelatedCar(carId);

		if(result.size()==0){
			CarImageDetail carImageDetail=new CarImageDetail();
			carImageDetail.setImage(this.environment.getProperty("defaultImagePath").getBytes());
			result.add(carImageDetail);
		}


		return new SuccessDataResult<>(result);
	}

	@Override
	public DataResult<List<CarSearchListDto>> getCarsNotOnMaintenance() {
		List<Car> result = this.carDao.findAllCarsWhichIsNotOnMaintenance();
		List<CarSearchListDto> response = result.stream()
				.map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CarSearchListDto>>(response,this.messageService.getMessage(Messages.CarsListed));
	}

	@Override
	public DataResult<CarDetailWithImage> getOneCarWithDetails(int carId) {
	List<CarDetailWithImage> result=this.getCarsWithDetails().getData();
	CarDetailWithImage response=result.stream().filter(c->c.getId()==carId).findAny().get();
	return new SuccessDataResult<>(response);

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
		return new ErrorResult();
	}

	private Result checkIfCarIsNotExists(int id){
		if(!this.carDao.existsById(id)){
			return new ErrorResult(this.messageService.getMessage(Messages.CarNotFound));
		}
		return new SuccessResult();
	}

	private Result checkIfBrandNotExists(int brandId) {
		Object id = this.carDao.getOneBrandId(brandId);

		if (id != null) {
			return new SuccessResult();
		}
		return new ErrorResult(this.messageService.getMessage(Messages.BrandNotFound));

	}

	private Result checkIfColorNotExists(int colorId) {
		Object id = this.carDao.getOneColorId(colorId);
		if (id != null) {
			return new SuccessResult();
		}
		return new ErrorResult(this.messageService.getMessage(Messages.ColorNotFound));
	}

	private Result checkIfCityIsNotExists(int cityId){
		if(!this.cityService.checkCityExists(cityId).isSuccess()){
			return new ErrorResult(this.messageService.getMessage(Messages.CityNotFound));
		}
		return new SuccessResult();
	}

	private Result checkIfCarCanNotDelete(int carId){

		List<Car> result=this.carDao.getCarsWhichHasRecordAnotherTable();
		for(Car car:result){
			if(car.getId()==carId){
				return new ErrorResult(this.messageService.getMessage(Messages.CarCanNotDelete));
			}
		}
		return new SuccessResult();

	}

}