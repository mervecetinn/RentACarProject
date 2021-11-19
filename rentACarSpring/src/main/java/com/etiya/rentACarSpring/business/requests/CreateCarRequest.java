package com.etiya.rentACarSpring.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
    private int brandId;
	
	private int colorId;
	
	private int modelYear;
	
	private double dailyPrice;
	
	private String description;
}
