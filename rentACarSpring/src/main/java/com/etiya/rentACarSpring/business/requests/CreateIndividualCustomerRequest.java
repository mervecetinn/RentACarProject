package com.etiya.rentACarSpring.business.requests;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest extends CreateUserRequest{
	
	@JsonIgnore
	private int id;
	
	@JsonIgnore
	private int userId;
	
	private String firstName;

	private String lastName;

	private LocalDateTime birthday;
	
}
