package com.etiya.rentACarSpring.business.requests.update;

import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {
	
	@NotNull
	@Min(value = 1)
	private int corporateCustomerId;

	@NotNull
	@NotBlank
	@Size(min=3,max=30)
	private String companyName;

	@NotNull
	@NotBlank
	@Size(min=10,max=10)
	private String taxNumber;

	@NotNull
	@JsonIgnore
	private int userId;

	@NotNull
	@Email
	private String email;

	@NotNull
	@NotBlank
	@Size(min=8,max=20)
	private String password;


}
