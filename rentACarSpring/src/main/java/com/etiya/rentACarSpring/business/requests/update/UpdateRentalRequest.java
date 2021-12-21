package com.etiya.rentACarSpring.business.requests.update;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {

	@NotNull
	@Min(value = 1, message="Lütfen bir rental id girin")
	private int rentalId;

	@JsonIgnore
	private LocalDateTime rentDate;


	private LocalDateTime returnDate;

	@JsonIgnore
	private int initialKilometer;

	private int returnKilometer;

	@NotNull
	@Min(value=1, message="Lütfen bir city id girin")
	private int returnToCityId;

	@NotNull
	@JsonIgnore
	private int carId;

	@NotNull
	@JsonIgnore
	private int userId;
}
