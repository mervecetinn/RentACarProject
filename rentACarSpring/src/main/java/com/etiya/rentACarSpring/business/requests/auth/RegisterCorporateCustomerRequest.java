package com.etiya.rentACarSpring.business.requests.auth;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCorporateCustomerRequest {
	
	@JsonIgnore
	private int corporateCustomerId;

	private String companyName;

	private String taxNumber;

	@JsonIgnore
	private int userId;

	private String email;

	private String password;

	private String passwordRepeat;

}
