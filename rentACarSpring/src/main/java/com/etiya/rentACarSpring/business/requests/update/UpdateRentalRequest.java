package com.etiya.rentACarSpring.business.requests.update;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {

	private int id;
	
	private LocalDateTime rentDate;
	
	
	private LocalDateTime returnDate;

	
	private int carId;
	
	private int customerId;
}
