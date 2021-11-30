package com.etiya.rentACarSpring.business.requests.create;

import javax.servlet.annotation.HandlesTypes;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.modelmapper.internal.bytebuddy.build.HashCodeAndEqualsPlugin.ValueHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	
	@NotNull
	@Range(min=1,message = "Geçerli bir id girin")
    private int brandId;
	
	
	@NotNull
	@Range(min=1,message = "Geçerli bir id girin")
	private int colorId;
	
	@Range(min=1000,max=3000,message="Geçerli bir yıl giriniz.")
	@NotNull
	private int modelYear;
	
	@NotNull
	@Min(100)
	private double dailyPrice;
	
	@NotNull
	@Size(min = 2, max=100)
	private String description;
}
