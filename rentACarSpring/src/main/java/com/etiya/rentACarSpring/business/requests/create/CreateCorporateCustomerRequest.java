package com.etiya.rentACarSpring.business.requests.create;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {

	@JsonIgnore
	private int corporateCustomerId;
	
	@NotNull
	@Size(min=3,max=30)
	private String companyName;
	
	@NotNull
	@Size(min=10,max=10)
	private String taxNumber;

	@Email
	@NotNull
	private String email;

	@NotNull
	@Size(min=8,max=20)
	private String password;

	
}
