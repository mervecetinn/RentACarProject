package com.etiya.rentACarSpring.business.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarImageRequest {
	
	private int id;
	
	private int carId;

	private String imagePath;

}
