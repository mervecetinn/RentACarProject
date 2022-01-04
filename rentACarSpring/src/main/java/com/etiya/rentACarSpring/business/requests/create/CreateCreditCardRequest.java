package com.etiya.rentACarSpring.business.requests.create;

import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest {

	@JsonIgnore
	private int id;

	@NotNull
	@Pattern(regexp = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$")
	private String cardNumber;
	
	@NotNull
	@NotBlank
	@Size(min=4,max=40)
	private String cardHolderName;
	
	@NotNull
	@NotBlank
	@Size(min=5,max=5)
	private String expirationDate;
	
	@NotNull
	@NotBlank
	@Size(min=3,max=3)
	private String cvv;
	
	@NotNull
	@Min(value = 1)
	private int userId;
}
