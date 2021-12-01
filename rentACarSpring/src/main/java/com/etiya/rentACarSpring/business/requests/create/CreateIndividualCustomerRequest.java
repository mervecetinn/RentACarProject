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

	//@NotNull
	//@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$",message = "Tarih, yıl-ay-gün şeklinde olmalıdır.")
	//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	//@JsonFormat(pattern = "yyyy/MM/dd")

	@Past(message = "Date input is invalid for a birth date.")
	private LocalDate birthday;
	
	
	
}
