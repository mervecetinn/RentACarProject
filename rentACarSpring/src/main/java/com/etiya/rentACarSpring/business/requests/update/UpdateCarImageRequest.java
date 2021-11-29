package com.etiya.rentACarSpring.business.requests.update;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarImageRequest {
	
	private int id;
	
	private MultipartFile imageFile;

}
