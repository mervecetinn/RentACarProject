package com.etiya.rentACarSpring.business.requests.delete;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteInvoiceRequest {

	@NotNull
	@Min(value = 1,message = "Lütfen bir id giriniz.")
	private int id;
}
