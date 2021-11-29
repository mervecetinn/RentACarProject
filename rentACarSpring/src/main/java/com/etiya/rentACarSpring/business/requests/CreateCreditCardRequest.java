package com.etiya.rentACarSpring.business.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest {

	@CreditCardNumber(ignoreNonDigitCharacters = true)
	private String cardNumber;
	
	@NotNull
	private String cardHolderName;
	
	@NotNull
	@Size(min=5,max=5)
	private String expirationDate;
	
	@NotNull
	@Size(min=3,max=3)
	private String cvv;
	
	@NotNull
	private int userId;
}
