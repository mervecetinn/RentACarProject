package com.etiya.rentACarSpring.business.requests.delete;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCreditCardRequest {

	@NotNull
	private int id;
}
