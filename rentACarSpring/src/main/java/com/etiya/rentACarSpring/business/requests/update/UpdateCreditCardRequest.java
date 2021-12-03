package com.etiya.rentACarSpring.business.requests.update;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
	@Min(value = 1,message ="Lütfen bir kart id girin")
	private int id;

	@NotNull
	@Pattern(regexp = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$",
			message = "Geçerli bir kart numarası giriniz.")
	private String cardNumber;
	
	@NotNull
	@Size(min=4,max=40)
	private String cardHolderName;
	
	@NotNull
	@Size(min=5,max=5,message = "Tarih 5 haneli olmalıdır.")
	private String expirationDate;
	
	@NotNull
	@Size(min=3,max=3,message = "Cvv 3 haneli olmalıdır.")
	private String cvv;
	
	@JsonIgnore
	private int userId;
}
