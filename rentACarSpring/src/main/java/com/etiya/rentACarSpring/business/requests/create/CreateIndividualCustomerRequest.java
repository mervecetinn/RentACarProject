package com.etiya.rentACarSpring.business.requests.create;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@NotNull
	private String email;

	@NotNull
	@Size(min=8,max=20)
	private String password;
	
	@NotNull
	@Size(min=2,max=20)
	private String firstName;

	@NotNull
	@Size(min=2,max=20)
	private String lastName;

	@NotNull
	private LocalDate birthday;
	
	
	
}
