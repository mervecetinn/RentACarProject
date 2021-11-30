package com.etiya.rentACarSpring.business.requests.auth;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterIndividualCustomerRequest {

	@NotNull
	private String email;
	
	@NotNull
	@Size(min=8,max=20)
	private String password;
	
	@NotNull
	@Size(min=2,max=20)
	private String firstName;
	
	@Size(min=2,max=20)
	private String lastName;
	
	@NotNull
	private LocalDate birthday;

}
