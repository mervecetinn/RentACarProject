package com.etiya.rentACarSpring.business.requests.create;

import javax.servlet.annotation.HandlesTypes;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;
import org.modelmapper.internal.bytebuddy.build.HashCodeAndEqualsPlugin.ValueHandling;

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
	@Range(min=1,message = "Geçerli bir id girin")
    private int brandId;
	
	
	@NotNull
	@Range(min=1,message = "Geçerli bir id girin")
	private int colorId;

	@NotNull
	@Range(min=1000,max=3000,message="Geçerli bir yıl giriniz.")
	private int modelYear;

	@NotNull
	private int kilometer;
	
	@NotNull
	@Min(100)
	private double dailyPrice;
	
	@NotNull
	@Size(min = 2, max=100)
	private String description;

	@NotNull
	@Range(min=1,message = "Geçerli bir id girin")
	private int cityId;

	@NotNull
	@Range(min=1,max=1900)
	private int minFindexScore;
}
