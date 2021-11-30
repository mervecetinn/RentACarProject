package com.etiya.rentACarSpring.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {
	
	
	private int corporateCustomerId;

	private String companyName;

	private String taxNumber;

	private int userId;

	private String email;

	private String password;

	private String passwordRepeat;

}
