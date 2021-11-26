package com.etiya.rentACarSpring.business.requests;


import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarImageRequest {
	private int carId;

	@JsonIgnore
	private String imagePath;

	
}
