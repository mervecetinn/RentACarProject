package com.etiya.rentACarSpring.business.requests.auth;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterIndividualCustomerRequest {

	private String email;
	private String password;
	private String passwordRepeat;
	private String firstName;
	private String lastName;
	private LocalDateTime birthday;

}
