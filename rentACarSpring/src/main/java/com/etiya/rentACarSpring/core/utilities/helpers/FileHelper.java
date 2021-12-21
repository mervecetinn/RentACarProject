package com.etiya.rentACarSpring.core.utilities.helpers;


import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface FileHelper {
	
	DataResult<String> uploadFile(int carId, MultipartFile file) throws IOException;

	DataResult<String> updateFile(MultipartFile file, String filePath) throws IOException;

	Result deleteFile(String imagePath);

	Result checkFile(MultipartFile file);
}
