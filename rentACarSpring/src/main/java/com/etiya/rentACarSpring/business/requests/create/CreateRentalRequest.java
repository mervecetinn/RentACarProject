package com.etiya.rentACarSpring.business.requests.create;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

	@JsonIgnore
	private int rentalId;
	
	@NotNull
	private int userId;

	@NotNull
	private int carId;

	@JsonIgnore
	private int takenFromCityId;

	@NotNull
	private LocalDateTime rentDate;


	@JsonIgnore
	private LocalDateTime returnDate;

	@JsonIgnore
	private int takenKilometer;

	private int returnKilometer;

	private int additionalItemId;

}
