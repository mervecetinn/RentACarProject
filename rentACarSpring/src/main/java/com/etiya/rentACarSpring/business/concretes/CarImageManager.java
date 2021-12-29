package com.etiya.rentACarSpring.business.concretes;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiya.rentACarSpring.business.abstracts.CarImageService;
import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.dtos.CarImageSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateCarImageRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCarImageRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCarImageRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.helpers.FileHelper;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.CarImageDao;
import com.etiya.rentACarSpring.entities.CarImage;

@Service
public class CarImageManager implements CarImageService {

	private CarImageDao carImageDao;
	private ModelMapperService modelMapperService;
	private FileHelper fileHelper;
	private CarService carService;
	private MessageService messageService;

	@Autowired
	public CarImageManager(CarImageDao carImageDao, ModelMapperService modelMapperService, FileHelper fileHelper,
			CarService carService,MessageService messageService) {
		this.carImageDao = carImageDao;
		this.modelMapperService = modelMapperService;
		this.fileHelper = fileHelper;
		this.carService = carService;
		this.messageService=messageService;
	}

	@Override
	public Result add(CreateCarImageRequest createCarImageRequest) throws IOException {
		Result result = BusinessRules.run(checkIfCountOfCarImagesExceededTheLimit(createCarImageRequest.getCarId()),
				this.fileHelper.checkFile(createCarImageRequest.getImageFile()),
				checkIfCarIsNotExists(createCarImageRequest.getCarId()));
		if (result != null) {
			return result;
		}

		CarImage carImage = modelMapperService.forRequest().map(createCarImageRequest, CarImage.class);
		carImage.setPath((this.fileHelper
				.uploadFile(createCarImageRequest.getCarId(), createCarImageRequest.getImageFile()).getData()));
		carImage.setImage(createCarImageRequest.getImageFile().getBytes());
		this.carImageDao.save(carImage);
		return new SuccessResult(this.messageService.getMessage(Messages.ImageUploaded));
	}

	@Override
	public Result update(UpdateCarImageRequest updateCarImageRequest) throws IOException {
		Result result = BusinessRules.run(this.fileHelper.checkFile(updateCarImageRequest.getImageFile()),
				checkImageIsNotExists(updateCarImageRequest.getId()));
		if (result != null) {
			return result;
		}
		CarImage carImage = this.carImageDao.getById(updateCarImageRequest.getId());
		carImage.setPath(
				this.fileHelper.updateFile(updateCarImageRequest.getImageFile(), carImage.getPath()).getData());
		carImage.setImage(updateCarImageRequest.getImageFile().getBytes());
		// CarImage carImage =
		// modelMapperService.forRequest().map(updateCarImageRequest, CarImage.class);
		this.carImageDao.save(carImage);
		return new SuccessResult(this.messageService.getMessage(Messages.ImageUpdated));
	}

	@Override
	public Result delete(DeleteCarImageRequest deleteCarImageRequest) {
		Result result = BusinessRules.run(checkImageIsNotExists(deleteCarImageRequest.getId()));
		if (result != null) {
			return result;
		}

		String imageInFolder = this.carImageDao.getById(deleteCarImageRequest.getId()).getPath();
		this.fileHelper.deleteFile(imageInFolder);
		this.carImageDao.deleteById(deleteCarImageRequest.getId());

		return new SuccessResult(this.messageService.getMessage(Messages.ImageDeleted));
	}

	@Override
	public DataResult<List<CarImageSearchListDto>> getAll() {
		List<CarImage> result = this.carImageDao.findAll();
		List<CarImageSearchListDto> response = result.stream()
				.map(carImage -> modelMapperService.forDto().map(carImage, CarImageSearchListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<CarImageSearchListDto>>(response,this.messageService.getMessage(Messages.ImagesListed));
	}

	private Result checkIfCountOfCarImagesExceededTheLimit(int carId) {
		int countOfCarImages = this.carImageDao.getByCarId(carId).size();
		if (countOfCarImages >= 5) {
			return new ErrorResult(this.messageService.getMessage(Messages.ImageLimitOfCarCanNotBeExceeded));
		}
		return new SuccessResult();
	}

	private Result checkIfCarIsNotExists(int carId) {
		if (!this.carService.checkCarExists(carId).isSuccess()) {
			return new ErrorResult(this.messageService.getMessage(Messages.CarNotFound));
		}
		return new SuccessResult();
	}

	private Result checkImageIsNotExists(int imageId) {
		if (!this.carImageDao.existsById(imageId)) {
			return new ErrorResult(this.messageService.getMessage(Messages.ImageNotFound));

		}
		return new SuccessResult();

	}

}
