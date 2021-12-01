package com.etiya.rentACarSpring.business.requests.update;

import java.time.LocalDateTime;

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
	private int rentalId;

	@NotNull
	private LocalDateTime rentDate;


	private LocalDateTime returnDate;

	@NotNull
	@JsonIgnore
	private int carId;

	@NotNull
	@JsonIgnore
	private int userId;
}
