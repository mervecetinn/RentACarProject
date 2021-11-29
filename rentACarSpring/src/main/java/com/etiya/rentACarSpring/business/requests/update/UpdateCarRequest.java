package com.etiya.rentACarSpring.business.requests.update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
	
	@NotNull
	private int id;
	
	@NotNull
    private int brandId;
	
	@NotNull
	private int colorId;
	
	@NotNull
	private int modelYear;
	
	@NotNull
	@Min(100)
	private int dailyPrice;
	
	@NotNull
	@Size(min = 2, max=100)
	private String description;
}
