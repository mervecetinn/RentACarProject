package com.etiya.rentACarSpring.business.requests.delete;

import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteIndividualCustomerRequest {
	
	@Min(value = 1)
	private int individualCustomerId;
}
