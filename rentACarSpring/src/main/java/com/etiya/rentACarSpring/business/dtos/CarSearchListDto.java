package com.etiya.rentACarSpring.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarSearchListDto {
	private int id;

	private int modelYear;

	private int kilometer;
	
	private double dailyPrice;

	private String description;
}
