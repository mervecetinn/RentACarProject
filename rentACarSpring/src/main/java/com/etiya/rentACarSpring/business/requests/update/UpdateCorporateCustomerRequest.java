package com.etiya.rentACarSpring.business.requests.update;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {
	
	@NotNull
	@Min(value = 1,message ="Lütfen bir kurumsal müşteri id girin")
	private int corporateCustomerId;

	@NotNull
	@Size(min=3,max=30)
	private String companyName;

	@NotNull
	@Size(min=10,max=10)
	private String taxNumber;

	@NotNull
	@JsonIgnore
	private int userId;

	@NotNull
	@Email(message = "Email düzeni hatalı.")
	private String email;

	@NotNull
	@Size(min=8,max=20)
	private String password;


}
