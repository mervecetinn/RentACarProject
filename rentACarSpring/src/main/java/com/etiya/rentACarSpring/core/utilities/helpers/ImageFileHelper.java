package com.etiya.rentACarSpring.core.utilities.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;

@Service
public class ImageFileHelper implements FileHelper {
	@Value("${mainPath}")
	private String mainPath;

	private MessageService messageService;

	@Autowired
	public ImageFileHelper(MessageService messageService){
		this.messageService=messageService;
	}

	@Override
	public DataResult<String> uploadFile(int carId, MultipartFile file) throws IOException {

		String carImagesFolderName=this.createCarImagesFolderName(carId);
		String carImageFileName = this.createImageFileName(file);
		String folderPath = createCarImagesFolderAndReturnFolderPath(carId);
		File carImageFile = new File(folderPath + "\\" + carImageFileName);
		carImageFile.createNewFile();

		FileOutputStream fileOutputStream = new FileOutputStream(carImageFile);
		fileOutputStream.write(file.getBytes());
		fileOutputStream.close();
		
		

		return new SuccessDataResult<String>(mainPath+carImagesFolderName+"\\"+carImageFileName);
	}

	@Override
	public DataResult<String> updateFile(MultipartFile file, String imagePath) throws IOException {

		String carImagesFolderPath = getFolderPathOfFile(imagePath);
		this.deleteFile(imagePath);
		String updatedCarImageFileName = this.createImageFileName(file);

		File updatedCarImageFile = new File(carImagesFolderPath + "\\" + updatedCarImageFileName);
		updatedCarImageFile.createNewFile();
        
		FileOutputStream fileOutputStream = new FileOutputStream(updatedCarImageFile);
		fileOutputStream.write(file.getBytes());
		fileOutputStream.close();

		return new SuccessDataResult<String>(carImagesFolderPath+"\\"+updatedCarImageFileName);
	}

	@Override
	public Result deleteFile(String imagePath) {
		if (!imagePath.isEmpty() && !imagePath.isBlank()) {
			File deletedImage = new File(imagePath);
			deletedImage.delete();
		}

		return new SuccessResult();
	}

	@Override
	public Result checkFile(MultipartFile file) {
		if (!checkFileExtensionIsValid(file).isSuccess()) {
			return new ErrorResult(this.messageService.getMessage(Messages.FileTypeNotValid));
		}
		if (!checkImageIsNotNull(file).isSuccess()) {
			return new ErrorResult(this.messageService.getMessage(Messages.FileNotSelected));
		}
		return new SuccessResult();
	}

	private String createCarImagesFolderName(int carId) {
		String carImagesFolderName = "car" + carId;
		return carImagesFolderName;
	}

	private String createCarImagesFolderAndReturnFolderPath(int carId) {
		String folderPath = mainPath + createCarImagesFolderName(carId);
		File folder = new File(folderPath);
		folder.mkdir();
		return folderPath;
	}

	private String createImageFileName(MultipartFile file) {
		String randomImageName = java.util.UUID.randomUUID().toString();
		String imageFileName = randomImageName + getFileExtension(file);
		return imageFileName;

	}

	private String getFileExtension(MultipartFile file) {
		String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		return fileExtension;
	}

	private Result checkImageIsNotNull(MultipartFile file) {
		if (file == null || file.isEmpty() || file.getSize() == 0) {
			return new ErrorResult(this.messageService.getMessage(Messages.FileNotSelected));
		}
		return new SuccessResult();
	}

	private Result checkFileExtensionIsValid(MultipartFile file) {
		if (getFileExtension(file).equals(".jpg") || getFileExtension(file).equals(".png")
				|| getFileExtension(file).equals(".jpeg")) {
			return new SuccessResult();
		}
		return new ErrorResult(this.messageService.getMessage(Messages.FileTypeNotValid));
	}

	private String getFolderPathOfFile(String imagePath) {
		String folderPathOfFile = imagePath.substring(0, imagePath.lastIndexOf("\\"));
		return folderPathOfFile;
	}
}
