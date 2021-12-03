package com.etiya.rentACarSpring.business.requests.update;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {

	@NotNull
	@Min(value = 1,message ="Lütfen bir bireysel müşteri id girin")
	private int individualCustomerId;
	
	@NotNull
	@JsonIgnore
	private int userId;
	
	@NotNull
	@Email(message = "Email düzeni hatalı.")
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
	@Past(message = "Date input is invalid for a birth date.")
	private LocalDate birthday;
}
