package com.etiya.rentACarSpring.business.requests.update;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {

	@NotNull
	@Min(value = 1,message ="Lütfen bir fatura id girin")
	private int id;

	@NotNull
	@Size(min=8)
	private String invoiceNumber;

	@NotNull
    private Date creationDate;

	@NotNull
	@Min(value = 1,message ="Lütfen bir rentali id girin")
	private int rentalId;
}
