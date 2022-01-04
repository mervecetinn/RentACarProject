package com.etiya.rentACarSpring.business.requests.create;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	@JsonIgnore
	private int id;

	@NotNull
	@Range(min=1)
    private int brandId;
	
	
	@NotNull
	@Range(min=1)
	private int colorId;

	@NotNull
	@Range(min=1000,max=3000)
	private int modelYear;

	@NotNull
	@Min(value = 0)
	private int kilometer;
	
	@NotNull
	@Range(min = 0,max = 1000000)
	private double dailyPrice;
	
	@NotNull
	@NotBlank
	@Size(min = 2, max=1000)
	private String description;

	@NotNull
	@Range(min=1)
	private int cityId;

	@NotNull
	@Range(min=0,max=1900)
	private int minFindexScore;
}
