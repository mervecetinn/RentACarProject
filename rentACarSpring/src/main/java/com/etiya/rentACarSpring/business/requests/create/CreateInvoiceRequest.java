package com.etiya.rentACarSpring.business.requests.create;

import java.util.Date;

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

	//@NotNull
	//private String invoiceNumber;
	@NotNull
    private Date creationDate;
	
	//@NotNull
	//private Date startDateOfRental;

	//@NotNull
	//private Date finishDateOfRental;
	
	//private int countOfRentalDays;
	
	//private double invoiceAmount;
	
	private int rentalId;
}
