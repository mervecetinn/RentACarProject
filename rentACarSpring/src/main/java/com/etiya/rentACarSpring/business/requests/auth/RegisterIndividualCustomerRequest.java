package com.etiya.rentACarSpring.business.requests.auth;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterIndividualCustomerRequest {

	@NotNull
	@Email
	private String email;
	
	@NotNull
	@NotBlank
	@Size(min=8,max=20)
	private String password;
	
	@NotNull
	@NotBlank
	@Size(min=2,max=20)
	private String firstName;

	@NotNull
	@NotBlank
	@Size(min=2,max=20)
	private String lastName;
	
	@NotNull
	@Past(message = "Date input is invalid for a birth date.")
	private LocalDate birthday;

}
