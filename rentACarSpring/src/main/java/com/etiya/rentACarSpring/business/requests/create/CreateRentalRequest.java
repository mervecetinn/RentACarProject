package com.etiya.rentACarSpring.business.requests.create;

import java.time.LocalDateTime;

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
	
	private int userId;

	private int carId;

	private LocalDateTime rentDate;

	private LocalDateTime returnDate;

}
