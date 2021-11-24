package com.etiya.rentACarSpring.business.requests;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest{
	
	private int userId;
	
	private String firstName;

	private String lastName;

	private LocalDateTime birthday;
	
}
