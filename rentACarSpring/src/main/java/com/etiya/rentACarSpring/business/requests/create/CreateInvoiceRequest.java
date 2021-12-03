package com.etiya.rentACarSpring.business.requests.create;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {

	@JsonIgnore
	private int id;

	@NotNull
    private LocalDate creationDate;

	@NotNull
	@Min(value = 1,message = "Lütfen bir rental id girin.")
	private int rentalId;
}
