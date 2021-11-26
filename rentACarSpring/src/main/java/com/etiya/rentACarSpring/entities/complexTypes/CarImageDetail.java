package com.etiya.rentACarSpring.entities.complexTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarImageDetail {
	private int id;
	private int carId;
	private String imagePath;
}
