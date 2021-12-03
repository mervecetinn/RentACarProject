package com.etiya.rentACarSpring.business.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerSearchListDto {
    private int individualCustomerId;
	
	private String firstName;

	private String lastName;

	private LocalDate birthday;
}
