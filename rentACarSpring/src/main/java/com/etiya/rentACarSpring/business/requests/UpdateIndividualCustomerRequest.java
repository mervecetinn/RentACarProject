package com.etiya.rentACarSpring.business.requests;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {

	private int id;
	
	private int userId;
	
	private String email;

	private String password;
	
	private String firstName;

	private String lastName;

	private LocalDateTime birthday;
}
