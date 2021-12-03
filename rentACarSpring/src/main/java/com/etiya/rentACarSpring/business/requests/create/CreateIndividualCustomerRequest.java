package com.etiya.rentACarSpring.business.requests.create;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest{
	
	@JsonIgnore
	private int individualCustomerId;
	
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

	@Past(message = "Date input is invalid for a birth date.")
	private LocalDate birthday;
	
	
	
}
