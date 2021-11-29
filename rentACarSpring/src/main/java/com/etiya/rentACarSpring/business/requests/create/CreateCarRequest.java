package com.etiya.rentACarSpring.business.requests.create;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	
	@NotNull
    private int brandId;
	
	@NotNull
	private int colorId;
	
	@NotNull
	private int modelYear;
	
	@NotNull
	@Min(100)
	private double dailyPrice;
	
	@NotNull
	@Size(min = 2, max=100)
	private String description;
}
