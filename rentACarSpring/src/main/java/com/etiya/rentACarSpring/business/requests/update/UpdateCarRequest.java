package com.etiya.rentACarSpring.business.requests.update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
	
    @NotNull
	@Range(min=1,message = "Geçerli bir car id girin")
	private int id;
	
	@NotNull
	@Range(min=1,message = "Geçerli bir  brand id girin")
    private int brandId;
	
	@NotNull
	@Range(min=1,message = "Geçerli bir color id girin")
	private int colorId;
	
	@NotNull
	@Range(min=1000,max=3000,message="Geçerli bir yıl giriniz.")
	private int modelYear;

	@NotNull
	@Min(value = 0,message = "Kilometre negatif sayı olamaz.")
	private int kilometer;
	
	@NotNull
	@Range(min = 0,max = 1000000)
	private int dailyPrice;
	
	@NotNull
	@NotBlank
	@Size(min = 2, max=1000)
	private String description;

	@NotNull
	@Range(min=1,message = "Geçerli bir id girin")
	private int cityId;

	@NotNull
	@Range(min=0,max=1900)
	private int minFindexScore;
}
