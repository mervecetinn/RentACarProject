package com.etiya.rentACarSpring.business.requests;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

	private int customerId;

	private int carId;

	private LocalDateTime rentDate;

	private LocalDateTime returnDate;

}
