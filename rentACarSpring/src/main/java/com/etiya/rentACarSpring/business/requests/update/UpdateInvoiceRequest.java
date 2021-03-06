package com.etiya.rentACarSpring.business.requests.update;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {

	@NotNull
	@Min(value = 1)
	private int id;

	@NotNull
	@NotBlank
	@Size(min=8)
	private String invoiceNumber;

	@NotNull
    private LocalDate creationDate;

	@NotNull
	@Min(value = 0)
	private double invoiceAmount;


}
