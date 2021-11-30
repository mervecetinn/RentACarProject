package com.etiya.rentACarSpring.business.concretes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.rentACarSpring.business.abstracts.CarImageService;
import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.constants.FilePathConfiguration;
import com.etiya.rentACarSpring.business.dtos.CarImageSearchListDto;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateCarImageRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCarImageRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCarImageRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.helpers.FileHelper;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorDataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.CarImageDao;
import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.CarImage;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetail;
import com.etiya.rentACarSpring.entities.complexTypes.CarImageDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Service
public class CarImageManager implements CarImageService {

	private CarImageDao carImageDao;
	private ModelMapperService modelMapperService;
	private FileHelper fileHelper;
	private CarService carService;

	@Autowired
	public CarImageManager(CarImageDao carImageDao, ModelMapperService modelMapperService, FileHelper fileHelper,
			CarService carService) {
		this.carImageDao = carImageDao;
		this.modelMapperService = modelMapperService;
		this.fileHelper = fileHelper;
		this.carService = carService;
	}

	@Override
	public Result add(CreateCarImageRequest createCarImageRequest) throws IOException {
		Result result = BusinessRules.run(checkIfCountOfCarImagesExceededTheLimit(createCarImageRequest.getCarId()),
				this.fileHelper.checkImageFile(createCarImageRequest.getImageFile()),
				checkIfCarIsNotExists(createCarImageRequest.getCarId()));
		if (result != null) {
			return result;
		}

		CarImage carImage = modelMapperService.forRequest().map(createCarImageRequest, CarImage.class);
		carImage.setPath((this.fileHelper
				.uploadImage(createCarImageRequest.getCarId(), createCarImageRequest.getImageFile()).getData()));
		carImage.setImage(createCarImageRequest.getImageFile().getBytes());
		this.carImageDao.save(carImage);
		return new SuccessResult("Resim başarıyla eklendi");
	}

	@Override
	public Result update(UpdateCarImageRequest updateCarImageRequest) throws IOException {
		Result result = BusinessRules.run(this.fileHelper.checkImageFile(updateCarImageRequest.getImageFile()),checkImageIsNotExists(updateCarImageRequest.getId()));
		if (result != null) {
			return result;
		}
		CarImage carImage = this.carImageDao.getById(updateCarImageRequest.getId());
		carImage.setPath(
				this.fileHelper.updateImage(updateCarImageRequest.getImageFile(), carImage.getPath()).getData());
		carImage.setImage(updateCarImageRequest.getImageFile().getBytes());
		// CarImage carImage =
		// modelMapperService.forRequest().map(updateCarImageRequest, CarImage.class);
		this.carImageDao.save(carImage);
		return new SuccessResult();
	}

	@Override
	public Result delete(DeleteCarImageRequest deleteCarImageRequest) {
		Result result = BusinessRules.run(checkImageIsNotExists(deleteCarImageRequest.getId()));
		if (result != null) {
			return result;
		}
		
		String imageInFolder = this.carImageDao.getById(deleteCarImageRequest.getId()).getPath();
		this.fileHelper.deleteImage(imageInFolder);
		this.carImageDao.deleteById(deleteCarImageRequest.getId());

		return new SuccessResult();
	}

	@Override
	public DataResult<List<CarImageSearchListDto>> getAll() {
		List<CarImage> result = this.carImageDao.findAll();
		List<CarImageSearchListDto> response = result.stream()
				.map(carImage -> modelMapperService.forDto().map(carImage, CarImageSearchListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<CarImageSearchListDto>>(response);
	}

	private Result checkIfCountOfCarImagesExceededTheLimit(int carId) {
		int countOfCarImages = this.carImageDao.getByCarId(carId).size();
		if (countOfCarImages >= 5) {
			return new ErrorResult("Bir arabaya ait en fazla 5 resim eklenebilir.");
		}
		return new SuccessResult();
	}

	private Result checkIfCarIsNotExists(int carId) {
		if (!this.carService.ifExistsByCarId(carId).isSuccess()) {
			return new ErrorResult("Böyle bir araba yok!");
		}
		return new SuccessResult();
	}
	
	private Result checkImageIsNotExists(int imageId) {
		if (!this.carImageDao.existsById(imageId)) {
			return new ErrorResult("Böyle bir resim yok.");

		}
		return new SuccessResult();

	}

}
