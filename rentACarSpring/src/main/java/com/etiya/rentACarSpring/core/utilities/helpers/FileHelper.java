package com.etiya.rentACarSpring.core.utilities.helpers;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface FileHelper {
	
	Result uploadImage(int carId, MultipartFile file) throws IOException;

	Result updateImage(MultipartFile file, String imagePath) throws IOException;

	Result deleteImage(String imagePath);

	Result checkImageFile(MultipartFile file);
}
