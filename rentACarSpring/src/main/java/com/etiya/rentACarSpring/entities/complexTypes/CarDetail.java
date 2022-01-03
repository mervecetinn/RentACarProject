package com.etiya.rentACarSpring.entities.complexTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetail {

	private int id;
	private String brandName;
	private String colorName;
	private int modelYear;
	private int kilometer;
	private double dailyPrice;
	private String description;

}
