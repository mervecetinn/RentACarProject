package com.etiya.rentACarSpring.business.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalSearchListDto {
    private int id;
	
	private LocalDateTime rentDate;
	
	
	private LocalDateTime returnDate;

	
	private int carId;
	
	private int customerId;
}
