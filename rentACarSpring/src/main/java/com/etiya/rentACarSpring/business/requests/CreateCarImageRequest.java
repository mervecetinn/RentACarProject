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
	
	@JsonIgnore
	private int id;
	
	private int carId;

	private MultipartFile imageFile;

	public CreateCarImageRequest(int carId, MultipartFile file) {
		this.carId=carId;
		this.imageFile=file;
	}
	
}
