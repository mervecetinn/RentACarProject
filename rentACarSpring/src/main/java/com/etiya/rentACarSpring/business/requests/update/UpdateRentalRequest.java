package com.etiya.rentACarSpring.business.requests.update;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {

	@NotNull
	private int id;

	@NotNull
	private LocalDateTime rentDate;

	@NotNull
	private LocalDateTime returnDate;

	@NotNull
	private int carId;

	@NotNull
	private int customerId;
}
