package com.etiya.rentACarSpring.business.requests;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest{
	
	@JsonIgnore
	private int individualCustomerId;
	
	@JsonIgnore
	private int userId;
	
	private String email;

	private String password;
	
	private String passwordRepeat;
	
	private String firstName;

	private String lastName;

	private LocalDateTime birthday;
	
	
	
}
