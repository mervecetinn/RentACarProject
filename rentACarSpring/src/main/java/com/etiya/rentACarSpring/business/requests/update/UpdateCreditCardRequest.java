package com.etiya.rentACarSpring.business.requests.update;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCreditCardRequest {
	
	@NotNull
	private int id;

	@NotNull
	private String cardNumber;
	
	@NotNull
	@Size(min=4,max=40)
	private String cardHolderName;
	
	@NotNull
	@Size(min=5,max=5)
	private String expirationDate;
	
	@NotNull
	@Size(min=3,max=3)
	private String cvv;
	
	@JsonIgnore
	private int userId;
}
